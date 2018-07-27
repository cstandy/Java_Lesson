package monopoly;

/*
Java Swing, 2nd Edition
By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
ISBN: 0-596-00408-7
Publisher: O'Reilly 
 */
// ColorPane.java
//A simple extension of JTextPane that allows the user to easily append
//colored text to the document.
//

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ColorPane extends JTextPane {

	public ColorPane() {

	}

	public void appendNaive(Color c, String s) { // naive implementation
		// bad: instiantiates a new AttributeSet object on each call
		SimpleAttributeSet aset = new SimpleAttributeSet();
		StyleConstants.setForeground(aset, c);

		int len = getText().length();
		setCaretPosition(len); // place caret at the end (with no selection)
		setCharacterAttributes(aset, false);
		replaceSelection(s); // there is no selection, so inserts at caret
	}

	public void append(Color c, String s) { // better implementation--uses
		this.setEditable(true);
		// StyleContext
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
				StyleConstants.Foreground, c);

		int len = getDocument().getLength(); // same value as
		// getText().length();
		setCaretPosition(len); // place caret at the end (with no selection)
		setCharacterAttributes(aset, false);
		replaceSelection(s); // there is no selection, so inserts at caret
		this.setEditable(false);
	}

	/*public static void main(String argv[]) {

		ColorPane pane = new ColorPane();
		for (int n = 1; n <= 400; n += 1) {
			if (isPrime(n)) {
				pane.append(Color.red, String.valueOf(n) + ' ');
			} else if (isPerfectSquare(n)) {
				pane.append(Color.blue, String.valueOf(n) + ' ');
			} else {
				pane.append(Color.black, String.valueOf(n) + ' ');
			}
		}
		for (int n = 1; n <= 400; n += 1) {
			if (isPrime(n)) {
				pane.append(Color.red, String.valueOf(n) + ' ');
			} else if (isPerfectSquare(n)) {
				pane.append(Color.blue, String.valueOf(n) + ' ');
			} else {
				pane.append(Color.black, String.valueOf(n) + ' ');
			}
		}

		JFrame f = new JFrame("ColorPane example");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jj = new JScrollPane(pane);

		f.getContentPane().setLayout(null);
		jj.setBounds(10, 10, 50, 50);
		f.setContentPane(jj);
		f.setSize(600, 400);
		f.setVisible(true);
	}

	public static boolean isPrime(int n) {
		if (n < 2)
			return false;
		double max = Math.sqrt(n);
		for (int j = 2; j <= max; j += 1)
			if (n % j == 0)
				return false; // j is a factor
		return true;
	}

	public static boolean isPerfectSquare(int n) {
		int j = 1;
		while (j * j < n && j * j > 0)
			j += 1;
		return (j * j == n);
	}*/

}