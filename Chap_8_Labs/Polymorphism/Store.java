public class Store {

	public static void main(String[] args){
		Payment p1 = new Payment();
		p1.checkout();
		payProcess(p1);

		Payment p2 = new CreditCardPayment();
		p2.checkout();
		payProcess(p2);
	}
	
	public static void payProcess(Payment p){
		if (p instanceof CreditCardPayment){
			((CreditCardPayment)p).sign();
		}
	}
}
