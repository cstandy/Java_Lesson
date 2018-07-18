public class WrapperClassTest {
	public static void main(String[] args) {
		int k = 100;
		Integer it1 = new Integer(k);
		int m = it1.intValue();
		System.out.println(m*k);
	}
}
