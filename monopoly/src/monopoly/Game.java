package monopoly;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Game {

	/* variables */
	private Map map;          // the map of the game
	private Stack<Boss> boss; // stack storing 6 boss
	private Role[] roles;     // list of 4 roles
	Random random = new Random(); //use for dice to random 1~6
	Scanner input = new Scanner(System.in);
	
	
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

		boss.push(new Boss("\033[1;31m<系上同學>\033[0m", 6, 1, 100, 6));
		boss.push(new Boss("\033[1;31m<阿國>\033[0m",    5, 3, 50, 5));
		boss.push(new Boss("\033[1;31m<雲平狗>\033[0m",   4, 2, 40, 4));
		boss.push(new Boss("\033[1;31m<大笨鳥>\033[0m",   3, 2, 30, 4));
		boss.push(new Boss("\033[1;31m<普物龜>\033[0m",   2, 1, 20, 3));
		boss.push(new Boss("\033[1;31m<松鼠>\033[0m",    1, 1, 10, 3));

		roles[0] = new Role("\033[0;33mVicky\033[0m");
		roles[1] = new Role("\033[0;33mAndy\033[0m");
		roles[2] = new Role("\033[0;33mLucy\033[0m");
		roles[3] = new Role("\033[0;33mLeo\033[0m");
	}

	/**
	  * @brief Run the game, containing all flow.
	  */
	public void run() {
		int dice = 0;
		
		System.out.println("___  ___                              _              _   _ _____ _   ___   _ ");
		System.out.println("|  \\/  |                             | |            | \\ | /  __ \\ | / / | | |");
		System.out.println("| .  . | ___  _ __   ___  _ __   ___ | |_   _ ______|  \\| | /  \\/ |/ /| | | |");
		System.out.println("| |\\/| |/ _ \\| '_ \\ / _ \\| '_ \\ / _ \\| | | | |______| . ` | |   |    \\| | | |");
		System.out.println("| |  | | (_) | | | | (_) | |_) | (_) | | |_| |      | |\\  | \\__/\\ |\\  \\ |_| |");
		System.out.println("\\_|  |_/\\___/|_| |_|\\___/| .__/ \\___/|_|\\__, |      \\_| \\_/\\____|_| \\_/\\___/ ");
		System.out.println("                         | |             __/ |                               ");
		System.out.println("                         |_|            |___/                                ");
		System.out.println("                                                       ~~~~~~~~~ [\033[0;91m電\033[1;93m機\033[0;92m漢\033[0;96m化\033[0;94m組\033[0;37m]");
		System.out.print("（按 Enter 繼續）");
		try { System.in.read(); } catch (Exception e) {}
		
loop:	while (true) {
			for (int i = 0; i < 4; i++)
			{				
				int prePosition = 0;
				int postPosition = 0;
				
				prePosition = roles[i].getPosition();
				dice = 1 + random.nextInt(6);
				
				System.out.print(" . " + roles[i].getName() + " 的回合（按 Enter 繼續）：");
				try { System.in.read(); } catch (Exception e) {}
				try { System.in.read(); } catch (Exception e) {}
				// dice = input.nextInt();
				dice = 1 + random.nextInt(6);
				System.out.println("   . 擲出了 " + dice + "。");
				System.out.println("   $ 玩家 " + roles[i].getName()
				         + " 目前位於" + map.walk(roles, dice, i).getName()
				         + "，身上有 " + roles[i].getMoney() + " 枚金幣。");		
				System.out.print(" . " + roles[i].getName() + " 使用能力骰子（按 Enter 繼續）：");
				try { System.in.read(); } catch (Exception e) {}
				input.nextLine();
				map.useAbility(roles, i, 1 + random.nextInt(6));
				
				postPosition = roles[i].getPosition();
				
				if (postPosition - prePosition < 0) {
					
					fightBoss(i); // fight bosses and ...
					
					// the ending condition of the game
					if (boss.empty()) break loop;
				}
			}
		}

		calculateScore();
	}


	/**
	  * @brief Calculate the Score at the final stage.
	  */
	private void calculateScore() {
		int[] score = {0, 0, 0, 0};;
		int winScore = 0;
		
		System.out.println(" __       __                                                    __                    __    __   ______   __    __  __    __ ");
		System.out.println("/  \\     /  |                                                  /  |                  /  \\  /  | /      \\ /  |  /  |/  |  /  |");
		System.out.println("$$  \\   /$$ |  ______   _______    ______    ______    ______  $$ | __    __         $$  \\ $$ |/$$$$$$  |$$ | /$$/ $$ |  $$ |");
		System.out.println("$$$  \\ /$$$ | /      \\ /       \\  /      \\  /      \\  /      \\ $$ |/  |  /  | ______ $$$  \\$$ |$$ |  $$/ $$ |/$$/  $$ |  $$ |");
		System.out.println("$$$$  /$$$$ |/$$$$$$  |$$$$$$$  |/$$$$$$  |/$$$$$$  |/$$$$$$  |$$ |$$ |  $$ |/      |$$$$  $$ |$$ |      $$  $$<   $$ |  $$ |");
		System.out.println("$$ $$ $$/$$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |$$ |  $$ |$$$$$$/ $$ $$ $$ |$$ |   __ $$$$$  \\  $$ |  $$ |");
		System.out.println("$$ |$$$/ $$ |$$ \\__$$ |$$ |  $$ |$$ \\__$$ |$$ |__$$ |$$ \\__$$ |$$ |$$ \\__$$ |        $$ |$$$$ |$$ \\__/  |$$ |$$  \\ $$ \\__$$ |");
		System.out.println("$$ | $/  $$ |$$    $$/ $$ |  $$ |$$    $$/ $$    $$/ $$    $$/ $$ |$$    $$ |        $$ | $$$ |$$    $$/ $$ | $$  |$$    $$/ ");
		System.out.println("$$/      $$/  $$$$$$/  $$/   $$/  $$$$$$/  $$$$$$$/   $$$$$$/  $$/  $$$$$$$ |        $$/   $$/  $$$$$$/  $$/   $$/  $$$$$$/  ");
		System.out.println("                                           $$ |                    /  \\__$$ |                                                ");
		System.out.println("                                           $$ |                    $$    $$/                                                 ");
		System.out.println("                                           $$/                      $$$$$$/                                                  ");
	
		for (int i = 0; i < 4; i++) {
			System.out.println(" # " + roles[i].getName() + ":");
			System.out.println("   # 帶回寵物所得的分數為：" + roles[i].getPoint() + " 分。");
			System.out.println("   # 用錢換得的分數為：" + ((int)(roles[i].getMoney() / 5) * 10) + " 分。");
			score[i] += roles[i].getPoint();
			score[i] += (int)(roles[i].getMoney() / 5) * 10;
			
			System.out.println("   # 擁有的土地有：");
			for (int j = 0; j < 32; j++) {
				if (map.blockList[j].getOwner() == roles[i].getName()) {
					System.out.println("     # " + map.blockList[j].getName() + ": " + (map.blockList[j].getPrice() * 10 + 10) + " 分。");
					score[i] += map.blockList[j].getPrice() * 10 + 10;
				}
			}
			
			if (score[i] > winScore) winScore = score[i];
			
			System.out.println("   # " + roles[i].getName() + " 的最後分數為：\033[0;91m" + score[i] + " \033[0m分。");
		}
		
		System.out.println("       _                         \r\n" + 
				"      (_)                        \r\n" + 
				" _ _ _ _ ____  ____   ____  ____ \r\n" + 
				"| | | | |  _ \\|  _ \\ / _  )/ ___)\r\n" + 
				"| | | | | | | | | | ( (/ /| |    \r\n" + 
				" \\____|_|_| |_|_| |_|\\____)_|    \r\n" + 
				"                                 ");

		
		for (int i = 0; i < 4; i++) {
			if (score[i] == winScore)
				System.out.println(" & " + roles[i].getName());
		}
	}

	/**
	  * @brief Let all roles to fight with the boss in order.
	  * @param curRole The index of the role who will fight the boss first
	  */
	private void fightBoss(int curRole) {
		int dice = 0;
		boolean[] skip = {false, false, false, false};
		
		System.out.println("");
		System.out.println(" . 有玩家經過原點，出現 boss。");
		System.out.println(" =================================================================");
		System.out.print(" ! BOSS 討伐戰：" + roles[curRole].getName() + " 遇到了生物 No." + boss.peek().getOrder() + "：小 " + boss.peek().getName() + "。（請按 Enter 繼續）");
		try { System.in.read(); } catch (Exception e) {}
		

		while (true) {
			dice = -1;
			int nextRole = 0;
			int remainMoney = roles[curRole].getMoney() - boss.peek().getCost();
			
			System.out.println("   $ 要獲得食物需先付出 " + boss.peek().getCost() + " 枚金幣。");
			System.out.println("   $ " + roles[curRole].getName() + " 目前有 " + roles[curRole].getMoney() + " 枚金幣。");
			System.out.println("   ! " + boss.peek().getName() + " 如果吃 " + boss.peek().getRequirement() + " 個桃太郎糰子，就會跟你回家。");
			System.out.print("   ? " + roles[curRole].getName() + " 要嘗試餵食？ 是(1)/否(0)：");
			
			if (input.nextInt() == 0) {
				skip[curRole] = true;
			} else if (remainMoney < 0) {
				System.out.println("   * " + roles[curRole].getName() + " 太窮了，動物都不理你！狗不理！");
				System.out.println(" . 輪到 " + roles[(curRole + 1) % 4].getName() + " 挑戰。");
				skip[curRole] = true;
			} else {
				System.out.println("   * " + roles[curRole].getName() +  " 挑戰餵食！");
				roles[curRole].setMoney(remainMoney);
				System.out.print("   * " + roles[curRole].getName() + " 花了錢買桃太郎糰子，剩 " + roles[curRole].getMoney() + " 元，");
				
				dice = 1 + random.nextInt(6);
				System.out.println("餵了 " + dice + " 個桃太郎糰子。");
				
				// 獲勝
				if (dice >= boss.peek().getRequirement()) {
					System.out.println(" ! "+ boss.peek().getName() + " 吃得好飽喔，好爽喔。");
					System.out.println(" ! " + boss.peek().getName() + " 跟著 " + roles[curRole].getName() + " 回家了，而且該玩家得到 " + boss.peek().getPoint() + " 分。");
					roles[curRole].addPoint(boss.peek().getPoint());
					boss.pop();
					System.out.println(" =================================================================");
					System.out.println("");
					break;
				} else {
					System.out.println(" ! "+ boss.peek().getName() + " 只吃 " + dice + " 個桃太郎糰子不會被騙回家，而要 " + boss.peek().getRequirement() + " 個才夠。");
				}
			}
			
			if (allSkip(skip)) { // break the while loop if all roles skipped this boss
				System.out.println(" !  因為所有玩家都不餵食，所以 " + boss.peek().getName() + " 走掉了，奔向自由。");
				System.out.println(" =================================================================");
				System.out.println("");
				boss.pop();
				break;
			} else {
				do {
					nextRole = (++curRole) % 4;
				} while (skip[nextRole]);
				System.out.println(" . 輪到 " + roles[nextRole].getName() + " 挑戰。");
				curRole = nextRole;
			}
		}
	}
	
	private boolean allSkip(boolean[] skip) {
		for (int i = 0; i < 4; i++)
			if (!skip[i]) return false;
		return true;
	}
}
