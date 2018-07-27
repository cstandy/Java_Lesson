package monopoly;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.*;

public class Game {

	/* variables */
	private Map map;          // the map of the game
	private Stack<Boss> boss; // stack storing 6 boss
	private Role[] roles;     // list of 4 roles
	private int curRole;      // current role
	Random random = new Random(); //use for dice to random 1~6
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.run();
	}

	/**
	  * @brief Constructor of Game.
	  */
	public Game() {

		GuiDesign gui = new GuiDesign();
		String name = gui.signUp();
		System.out.println(name);
		
		int inininin = gui.getDecision();
		System.out.println(inininin);
		
		map     = new Map();
		boss    = new Stack<Boss>();
		roles   = new Role[4];
		curRole = 0;

		// boss.push(new Boss());
		// boss.push(new Boss());
		// boss.push(new Boss());
		// boss.push(new Boss());
		// boss.push(new Boss());
		// boss.push(new Boss());

		roles[0] = new Role("Vicky");
		roles[1] = new Role("Andy");
		roles[2] = new Role("Lucy");
		roles[3] = new Role("Alan");
	}

	/**
	  * @brief Run the game, containing all flow.
	  */
	public void run() {
		int dice = 0;
		
		while (true) {
			for (int i = 0; i < 4; i++)
			{
				dice = 1 + random.nextInt(6);
				
				System.out.print(" . " + roles[i].getName() + " 的回合：");
				try { System.in.read(); } catch (Exception e) {}
				System.out.println("   . 擲出了 " + dice + "。");
				System.out.println("   $ 玩家 " + roles[i].getName()
				         + " 目前位於 " + map.walk(roles, dice, i).getName()
				         + "，身上有 " + roles[i].getMoney() + " 枚金幣。");
				System.out.print(" . " + roles[i].getName() + " 使用能力骰子：");
				try { System.in.read(); } catch (Exception e) {}
				map.useAbility(roles, i, 1 + random.nextInt(6));
				// map.print(roles);
			}
			
			// the ending condition of the game
			// if (boss.empty() == true)
			// 	break;
		}

		// calculateScore();
	}


	/**
	  * @brief Calculate the Score at the final stage.
	  */
	private void calculateScore() {}

	/**
	  * @brief Let all roles to fight with the boss in order.
	  */
	private void fightBoss() {}
}
