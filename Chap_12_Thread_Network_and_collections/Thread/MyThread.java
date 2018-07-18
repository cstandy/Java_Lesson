public class MyThread extends Thread {

	private String name;

	public MyThread(String name){ this.name = name; }

	public void run() {

		while(true) {
			System.out.println("Hello! I am " + name);

			try {
				this.sleep((long)(Math.random() * 2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
