package MainLogic;

import Services.CaptchaFighterService;
import Services.DataExtractorService;
import Services.HtmlParserService;
import Services.NotificationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTask implements Runnable {

	private static final String LOTS_FILE_NAME = "lot.properties";

	private HashMap<String, TradingLot> lots;
	private int counter;
	private Map<String, String> urls;
	private List<String> lotNames;
	private DataExtractorService dataExtractorService;
	private NotificationService notificationService;
	private CaptchaFighterService captchaFighterService;
	private HtmlParserService htmlParserService;

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
//		applicationHelper.getCaptchaFighterService().fight(30_000, 60_000);
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
		if (notificationService.isCorrectLotForShow(tradingLot)) {
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
		List<String> names = new ArrayList<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			names.add(entry.getKey());
		}
		return names;
	}

	public DataExtractorService getDataExtractorService() {
		return dataExtractorService;
	}

	public void setDataExtractorService(DataExtractorService dataExtractorService) {
		this.dataExtractorService = dataExtractorService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public CaptchaFighterService getCaptchaFighterService() {
		return captchaFighterService;
	}

	public void setCaptchaFighterService(CaptchaFighterService captchaFighterService) {
		this.captchaFighterService = captchaFighterService;
	}

	public HtmlParserService getHtmlParserService() {
		return htmlParserService;
	}

	public void setHtmlParserService(HtmlParserService htmlParserService) {
		this.htmlParserService = htmlParserService;
	}
}