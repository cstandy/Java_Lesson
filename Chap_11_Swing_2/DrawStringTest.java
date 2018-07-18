import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

public class DrawStringTest extends JFrame {

	public static void main(String[] args) {
		DrawStringTest frame = new DrawStringTest();
		frame.setVisible(true);
	}

	public DrawStringTest() {
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLUE);
		Font f = new Font("Arial Bold", Font.BOLD|Font.ITALIC, 30);
		g.setFont(f);
		g.drawString("Hello! Welcome to Java World!", 100, 100);
	}
}
