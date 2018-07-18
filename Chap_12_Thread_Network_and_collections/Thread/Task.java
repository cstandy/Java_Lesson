public class Task implements Runnable {

	public void run() {
		while(true) {
			System.out.println("This is a task");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
