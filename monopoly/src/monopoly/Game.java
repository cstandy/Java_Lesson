package monopoly;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.management.relation.RoleList;
import javax.swing.*;

public class Game {

	/* variables */
	private Map map;          // the map of the game
	private Stack<Boss> boss; // stack storing 6 boss
	private Role[] roles;     // list of 4 roles
	Random random = new Random(); //use for dice to random 1~6
	//Scanner input = new Scanner(System.in);
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
		try { Thread.sleep(500); } catch (InterruptedException e) {}
		//try { System.in.read(); } catch (Exception e) {}

		boss.push(new Boss("<系上同學>", 6, 1, 100, 6));
		// boss.push(new Boss("<阿國>",    5, 3, 50, 5));
		// boss.push(new Boss("<雲平狗>",   4, 2, 40, 4));
		// boss.push(new Boss("<大笨鳥>",   3, 2, 30, 4));
		// boss.push(new Boss("<普物龜>",   2, 1, 20, 3));
		// boss.push(new Boss("<松鼠>",    1, 1, 10, 3));



		System.out.println("");
		name = gameSignUp("<劍士>");
		roles[0] = new Role(name, "<劍士>", gui);
		name = gameSignUp("<法師>");
		roles[1] = new Role(name,  "<法師>", gui);
		name = gameSignUp("<弓手>");
		roles[2] = new Role(name,  "<弓手>", gui);
		name = gameSignUp("<盜賊>");
		roles[3] = new Role(name,   "<盜賊>", gui);
		System.out.println("");
		
		gui.refreshRole(roles);
		gui.refreshBoss(boss.peek(), false);
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

		while (true) {  
			/*if (!name.matches("[]*")) {
				System.out.println("   ! 姓名需由大寫開頭。");
				gui.outputArea.append(Color.black, "   ! 姓名需由大寫開頭。\n");
				System.out.print("   ! 請重新輸入：");
				gui.outputArea.append(Color.black, "   ! 請重新輸入：");
				// name = input.next();
				name = gui.signUp();
			} else */if (name.length() > 8) {
				System.out.println("   ! 姓名需在 8 個字元以內。");
				gui.outputArea.append(Color.black, "   ! 姓名需在 8 個字元以內。\n");
				System.out.print("   ! 請重新輸入：");
				gui.outputArea.append(Color.black, "   ! 請重新輸入：");
				// name = input.next();
				name = gui.signUp();
			}else if(nameUsed(name)) { // 玩家名字已存在
				gui.outputArea.append(Color.black, "   ! 姓名不得與其他玩家重複。\n");
				gui.outputArea.append(Color.black, "   ! 請重新輸入：");
				name = gui.signUp();
			}else if(name.length() < 1) {
				System.out.println("   ! 姓名需在 1 個字元以上。");
				gui.outputArea.append(Color.black, "   ! 姓名需在 1 個字元以上。\n");
				System.out.print("   ! 請重新輸入：");
				gui.outputArea.append(Color.black, "   ! 請重新輸入：");
				// name = input.next();
				name = gui.signUp();
			}
			else { // 合法名字則跳出 
				break;
			}
		}

