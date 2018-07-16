import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuTest {

	public static void main (String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenu diner     = new JMenu("Menu");
		JMenuItem item1 = new JMenuItem("MenuItem1");
		JMenuItem item2 = new JMenuItem("MenuItem2");
		item1.addActionListener(new MyButtonListener());
		item2.addActionListener(new MyButtonListener());
		diner.add(item1);
		diner.add(item2);

		JMenuBar bar = new JMenuBar();
		bar.add(diner);

		frame.setJMenuBar(bar);
		frame.setVisible(true);
	}
}
