import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamTest {

	public static void main (String[] args) {

		Student stu = new Student("Hacker", 20);

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./output2.obj"));

			oos.writeInt(123);
			oos.writeObject(stu);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
