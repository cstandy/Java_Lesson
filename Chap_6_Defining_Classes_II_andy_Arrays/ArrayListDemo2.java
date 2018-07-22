import java.util.ArrayList;

public class ArrayListDemo2 {

	public static void main(String[] args) {

		ArrayList<String> names = new ArrayList<String>();

		names.add("A");
		names.add("B");
		names.add("C");

		// for(int i = 0; i < 3; i++) {
			names.remove("A");
		// }

		System.out.println(names.size());
	}
}
