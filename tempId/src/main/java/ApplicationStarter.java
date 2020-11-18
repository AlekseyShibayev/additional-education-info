public class ApplicationStarter {

	public static void main(String[] args) {
		Runnable runnable = new SearchTask();
		runnable.run();
	}
}
