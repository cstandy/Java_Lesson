import java.util.*;
public class SetTest {
	public static void main(String[] args) {
		TreeSet treeset = new TreeSet();
		treeset.add("c");
		treeset.add("b");
		treeset.add("a");
		Iterator itr1 = treeset.iterator();

		while(itr1.hasNext()){
			System.out.println(itr1.next());
		}

		HashSet hashset = new HashSet();
		hashset.add("c");
		hashset.add("b");
		hashset.add("a");
		Iterator itr2 = hashset.iterator();

		while(itr2.hasNext()){
			System.out.println(itr2.next());
		}
	}
}
