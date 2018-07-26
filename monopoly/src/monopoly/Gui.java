package monopoly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui {
	/* variables */
	private JFrame frame;
	private JTextArea[] blockArea = new JTextArea[32];
	private JTextArea[] roleArea = new JTextArea[4];
	private JTextField input;


	public Gui() { 
		frame = new JFrame();
		frame.setSize(700, 700);
		
		for(int a=0; a<32; a++) {
			blockArea[a] = new JTextArea("第"+a);//+"格\nssss\ndfghj笨笨\noppp");
			blockArea[a].setEditable(false);
			//blockArea[a].setSize(40, 40);
			//blockArea[a].setFont(new Font("monospaced", Font.PLAIN, 16));
		}
		
		for(int b=0; b<4; b++) {
			roleArea[b] = new JTextArea("角色"+b+"\nfgdkg\n哈哈哈\n噗噗噗\nsdfgh\n");
			roleArea[b].setEditable(false);
			//roleArea[b].setSize(80, 40);
			//roleArea[b].setFont(new Font("monospaced", Font.PLAIN, 16));
		}
		
		input = new JTextField(2);
		
		frame.setLayout(new GridBagLayout());
		
		GridBagConstraints[] gridBagBlock = new GridBagConstraints[32];
		// 由右下角(Start)開始往左
		for(int c=0; c<32; c++) {
			gridBagBlock[c] = new GridBagConstraints();
			if(c <= 16 && c >= 8) {
				gridBagBlock[c].gridx = 0;
			}
			else if(c >= 17 && c <= 23) {
				gridBagBlock[c].gridx = (c-16)*3;
			}
			else if(c >= 1 && c <= 7) {
				gridBagBlock[c].gridx = (8-c)*3;
			}
			else {
				gridBagBlock[c].gridx = 24;
			}
			if(c <= 24 && c >= 16) {
				gridBagBlock[c].gridy = 0;
			}
			else if(c >= 9 && c <= 15) {
				gridBagBlock[c].gridy = (16-c)*3;
			}
			else if(c >= 25 && c <= 32) {
				gridBagBlock[c].gridy = (c-24)*3;
			}
			else {
				gridBagBlock[c].gridy = 24;
			}
			gridBagBlock[c].gridwidth = 3;
			gridBagBlock[c].gridheight = 2;
			gridBagBlock[c].weightx = 0.01;
			gridBagBlock[c].weighty = 0.2;
			gridBagBlock[c].fill = GridBagConstraints.BOTH;
			gridBagBlock[c].anchor = GridBagConstraints.WEST;
			frame.add(blockArea[c], gridBagBlock[c]);
		}

		GridBagConstraints[] gridBagRole = new GridBagConstraints[4];
		for(int d=0; d<4; d++) {
			gridBagRole[d] = new GridBagConstraints();
			gridBagRole[d].gridx = d*6;
			gridBagRole[d].gridy = 28;
			gridBagRole[d].gridwidth = 6;
			gridBagRole[d].gridheight = 3;
			gridBagRole[d].weightx = 0.01;
			gridBagRole[d].weighty = 0.1;
			gridBagRole[d].fill = GridBagConstraints.BOTH;
			gridBagRole[d].anchor = GridBagConstraints.WEST;
			frame.add(roleArea[d], gridBagRole[d]);
		}
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 8;
		c2.gridy = 8;
		c2.weightx = 0;
		c2.weighty = 0.0;
		c2.fill = GridBagConstraints.BOTH;
		c2.anchor = GridBagConstraints.CENTER;
		c2.ipady = 0;
		frame.add(input, c2);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set frame location on the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		frame.setVisible(true);
		input.requestFocusInWindow();
	}
}
