package monopoly;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

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
	public JTextArea outputArea; 
	public JTextField input;
	private JButton diceButton;
	public JTextArea diceArea;
	public JTextArea bossArea;
	private String userInput = null;

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

		LineBorder lineBorder=new LineBorder(new Color(190,190,190),2);

		// 遊戲方塊區
		for(int a=0; a < 32; a++) {
			blockArea[a] = new JTextArea(a+"哇啦哇啦"+"\n金幣\n價錢\n所有人");
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
		}
		// 設置顏色
		blockArea[1].setBorder(new LineBorder(new Color(65,105,225), 	4));
		blockArea[3].setBorder(new LineBorder(new Color(65,105,225), 	4));
		blockArea[5].setBorder(new LineBorder(new Color(123,104,238), 	4));
		blockArea[7].setBorder(new LineBorder(new Color(123,104,238), 	4));
		blockArea[9].setBorder(new LineBorder(new Color(255,20,147), 	4));
		blockArea[11].setBorder(new LineBorder(new Color(255,20,147), 	4));
		blockArea[13].setBorder(new LineBorder(new Color(255,0,0), 		4));
		blockArea[15].setBorder(new LineBorder(new Color(255,0,0), 		4));
		blockArea[17].setBorder(new LineBorder(new Color(255,140,0), 	4));
		blockArea[19].setBorder(new LineBorder(new Color(255,140,0), 	4));
		blockArea[21].setBorder(new LineBorder(new Color(255,215,0), 	4));
		blockArea[23].setBorder(new LineBorder(new Color(255,215,0), 	4));
		blockArea[25].setBorder(new LineBorder(new Color(0,205,0), 		4));
		blockArea[27].setBorder(new LineBorder(new Color(0,205,0), 		4));
		blockArea[29].setBorder(new LineBorder(new Color(135,206,250), 	4));
		blockArea[31].setBorder(new LineBorder(new Color(135,206,250), 	4));

		// 右邊角色列表
		for(int a=0; a < 4; a++) {
			roleArea[a] = new JTextArea(""+a);
			roleArea[a].setForeground(Color.BLACK);
			roleArea[a].setEditable(false);
			roleArea[a].setBackground(Color.WHITE);
			roleArea[a].setBounds(830, 35+a*108, 240, 108);
			roleArea[a].setBorder(lineBorder);
			roleArea[a].setFont(new Font("monospaced", Font.PLAIN, 14));
			frame.getContentPane().add(roleArea[a]);
		}

		// 輸出界面
		outputArea = new JTextArea();
		outputArea.setForeground(Color.BLACK);
		outputArea.setEditable(false);
		outputArea.setBackground(Color.WHITE);
		outputArea.setBounds(110, 117, 610, 386);
		outputArea.setBorder(lineBorder);
		outputArea.setFont(new Font("monospaced", Font.PLAIN, 14));
		JScrollPane scroll = new JScrollPane(outputArea);
		frame.getContentPane().add(outputArea);

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
				Random random = new Random();
				int a = random.nextInt(6)+1;
				diceArea.setText("你骰到了" + a + "點");
				//methodA();
			}
		});
		frame.getContentPane().add(diceButton);

		//骰子顯示
		diceArea = new JTextArea();
		diceArea.setForeground(Color.BLACK);
		diceArea.setEditable(false);
		diceArea.setBackground(Color.WHITE);
		diceArea.setBounds(575, 540, 145, 40);
		diceArea.setBorder(lineBorder);
		diceArea.setFont(new Font("monospaced", Font.PLAIN, 21));
		//diceArea.set
		frame.getContentPane().add(diceArea);


		//右下角BOSS顯示
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
		outputArea.append("輸入名字：");
		while(this.userInput == null) {
			for(int a=0; a<99999; a++) {
				System.out.println("還在等待中．．．");
			}
		}
		String name = this.userInput;
		outputArea.append(name + "\n");
		this.userInput = null;
		return name;
	}

	public int getDecision() {
		outputArea.append("輸入數字：");
		while(this.userInput == null) {
			for(int a=0; a<99999; a++) {
				System.out.println("還在等待中．．．");
			}
		}
		int integerInput = 0;
		integerInput = Integer.parseInt(userInput);
		outputArea.append(integerInput + "\n");
		/*do {
			if(userInput.equals("0") 
					|| userInput.equals("1") 
					|| userInput.equals("2") 
					|| userInput.equals("3") 
					|| userInput.equals("4")) {
				integerInput = Integer.parseInt(userInput);
				outputArea.append("你輸入了" + integerInput + "\n");
			}
			else if(this.userInput == null) {
				for(int a=0; a<99999; a++) {
					System.out.println("還在等待中．．．");
				}
			}
			else {
				outputArea.append("非法的輸入！！請再輸入一次\n");
				this.userInput = null;
			}
		}while(!(userInput.equals("0") 
				|| userInput.equals("1") 
				|| userInput.equals("2") 
				|| userInput.equals("3") 
				|| userInput.equals("4")));*/
		this.userInput = null;
		return integerInput;
	}

}
