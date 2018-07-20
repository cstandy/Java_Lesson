import java.util.Stack;

public class Test {
    public static void main (String[] args) {
    	
		Stack<String> st = new Stack<String>();

		st.push("Andy");
		st.push("Juliana");
		st.push("Vicky");
	
		while (st.empty() != true)
		{
			System.out.println(st.peek());
			st.pop();
		}
	}
}
