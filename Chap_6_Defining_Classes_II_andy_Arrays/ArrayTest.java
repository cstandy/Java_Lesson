public class ArrayTest {

	public static void main(String[] args) {

		double[] reading = new double[100];

		for (int i = 0; i < reading.length; i++){
			reading[i] = 42.0;
		}

		System.out.println(reading[38]);

		int[] age = {12, 24, 36};

		System.out.println(age.length);
		System.out.println(age[2]);


		String[] names = new String[3];

		System.out.println(names[0]);

		names[0] = "Apple";

		System.out.println(names[0]);
	}
}
