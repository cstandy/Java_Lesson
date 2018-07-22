public class ArrayTest2 {

	public static void main(String[] args) {

		int[] scores = new int[100];

		for(int i = 0; i < scores.length; i++) {
			scores[i] = i;
		}

		System.out.println(scores[98]);
	}
}
