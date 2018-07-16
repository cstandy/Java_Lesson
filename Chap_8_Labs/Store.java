public class Store {

	public static void main(String[] args){
		Payment p1 = new Payment();
		p1.checkout();

		Payment p2 = new CreditCardPayment();
		p2.checkout();
	}
}
