package monopoly;

import java.awt.EventQueue;

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.awt.Font;

public class GuiDesign {

	private JFrame frame;
	public JTextArea[] blockArea = new JTextArea[32];
	public JTextArea[] roleArea = new JTextArea[4];
	public JTextField input;
	private JButton diceButton;
	public JTextArea diceArea;
	public JTextArea bossArea;
	private String userInput = null;
	JTextArea display;
	public ColorPane outputArea;
	private JPanel[][] role = new JPanel[4][32];
	private JLayeredPane layeredPane;
	public int diceThrown;

	/**
	 * Create the application.
	 */
	public GuiDesign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane = frame.getLayeredPane();

		LineBorder lineBorder=new LineBorder(new Color(190,190,190),3);

		// 輸出界面
		outputArea = new ColorPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jj = new JScrollPane(outputArea);
		jj.setBounds(110, 117, 610, 386);
		jj.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jj.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(jj);

		outputArea.setForeground(Color.BLACK);
		outputArea.setEditable(false);
		outputArea.setBackground(Color.WHITE);
		outputArea.setBorder(lineBorder);
		outputArea.setFont(new Font("monospaced", Font.PLAIN, 14));

		/*just for test
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"dcfjhgfdfghjjfhdh\n");
		outputArea.append(Color.red,"一個哇啦哇啦哇啦哇啦兩個哇啦哇啦哇啦哇啦三個哇啦哇啦哇啦哇啦四個哇啦哇啦哇啦哇啦五個哇啦哇啦哇啦哇啦\n");
		outputArea.append(Color.red,"oneeeeeeeetwoooooooothreeeeeeefourrrrrrrfiveeeeeeesixxxxxxxxsevennnnnneighttttttnineeeeeeetennnnnnnn\n");
		 */

		// 角色列表顯示於block上的四角
		for(int b=0; b<4; b++) {
			for(int a=0; a<32; a++) {
				role[b][a] = new JPanel();
				if(a/8 == 0) {
					role[b][a].setBounds((8-a)*90+10+70+b%2*10-3, 611+52+b/2*10-3, 9, 9);
				}
				else if(a/8 == 1) {
					role[b][a].setBounds(10+70+b%2*10-3, (16-a)*72+35+52+b/2*10-3, 9, 9);
				}
				else if(a/8 == 2) {
					role[b][a].setBounds((a-16)*90+10+70+b%2*10-3, 35+52+b/2*10-3, 9, 9);
				}
				else{
					role[b][a].setBounds(730+70+b%2*10-3, (a%8)*72+35+52+b/2*10-3, 9, 9);
				}
				if(b==0) {role[b][a].setBackground(new Color(255,69,0));}
				else if(b==1) {role[b][a].setBackground(new Color(154,205,50));}
				else if(b==2) {role[b][a].setBackground(new Color(92,172,238));}
				else{role[b][a].setBackground(new Color(255,215,0));}
				role[b][a].setVisible(false);
				frame.getContentPane().add(role[b][a]);
				layeredPane.add(role[b][a],new Integer(300));
			}
		}
		for(int c=0; c<4; c++) {
			role[c][0].setVisible(true);
		}

		// 遊戲方塊區
		for(int a=0; a < 32; a++) {
			blockArea[a] = new JTextArea("哇啦哇啦哇啦哇啦哇啦哇啦哇啦哇啦哇啦哇啦"+"\n金幣\n價錢\n所有人");
			blockArea[a].setForeground(Color.BLACK);
			blockArea[a].setEditable(false);
			blockArea[a].setBackground(Color.WHITE);
			if(a/8 == 0) {
				blockArea[a].setBounds((8-a)*90+10, 611, 90, 72);
			}
			else if(a/8 == 1) {
				blockArea[a].setBounds(10, (16-a)*72+35, 90, 72);
			}
			else if(a/8 == 2) {
				blockArea[a].setBounds((a%8)*90+10, 35, 90, 72);
			}
			else{
				blockArea[a].setBounds(730, (a%8)*72+35, 90, 72);
			}
			blockArea[a].setFont(new Font("monospaced", Font.PLAIN, 12));
			blockArea[a].setBorder(lineBorder);
			frame.getContentPane().add(blockArea[a]);
			layeredPane.add(blockArea[a],new Integer(200));
		}
		// 設置顏色
		blockArea[1].setBorder(new LineBorder(new Color(65,105,225), 	3));
		blockArea[3].setBorder(new LineBorder(new Color(65,105,225), 	3));
		blockArea[5].setBorder(new LineBorder(new Color(123,104,238), 	3));
		blockArea[7].setBorder(new LineBorder(new Color(123,104,238), 	3));
		blockArea[9].setBorder(new LineBorder(new Color(255,20,147), 	3));
		blockArea[11].setBorder(new LineBorder(new Color(255,20,147), 	3));
		blockArea[13].setBorder(new LineBorder(new Color(255,0,0), 		3));
		blockArea[15].setBorder(new LineBorder(new Color(255,0,0), 		3));
		blockArea[17].setBorder(new LineBorder(new Color(255,140,0), 	3));
		blockArea[19].setBorder(new LineBorder(new Color(255,140,0), 	3));
		blockArea[21].setBorder(new LineBorder(new Color(255,215,0), 	3));
		blockArea[23].setBorder(new LineBorder(new Color(255,215,0), 	3));
		blockArea[25].setBorder(new LineBorder(new Color(0,205,0), 		3));
		blockArea[27].setBorder(new LineBorder(new Color(0,205,0), 		3));
		blockArea[29].setBorder(new LineBorder(new Color(135,206,250), 	3));
		blockArea[31].setBorder(new LineBorder(new Color(135,206,250), 	3));

