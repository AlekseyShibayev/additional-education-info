package com.company.app.mainLogic;

import com.company.app.services.api.CaptchaFighterService;
import com.company.app.services.api.DataExtractorService;
import com.company.app.services.api.HtmlParserService;
import com.company.app.services.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SearchService implements Runnable {

	private static final String LOTS_FILE_NAME = "lot.properties";

	private HashMap<String, TradingLot> lots;
	private int counter;
	private Map<String, String> urls;
	private List<String> lotNames;
	@Autowired
	private DataExtractorService dataExtractorService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private CaptchaFighterService captchaFighterService;
	@Autowired
	private HtmlParserService htmlParserService;

	@PostConstruct
	public void init() {
		this.lots = new HashMap<>();
		this.counter = 0;
		this.urls = dataExtractorService.getProperties(LOTS_FILE_NAME);
		this.lotNames = getLotNames(urls);
	}

	public void run() {
		try {
			while (true) {
				if (counter == 0) {
					preparingToWork();
				}
				doSearchLogic();
				doAfterRound();
			}
        } catch (Exception e) {
			doIfCatchAnyException(e);
		}
	}

	private void preparingToWork() throws InterruptedException {
		notificationService.eventNotification("Application started with [" + urls.size() + "] parameters.\nSearching:\n" + lotNames);
	}

	private void doAfterRound() throws InterruptedException {
		incrementCaptchaCounter();
		notificationService.logNotification("finished: " + counter);
		captchaFighterService.fight(300_000, 450_000);
	}

	private void doIfCatchAnyException(Exception e) {
		notificationService.logNotification(String.valueOf(counter));
		notificationService.logNotification(lots);
		notificationService.errorNotification(e);
		notificationService.showLog();
		Thread.currentThread().interrupt();
	}

	private void incrementCaptchaCounter() {
		counter++;
	}

	private void doSearchLogic() throws Exception {
		List<String> queue = captchaFighterService.getQueue(lotNames);
		notificationService.logNotification("try started with request order: " + queue.toString());
		workWithQueue(queue);
	}

	private void workWithQueue(List<String> queue) throws IOException, InterruptedException {
		for (String currentKey : queue) {
			notificationService.logNotification("try execute with: " + currentKey);
			executeSearch(currentKey, urls.get(currentKey));
			doAfterSingleSearch();
		}
	}

	private void doAfterSingleSearch() throws InterruptedException {
		captchaFighterService.fight(30_000, 100_000);
	}

	private void executeSearch(String lotName, String urlName) throws IOException {
		String htmlResponse = dataExtractorService.getHtmlResponse(urlName);
		TradingLot tradingLot = htmlParserService.createTradingLot(htmlResponse);
		System.out.println(lotName + ": " + tradingLot);
		if (isNotFoundEarlier(tradingLot)) {
			rewriteTradingLot(tradingLot);
			doNotification(lotName, tradingLot);
		}
	}

    private void doNotification(String lotName, TradingLot tradingLot) {
		if (tradingLot.isReadyToPrint()) {
			notificationService.eventNotification(lotName + ":\n" + tradingLot);
		}
    }

	private void rewriteTradingLot(TradingLot tradingLot) {
		lots.put(tradingLot.getName(), tradingLot);
    }

    private boolean isNotFoundEarlier(TradingLot tradingLot) {
		String name = tradingLot.getName();
		if (lots.containsKey(name)) {
			TradingLot oldLot = lots.get(name);
			return oldLot.equals(tradingLot) ? false : true;
		} else {
			return true;
		}
	}

	private List<String> getLotNames(Map<String, String> map) {
		return new ArrayList<>(map.keySet());
	}
}