		return name;
	}
	
	//  判斷名字有沒有被其他人用過
	private boolean nameUsed(String name) {
		for(int i = 0; i < 4; i++) {
			if(roles[i]!=null && roles[i].getName().equals(name)) {
				return true;
			}
		}
		return false;
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
				try { Thread.sleep(1000); } catch (InterruptedException e) {}
				gui.dice = true;
				gui.outputArea.append(Color.BLACK, " . 玩家 ");
				gui.outputArea.append(new Color(255,20,147), roles[i].getName());
				gui.outputArea.append(Color.BLACK, " 請按下 < 丟 骰 子 ! > 走路\n");
				// 等待按按鈕，如果輸入有值才會繼續
				while(this.gui.diceThrown == 0) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}
				System.out.println("   . 擲出了 " + gui.diceThrown + "。");
				gui.outputArea.append(Color.BLACK, "   . 擲出了 " + gui.diceThrown + "。\n");
				Block walkBlock = map.walk(roles, gui.diceThrown, i);
				//System.out.println("   $ 玩家 " + roles[i].getName()
				//		+ " 目前位於" + map.walk(roles, gui.diceThrown, i).getName()
				//		+ "，身上有 " + roles[i].getMoney() + " 枚金幣。");
				
				gui.outputArea.append(Color.BLACK, "   $ 玩家 ");
				gui.outputArea.append(new Color(255,20,147), roles[i].getName());
				gui.outputArea.append(Color.BLACK, " 目前位於");
				gui.outputArea.append(new Color(210,105,30), walkBlock.getName());
				gui.outputArea.append(Color.BLACK, "，身上有 " + roles[i].getMoney() + " 枚金幣。\n");
				gui.refreshRole(roles);
				gui.diceThrown = 0;
				gui.dice = false;
				
				//System.out.print(" . " + roles[i].getName() + " 使用能力骰子（按 Enter 繼續）：");
				//gui.outputArea.append(Color.BLACK, " . " + roles[i].getName() + " 使用能力骰子（按 Enter 繼續）：\n");
				//try { System.in.read(); } catch (Exception e) {}
				//input.nextLine();
				try { Thread.sleep(1000); } catch (InterruptedException e) {}
				gui.dice = true;
				gui.outputArea.append(Color.BLACK, " . 玩家 ");
				gui.outputArea.append(new Color(255,20,147), roles[i].getName());
				gui.outputArea.append(Color.BLACK, " 請按下 < 丟 骰 子 ! > 來發動能力\n");
				// 等待按按鈕，如果輸入有值才會繼續
				while(this.gui.diceThrown == 0) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}
				map.useAbility(roles, i, gui.diceThrown);
				gui.refreshRole(roles);
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
			gui.outputArea.append(Color.BLACK, "   # 擁有的土地有：\n");
			for (int j = 0; j < 32; j++) {
				if (map.blockList[j].getOwner() == roles[i].getName()) {
					System.out.println("     # " + map.blockList[j].getName() + ": " + (map.blockList[j].getPrice() * 10 + 10) + " 分。");
					gui.outputArea.append(Color.BLACK, "     # ");
					gui.outputArea.append(new Color(210,105,30), map.blockList[j].getName());
					gui.outputArea.append(Color.BLACK, ": " + (map.blockList[j].getPrice() * 10 + 10) + " 分。\n");
					score[i] += map.blockList[j].getPrice() * 10 + 10;
				}
			}

			// 求出最高分者的分數
			if (score[i] > winScore) winScore = score[i];

			System.out.println("   # " + roles[i].getName() + " 的最後分數為：\033[0;91m" + score[i] + " \033[0m分。");
			gui.outputArea.append(Color.BLACK, "   # ");
			gui.outputArea.append(new Color(255,20,147), roles[i].getName());
			gui.outputArea.append(Color.BLACK,  " 的最後分數為：");
			gui.outputArea.append(Color.RED, " " + score[i] + " ");
			gui.outputArea.append(Color.BLACK, "分。\n");
		}

		System.out.println("       _                         \r\n" + 
				"      (_)                        \r\n" + 
				" _ _ _ _ ____  ____   ____  ____ \r\n" + 
				"| | | | |  _ \\|  _ \\ / _  )/ ___)\r\n" + 
				"| | | | | | | | | | ( (/ /| |    \r\n" + 
				" \\____|_|_| |_|_| |_|\\____)_|    \r\n" + 
				"                                 ");
		gui.outputArea.append(new Color(255, 215, 0), "       _                         \r\n" + 
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
				gui.outputArea.append(Color.BLACK, " & ");
				gui.outputArea.append(new Color(255,20,147), roles[i].getName());
			}
		}
		
		gui.outputArea.append(new Color(102, 205, 0), "  ==================== Thank you for playing ===================  \n\n");
		
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
		gui.outputArea.append(Color.BLACK, " ! BOSS 討伐戰：");
		gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
		gui.outputArea.append(Color.BLACK, " 遇到了生物 No." + boss.peek().getOrder() + "：小 ");
		gui.outputArea.append(Color.RED, boss.peek().getName() + "\n");
		gui.refreshBoss(boss.peek(), true);
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
			gui.outputArea.append(Color.BLACK, "   $ ");
			gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
			gui.outputArea.append(Color.BLACK," 目前有 " + roles[curRole].getMoney() + " 枚金幣。\n");

			// 如果錢不夠，就不能挑戰
			if (remainMoney < 0) {
				System.out.println("   * " + roles[curRole].getName() + " 太窮了，動物都不理你！狗不理！");
				gui.outputArea.append(Color.BLACK, "   * ");
				gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
				gui.outputArea.append(Color.BLACK," 太窮了，動物都不理你！狗不理！\n");
				skip[curRole] = true;
			} else { // 錢夠挑戰才詢問
				System.out.println("   ! " + boss.peek().getName() + " 如果吃 " + boss.peek().getRequirement() + " 個桃太郎糰子，就會跟你回家。");
				gui.outputArea.append(Color.BLACK, "   ! ");
				gui.outputArea.append(Color.RED, boss.peek().getName());
				gui.outputArea.append(Color.BLACK, " 如果吃 " + boss.peek().getRequirement() + " 個桃太郎糰子，就會跟你回家。\n");
				System.out.print("   ? " + roles[curRole].getName() + " 要嘗試餵食（隨機餵食桃太郎糰子）？ \033[0;32m是(1)/否(0)\033[0m：");
				gui.outputArea.append(Color.BLACK, "   ? ");
				gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
				gui.outputArea.append(Color.BLACK, " 要嘗試餵食（隨機餵食桃太郎糰子）？");
				gui.outputArea.append(new Color(0,100,0), "是(1)/否(0)");

				// 如果不挑戰，就標示起來
				//if (input.nextInt() == 0) {
				if(gui.getDecision(0, 1) == 0) {
					skip[curRole] = true;
				} else {
					//System.out.print("   * " + roles[curRole].getName() +  " 挑戰餵食！（按 Enter 繼續）");
					gui.outputArea.append(Color.BLACK, "   * ");
					gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
					gui.outputArea.append(Color.BLACK, " 挑戰餵食！\n");
					//try { System.in.read(); } catch (Exception e) {}
					roles[curRole].setMoney(remainMoney);
					System.out.print("   * " + roles[curRole].getName() + " 花了錢買桃太郎糰子，剩 " + roles[curRole].getMoney() + " 元，");
					gui.outputArea.append(Color.BLACK, "   * ");
					gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
					gui.outputArea.append(Color.BLACK, " 花了錢買桃太郎糰子，剩 " + roles[curRole].getMoney() + " 元，\n");
					gui.refreshRole(roles);
					
					// 把大家花的錢存起來，在打最後一隻王時才會返還該次全體費用作為Bonus					
					lastBonus += boss.peek().getCost();

					// 隨機產生 1-6 的數字
					gui.dice = true;
					gui.outputArea.append(Color.BLACK, " . 玩家 ");
					gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
					gui.outputArea.append(Color.BLACK, " 請按下 < 丟 骰 子 ! > 來餵食\n");
					// 等待按按鈕，如果輸入有值才會繼續
					while(this.gui.diceThrown == 0) {
						try { Thread.sleep(1000); } catch (InterruptedException e) {}
					}
					System.out.println("餵了 " + gui.diceThrown + " 個桃太郎糰子。");
					gui.outputArea.append(Color.BLACK, "   * 餵了 " + gui.diceThrown + " 個桃太郎糰子。\n");
					dice = gui.diceThrown;
					gui.diceThrown = 0;
					gui.dice = false;

					// 如果產生的數字超過要求就獲勝
					if (dice >= boss.peek().getRequirement()) {
						System.out.println("   ! "+ boss.peek().getName() + " 吃得好飽喔，好爽喔。");
						gui.outputArea.append(Color.BLACK, " ! ");
						gui.outputArea.append(Color.RED, boss.peek().getName());
						gui.outputArea.append(Color.BLACK, " 吃得好飽喔，好爽喔。\n");
						System.out.println("   ! " + boss.peek().getName() + " 跟著 " + roles[curRole].getName() + " 回家了，而且該玩家得到 " + boss.peek().getPoint() + " 分。\n");
						gui.outputArea.append(Color.BLACK, " ! ");
						gui.outputArea.append(Color.RED, boss.peek().getName());
						gui.outputArea.append(Color.BLACK, " 跟著 ");
						gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
						gui.outputArea.append(Color.BLACK, " 回家了，而且該玩家得到 " + boss.peek().getPoint() + " 分。\n\n");

						// 將寵物的分數加到玩家身上
						roles[curRole].addPoint(boss.peek().getPoint());
						gui.refreshRole(roles);

						// 移除那個 boss
						boss.pop();
						if(!boss.isEmpty()) {gui.refreshBoss(boss.peek(), false);}
						

						// 最後一隻Boss，可以額外獲得金幣
						if(boss.isEmpty()) {
							roles[curRole].addMoney(lastBonus);
							System.out.println(" ! 擊敗最後一隻boss可獲得額外 Bonus ，可以領取這次戰鬥大家繳納的金幣！ 總共是 " + lastBonus + " 枚金幣！");
							gui.outputArea.append(Color.BLACK, " ! 擊敗最後一隻boss可獲得額外 Bonus ，可以領取這次戰鬥大家繳納的金幣！ 總共是 " + lastBonus + " 枚金幣！\n");
							System.out.println(" $ 現在 " + roles[curRole].getName() + "身上有 " + roles[curRole].getMoney() + " 枚金幣！");
							gui.outputArea.append(Color.BLACK, " $ 現在 ");
							gui.outputArea.append(new Color(255,20,147), roles[curRole].getName());
							gui.outputArea.append(Color.BLACK, "身上有 " + roles[curRole].getMoney() + " 枚金幣！\n");
							gui.refreshRole(roles);
						}

						System.out.println(" =================================================================");
						gui.outputArea.append(Color.BLACK, " =================================================================\n");
						System.out.println("");
						gui.outputArea.append(Color.BLACK, "\n");
						break;
					} else {
						System.out.println(" ! "+ boss.peek().getName() + " 只吃 " + dice + " 個桃太郎糰子不會被騙回家，而要 " + boss.peek().getRequirement() + " 個才夠。");
						gui.outputArea.append(Color.BLACK, " ! ");
						gui.outputArea.append(Color.RED, boss.peek().getName());
						gui.outputArea.append(Color.BLACK, " 只吃 " + dice + " 個桃太郎糰子不會被騙回家，而要 " + boss.peek().getRequirement() + " 個才夠。\n");
						try { Thread.sleep(2000); } catch (InterruptedException e) {}
					}
				}
			}

			// 如果所有人都不打王，就跳過這個王
			if (allSkip(skip)) {
				System.out.println(" !  因為所有玩家都不餵食，所以 " + boss.peek().getName() + " 走掉了，奔向自由。");
				gui.outputArea.append(Color.BLACK, " !  因為所有玩家都不餵食，所以 ");
				gui.outputArea.append(Color.RED, boss.peek().getName());
				gui.outputArea.append(Color.BLACK, " 走掉了，奔向自由。\n");
				System.out.println(" =================================================================");
				gui.outputArea.append(Color.BLACK, " =================================================================\n");
				System.out.println("");
				gui.outputArea.append(Color.BLACK, "\n");
				boss.pop();
				gui.refreshBoss(boss.peek(), false);
				break;
			} else {
				// 如果下一個人已經被標示放棄，就再往後找下去
				do {
					nextRole = (++curRole) % 4;
				} while (skip[nextRole]);
				System.out.println(" . 輪到 " + roles[nextRole].getName() + " 挑戰。\n");
				gui.outputArea.append(Color.BLACK, " . 輪到 ");
				gui.outputArea.append(new Color(255,20,147), roles[nextRole].getName());
				gui.outputArea.append(Color.BLACK, " 挑戰。\n");
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
		gui.outputArea.append(new Color(205,38,38), " _   _ _____ _   ___   _ \n");
		gui.outputArea.append(Color.black, "|  \\/  |                             | |      ");
		gui.outputArea.append(new Color(205,38,38), "| \\ | /  __ \\ | / / | | |\n");
		gui.outputArea.append(Color.black, "| .  . | ___  _ __   ___  _ __   ___ | |_   _ ");
		gui.outputArea.append(new Color(205,38,38), "|  \\| | /  \\/ |/ /| | | |\n");
		gui.outputArea.append(Color.black, "| |\\/| |/ _ \\| '_ \\ / _ \\| '_ \\ / _ \\| | | | |");
		gui.outputArea.append(new Color(205,38,38), "| . ` | |   |    \\| | | |\n");
		gui.outputArea.append(Color.black, "| |  | | (_) | | | | (_) | |_) | (_) | | |_| |");
		gui.outputArea.append(new Color(205,38,38), "| |\\  | \\__/\\ |\\  \\ |_| |\n");
		gui.outputArea.append(Color.black, "\\_|  |_/\\___/|_| |_|\\___/| .__/ \\___/|_|\\__, |");
		gui.outputArea.append(new Color(205,38,38), "\\_| \\_/\\____|_| \\_/\\___/ \n");
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
		gui.outputArea.append(Color.black, "]\n");
		
		/*
		gui.outputArea.append(Color.black, "  ==================================================================  \n\n");
		
		gui.outputArea.append(Color.black, " . 遊戲面板：中央為資訊輸出區，右側為角色資訊欄，右下為 BOSS 資訊欄\n\n");
		
		gui.outputArea.append(Color.black, " . 操作說明：下方有 3 個方塊\n");
		gui.outputArea.append(Color.black, "           * 左邊為輸入區，須先移動鼠標至此，後輸入名字或數字或 Enter 繼續\n");
		gui.outputArea.append(Color.black, "           * 中間為丟骰子的按鈕，以滑鼠左鍵點擊\n");
		gui.outputArea.append(Color.black, "           * 右邊會顯示骰子的數值\n\n");
		gui.outputArea.append(Color.black, "  __________________________<按 ENTER 繼續>__________________________  \n");
		gui.signUp();
		
		gui.outputArea.append(Color.black, " . 遊戲類型：本遊戲為融合了各種元素的大富翁，角色擁有職業技能，適合 4 人遊玩\n");
		gui.outputArea.append(Color.black, "          （當然也可以自己玩，不過太邊緣了，請多交朋友）\n\n");
		gui.outputArea.append(Color.black, "  __________________________<按 ENTER 繼續>__________________________  \n");
		gui.signUp();
		
		gui.outputArea.append(Color.black, " . 遊戲流程：玩家每回合有投擲 1 次移動骰子和 1 次能力骰子的機會\n");
		gui.outputArea.append(Color.black, "           1. 先丟骰子決定移動距離\n");
		gui.outputArea.append(Color.black, "           2. 移動到該格子，沿途可以撿走路上的金幣，然後執行目的地那一格的規則\n");
		gui.outputArea.append(Color.black, "              * 一般土地（可買賣，收租賺個錢）\n");
		gui.outputArea.append(Color.black, "              * 傳送門（可以移動到下一個最近的傳送門）\n");
		gui.outputArea.append(Color.black, "              * 提款機（可領個錢，耍個廢）\n");
		gui.outputArea.append(Color.black, "              * 陷阱（會被戳，很痛，會噴個 2 枚金幣）\n");
		gui.outputArea.append(Color.black, "              * 超級星星（可以發動職業技能，很有用的東西）\n");
		gui.outputArea.append(Color.black, "              * 什麼事都沒有，浪費人生的格子\n");
		gui.outputArea.append(Color.black, "           3. 再次丟骰子決定使用什麼特殊能力\n");
		gui.outputArea.append(Color.black, "              * 錢錢錢 - 跟銀行領錢錢\n");
		gui.outputArea.append(Color.black, "              * 小烏賊 - 跟別人偷 2 枚金幣\n");
		gui.outputArea.append(Color.black, "              * 綠龜殼 - 打前面人家臉，讓他嚇到掉 3 枚金幣\n");
		gui.outputArea.append(Color.black, "              * 紅龜殼 - 打後面人家臉，讓他嚇到掉 3 枚金幣\n");
		gui.outputArea.append(Color.black, "              * 碰！！ - 打大家的臉，大家嚇到掉 1 枚金幣\n");
		gui.outputArea.append(Color.black, "           4. 如果通過起點，則會翻開一張 BOSS 牌，進入戰鬥\n");
		gui.outputArea.append(Color.black, "           5. BOSS 戰由經過起點之玩家開始，依序挑戰直到有人成功或全員放棄\n");
		gui.outputArea.append(Color.black, "           6. 換進入 BOSS 戰前的下一位玩家，重複執行到最後一隻 BOSS 被打敗為止\n\n");
		gui.outputArea.append(Color.black, "  __________________________<按 ENTER 繼續>__________________________  \n");
		gui.signUp();

		gui.outputArea.append(Color.black, " . 遊戲目的：獲得最高分數的玩家獲勝\n");
		gui.outputArea.append(Color.black, "           * 其中地產可獲得地租＊10 + 10分\n");
		gui.outputArea.append(Color.black, "           * 金幣每 5 元計 10分，未滿則不計分\n");
		gui.outputArea.append(Color.black, "           * 打敗 BOSS 會有另外的分數，請多參與戰鬥\n");
		gui.outputArea.append(Color.black, "           * 打敗最後一隻 BOSS 可額外獲得該次戰鬥投入的所有金幣\n\n");
		gui.outputArea.append(Color.black, "  __________________________<按 ENTER 繼續>__________________________  \n");
		gui.signUp();
		gui.outputArea.append(Color.black, "\n\n  ===================   S    T    A     R     T   ==================  \n");
		
		*/
		
		gui.outputArea.append(Color.black, "  =====================================================================  \n\n");

		gui.outputArea.append(Color.black, " . 遊戲面板：中央為");
		gui.outputArea.append(new Color(30, 144, 255), "資訊輸出區");
		gui.outputArea.append(Color.black, "，右側為");
		gui.outputArea.append(new Color(30, 144, 255), "角色資訊欄");
		gui.outputArea.append(Color.black, "，右下為 ");
		gui.outputArea.append(new Color(30, 144, 255), "BOSS 資訊欄\n\n");
		
		gui.outputArea.append(Color.black, " . 操作說明：資訊輸出欄下方有 3 個小區塊\n");
		gui.outputArea.append(Color.black, "           * 左邊為");
		gui.outputArea.append(new Color(30, 144, 255), "輸入區");
		gui.outputArea.append(Color.black, "，須先移動鼠標至此，後");
		gui.outputArea.append(new Color(30, 144, 255), "輸入名字或數字或 Enter 繼續\n");
		gui.outputArea.append(Color.black, "           * 中間為丟骰子的按鈕，以");
		gui.outputArea.append(new Color(30, 144, 255), "滑鼠左鍵點擊\n");
		gui.outputArea.append(Color.black, "           * 右邊會顯示骰子的數值\n\n");
		
		gui.outputArea.append(new Color(154, 205, 50),  "  ___________________________<按 ENTER 繼續>___________________________  \n");
		gui.signUp();
		
		gui.outputArea.append(Color.black, " . 遊戲類型：本遊戲為融合了各種元素的大富翁，角色擁有");
		gui.outputArea.append(new Color(30, 144, 255), "職業技能");
		gui.outputArea.append(Color.black, "，適合 4 人遊玩\n");
		gui.outputArea.append(Color.black, "          （當然也可以自己玩，不過太邊緣了，請多交朋友）\n\n");
		gui.outputArea.append(new Color(154, 205, 50), "  ___________________________<按 ENTER 繼續>___________________________  \n");
		gui.signUp();
		
		gui.outputArea.append(Color.black, " . 遊戲流程：玩家每回合有投擲 1 次");
		gui.outputArea.append(new Color(30, 144, 255), "移動骰子");
		gui.outputArea.append(Color.black, "和 1 次");
		gui.outputArea.append(new Color(30, 144, 255), "能力骰子");
		gui.outputArea.append(Color.black, "的機會\n");
		gui.outputArea.append(Color.black, "           1. 先丟骰子決定移動距離\n");
		gui.outputArea.append(Color.black, "           2. 移動到該格子，沿途可以撿走路上的金幣，然後執行目的地那一格的規則\n");
		gui.outputArea.append(Color.black, "              * 一般土地（可買賣，收租賺個錢）\n");
		gui.outputArea.append(Color.black, "              * 傳送門（可以移動到下一個最近的傳送門）\n");
		gui.outputArea.append(Color.black, "              * 提款機（可領個錢，耍個廢）\n");
		gui.outputArea.append(Color.black, "              * 陷阱（會被戳，很痛，會噴個 2 枚金幣）\n");
		gui.outputArea.append(Color.black, "              * 超級星星（可以發動職業技能，很有用的東西）\n");
		gui.outputArea.append(Color.black, "              * 什麼事都沒有，浪費人生的格子\n");
		gui.outputArea.append(Color.black, "           3. 再次丟骰子決定使用什麼特殊能力\n");
		gui.outputArea.append(Color.black, "              * 錢錢錢 - 跟銀行領錢錢\n");
		gui.outputArea.append(Color.black, "              * 小烏賊 - 跟別人偷 2 枚金幣\n");
		gui.outputArea.append(Color.black, "              * 綠龜殼 - 打前面人家臉，讓他嚇到掉 3 枚金幣\n");
		gui.outputArea.append(Color.black, "              * 紅龜殼 - 打後面人家臉，讓他嚇到掉 3 枚金幣\n");
		gui.outputArea.append(Color.black, "              * 碰！！ - 打大家的臉，大家嚇到掉 1 枚金幣\n");
		gui.outputArea.append(Color.black, "           4. 如果通過起點，則會");
		gui.outputArea.append(new Color(30, 144, 255), "翻開一張 BOSS 牌");
		gui.outputArea.append(Color.black, "，進入戰鬥\n");
		gui.outputArea.append(Color.black, "           5. BOSS 戰由經過起點之玩家開始，依序挑戰直到有人成功或全員放棄\n");
		gui.outputArea.append(Color.black, "           6. 換進入 BOSS 戰前的下一位玩家，重複執行");
		gui.outputArea.append(new Color(30, 144, 255), "到最後一隻 BOSS 被打敗為止\n\n");
		gui.outputArea.append(new Color(154, 205, 50), "  ___________________________<按 ENTER 繼續>___________________________  \n");
		gui.signUp();
		
		
		gui.outputArea.append(Color.black, " . 遊戲目的：獲得");
		gui.outputArea.append(new Color(30, 144, 255), "最高分數的玩家獲勝\n");
		gui.outputArea.append(Color.black, "           * 其中地產可獲得地租＊10 + 10分\n");
		gui.outputArea.append(Color.black, "           * 金幣每 5 元計 10分，未滿則不計分\n");
		gui.outputArea.append(Color.black, "           * 打敗 BOSS 會有另外的分數，請多參與戰鬥\n");
		gui.outputArea.append(Color.black, "           * 打敗最後一隻 BOSS 可額外獲得該次戰鬥投入的所有金幣\n\n");
		gui.outputArea.append(new Color(154, 205, 50), "  ___________________________<按 ENTER 繼續>___________________________  \n");
		
		gui.signUp();
		
		gui.outputArea.append(Color.black, "  =========================");
		gui.outputArea.append(Color.red, "   S  T  A  R  T   ");
		gui.outputArea.append(Color.black, "========================  \n\n");
		
		
		
		
		
	}
}
