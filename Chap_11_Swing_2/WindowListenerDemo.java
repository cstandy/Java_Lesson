import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowListenerDemo extends JFrame {

    public static void main(String[] args) {
        WindowListenerDemo demoWindow = new WindowListenerDemo();
        demoWindow.setVisible(true);
    }

    public WindowListenerDemo() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CheckOnExit());
        JLabel aLabel = new JLabel("I like to be sure you are sincere.");
        add(aLabel);
    }

    private class CheckOnExit implements WindowListener {
        public void windowClosing(WindowEvent e) {
            ConfirmWindow checkers = new ConfirmWindow();
            checkers.setVisible(true);
        }

        public void windowOpened     (WindowEvent e) {}
        public void windowClosed     (WindowEvent e) {}
        public void windowIconified  (WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowActivated  (WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
    }

    private class ConfirmWindow extends JFrame implements ActionListener {
        public ConfirmWindow() {
            setSize(200, 100);
            setLayout(new BorderLayout());
            JLabel confirmLabel = new JLabel("Are you sure want to exit?");
            add(confirmLabel, BorderLayout.CENTER);
            JButton exitButton = new JButton("Yes");
            exitButton.addActionListener(this);
            add(exitButton, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
