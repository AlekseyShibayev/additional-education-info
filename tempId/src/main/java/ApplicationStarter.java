public class ApplicationStarter {

	public static void main(String[] args) {
		Runnable runnable = new MyRunnable();
		while (true) {
			runnable.run();
			System.out.println(". . . . .");
		}
	}
}
