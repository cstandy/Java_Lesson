package monopoly

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

		// roles[0] = new Role();
		// roles[1] = new Role();
		// roles[2] = new Role();
		// roles[3] = new Role();
	}

	/**
	  * @brief Run the game, containing all flow.
	  */
	public void run() {
		
		while (true) {
			
			// the ending condition of the game
			if (boss.empty() == true)
				break;
		}

		calculateScore();

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
