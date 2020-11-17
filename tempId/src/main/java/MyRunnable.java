import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static java.lang.Thread.sleep;

public class MyRunnable implements Runnable {

	private static final String URL = "https://www.google.com/";

	public void run() {
		try {
			doLogic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doLogic() throws Exception {
		Connection connect = Jsoup.connect(URL);
		Document document = connect.get();

		//todo

		sleep(1000);
	}
}