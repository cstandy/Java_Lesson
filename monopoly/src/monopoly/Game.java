package monopoly;

import java.util.Scanner;
import java.util.Stack;

public class Game {

	/* variables */
	private Map map;          // the map of the game
	private Stack<Boss> boss; // stack storing 6 boss
	private Role[] roles;     // list of 4 roles
	private int curRole;      // current role

	public static void main(String[] args) {
		
		Game game = new Game();
		game.run();
	}

	/**
	  * @brief Constructor of Game.
	  */
	public Game() {

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
		Scanner input = new Scanner(System.in);
		
		while (true) {
			for (int i = 0; i < 4; i++)
			{
				System.out.println("Role " + i
						         + " is at " + map.walk(roles, input.nextInt(), i).getName()
						         + " with $" + roles[i].getMoney());
				map.print(roles);
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
