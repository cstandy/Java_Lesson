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
	Random random = new Random(); //use for dice to random 1~6
	Scanner input = new Scanner(System.in);
	GuiDesign gui;


	public static void main(String[] args) {

		Game game = new Game();
		game.run();
	}

	/**
	 * @brief Constructor of Game.
	 */
	public Game() {

		gui= new GuiDesign();
		map     = new Map(gui);
		boss    = new Stack<Boss>();
		roles   = new Role[4];
		String name = "";

		title();
		System.out.println("___  ___                              _              _   _ _____ _   ___   _ ");
		System.out.println("|  \\/  |                             | |            | \\ | /  __ \\ | / / | | |");
		System.out.println("| .  . | ___  _ __   ___  _ __   ___ | |_   _ ______|  \\| | /  \\/ |/ /| | | |");
		System.out.println("| |\\/| |/ _ \\| '_ \\ / _ \\| '_ \\ / _ \\| | | | |______| . ` | |   |    \\| | | |");
		System.out.println("| |  | | (_) | | | | (_) | |_) | (_) | | |_| |      | |\\  | \\__/\\ |\\  \\ |_| |");
		System.out.println("\\_|  |_/\\___/|_| |_|\\___/| .__/ \\___/|_|\\__, |      \\_| \\_/\\____|_| \\_/\\___/ ");
		System.out.println("                         | |             __/ |                               ");
		System.out.println("                         |_|            |___/                                ");
		System.out.println("                                                       ~~~~~~~~~ [\033[0;91m電\033[1;93m機\033[0;92m漢\033[0;96m化\033[0;94m組\033[0m]");
		//System.out.print("（按 Enter 開始）");
		try { Thread.sleep(1000); } catch (InterruptedException e) {}
		//try { System.in.read(); } catch (Exception e) {}

		boss.push(new Boss("\033[1;31m<系上同學>\033[0m", 6, 1, 100, 6));
		boss.push(new Boss("\033[1;31m<阿國>\033[0m",    5, 3, 50, 5));
		boss.push(new Boss("\033[1;31m<雲平狗>\033[0m",   4, 2, 40, 4));
		boss.push(new Boss("\033[1;31m<大笨鳥>\033[0m",   3, 2, 30, 4));
		boss.push(new Boss("\033[1;31m<普物龜>\033[0m",   2, 1, 20, 3));
		boss.push(new Boss("\033[1;31m<松鼠>\033[0m",    1, 1, 10, 3));



		System.out.println("");
		name = gameSignUp("\033[1;93m<劍士>\033[0m");
		roles[0] = new Role("\033[0;33m" + name + "\033[0m", "\033[1;93m<劍士>\033[0m", gui);
		name = gameSignUp("\033[1;93m<法師>\033[0m");
		roles[1] = new Role("\033[0;33m" + name + "\033[0m",  "\033[1;93m<法師>\033[0m", gui);
		name = gameSignUp("\033[1;93m<弓手>\033[0m");
		roles[2] = new Role("\033[0;33m" + name + "\033[0m",  "\033[1;93m<弓手>\033[0m", gui);
		name = gameSignUp("\033[1;93m<盜賊>\033[0m");
		roles[3] = new Role("\033[0;33m" + name + "\033[0m",   "\033[1;93m<盜賊>\033[0m", gui);
		System.out.println("");
	}

	/**
	 * @brief Sign up requirement for roles
	 */
	private String gameSignUp(String career) {
		String name = "";

		System.out.print(" . 請輸入職業為" + career + "的玩家姓名（英文）：");
		gui.outputArea.append(Color.black," . 請輸入職業為");
		gui.outputArea.append(Color.blue, career);
		gui.outputArea.append(Color.black, "的玩家姓名（英文）：");
		// name = input.next();
		name = gui.signUp();

		while (!name.matches("[A-Z][a-zA-Z0-9]*") || name.length() > 8) {
			if (!name.matches("[A-Z][a-zA-Z0-9]*")) {
				System.out.println("   ! 姓名需由大寫開頭。");
				gui.outputArea.append(Color.black, "   ! 姓名需由大寫開頭。\n");
				System.out.print("   ! 請重新輸入：");
				gui.outputArea.append(Color.black, "   ! 請重新輸入：");
				// name = input.next();
				name = gui.signUp();
			} else if (name.length() > 8) {
				System.out.println("   ! 姓名需在 8 個字元以內。");
				gui.outputArea.append(Color.black, "   ! 姓名需在 8 個字元以內。\n");
				System.out.print("   ! 請重新輸入：");
				gui.outputArea.append(Color.black, "   ! 請重新輸入：");
				// name = input.next();
				name = gui.signUp();
			}
		}

		return name;
	}

	/**
	 * @brief Run the game, containing all flow.
	 */
	public void run() {
		loop:	while (true) {

			for (int i = 0; i < 4; i++)
			{
				int prePosition = 0;
				int postPosition = 0;

				prePosition = roles[i].getPosition();
				
				
				//System.out.print(" . " + roles[i].getName() + " 的回合（按 Enter 繼續）：");
				//gui.outputArea.append(Color.BLACK, " . " + roles[i].getName() + " 的回合（按 Enter 繼續）：\n");
				//try { System.in.read(); } catch (Exception e) {}
				//try { System.in.read(); } catch (Exception e) {}
				gui.dice = true;
				gui.outputArea.append(Color.BLACK, "請按下Throw Dice\n");
				// 等待按按鈕，如果輸入有值才會繼續
				while(this.gui.diceThrown == 0) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}
				System.out.println("   . 擲出了 " + gui.diceThrown + "。");
				gui.outputArea.append(Color.BLACK, "   . 擲出了 " + gui.diceThrown + "。\n");
				//System.out.println("   $ 玩家 " + roles[i].getName()
				//		+ " 目前位於" + map.walk(roles, gui.diceThrown, i).getName()
				//		+ "，身上有 " + roles[i].getMoney() + " 枚金幣。");
				gui.outputArea.append(Color.BLACK, "   $ 玩家 " + roles[i].getName()
						+ " 目前位於" + map.walk(roles, gui.diceThrown, i).getName()
						+ "，身上有 " + roles[i].getMoney() + " 枚金幣。\n");
				gui.diceThrown = 0;
				gui.dice = false;
				
				//System.out.print(" . " + roles[i].getName() + " 使用能力骰子（按 Enter 繼續）：");
				//gui.outputArea.append(Color.BLACK, " . " + roles[i].getName() + " 使用能力骰子（按 Enter 繼續）：\n");
				//try { System.in.read(); } catch (Exception e) {}
				//input.nextLine();
				gui.dice = true;
				gui.outputArea.append(Color.BLACK, "請按下Throw Dice\n");
				// 等待按按鈕，如果輸入有值才會繼續
				while(this.gui.diceThrown == 0) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}
				map.useAbility(roles, i, gui.diceThrown);
				gui.diceThrown = 0;
				gui.dice = false;

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



		// 將所有人的分數分別加總
		for (int i = 0; i < 4; i++) {
			System.out.println(" # " + roles[i].getName() + ":");
			gui.outputArea.append(Color.BLACK, " # " + roles[i].getName() + ":\n");
			System.out.println("   # 帶回寵物所得的分數為：" + roles[i].getPoint() + " 分。");
			gui.outputArea.append(Color.BLACK, "   # 帶回寵物所得的分數為：" + roles[i].getPoint() + " 分。\n");
			System.out.println("   # 用錢換得的分數為：" + ((int)(roles[i].getMoney() / 5) * 10) + " 分。");
			gui.outputArea.append(Color.BLACK, "   # 用錢換得的分數為：" + ((int)(roles[i].getMoney() / 5) * 10) + " 分。\n");

			// 帶寵物所得到的分數
			score[i] += roles[i].getPoint();

			// 用身上的錢換到的分數（每 5 枚金幣換 10 分，少於 5 枚的不計）
			score[i] += (int)(roles[i].getMoney() / 5) * 10;

			// 列出所有的土地並加分
			System.out.println("   # 擁有的土地有：");
			for (int j = 0; j < 32; j++) {
				if (map.blockList[j].getOwner() == roles[i].getName()) {
					System.out.println("     # " + map.blockList[j].getName() + ": " + (map.blockList[j].getPrice() * 10 + 10) + " 分。");
					gui.outputArea.append(Color.BLACK, "     # " + map.blockList[j].getName() + ": " + (map.blockList[j].getPrice() * 10 + 10) + " 分。\n");
					score[i] += map.blockList[j].getPrice() * 10 + 10;
				}
			}

			// 求出最高分者的分數
			if (score[i] > winScore) winScore = score[i];

			System.out.println("   # " + roles[i].getName() + " 的最後分數為：\033[0;91m" + score[i] + " \033[0m分。");
			gui.outputArea.append(Color.BLACK, "   # " + roles[i].getName() + " 的最後分數為：\033[0;91m" + score[i] + " \033[0m分。\n");
		}

		System.out.println("       _                         \r\n" + 
				"      (_)                        \r\n" + 
				" _ _ _ _ ____  ____   ____  ____ \r\n" + 
				"| | | | |  _ \\|  _ \\ / _  )/ ___)\r\n" + 
				"| | | | | | | | | | ( (/ /| |    \r\n" + 
				" \\____|_|_| |_|_| |_|\\____)_|    \r\n" + 
				"                                 ");
		gui.outputArea.append(Color.BLACK, "       _                         \r\n" + 
				"      (_)                        \r\n" + 
				" _ _ _ _ ____  ____   ____  ____ \r\n" + 
				"| | | | |  _ \\|  _ \\ / _  )/ ___)\r\n" + 
				"| | | | | | | | | | ( (/ /| |    \r\n" + 
				" \\____|_|_| |_|_| |_|\\____)_|    \r\n" + 
				"                                 \n");

		// 印出一個或複數個優勝者
		for (int i = 0; i < 4; i++) {
			if (score[i] == winScore) {
				System.out.println(" & " + roles[i].getName());
				gui.outputArea.append(Color.BLACK, " & " + roles[i].getName());
			}
		}
	}

	/**
	 * @brief Let all roles to fight with the boss in order.
	 * @param curRole The index of the role who will fight the boss first
	 */
	private void fightBoss(int curRole) {
		int dice = 0;
		boolean[] skip = {false, false, false, false};

		// 雖然寫經過原點，但其實判斷不在這個 method 裡
		System.out.println("");
		gui.outputArea.append(Color.BLACK, "");
		System.out.println(" . 有玩家經過原點，出現 boss。");
		gui.outputArea.append(Color.BLACK, " . 有玩家經過原點，出現 boss。\n");
		System.out.println(" =================================================================");
		gui.outputArea.append(Color.BLACK, " =================================================================\n");
		//System.out.print(" ! BOSS 討伐戰：" + roles[curRole].getName() + " 遇到了生物 No." + boss.peek().getOrder() + "：小 " + boss.peek().getName() + "。（請按 Enter 繼續）");
		gui.outputArea.append(Color.BLACK, " ! BOSS 討伐戰：" + roles[curRole].getName() + " 遇到了生物 No." + boss.peek().getOrder() + "：小 " + boss.peek().getName() + "\n");
		try { Thread.sleep(1000); } catch (InterruptedException e) {}
		//try { System.in.read(); } catch (Exception e) {}

		// 最後一隻王，特別規則，會計算大家付的錢，送給打死最後一隻王的人
		int lastBonus = 0;

		while (true) {
			dice = -1;
			int nextRole = 0;
			int remainMoney = roles[curRole].getMoney() - boss.peek().getCost();


			System.out.println("   $ 要獲得食物需先付出 " + boss.peek().getCost() + " 枚金幣。");
			gui.outputArea.append(Color.BLACK, "   $ 要獲得食物需先付出 " + boss.peek().getCost() + " 枚金幣。\n");
			System.out.println("   $ " + roles[curRole].getName() + " 目前有 " + roles[curRole].getMoney() + " 枚金幣。");
			gui.outputArea.append(Color.BLACK, "   $ " + roles[curRole].getName() + " 目前有 " + roles[curRole].getMoney() + " 枚金幣。\n");

			// 如果錢不夠，就不能挑戰
			if (remainMoney < 0) {
				System.out.println("   * " + roles[curRole].getName() + " 太窮了，動物都不理你！狗不理！");
				gui.outputArea.append(Color.BLACK, "   * " + roles[curRole].getName() + " 太窮了，動物都不理你！狗不理！\n");
				skip[curRole] = true;
			} else {	// 錢夠挑戰才詢問
				System.out.println("   ! " + boss.peek().getName() + " 如果吃 " + boss.peek().getRequirement() + " 個桃太郎糰子，就會跟你回家。");
				gui.outputArea.append(Color.BLACK, "   ! " + boss.peek().getName() + " 如果吃 " + boss.peek().getRequirement() + " 個桃太郎糰子，就會跟你回家。\n");
				System.out.print("   ? " + roles[curRole].getName() + " 要嘗試餵食（隨機餵食桃太郎糰子）？ \033[0;32m是(1)/否(0)\033[0m：");
				gui.outputArea.append(Color.BLACK, "   ? " + roles[curRole].getName() + " 要嘗試餵食（隨機餵食桃太郎糰子）？ \033[0;32m是(1)/否(0)\033[0m：\n");

				// 如果不挑戰，就標示起來
				if (input.nextInt() == 0) {
					skip[curRole] = true;
				} else {
					//System.out.print("   * " + roles[curRole].getName() +  " 挑戰餵食！（按 Enter 繼續）");
					gui.outputArea.append(Color.BLACK, "   * " + roles[curRole].getName() +  " 挑戰餵食！\n");
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
					//try { System.in.read(); } catch (Exception e) {}
					roles[curRole].setMoney(remainMoney);
					System.out.print("   * " + roles[curRole].getName() + " 花了錢買桃太郎糰子，剩 " + roles[curRole].getMoney() + " 元，");
					gui.outputArea.append(Color.BLACK, "   * " + roles[curRole].getName() + " 花了錢買桃太郎糰子，剩 " + roles[curRole].getMoney() + " 元，\n");

					// 把大家花的錢存起來，在打最後一隻王時才會返還該次全體費用作為Bonus					
					lastBonus += boss.peek().getCost();

					// 隨機產生 1-6 的數字
					dice = 1 + random.nextInt(6);
					System.out.println("餵了 " + dice + " 個桃太郎糰子。");
					gui.outputArea.append(Color.BLACK, "餵了 " + dice + " 個桃太郎糰子。\n");

					// 如果產生的數字超過要求就獲勝
					if (dice >= boss.peek().getRequirement()) {
						System.out.println(" ! "+ boss.peek().getName() + " 吃得好飽喔，好爽喔。");
						gui.outputArea.append(Color.BLACK, " ! "+ boss.peek().getName() + " 吃得好飽喔，好爽喔。\n");
						System.out.println(" ! " + boss.peek().getName() + " 跟著 " + roles[curRole].getName() + " 回家了，而且該玩家得到 " + boss.peek().getPoint() + " 分。\n");
						gui.outputArea.append(Color.BLACK, " ! " + boss.peek().getName() + " 跟著 " + roles[curRole].getName() + " 回家了，而且該玩家得到 " + boss.peek().getPoint() + " 分。\n\n");

						// 將寵物的分數加到玩家身上
						roles[curRole].addPoint(boss.peek().getPoint());

						// 移除那個 boss
						boss.pop();

						// 最後一隻Boss，可以額外獲得金幣
						if(boss.isEmpty()) {
							roles[curRole].addMoney(lastBonus);
							System.out.println(" ! 擊敗最後一隻boss可獲得額外 Bonus ，可以領取這次戰鬥大家繳納的金幣！ 總共是 " + lastBonus + " 枚金幣！");
							gui.outputArea.append(Color.BLACK, " ! 擊敗最後一隻boss可獲得額外 Bonus ，可以領取這次戰鬥大家繳納的金幣！ 總共是 " + lastBonus + " 枚金幣！\n");
							System.out.println(" $ 現在 " + roles[curRole].getName() + "身上有 " + roles[curRole].getMoney() + " 枚金幣！");
							gui.outputArea.append(Color.BLACK, " $ 現在 " + roles[curRole].getName() + "身上有 " + roles[curRole].getMoney() + " 枚金幣！\n");
						}

						System.out.println(" =================================================================");
						gui.outputArea.append(Color.BLACK, " =================================================================\n");
						System.out.println("");
						gui.outputArea.append(Color.BLACK, "\n");
						break;
					} else {
						System.out.println(" ! "+ boss.peek().getName() + " 只吃 " + dice + " 個桃太郎糰子不會被騙回家，而要 " + boss.peek().getRequirement() + " 個才夠。");
						gui.outputArea.append(Color.BLACK, " ! "+ boss.peek().getName() + " 只吃 " + dice + " 個桃太郎糰子不會被騙回家，而要 " + boss.peek().getRequirement() + " 個才夠。\n");
					}
				}
			}

			// 如果所有人都不打王，就跳過這個王
			if (allSkip(skip)) {
				System.out.println(" !  因為所有玩家都不餵食，所以 " + boss.peek().getName() + " 走掉了，奔向自由。");
				gui.outputArea.append(Color.BLACK, " !  因為所有玩家都不餵食，所以 " + boss.peek().getName() + " 走掉了，奔向自由。\n");
				System.out.println(" =================================================================");
				gui.outputArea.append(Color.BLACK, " =================================================================\n");
				System.out.println("");
				gui.outputArea.append(Color.BLACK, "\n");
				boss.pop();
				break;
			} else {
				// 如果下一個人已經被標示放棄，就再往後找下去
				do {
					nextRole = (++curRole) % 4;
				} while (skip[nextRole]);
				System.out.println(" . 輪到 " + roles[nextRole].getName() + " 挑戰。\n");
				gui.outputArea.append(Color.black, " . 輪到 " + roles[nextRole].getName() + " 挑戰。\n");
				curRole = nextRole;
			}
		}
	}

	// 在 fightBoss() 中幫助判斷是否所有玩家都跳過這個王
	private boolean allSkip(boolean[] skip) {
		for (int i = 0; i < 4; i++)
			if (!skip[i]) return false;
		return true;
	}

	private void title() {  
		gui.outputArea.append(Color.black, "___  ___                              _       ");
		gui.outputArea.append(Color.black, " _   _ _____ _   ___   _ \n");
		gui.outputArea.append(Color.black, "|  \\/  |                             | |      ");
		gui.outputArea.append(Color.black, "| \\ | /  __ \\ | / / | | |\n");
		gui.outputArea.append(Color.black, "| .  . | ___  _ __   ___  _ __   ___ | |_   _ ");
		gui.outputArea.append(Color.black, "|  \\| | /  \\/ |/ /| | | |\n");
		gui.outputArea.append(Color.black, "| |\\/| |/ _ \\| '_ \\ / _ \\| '_ \\ / _ \\| | | | |");
		gui.outputArea.append(Color.black, "| . ` | |   |    \\| | | |\n");
		gui.outputArea.append(Color.black, "| |  | | (_) | | | | (_) | |_) | (_) | | |_| |");
		gui.outputArea.append(Color.black, "| |\\  | \\__/\\ |\\  \\ |_| |\n");
		gui.outputArea.append(Color.black, "\\_|  |_/\\___/|_| |_|\\___/| .__/ \\___/|_|\\__, |");
		gui.outputArea.append(Color.black, "\\_| \\_/\\____|_| \\_/\\___/ \n");
		gui.outputArea.append(Color.black, "                         | |             __/ |\n");
		gui.outputArea.append(Color.black, "                         |_|            |___/ \n");
		// gui.outputArea.append(Color.black, " _   _ _____ _   ___   _ \n");
		// gui.outputArea.append(Color.black, "| \\ | /  __ \\ | / / | | |\n");
		// gui.outputArea.append(Color.black, "|  \\| | /  \\/ |/ /| | | |\n");
		// gui.outputArea.append(Color.black, "| . ` | |   |    \\| | | |\n");
		// gui.outputArea.append(Color.black, "| |\\  | \\__/\\ |\\  \\ |_| |\n");
		// gui.outputArea.append(Color.black, "\\_| \\_/\\____|_| \\_/\\___/ \n");
		gui.outputArea.append(Color.black, "                                                    ~~~~~~~~ [");
		gui.outputArea.append(Color.RED, "電");
		gui.outputArea.append(Color.ORANGE, "機");
		gui.outputArea.append(Color.GREEN, "漢");
		gui.outputArea.append(Color.CYAN, "化");
		gui.outputArea.append(Color.BLUE, "組");
		gui.outputArea.append(Color.black, "]");
		

	}
}
