package MainLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTask implements Runnable {

	private static final String LOTS_FILE_NAME = "lot.properties";

	private ApplicationHelper applicationHelper;
	private HashMap<String, TradingLot> lots;
	private int counter;
	private Map<String, String> urls;
	private List<String> lotNames;

	public SearchTask(ApplicationHelper applicationHelper) {
		this.applicationHelper = applicationHelper;
		this.lots = new HashMap<>();
		this.counter = 0;
		this.urls = applicationHelper.getDataExtractorService().getProperties(LOTS_FILE_NAME);
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
		applicationHelper.getNotificationService().eventNotification("Application started with [" + urls.size() + "] parameters.\nSearching:\n" + lotNames);
//		applicationHelper.getCaptchaFighterService().fight(30_000, 60_000);
	}

	private void doAfterRound() throws InterruptedException {
		incrementCaptchaCounter();
		applicationHelper.getNotificationService().logNotification("finished: " + counter);
		applicationHelper.getCaptchaFighterService().fight(300_000, 450_000);
	}

	private void doIfCatchAnyException(Exception e) {
		applicationHelper.getNotificationService().logNotification(String.valueOf(counter));
		applicationHelper.getNotificationService().logNotification(lots);
		applicationHelper.getNotificationService().errorNotification(e);
		applicationHelper.getNotificationService().showLog();
		Thread.currentThread().interrupt();
	}

	private void incrementCaptchaCounter() {
		counter++;
	}

	private void doSearchLogic() throws Exception {
		List<String> queue = applicationHelper.getCaptchaFighterService().getQueue(lotNames);
		applicationHelper.getNotificationService().logNotification("try started with request order: " + queue.toString());
		workWithQueue(queue);
	}

	private void workWithQueue(List<String> queue) throws IOException, InterruptedException {
		for (String currentKey : queue) {
			applicationHelper.getNotificationService().logNotification("try execute with: " + currentKey);
			executeSearch(currentKey, urls.get(currentKey));
			doAfterSingleSearch();
		}
	}

	private void doAfterSingleSearch() throws InterruptedException {
		applicationHelper.getCaptchaFighterService().fight(30_000, 100_000);
	}

	private void executeSearch(String lotName, String urlName) throws IOException {
		String htmlResponse = applicationHelper.getDataExtractorService().getHtmlResponse(urlName);
		TradingLot tradingLot = applicationHelper.getHtmlParserService().createTradingLot(htmlResponse);
		System.out.println(lotName + ": " + tradingLot);
		if (isNotFoundEarlier(tradingLot)) {
			rewriteTradingLot(tradingLot);
			doNotification(lotName, tradingLot);
		}
	}

    private void doNotification(String lotName, TradingLot tradingLot) {
		if (applicationHelper.getNotificationService().isCorrectLotForShow(tradingLot)) {
			applicationHelper.getNotificationService().eventNotification(lotName + ":\n" + tradingLot);
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
}