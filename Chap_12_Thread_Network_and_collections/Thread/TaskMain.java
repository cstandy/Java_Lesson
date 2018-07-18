public class TaskMain {

	public static void main(String[] args) {

		Thread thread = new Thread(new Task());
		thread.start();
	}
}
