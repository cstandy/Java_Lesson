public class AnonymousObjectTest {

	public static void main(String[] args) {
		AnonymousObjectTest obj = new AnonymousObjectTest();
		obj.showMessage(new String("I am an anonymous object"));
	}

	/**
	 @brief  This method is to show a given message
	 @parame The message to be shown
	*/
	public void showMessage(String message){
		System.out.println(message);
	}
}
