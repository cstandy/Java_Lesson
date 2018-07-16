import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent  e){

		String command = e.getActionCommand();

		if (command.equals("MenuItem1"))
			System.out.println("You pressed menuitem1");
		else if (command.equals("MenuItem2"))
			System.out.println("You pressed menuitem2");

		/*
		System.out.println(e.getActionCommand());
		System.out.println(e.getSource());
		*/
	}
}
