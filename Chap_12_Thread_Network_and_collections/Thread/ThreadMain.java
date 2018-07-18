public class ThreadMain {

	public static void main(String[] args) {

		MyThread thread1 = new MyThread("Jimmy");
		thread1.start();
		MyThread thread2 = new MyThread("Lorenz");
		thread2.start();
		MyThread thread3 = new MyThread("Satriani");
		thread3.start();

	}
}
