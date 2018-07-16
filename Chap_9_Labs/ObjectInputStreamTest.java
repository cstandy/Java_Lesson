import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectInputStreamTest {

	public static void main (String[] args) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./output2.obj"));

			int num = ois.readInt();
			System.out.println("num" + num);

			Student stu = (Student)ois.readObject();
			System.out.println("Student name = " + stu.getName());
			System.out.println("Student age  = " + stu.getAge());

			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