		// 右邊角色列表
		for(int a=0; a < 4; a++) {
			roleArea[a] = new JTextArea(""+a);
			roleArea[a].setForeground(Color.BLACK);
			roleArea[a].setEditable(false);
			roleArea[a].setBackground(Color.WHITE);
			roleArea[a].setBounds(830, 35+a*108, 240, 108);
			if(a==0) {roleArea[a].setBorder(new LineBorder(new Color(255,69,0), 3));}
			else if(a==1) {roleArea[a].setBorder(new LineBorder(new Color(154,205,50), 3));}
			else if(a==2) {roleArea[a].setBorder(new LineBorder(new Color(92,172,238), 3));}
			else{roleArea[a].setBorder(new LineBorder(new Color(255,215,0), 3));}
			roleArea[a].setFont(new Font("monospaced", Font.PLAIN, 14));
			frame.getContentPane().add(roleArea[a]);
		}

		// 輸入文字條
		input = new JTextField();
		input.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField src = (JTextField) e.getSource();
				String b = (src.getText());
				userInput = b;
				src.setText("");
			}
		});
		input.setBounds(110, 540, 145, 40);
		input.setColumns(10);
		input.setBorder(lineBorder);
		input.setFont(new Font("monospaced", Font.PLAIN, 24));
		frame.getContentPane().add(input);

		// 骰子按鈕
		diceButton = new JButton("Throw Dice!");
		diceButton.setBounds(265, 540, 300, 40);
		diceButton.setBorder(lineBorder);
		diceButton.setFont(new Font("monospaced", Font.PLAIN, 24));
		diceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if(map.dice) {
					Random random = new Random();
					int a = random.nextInt(6)+1;
					diceThrown = a;
					diceArea.setText("你骰到了" + a + "點");
				//}
			}
		});
		frame.getContentPane().add(diceButton);

		// 骰子顯示
		diceArea = new JTextArea();
		diceArea.setForeground(Color.BLACK);
		diceArea.setEditable(false);
		diceArea.setBackground(Color.WHITE);
		diceArea.setBounds(575, 540, 145, 40);
		diceArea.setBorder(lineBorder);
		diceArea.setFont(new Font("monospaced", Font.PLAIN, 21));
		frame.getContentPane().add(diceArea);

		// 右下角BOSS顯示
		bossArea = new JTextArea("ioot");
		bossArea.setForeground(Color.BLACK);
		bossArea.setEditable(false);
		bossArea.setBackground(Color.WHITE);
		bossArea.setBounds(830, 477, 240, 206);
		bossArea.setBorder(lineBorder);
		bossArea.setFont(new Font("monospaced", Font.PLAIN, 14));
		frame.getContentPane().add(bossArea);

		frame.setVisible(true);
	}

	public String signUp() {
		outputArea.append(Color.blue,"輸入名字：");
		while(this.userInput == null) {
			for(int a=0; a<99999; a++) {
				System.out.println("還在等待中．．．");
			}
		}
		String name = this.userInput;
		outputArea.append(Color.blue,name + "\n");
		this.userInput = null;
		return name;
	}

	public int getDecision() {
		outputArea.append(Color.blue,"輸入數字：");
		while(this.userInput == null) {
			System.out.println("還在等待中．．．");
		}
		int integerInput = 0;
		integerInput = Integer.parseInt(userInput);
		outputArea.append(Color.blue,integerInput + "\n");
		this.userInput = null;
		return integerInput;
	}

	/**
	 * @brief set role's position on the map
	 */
	public void movePosition(Role roleIn, int whichOne) {
		for(int a=0; a<32; a++) {
			if(a==roleIn.getPosition()) {
				role[whichOne][a].setVisible(true);
			}
			else {
				role[whichOne][a].setVisible(false);
			}
		}
	}
	public void refreshBlock(Block[] blockIn) {
		for(int a=0; a<32; a++) {
			blockArea[a].setText(blockIn[a].getName() + "\n" 
					+ "金幣：" + blockIn[a].getMoneyDropped() + "\n" 
					+ "價錢：" + blockIn[a].getPrice() + "\n" 
					+ blockIn[a].getOwner() + "的");
		}
	}
	public void refreshRole(Role[] roleIn) {
		for(int a=0; a<4; a++) {
			blockArea[a].setText(roleIn[a] + "\n" 
					+ "：" + roleIn[a] + "\n" 
					+ "：" + roleIn[a] + "\n" 
					+ roleIn[a] + "");
		}
	}
	public void refreshBoss(Boss bossIn, Boolean turnOn) {
		if(turnOn) {
			bossArea.setText(bossIn + "\n" 
					+ "：" + bossIn + "\n" 
					+ "：" + bossIn + "\n" 
					+ bossIn + "");
		}
		else {
			bossArea.setText("?????" + "\n" 
					+ "：" + "?????" + "\n" 
					+ "：" + "?????" + "\n" 
					+ "?????" + "");
		}
	}
}

