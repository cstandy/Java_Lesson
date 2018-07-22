import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {

		ArrayList<String> names = new ArrayList<String>();

		names.add("Apple");
		names.add("Orange");
		names.add("pear");

		System.out.println(names.get(1));
	}
}
