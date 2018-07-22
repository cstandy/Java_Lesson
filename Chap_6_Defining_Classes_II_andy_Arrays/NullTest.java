public class NullTest {

	public static void main(String[] args) {
		NullTest nt = new NullTest();
		nt.showMessage();
		NullTest nt2 = null;
		nt2.showMessage();
	}

	public void showMessage(){
		System.out.println("Hi!");
	}
}
