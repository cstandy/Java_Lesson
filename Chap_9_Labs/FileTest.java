import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileTest {

	public static void main (String[] args) {

		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream("./output.txt", true));

			writer.println("Dear Alan,");
			writer.println("How are you?");
			writer.println("Joe");

			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
