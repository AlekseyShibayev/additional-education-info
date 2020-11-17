import static java.lang.Thread.sleep;

public class MyRunnable implements Runnable {

	private static final String URL = "testUrl";

	public void run() {
		try {
			doLogic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doLogic() throws Exception {
		System.out.println("logic");
		sleep(1000);
	}
}