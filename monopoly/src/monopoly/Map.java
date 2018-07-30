package monopoly;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

public class Map {
	/* variables */
	Block[] blockList = new Block[32];
	Scanner mapInput = new Scanner(System.in);
	Random random = new Random(); // use for dice to random 1~6
	GuiDesign gui;

	/* methods */

	/**
	 * @brief The Constructor of the class.
	 */
	public Map(GuiDesign gui) {
		this.gui = gui;
		blockList[0]  = new Block("[世界起源]",           	true,  false, 0, 0);
		blockList[1]  = new Block("[遊俠地下道]",	false, true,  1, 1);
		blockList[2]  = new Block("[傳送門]",            	false, false, 0, 0);
		blockList[3]  = new Block("[後站大遠百]",           	false, true,  1, 1);
		blockList[4]  = new Block("[提款機]",              false, false, 0, 0);
		blockList[5]  = new Block("[消失的牛車伯]",          	false, true,  1, 2);
		blockList[6]  = new Block("[超級星星]",               false, false, 0, 0);
		blockList[7]  = new Block("[育樂街紅樓]",            false, true,  1, 2);
		blockList[8]  = new Block("[光口]",               true,  false, 0, 0);
		blockList[9]  = new Block("[勝利早點]",             	false, true,  2, 3);
		blockList[10] = new Block("[傳送門]",               false, false, 0, 0);
		blockList[11] = new Block("[老邱五金行]",                	false, true,  2, 3);
		blockList[12] = new Block("[陷阱]",               false, false, 0, 0);
		blockList[13] = new Block("[九乘九]",               false, true,  2, 4);
		blockList[14] = new Block("[超級星星]",               false, false, 0, 0);
		blockList[15] = new Block("[21世紀風味館]",         	false, true,  2, 4);
		blockList[16] = new Block("[D-24]",               true,  false, 0, 0);
		blockList[17] = new Block("[電機三館麥當勞]",         false, true,  3, 5);
		blockList[18] = new Block("[傳送門]",               false, false, 0, 0);
		blockList[19] = new Block("[7-11大學店]",           false, true,  3, 5);
		blockList[20] = new Block("[提款機]",              false, false, 0, 0);
		blockList[21] = new Block("[四海遊龍]",      false, true,  3, 6);
		blockList[22] = new Block("[超級星星]",               false, false, 0, 0);
		blockList[23] = new Block("[錢鼠]",                	false, true,  3, 6);
		blockList[24] = new Block("[勝後]",             true,  false, 0, 0);
		blockList[25] = new Block("[電機本館]",             	false, true,  4, 7);
		blockList[26] = new Block("[傳送門]",               false, false, 0, 0);
		blockList[27] = new Block("[奇美樓]",               false, true,  4, 7);
		blockList[28] = new Block("[陷阱]",               false, false, 0, 0);
		blockList[29] = new Block("[東安路迷客夏]",          	false, true,  5, 8);
		blockList[30] = new Block("[超級星星]",               false, false, 0, 0);
		blockList[31] = new Block("[世界彼端伊都]",         	false, true,  5, 8);

		gui.refreshBlock(blockList);
	}

	/**
	 * @param input
	 * @return reference of next block 
	 */
	public Block nextBlock(int input) {return blockList[(input+1)%32];}

	/**
	 * @return true->price*2	false->price*1
	 */
	public boolean ownCouple(int input) {
		if     (blockList[input].getOwner() == blockList[(input+2) %32].getOwner())	return true;
		else if(blockList[input].getOwner() == blockList[(input+30)%32].getOwner())	return true;
		else	return false;
	}

	/**
	 * @brief 一步一步走，每步都看有沒有錢可以拿，走到底之後看是不是水管，是的話就從下一個水管出去並且把中間的錢都拿走
	 * @parame place:		原本位置
	 * @parame roleList:	所有人的資料
	 * @parame movement:	要走幾步
	 */
	public Block walk(Role[] roleList, int movement, int nowRole) {
		gui.diceThrown = 0;
		gui.dice = false;
		
		int pickedMoney = 0;
		
		// 一步一步走（要看撿錢）
		for(int a=0; a < movement; a++) {
			roleList[nowRole].moveOn(nowRole);
			gui.refreshBlock(blockList);
			// 撿錢
			pickedMoney += blockList[roleList[nowRole].getPosition()].pickMoney();
		}
		
		System.out.println("   $ 你獲得了沿路上的 " + pickedMoney + " 枚金幣。");
		gui.outputArea.append(Color.BLACK, "   $ 你獲得了沿路上的 " + pickedMoney + " 枚金幣。\n");
		
		roleList[nowRole].addMoney(pickedMoney);
		gui.refreshRole(roleList);
		gui.refreshBlock(blockList);
		this.event(roleList, nowRole);
		return blockList[roleList[nowRole].getPosition()];
	}

