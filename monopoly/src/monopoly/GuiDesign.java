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
		bossArea.setFont(new Font("monospaced", Font.PLAIN, 20));
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
			if(a%2 == 1 && !blockIn[a].getOwner().equals("")) {
				blockArea[a].setText(blockIn[a].getName() + "\n" 
						+ "金幣： " + blockIn[a].getMoneyDropped() + "\n" 
						+ "價錢： " + blockIn[a].getPrice() + "\n" 
						+ blockIn[a].getOwner() + "的");
			}
			else if(a%2 == 1 && blockIn[a].getOwner().equals("")) {
				blockArea[a].setText(blockIn[a].getName() + "\n" 
						+ "金幣： " + blockIn[a].getMoneyDropped() + "\n" 
						+ "價錢： " + blockIn[a].getPrice());
			}
			else {
				blockArea[a].setText(blockIn[a].getName() + "\n" 
						+ "金幣： " + blockIn[a].getMoneyDropped());
			}
		}
	}
	public void refreshRole(Role[] roleIn) {
		for(int a=0; a<4; a++) {
			switch (roleIn[a].getCareer()) {
			case "\033[1;93m<劍士>\033[0m":
				roleArea[a].setText("名字：" + roleIn[a].getName() + "       " 
						+ "職業：" + roleIn[a].getCareer() + "\n" 
						+ "超級星星：\n  向每個人收 2 枚金幣\n"
						+ "碰！！：增加為 2 點傷害\n" 
						+ "金錢： " + roleIn[a].getMoney() + "\n"
						+ "分數累積： " + roleIn[a].getPoint() + "分");
				break;
			case "\033[1;93m<法師>\033[0m":
				roleArea[a].setText("名字：" + roleIn[a].getName() + "       " 
						+ "職業：" + roleIn[a].getCareer() + "\n" 
						+ "超級星星：\n  收集全部地上所有的錢\n"
						+ "紅龜殼：增加紅龜殼傷害\n" 
						+ "金錢： " + roleIn[a].getMoney() + "\n"
						+ "分數累積： " + roleIn[a].getPoint() + "分");
				break;
			case "\033[1;93m<弓手>\033[0m":
				roleArea[a].setText("名字：" + roleIn[a].getName() + "       " 
						+ "職業：" + roleIn[a].getCareer() + "\n" 
						+ "超級星星：\n  向銀行收所擁有的地租\n"
						+ "綠龜殼：增加綠龜殼傷害\n" 
						+ "金錢： " + roleIn[a].getMoney() + "\n"
						+ "分數累積： " + roleIn[a].getPoint() + "分");
				break;
			case "\033[1;93m<盜賊>\033[0m":
				roleArea[a].setText("名字：" + roleIn[a].getName() + "       " 
						+ "職業：" + roleIn[a].getCareer() + "\n" 
						+ "超級星星：\n  再次丟骰子並獲得該值 +5 枚金幣\n"
						+ "小烏賊：增加奪取金幣量\n" 
						+ "金錢： " + roleIn[a].getMoney() + "\n"
						+ "分數累積： " + roleIn[a].getPoint() + "分");
				break;
			default: break;
			}
		}
	}
	public void refreshBoss(Boss bossIn, Boolean turnOn) {
		if(turnOn) {
			bossArea.setText( "第 " + bossIn.getOrder() + " 隻 BOSS\n"
					+ bossIn.getName() + "\n" 
					+ "參加費用： " + bossIn.getCost() + "\n" 
					+ "餵食數量： " + bossIn.getRequirement() + "\n" 
					+ "可得分數： " + bossIn.getPoint());
		}
		else {
			bossArea.setText("第 " + bossIn.getOrder() + " 隻 BOSS\n"
					+ "?????\n" 
					+ "參加費用： ?????\n" 
					+ "餵食數量： ?????\n" 
					+ "可得分數： ?????");
		}
	}
	public void refreshBoss(Boss bossIn, Boolean turnOn, int money) {
		if(turnOn) {
			bossArea.setText( "第 6 隻 BOSS\n"
					+ bossIn.getName() + "\n" 
					+ "參加費用： " + bossIn.getCost() + "\n" 
					+ "餵食數量： " + bossIn.getRequirement() + "\n" 
					+ "可得分數： " + bossIn.getPoint() + "\n"
					+ "累積金錢： " + money);
		}
		else {
			bossArea.setText( "第 6 隻 BOSS\n"
					+ "?????\n" 
					+ "參加費用： ?????\n" 
					+ "餵食數量： ?????\n" 
					+ "可得分數： ?????\n"
					+ "累積金錢： ?????");
		}
	}
}

