import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyNewFrame extends JFrame implements ActionListener {

	public static void main (String[] args) {
		MyNewFrame frame = new MyNewFrame();
		frame.setVisible(true);
	}

	public MyNewFrame() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JTextField input = new JTextField(50);
		input.setLocation(100, 100);
		input.setSize(input.getPreferredSize());
		input.setText("<Input your name here>");
		add(input);
	}

	public void actionPerformed(ActionEvent e) {}
}