	/**
	 * @brief 踩到各種地會發生的事件
	 * @param roleList 所有的玩家
	 * @param nowRole  現在操作的玩家
	 */
	private void event(Role[] roleList, int nowRole) {
		if(blockList[roleList[nowRole].getPosition()].getNormal() == true) { // 如果是踩到一般地
			normalEvent(roleList, nowRole);
			gui.refreshRole(roleList);
			gui.refreshBlock(blockList);
		}else { // 特殊地再細分
			if(blockList[roleList[nowRole].getPosition()].getName().equals("[傳送門]")) { // Tube 水管
				int moneyPick = 0;
				for(int a = 1; a <= 8; a++) { // 走8步撿錢
					roleList[nowRole].moveOn(nowRole);
					gui.refreshBlock(blockList);
					moneyPick += blockList[roleList[nowRole].getPosition()].pickMoney();
				}
				
				roleList[nowRole].addMoney(moneyPick);
				
				System.out.println("   $ " + roleList[nowRole].getName()
						+ " 經過了水管，沿途撿走了 " + moneyPick
						+ " 枚金幣，現在有 " + roleList[nowRole].getMoney() + " 枚金幣了。");
				gui.outputArea.append(Color.BLACK, "   $ ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, " 經過了傳送門，沿途撿走了 " + moneyPick
						+ " 枚金幣，現在有 " + roleList[nowRole].getMoney() + " 枚金幣了。\n");
				gui.refreshRole(roleList);
				gui.refreshBlock(blockList);
			}
			else if(blockList[roleList[nowRole].getPosition()].getName().equals("[陷阱]")){ // Trap 陷阱石頭
				int drop = dropMoney(roleList[nowRole], 2);
				
				blockList[roleList[nowRole].getPosition()].setMoneyDropped(blockList[roleList[nowRole].getPosition()].getMoneyDropped() + drop);
				
				System.out.println("   ! " + roleList[nowRole].getName()
						+ " 被陷阱戳到了一下，掉落 " + drop + " 枚金幣在"
						+ blockList[roleList[nowRole].getPosition()].getName() + "上。");
				System.out.println("   $ 現在" + blockList[roleList[nowRole].getPosition()].getName()
						+ "上有 " + blockList[roleList[nowRole].getPosition()].getMoneyDropped()
						+ " 枚金幣了，而 " + roleList[nowRole].getName() + " 剩 "
						+ roleList[nowRole].getMoney() + " 枚金幣。");
			
				gui.outputArea.append(Color.BLACK, "   ! ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK," 被陷阱戳到了一下，掉落 " + drop + " 枚金幣在"
						+ blockList[roleList[nowRole].getPosition()].getName() + "上。\n");
				gui.outputArea.append(Color.BLACK, "   $ 現在");
				gui.outputArea.append(new Color(210,105,30), blockList[roleList[nowRole].getPosition()].getName());
				gui.outputArea.append(Color.BLACK, "上有 " + blockList[roleList[nowRole].getPosition()].getMoneyDropped() + " 枚金幣了，而 ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, " 剩 " + roleList[nowRole].getMoney() + " 枚金幣。\n");
				gui.refreshRole(roleList);
				gui.refreshBlock(blockList);
			}
			else if(blockList[roleList[nowRole].getPosition()].getName().equals("[提款機]")){ // Money 提款機
				//int moneyBlock = 1 + random.nextInt(6); // 隨機 1 + 0~5 = 1~6
				
				System.out.print("   $ " + roleList[nowRole].getName() + " 走到了提款機，可以丟骰子拿錢：");
				try { Thread.sleep(1000); } catch (InterruptedException e) {}
				//try { System.in.read(); } catch (Exception e) {}
				//try { System.in.read(); } catch (Exception e) {}
				
				gui.outputArea.append(Color.BLACK, "   $ ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, " 走到了提款機，可以丟骰子拿錢：\n");
				gui.dice = true;
				gui.outputArea.append(Color.BLACK, "     * 玩家 ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, " 請按下 Throw Dice\n");
				// 等待按按鈕，如果輸入有值才會繼續
				while(this.gui.diceThrown == 0) {
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}
				System.out.println("     * " + roleList[nowRole].getName() + " 丟出了 " + gui.diceThrown + "。");
				System.out.println("     $ " + roleList[nowRole].getName() + " 可以跟銀行領取 " + gui.diceThrown + " 枚金幣，真幸運！");
				
				gui.outputArea.append(Color.BLACK, "     * ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, " 丟出了 " + gui.diceThrown + "。\n");
				gui.outputArea.append(Color.BLACK, "     $ ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, " 可以跟銀行領取 " + gui.diceThrown + " 枚金幣，真幸運！\n");
				
				roleList[nowRole].addMoney(gui.diceThrown);
				gui.refreshRole(roleList);
				gui.refreshBlock(blockList);
				
				gui.diceThrown = 0;
				gui.dice = false;
			}
			else if (blockList[roleList[nowRole].getPosition()].getName().equals("[超級星星]")) {
				switch (roleList[nowRole].getCareer()) {
				case "<劍士>":
					System.out.println("   & " + roleList[nowRole].getCareer() + "的星星能力：向每個人收 2 枚金幣。");
					gui.outputArea.append(Color.BLACK, "   & " + roleList[nowRole].getCareer() + "的星星能力：向每個人收 2 枚金幣。\n");
					
					for (int i = 0; i < 4; i++) {
						if (i != nowRole) giveMoney(roleList[i], roleList[nowRole], 2);
					}
					gui.refreshRole(roleList);
					gui.refreshBlock(blockList);
					break;
				case "<法師>":
					System.out.println("   & " + roleList[nowRole].getCareer() + "的星星能力：收集全部地上所有的錢。");
					gui.outputArea.append(Color.BLACK, "   & " + roleList[nowRole].getCareer() + "的星星能力：收集全部地上所有的錢。\n");
					
					int pickedMoney = 0;
					
					// 一步一步走（要看撿錢）
					for(int i = 0; i < 32; i++) {
						roleList[nowRole].moveOn(nowRole);
						
						// 撿錢
						pickedMoney += blockList[roleList[nowRole].getPosition()].pickMoney();
					}
					
					System.out.println("     $ 你獲得了沿路上的 " + pickedMoney + " 枚金幣。");
					gui.outputArea.append(Color.BLACK, "     $ 你獲得了沿路上的 " + pickedMoney + " 枚金幣。\n");

					roleList[nowRole].addMoney(pickedMoney);
					gui.refreshRole(roleList);
					gui.refreshBlock(blockList);
					break;
				case "<弓手>":
					System.out.println("   & " + roleList[nowRole].getCareer() + "的星星能力：向所有自己的地盤收租。");
					gui.outputArea.append(Color.BLACK, "   & " + roleList[nowRole].getCareer() + "的星星能力：向所有自己的地盤收租。\n");
					
					int rentMoney = 0;
					
					for (int i = 0; i < 32; i++) {
						if (blockList[i].getOwner().equals(roleList[nowRole].getName())) {
							rentMoney += blockList[i].getPrice();
							System.out.println("       + " + blockList[i].getName() + ": " + blockList[i].getPrice() + " 枚金幣。");
							gui.outputArea.append(Color.BLACK, "       + ");
							gui.outputArea.append(new Color(210,105,30), blockList[i].getName());
							gui.outputArea.append(Color.BLACK, ": " + blockList[i].getPrice() + " 枚金幣。\n");
						}
					}
					
					System.out.println("     $ 你向所有房地產收租，共有 " + rentMoney + " 枚金幣。");
					gui.outputArea.append(Color.BLACK, "     $ 你向所有房地產收租，共有 " + rentMoney + " 枚金幣。\n");
					
					roleList[nowRole].addMoney(rentMoney);
					gui.refreshRole(roleList);
					gui.refreshBlock(blockList);
					break;
				case "<盜賊>":
					System.out.print("   & " + roleList[nowRole].getCareer() + "的星星能力：再丟一次骰子並獲得該值 + 5 枚金幣。");
					gui.outputArea.append(Color.BLACK, "   & " + roleList[nowRole].getCareer()
							+ "的星星能力：再丟一次骰子並獲得該值 + 5 枚金幣。\n");
					gui.dice = true;
					gui.outputArea.append(Color.BLACK, " . 玩家 ");
					gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
					gui.outputArea.append(Color.BLACK, " 請按下Throw Dice\n");
					// 等待按按鈕，如果輸入有值才會繼續
					while(this.gui.diceThrown == 0) {
						try { Thread.sleep(1000); } catch (InterruptedException e) {}
					}
					roleList[nowRole].addMoney(gui.diceThrown + 5);
					gui.refreshRole(roleList);
					gui.refreshBlock(blockList);
					System.out.println("     $ 你擲出了 " + gui.diceThrown + "，可獲得 " + (gui.diceThrown + 5) + " 枚金幣。");
					gui.outputArea.append(Color.BLACK, "     $ 你擲出了 " + gui.diceThrown + "，可獲得 " + (gui.diceThrown + 5) + " 枚金幣。\n");
					gui.diceThrown = 0;
					gui.dice = false;
					break;
				default: break;
				}
			} 
			else { // 其他暫時沒事做
				System.out.println("   * " + roleList[nowRole].getName() + " 在這一回合裡什麼特別的事也沒發生呢。");
				gui.outputArea.append(Color.BLACK, "   * ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, " 在這一回合裡什麼特別的事也沒發生呢。\n");
			}
		}
	}
	
	private void normalEvent(Role[] roleList, int nowRole) {
		// 沒有人的
		if(blockList[roleList[nowRole].getPosition()].getOwner() == "") {
			// 錢夠
			if(blockList[roleList[nowRole].getPosition()].getPrice() <= roleList[nowRole].getMoney()) {
				System.out.println("   * " + roleList[nowRole].getName() + "，你到了一塊無主地"
						+ blockList[roleList[nowRole].getPosition()].getName() + "，現在買只要 "
						+ blockList[roleList[nowRole].getPosition()].getPrice() + " 枚金幣。");
				System.out.println("     ? 你現在身上有 " + roleList[nowRole].getMoney()
						+ " 枚金幣，要買嗎？");
				System.out.print("     ? \033[0;32m是(1)/否(0)\033[0m：");
				
				gui.outputArea.append(Color.BLACK, "   * ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, "，你到了一塊無主地");
				gui.outputArea.append(new Color(210,105,30), blockList[roleList[nowRole].getPosition()].getName());
				gui.outputArea.append(Color.BLACK, "，現在買只要 " + blockList[roleList[nowRole].getPosition()].getPrice() + " 枚金幣。\n");
				gui.outputArea.append(Color.BLACK,"     ? 你現在身上有 " + roleList[nowRole].getMoney()
						+ " 枚金幣，要買嗎？\n");
				gui.outputArea.append(Color.BLACK, "     ? ");
				gui.outputArea.append(Color.GREEN, "是(1)/否(0)");
				gui.outputArea.append(Color.BLACK, "：");
				
				// 買
				// if(mapInput.nextInt() == 1) {
				if(gui.getDecision() == 1) {
					roleList[nowRole].setMoney(roleList[nowRole].getMoney() - blockList[roleList[nowRole].getPosition()].getPrice()); // 付錢
					blockList[roleList[nowRole].getPosition()].setOwner(roleList[nowRole].getName()); // 得到土地（owner
					roleList[nowRole].setBlockNumber(true); // 增加土地數量 + 1
					System.out.println("   * " + roleList[nowRole].getName() + " 買到了"
							+ blockList[roleList[nowRole].getPosition()].getName() + "。");
					gui.outputArea.append(Color.BLACK, "   * ");
					gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
					gui.outputArea.append(Color.BLACK, " 買到了");
					gui.outputArea.append(new Color(210,105,30), blockList[roleList[nowRole].getPosition()].getName());
					gui.outputArea.append(Color.BLACK, "。\n");
					gui.refreshRole(roleList);
					gui.refreshBlock(blockList);
				}
				// 不買
				else {
					System.out.println("   * 你什麼都沒做，又平安的度過一回合。");
					gui.outputArea.append(Color.BLACK, "   * 你什麼都沒做，又平安的度過一回合。\n");
				}
			}
			// 錢不夠
			else {
				System.out.println("   $ " + roleList[nowRole].getName() + "，雖然是空地，但你沒金幣可以買，只能下次再來囉～");
				gui.outputArea.append(Color.BLACK, "   $ ");
				gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
				gui.outputArea.append(Color.BLACK, "，雖然是空地，但你沒金幣可以買，只能下次再來囉～");
			}
		}
		// 有人的
		else{
			// 找owner給錢
			System.out.println("   ! " + roleList[nowRole].getName()
					+ "，你踩到了 " + blockList[roleList[nowRole].getPosition()].getOwner()
					+ " 的地：" + blockList[roleList[nowRole].getPosition()].getName() + "。");
			gui.outputArea.append(Color.BLACK, "   ! ");
			gui.outputArea.append(Color.ORANGE, roleList[nowRole].getName());
			gui.outputArea.append(Color.BLACK, "，你踩到了 ");
			gui.outputArea.append(Color.ORANGE, blockList[roleList[nowRole].getPosition()].getOwner());
			gui.outputArea.append(Color.BLACK, " 的地：" );
			gui.outputArea.append(new Color(210,105,30), blockList[roleList[nowRole].getPosition()].getName());
			gui.outputArea.append(Color.BLACK, "。\n");
			
			for(int b = 1; b <= 3; b++) {
				if(blockList[roleList[nowRole].getPosition()].getOwner().equals(roleList[(nowRole + b) % 4].getName()))
					giveMoney(roleList[nowRole], roleList[(nowRole+b)%4], this.blockList[roleList[nowRole].getPosition()].getPrice());
			}
			gui.refreshRole(roleList);
			gui.refreshBlock(blockList);
		}
	}
	
	public int giveMoney(Role poorGuy, Role luckyGuy, int pay) {
		int payment = pay;
		int moneyReceived  = poorGuy.getMoney();
		
		System.out.println("   $ " + poorGuy.getName() + " 該付錢囉，你需要付 " + payment + " 枚金幣。");
		gui.outputArea.append(Color.BLACK, "   $ ");
		gui.outputArea.append(Color.ORANGE, poorGuy.getName());
		gui.outputArea.append(Color.BLACK, " 該付錢囉，你需要付 " + payment + " 枚金幣。\n");
		
		// 先把錢給吐出來
		while((!poorGuy.lossMoney(payment)) && poorGuy.getBlockNumber() > 0) {//當錢不夠並且還有地就繼續賣（賣到脫褲子
			moneyReceived = moneyReceived + sell(poorGuy);
		}
		
		// 把錢給他
		if(moneyReceived >= payment) {// 夠還
			System.out.println("   $ " + poorGuy.getName() + " 付給 " + luckyGuy.getName() + " " + payment + " 枚金幣。");
			gui.outputArea.append(Color.BLACK, "   $ ");
			gui.outputArea.append(Color.ORANGE, poorGuy.getName());
			gui.outputArea.append(Color.BLACK, " 付給 ");
			gui.outputArea.append(Color.ORANGE, luckyGuy.getName());
			gui.outputArea.append(Color.BLACK, " " + payment + " 枚金幣。\n");
			
			poorGuy.setMoney(moneyReceived - payment);
			luckyGuy.addMoney(payment);
			
			return payment;
		}
		else { // 還是不夠還
			System.out.println("   $ " + poorGuy.getName() + " 的金幣不夠，只付給 " + luckyGuy.getName() + " " +  moneyReceived + " 枚金幣。");
			gui.outputArea.append(Color.BLACK, "   $ ");
			gui.outputArea.append(Color.ORANGE, poorGuy.getName());
			gui.outputArea.append(Color.BLACK, " 的金幣不夠，只付給 ");
			gui.outputArea.append(Color.ORANGE, luckyGuy.getName());
			gui.outputArea.append(Color.BLACK, " " +  moneyReceived + " 枚金幣。\n");
			
			poorGuy.setMoney(0);
			luckyGuy.addMoney(moneyReceived);
			
			return moneyReceived;
		}
	}
	
	public int dropMoney(Role poorGuy, int pay) {
		int payment = pay;
		int moneyReceived  = poorGuy.getMoney();
		
		// 先把錢給吐出來
		while((!poorGuy.lossMoney(payment)) && poorGuy.getBlockNumber() > 0) { // 當錢不夠並且還有地就繼續賣（賣到脫褲子
			moneyReceived = moneyReceived + sell(poorGuy);
		}
		
		// 把錢給他
		if(moneyReceived > payment) { // 夠還
			poorGuy.setMoney(moneyReceived - payment);
			return payment;
		}
		else { // 不用賣直接還
			poorGuy.setMoney(0);
			return moneyReceived;
		}
	}
	
	private int sell(Role poorGuy) {
		System.out.println("   ! " + poorGuy.getName() + "，你的金幣不足（剩 " + poorGuy.getMoney() + " 枚金幣），該賣地囉，請問你要賣哪塊地？");
		gui.outputArea.append(Color.BLACK, "   ! ");
		gui.outputArea.append(Color.ORANGE, poorGuy.getName());
		gui.outputArea.append(Color.BLACK, "，你的金幣不足（剩 " + poorGuy.getMoney()
				+ " 枚金幣），該賣地囉，請問你要賣哪塊地？\n");
		
		Block[] blockBelong = new Block[poorGuy.getBlockNumber()];
		
		// 找有哪些地屬於他
		int pointer = 0;
		for(int a = 1; a < 32; a = a + 2) {
			if(blockList[a].getOwner().equals(poorGuy.getName())) {
				blockBelong[pointer] = blockList[a];
				pointer = pointer + 1;
			}
		}
		
		// 問玩家要賣哪一塊
		System.out.println("   ? 你現在有這些地：");
		gui.outputArea.append(Color.BLACK, "   ? 你現在有這些地：\n");
		
		for(int b = 0; b < pointer; b++) {
			System.out.println("     ? " + (b+1) + ". " + blockBelong[b].getName() + "價值 " + blockBelong[b].getPrice() + " 枚金幣。");
			gui.outputArea.append(Color.BLACK, "     ? " + (b+1) + ". ");
			gui.outputArea.append(new Color(210,105,30), blockBelong[b].getName());
			gui.outputArea.append(Color.BLACK, "價值 " + blockBelong[b].getPrice() + " 枚金幣。\n");
		}
		
		System.out.print("   ? 請問你要賣哪塊？");
		gui.outputArea.append(Color.BLACK, "   ? 請問你要賣哪塊？");
		
		// int whichSold = mapInput.nextInt() - 1;
		int whichSold = gui.getDecision() - 1;
		int sellMoney = blockBelong[whichSold].getPrice();
		
		poorGuy.addMoney(sellMoney); // 拿錢
		blockBelong[whichSold].setOwner("");// 失去土地（owner
		poorGuy.setBlockNumber(false);
		
		return sellMoney;
	}

	// /**
	//  * @brief print out the current condition of the map, including where the roles are
	//  */
	// public void print(Role[] roleList) {
	// 	for (int i = 0; i < 32; i++) {
	// 		System.out.println(blockList[i].getName() + "\t" + ((anyRoleHere(roleList, i) == -1) ? "" : ("<-" + roleList[anyRoleHere(roleList, i)].getName())));
	// 	}
	// }
	// /**
	//  * @brief check if there is any role here
	//  * @return which roles is in this position
	//  */
	// private int anyRoleHere(Role[] roleList, int place) {
	// 	for (int i = 0; i < 4; i++)
	// 	{
	// 		if (roleList[i].getPosition() == place)
	// 			return i;
	// 	}
	// 	return -1;
	// }

	/**
	 * @brief        Use the ability.
	 * @parame dice  The face of dice thrown.
	 */
	public void useAbility(Role[] roles, int curRole, int dice) {
		gui.diceThrown = 0;
		gui.dice = false;
		
		switch (dice) {
		case 1:  useAbility_1(roles[curRole]); break;
		case 2:  useAbility_2(roles, curRole); break;
		case 3:  useAbility_3(roles, curRole); break;
		case 4:  useAbility_4(roles, curRole); break;
		case 5:  useAbility_5(roles, curRole); break;
		case 6:
		default: useAbility_1(roles[curRole]); break;
		}
		gui.refreshRole(roles);
		gui.refreshBlock(blockList);
	}

	/**
	 * @brief get money from the bank
	 */
	protected void useAbility_1(Role luckyGuy) {
		System.out.println("   . 發動能力：\033[0;95m錢錢錢\033[0m");
		gui.outputArea.append(Color.BLACK, "   . 發動能力：");
		gui.outputArea.append(Color.PINK , "錢錢錢\n");
		
		luckyGuy.addMoney(3);
		
		System.out.println("     $ " + luckyGuy.getName() + " 跟銀行領取 3 枚金幣，身上共有 " 
				+ luckyGuy.getMoney() + " 枚金幣。");
		gui.outputArea.append(Color.BLACK, "   $ ");
		gui.outputArea.append(Color.ORANGE, luckyGuy.getName());
		gui.outputArea.append(Color.BLACK, " 跟銀行領取 3 枚金幣，身上共有 " 
				+ luckyGuy.getMoney() + " 枚金幣。\n");
	}

	/**
	 * @brief steal 2 coins from another role
	 * @parameter roles   the role list
	 * @parameter curRole the order of the current role
	 */
	protected void useAbility_2(Role[] roles, int curRole) {
		System.out.println("   . 發動能力：\033[0;95m小烏賊\033[0m");
		gui.outputArea.append(Color.BLACK, "   . 發動能力：");
		gui.outputArea.append(Color.PINK , "小烏賊\n");
		
		// 如果是盜賊，有能力加乘！
		if (roles[curRole].getCareer().equals("<盜賊>")) {
			System.out.println("     % 因為 " + roles[curRole].getName() + " 是"
					+ roles[curRole].getCareer() + "，所以有能力加乘！");
			System.out.println("     % \033[1;93m<盜賊>\033[0m可以向某位玩家奪取 3 枚金幣！");
			
			gui.outputArea.append(Color.BLACK, "     % 因為 ");
			gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
			gui.outputArea.append(Color.BLACK, " 是" + roles[curRole].getCareer() + "，所以有能力加乘！\n");
			gui.outputArea.append(Color.BLACK, "     % ");
			gui.outputArea.append(Color.YELLOW, "<盜賊>");
			gui.outputArea.append(Color.BLACK, " 可以向某位玩家奪取 3 枚金幣！\n");
		} else {
			System.out.println("     % 你現在可以向某位玩家奪取 2 枚金幣！");
			gui.outputArea.append(Color.BLACK, "     % 你現在可以向某位玩家奪取 2 枚金幣！\n");
		}
		
		System.out.println("       ? 第 1 位：" + roles[(curRole + 1) % 4].getName());
		System.out.println("       ? 第 2 位：" + roles[(curRole + 2) % 4].getName());
		System.out.println("       ? 第 3 位：" + roles[(curRole + 3) % 4].getName());

		gui.outputArea.append(Color.BLACK, "       ? 第 1 位：");
		gui.outputArea.append(Color.ORANGE, roles[(curRole + 1) % 4].getName() + "\n");
		gui.outputArea.append(Color.BLACK, "       ? 第 2 位：");
		gui.outputArea.append(Color.ORANGE, roles[(curRole + 2) % 4].getName() + "\n");
		gui.outputArea.append(Color.BLACK, "       ? 第 3 位：");
		gui.outputArea.append(Color.ORANGE, roles[(curRole + 3) % 4].getName() + "\n");
		
		System.out.print("       ? \033[0;32m選擇一位\033[0m： ");
		gui.outputArea.append(Color.BLACK, "       ? ");
		gui.outputArea.append(Color.GREEN, "選擇一位");
		gui.outputArea.append(Color.BLACK, "：");
		
		// int goal = (mapInput.nextInt() + curRole) % 4;
		int goal = (gui.getDecision() + curRole) % 4;

		int moneyReceived = giveMoney(roles[goal], roles[curRole], (roles[curRole].getCareer().equals("<盜賊>")) ? 3 : 2);
		
		System.out.println("   $ " + roles[curRole].getName() + " 從 " + roles[goal].getName() + " 手裡獲得 " + moneyReceived + " 枚金幣！");
		System.out.println("   $ 現在 " + roles[curRole].getName() + " 有 " + roles[curRole].getMoney() + " 枚金幣而 "
				+ roles[goal].getName() + " 剩 " + roles[goal].getMoney() + " 枚金幣。");
		gui.outputArea.append(Color.BLACK, "   $ ");
		gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
		gui.outputArea.append(Color.BLACK, " 從 ");
		gui.outputArea.append(Color.ORANGE, roles[goal].getName());
		gui.outputArea.append(Color.BLACK, " 手裡獲得 " + moneyReceived + " 枚金幣！\n");
		gui.outputArea.append(Color.BLACK, "   $ 現在 ");
		gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
		gui.outputArea.append(Color.BLACK, " 有 " + roles[curRole].getMoney() + " 枚金幣而 ");
		gui.outputArea.append(Color.ORANGE, roles[goal].getName());
		gui.outputArea.append(Color.BLACK, " 剩 " + roles[goal].getMoney() + " 枚金幣。\n");
	}

	/**
	 * @brief let the forward poorGuy drop 3 coins on the block
	 */
	protected void useAbility_3(Role[] roles, int curRole) {
		
		System.out.println("   . 發動能力：\033[0;95m綠龜殼\033[0m");
		System.out.println("     % 可以拿來丟前方一位玩家，讓他嚇得掉錢錢。");
		
		gui.outputArea.append(Color.BLACK, "   . 發動能力：");
		gui.outputArea.append(Color.PINK , "綠龜殼\n");
		gui.outputArea.append(Color.BLACK, "     % 可以拿來丟前方一位玩家，讓他嚇得掉錢錢。\n");
		
		// 如果是<弓手>，有能力加乘！
		if (roles[curRole].getCareer().equals("<弓手>")) {
			System.out.println("     % 因為 " + roles[curRole].getName() + " 是"
					+ roles[curRole].getCareer() + "，所以有能力加乘！");
			System.out.println("     % \033[1;93m<弓手>\033[0m丟比較準，所以被丟到的人損失比較多。");
			
			gui.outputArea.append(Color.BLACK, "     % 因為 ");
			gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
			gui.outputArea.append(Color.BLACK, " 是" + roles[curRole].getCareer() + "，所以有能力加乘！\n");
			gui.outputArea.append(Color.BLACK, "     % ");
			gui.outputArea.append(Color.YELLOW, "<弓手>");
			gui.outputArea.append(Color.BLACK, "丟比較準，所以被丟到的人損失比較多。\n");
		}
		
		int goal = findForwardRole(roles, curRole);
		int drop = dropMoney(roles[goal], (roles[curRole].getCareer().equals("<弓手>")) ? 4 : 3);
		gui.refreshRole(roles);
		gui.refreshBlock(blockList);
		
		blockList[roles[goal].getPosition()].setMoneyDropped(blockList[roles[goal].getPosition()].getMoneyDropped() + drop);
		
		System.out.println("     ! " + roles[curRole].getName() + " 的前方一位玩家 " + roles[goal].getName()
				+ " 被綠龜殼打落了 " + drop + " 枚金幣在" + blockList[roles[goal].getPosition()].getName() + "上。");
		System.out.println("   $ 現在" + blockList[roles[goal].getPosition()].getName() + "上有 "
				+ blockList[roles[goal].getPosition()].getMoneyDropped() + " 枚金幣了，而 "
				+ roles[goal].getName() + " 剩 " + roles[goal].getMoney() + " 枚金幣。");
		
		gui.outputArea.append(Color.BLACK, "     ! ");
		gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
		gui.outputArea.append(Color.BLACK, " 的前方一位玩家 ");
		gui.outputArea.append(Color.ORANGE, roles[goal].getName());
		gui.outputArea.append(Color.BLACK, " 被綠龜殼打落了 " + drop + " 枚金幣在");
		gui.outputArea.append(new Color(210,105,30), blockList[roles[goal].getPosition()].getName());
		gui.outputArea.append(Color.BLACK, "上。\n");
		gui.outputArea.append(Color.BLACK, "   $ 現在");
		gui.outputArea.append(new Color(210,105,30), blockList[roles[goal].getPosition()].getName());
		gui.outputArea.append(Color.BLACK, "上有 " + blockList[roles[goal].getPosition()].getMoneyDropped() + " 枚金幣了，而 ");
		gui.outputArea.append(Color.ORANGE, roles[goal].getName());
		gui.outputArea.append(Color.BLACK, " 剩 " + roles[goal].getMoney() + " 枚金幣。\n");
	}

	/**
	 * @brief let the backward poorGuy drop 3 coins on the block
	 */
	protected void useAbility_4(Role[] roles, int curRole) {
		
		System.out.println("   . 發動能力：\033[0;95m紅龜殼\033[0m");
		System.out.println("     % 可以拿來丟後方一位玩家，讓他嚇得掉錢錢。");
		
		gui.outputArea.append(Color.BLACK, "   . 發動能力：");
		gui.outputArea.append(Color.PINK , "紅龜殼\n");
		gui.outputArea.append(Color.BLACK, "     % 可以拿來丟後方一位玩家，讓他嚇得掉錢錢。\n");
		
		// 如果是<法師>，有能力加乘！
		if (roles[curRole].getCareer().equals("<法師>")) {
			System.out.println("     % 因為 " + roles[curRole].getName() + " 是"
					+ roles[curRole].getCareer() + "，所以有能力加乘！");
			System.out.println("     % \033[1;93m<法師>\033[0m背後有長眼睛，很會丟，所以被丟到的人損失比較多。");
			
			gui.outputArea.append(Color.BLACK, "     % 因為 ");
			gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
			gui.outputArea.append(Color.BLACK, " 是"	+ roles[curRole].getCareer() + "，所以有能力加乘！\n");
			gui.outputArea.append(Color.BLACK, "     % ");
			gui.outputArea.append(Color.YELLOW, "<法師>");
			gui.outputArea.append(Color.BLACK, "背後有長眼睛，很會丟，所以被丟到的人損失比較多。\n");
		}
		
		int goal = findBackwardRole(roles, curRole);
		int drop = dropMoney(roles[goal], (roles[curRole].getCareer().equals("<法師>")) ? 4 : 3);
		gui.refreshRole(roles);
		gui.refreshBlock(blockList);
		
		blockList[roles[goal].getPosition()].setMoneyDropped(blockList[roles[goal].getPosition()].getMoneyDropped() + drop);
		
		System.out.println("     ! " + roles[curRole].getName() + " 的後方一位玩家 " + roles[goal].getName()
				+ " 被紅龜殼打落了 " + drop + " 枚金幣在" + blockList[roles[goal].getPosition()].getName() + "上。");
		System.out.println("   $ 現在" + blockList[roles[goal].getPosition()].getName() + "上有 "
				+ blockList[roles[goal].getPosition()].getMoneyDropped() + " 枚金幣了，而 "
				+ roles[goal].getName() + " 剩 " + roles[goal].getMoney() + " 枚金幣。");
		
		gui.outputArea.append(Color.BLACK, "     ! ");
		gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
		gui.outputArea.append(Color.BLACK, " 的後方一位玩家 ");
		gui.outputArea.append(Color.ORANGE, roles[goal].getName());
		gui.outputArea.append(Color.BLACK, " 被紅龜殼打落了 " + drop + " 枚金幣在");
		gui.outputArea.append(new Color(210,105,30), blockList[roles[goal].getPosition()].getName());
		gui.outputArea.append(Color.BLACK, "上。\n");
		gui.outputArea.append(Color.BLACK, "   $ 現在");
		gui.outputArea.append(new Color(210,105,30), blockList[roles[goal].getPosition()].getName());
		gui.outputArea.append(Color.BLACK, "上有 " + blockList[roles[goal].getPosition()].getMoneyDropped() + " 枚金幣了，而 ");
		gui.outputArea.append(Color.ORANGE, roles[goal].getName());
		gui.outputArea.append(Color.BLACK, " 剩 " + roles[goal].getMoney() + " 枚金幣。\n");
	}

	/**
	 * @brief let the all other poorGuy drop 3 coins on the block
	 */
	protected void useAbility_5(Role[] roles, int curRole) {
		int drop = 0;
		System.out.println("   . 發動能力：\033[0;95m碰！！\033[0m");
		System.out.println("     % 碰！ 所有其他玩家受到攻擊！");
		
		gui.outputArea.append(Color.BLACK, "   . 發動能力：");
		gui.outputArea.append(Color.PINK , "碰！！\n");
		gui.outputArea.append(Color.BLACK, "     % 碰！ 所有其他玩家受到攻擊！\n");
		
		// 如果是<劍士>，有能力加乘！
		if (roles[curRole].getCareer().equals("<劍士>")) {
			System.out.println("     % 因為 " + roles[curRole].getName() + " 是"
					+ roles[curRole].getCareer() + "，所以有能力加乘！");
			System.out.println("     % \033[1;93m<劍士>\033[0m使所有玩家在原地被打落 2 枚金幣。");
			
			gui.outputArea.append(Color.BLACK, "     % 因為 ");
			gui.outputArea.append(Color.ORANGE, roles[curRole].getName());
			gui.outputArea.append(Color.BLACK, " 是"	+ roles[curRole].getCareer() + "，所以有能力加乘！\n");
			gui.outputArea.append(Color.BLACK, "     % ");
			gui.outputArea.append(Color.YELLOW, "<劍士>");
			gui.outputArea.append(Color.BLACK, "使所有玩家在原地被打落 2 枚金幣。\n");
		} else {
			System.out.println("     % 所有玩家在原地被打落 1 枚金幣。");
			gui.outputArea.append(Color.BLACK, "     % 所有玩家在原地被打落 1 枚金幣。\n");
		}

		
		for (int goal = 0; goal < 4; goal++)
		{
			if(!roles[goal].getName().equals(roles[curRole].getName())) {
				drop = dropMoney(roles[goal], (roles[curRole].getCareer().equals("<劍士>")) ? 2 : 1);
				gui.refreshRole(roles);
				gui.refreshBlock(blockList);
				blockList[roles[goal].getPosition()].setMoneyDropped(blockList[roles[goal].getPosition()].getMoneyDropped() + drop);
				
				System.out.println("     ! " + roles[goal].getName() + " 被打落 " + drop + " 枚金幣在"
						+ blockList[roles[goal].getPosition()].getName() + "上。");
				System.out.println("   $ 現在" + blockList[roles[goal].getPosition()].getName() + "上有 "
						+ blockList[roles[goal].getPosition()].getMoneyDropped() + " 枚金幣了，而 "
						+ roles[goal].getName() + " 剩 " + roles[goal].getMoney() + " 枚金幣。");
				
				gui.outputArea.append(Color.BLACK, "     ! ");
				gui.outputArea.append(Color.ORANGE, roles[goal].getName());
				gui.outputArea.append(Color.BLACK, " 被打落 " + drop + " 枚金幣在" + blockList[roles[goal].getPosition()].getName() + "上。\n");
				gui.outputArea.append(Color.BLACK, "   $ 現在");
				gui.outputArea.append(new Color(210,105,30), blockList[roles[goal].getPosition()].getName());
				gui.outputArea.append(Color.BLACK, "上有 " + blockList[roles[goal].getPosition()].getMoneyDropped() + " 枚金幣了，而 ");
				gui.outputArea.append(Color.ORANGE, roles[goal].getName());
				gui.outputArea.append(Color.BLACK, " 剩 " + roles[goal].getMoney() + " 枚金幣。\n");
			}
		}
	}

	private int findForwardRole(Role[] roles, int curRole) {
		int[] diff = {0, 0, 0, 0};
		int minDiff = 32;
		int forwardPosition = 0;
		int samePosition = 0;
		
		for (int i = 0; i < 4; i++)
		{
			diff[i] = roles[i].getPosition() - roles[curRole].getPosition();
			if (diff[i] <= 0) diff[i] = diff[i] + 32;
			if (diff[i] < minDiff) {
				samePosition = 1;
				minDiff = diff[i];
				forwardPosition = i;
			} else if (diff[i] == minDiff) {
				samePosition++;
			}
		}
		
		if (samePosition != 1) {
			int[] place = {0, 0, 0, 0};
			int roleRec = 0;
			
			System.out.println("       ? 有多於一個人在同一個位置：");
			gui.outputArea.append(Color.BLACK, "       ? 有多於一個人在同一個位置：\n");
			
			for (int i = 0; i < 4; i++)
				if (diff[i] == minDiff && i != curRole) {
					System.out.println("       ? 第 " + (roleRec + 1) + " 位：" + roles[i].getName());
					gui.outputArea.append(Color.BLACK, "       ? 第 " + (roleRec + 1) + " 位：");
					gui.outputArea.append(Color.ORANGE, roles[i].getName() + "\n");
					place[roleRec] = i;
					roleRec++;
				}
					
			
			System.out.print("       ? \033[0;32m選擇一位\033[0m：");
			gui.outputArea.append(Color.BLACK, "       ? ");
			gui.outputArea.append(Color.GREEN, "選擇一位");
			gui.outputArea.append(Color.BLACK, "：");
			
			// forwardPosition = place[mapInput.nextInt() - 1];
			forwardPosition = place[gui.getDecision() - 1];
		}
		
		return forwardPosition;
	}

	private int findBackwardRole(Role[] roles, int curRole) {
		int[] diff = {0, 0, 0, 0};
		int minDiff = 32;
		int backwardPosition = 0;
		int samePosition = 0;

		for (int i = 0; i < 4; i++)
		{
			diff[i] = roles[curRole].getPosition() - roles[i].getPosition();
			if (diff[i] <= 0) diff[i] = diff[i] + 32;
			if (diff[i] < minDiff) {
				samePosition = 1;
				minDiff = diff[i];
				backwardPosition = i;
			} else if (diff[i] == minDiff) {
				samePosition++;
			}
		}

		if (samePosition != 1) {
			int[] place = {0, 0, 0, 0};
			int roleRec = 0;
			
			System.out.println("       ? 有多於一個人在同一個位置：");
			gui.outputArea.append(Color.BLACK, "       ? 有多於一個人在同一個位置：\n");
			
			for (int i = 0; i < 4; i++)
				if (diff[i] == minDiff && i != curRole) {
					System.out.println("       ? 第 " + (roleRec + 1) + " 位：" + roles[i].getName());
					gui.outputArea.append(Color.BLACK, "       ? 第 " + (roleRec + 1) + " 位：");
					gui.outputArea.append(Color.ORANGE, roles[i].getName() + "\n");
					place[roleRec] = i;
					roleRec++;
				}
			
			System.out.print("       ? \033[0;32m選擇一位\033[0m：");
			gui.outputArea.append(Color.BLACK, "       ? ");
			gui.outputArea.append(Color.GREEN, "選擇一位");
			gui.outputArea.append(Color.BLACK, "：");
			
			// backwardPosition = place[mapInput.nextInt() - 1];
			backwardPosition = place[gui.getDecision() - 1];
		}
		
		return backwardPosition;
	}
}
