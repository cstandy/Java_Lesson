import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser {

	public static void main(String args[]) {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter a sentence and I'll display each word you entered: ");
		String sentence = keyboard.nextLine();

		// Parse the string into tokens and echo back to the user
		StringTokenizer tk = new StringTokenizer(sentence, " ");
		System.out.println("Here are the tokens: ");
		
		while (tk.hasMoreTokens()) {
			System.out.println(tk.nextToken());
		}
	}
}
