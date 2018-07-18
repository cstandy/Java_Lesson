import java.util.*;

public class MapTest {

	public static void main(String[] args) {

		HashMap hashmap = new HashMap();
		hashmap.put("Joy", "175");
		hashmap.put("Sandy", "178");
		hashmap.put("Blue", "166");

		Iterator itr1 = hashmap.keySet().iterator();

		while(itr1.hasNext()){
			String key = (String)itr1.next();
			String value = (String)hashmap.get(key);
			System.out.println(key +":" + value);
		}
	}
}
