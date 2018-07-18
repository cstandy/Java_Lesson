import java.util.ArrayList;

public class ListTest {

	public static void main(String[] args) {
		ArrayList alist = new ArrayList();

		alist.add("c");
		alist.add("b");
		alist.add("a");

		for(int i = 0; i < alist.size(); i++){
			System.out.println(alist.get(i));
		}
	}
}
