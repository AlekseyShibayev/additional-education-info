package MainLogic;

import java.io.IOException;
import java.util.*;

public class SearchTask implements Runnable {

	private ApplicationHelper applicationHelper;
	private Set<TradingLot> lots;
	private int counter;
	private Map<String, String> urls;
	private List<String> lotNames;

	SearchTask() {
		applicationHelper = new ApplicationHelper();
		this.lots = new HashSet<>();
		this.counter = 0;
		this.urls = applicationHelper.getFileDataExtractorService().extract();
		this.lotNames = getLotNames(urls);
	}

	public void run() {
		try {
			while (true) {
				if (counter == 0) {
					preparingToWork();
				}
				doLogic();
				doAfterRound();
			}
        } catch (Exception e) {
			doIfCatchAnyException(e);
		}
	}

	private void preparingToWork() throws InterruptedException {
		applicationHelper.getNotificationService().eventNotification("Application started. Searching: " + lotNames);
//		applicationHelper.getCaptchaFighterService().fight(30_000, 60_000);
	}

	private void doAfterRound() throws InterruptedException {
		applicationHelper.getNotificationService().eventNotification(". . . . . .");
		incrementCaptchaCounter();
		applicationHelper.getNotificationService().logNotification("finished: " + counter);
		applicationHelper.getCaptchaFighterService().fight(50_000, 90_000);
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

	private void doLogic() throws Exception {
		List<String> queue = applicationHelper.getCaptchaFighterService().getQueue(lotNames);
		applicationHelper.getNotificationService().logNotification("try started with request order: " + queue.toString());
		workWithQueue(queue);
	}

	private void workWithQueue(List<String> queue) throws IOException, InterruptedException {
		for (String currentKey : queue) {
			applicationHelper.getNotificationService().logNotification("try execute with: " + currentKey);
			executeSearch(currentKey, urls.get(currentKey));
			applicationHelper.getCaptchaFighterService().fight(15_000, 30_000);
		}
	}

	private void executeSearch(String lotName, String urlName) throws IOException {
		String htmlResponse = applicationHelper.getHtmlResponseExtractorService().extractHtmlResponse(urlName);
		TradingLot tradingLot = applicationHelper.getHtmlParserService().createTradingLot(htmlResponse);
		if (isNotFoundEarlier(tradingLot)) {
			doAction(lotName, tradingLot);
		}
	}

	private void doAction(String lotName, TradingLot tradingLot) {
        removeOldTradingLot(tradingLot);
        addNewTradingLot(tradingLot);
        doNotification(lotName, tradingLot);
    }

    private void doNotification(String lotName, TradingLot tradingLot) {
        if (counter != 0) {
			applicationHelper.getNotificationService().eventNotification(lotName + ": " + tradingLot);
        }
    }

    private void addNewTradingLot(TradingLot tradingLot) {
        lots.add(tradingLot);
    }

    private void removeOldTradingLot(TradingLot tradingLot) {
        String name = tradingLot.getName();
        for (TradingLot lot : lots) {
            if (name.equals(lot.getName())) {
                lots.remove(lot);
            }
        }
    }

    private boolean isNotFoundEarlier(TradingLot tradingLot) {
		return !lots.contains(tradingLot);
	}

	private List<String> getLotNames(Map<String, String> map) {
		List<String> names = new ArrayList<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			names.add(entry.getKey());
		}
		return names;
	}
}