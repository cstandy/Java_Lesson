package monopoly;

import java.util.Scanner;

public class Map {
	/* variables */
	Block[] blockList = new Block[32];
	Scanner mapInput = new Scanner(System.in);

	/* methods */

	/**
	 * @brief The Constructor of the class.
	 */
	public Map() {
		blockList[0]  = new Block("Start",                  true, false, 0, 0);
		blockList[1]  = new Block("台南後站（遊俠聚集地）", false, true, 1, 1);
		blockList[2]  = new Block("Tube",                   true, false, 0, 0);
		blockList[3]  = new Block("後站大遠百",             false, true, 1, 1);
		blockList[4]  = new Block("Money",                  true, false, 0, 0);
		blockList[5]  = new Block("消失的牛車伯",           false, true, 1, 2);
		blockList[6]  = new Block("Star",                   true, false, 0, 0);
		blockList[7]  = new Block("育樂街紅樓",             false, true, 1, 2);
		blockList[8]  = new Block("Jail",                   true, false, 0, 0);
		blockList[9]  = new Block("勝利早點",               false, true, 2, 3);
		blockList[10] = new Block("Tube",                   true, false, 0, 0);
		blockList[11] = new Block("老邱",                   false, true, 2, 3);
		blockList[12] = new Block("Trap",                   true, false, 0, 0);
		blockList[13] = new Block("九乘九",                 false, true, 2, 4);
		blockList[14] = new Block("Star",                   true, false, 0, 0);
		blockList[15] = new Block("21世紀風味館",           false, true, 2, 4);
		blockList[16] = new Block("Park",                   true, false, 0, 0);
		blockList[17] = new Block("電機三館麥當勞",         false, true, 3, 5);
		blockList[18] = new Block("Tube",                   true, false, 0, 0);
		blockList[19] = new Block("7-11大學店",             false, true, 3, 5);
		blockList[20] = new Block("Money",                  true, false, 0, 0);
		blockList[21] = new Block("Double Cheese",          false, true, 3, 6);
		blockList[22] = new Block("Star",                   true, false, 0, 0);
		blockList[23] = new Block("錢鼠",                   false, true, 3, 6);
		blockList[24] = new Block("ToJail",                 true, false, 0, 0);
		blockList[25] = new Block("電機本館",               false, true, 4, 7);
		blockList[26] = new Block("Tube",                   true, false, 0, 0);
		blockList[27] = new Block("奇美樓",                 false, true, 4, 7);
		blockList[28] = new Block("Trap",                   true, false, 0, 0);
		blockList[29] = new Block("東安路迷客夏",           false, true, 5, 8);
		blockList[30] = new Block("Star",                   true, false, 0, 0);
		blockList[31] = new Block("世界彼端伊都",           false, true, 5, 8);
		
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
	 * @parame movement:		要走幾步
	 */
	public Block walk(Role[] roleList, int movement, int nowRole) {
		//一步一步走（要看撿錢）
		for(int a=0; a < movement; a++) {
			roleList[nowRole].moveOn();
			//撿錢
			roleList[nowRole].addMoney(blockList[roleList[nowRole].getPosition()].pickMoney());
		}
		//如果最後是水管
		if(blockList[roleList[nowRole].getPosition()].getName()=="Tube") {
			for(int a=1; a <= 8; a++) {
				roleList[nowRole].moveOn();
				//撿錢
				roleList[nowRole].addMoney(blockList[roleList[nowRole].getPosition()].pickMoney());
			}
		}
		this.event(roleList, nowRole);
		return blockList[roleList[nowRole].getPosition()];
	}

	/**
	 * @brief 
	 * @param roleList
	 * @param nowRole
	 */
	private void event(Role[] roleList, int nowRole) {
		//沒有人的
		if(blockList[roleList[nowRole].getPosition()].getOwner()=="") {
			//錢夠
			if(blockList[roleList[nowRole].getPosition()].getPrice() <= roleList[nowRole].getMoney()) {
				System.out.println("此地無人，風水寶地不買嗎？ yes/1 no/0");
				//買
				if(mapInput.nextInt() == 1) {
					roleList[nowRole].setMoney(roleList[nowRole].getMoney() - blockList[roleList[nowRole].getPosition()].getPrice());//付錢
					blockList[roleList[nowRole].getPosition()].setOwner(roleList[nowRole].getName());//得到土地（owner
					System.out.println(roleList[nowRole].getName() + "買到了" + blockList[roleList[nowRole].getPosition()].getName());
				}
				//不買
				else {
					System.out.println("你什麼都沒做，又度過一回合");
				}
			}
			//錢不夠
			else {
				System.out.println("此地無人，但你沒錢233333");
			}
		}
		//有人的
		else{
			//找owner給錢
			for(int b=1; b<=3; b++) {
				if(blockList[roleList[nowRole].getPosition()].getOwner().equals(roleList[(nowRole+b)%4].getName()))
					giveMoney(roleList[nowRole], roleList[(nowRole+b)%4]);
			}
		}
	}
	public void giveMoney(Role poorGuy, Role luckyGuy) {
		int payment = this.blockList[poorGuy.getPosition()].getPrice();
		int moneyReceived  = poorGuy.getMoney();
		System.out.println("有人啦笨蛋，吐錢出來，請付" + payment + "$");
		//先把錢給吐出來
		while((!poorGuy.lossMoney(payment)) && poorGuy.getBlockNumber() > 0) {//當錢不夠並且還有地就繼續賣（賣到脫褲子
			moneyReceived = moneyReceived + sell(poorGuy);
		}
		//把錢給他
		if(moneyReceived > payment) {//夠還
			System.out.println(poorGuy.getName() + "付給" + luckyGuy.getName() + payment + "元");
			poorGuy.setMoney(moneyReceived - payment);
			luckyGuy.addMoney(payment);
		}
		else {//不用賣直接還
			System.out.println(poorGuy.getName() + "錢不夠，只付給" + luckyGuy.getName() + moneyReceived + "元");
			poorGuy.setMoney(0);
			luckyGuy.addMoney(moneyReceived);
		}
	}
	public int dropMoney(Role poorGuy) {
		int payment = this.blockList[poorGuy.getPosition()].getPrice();
		int moneyReceived  = poorGuy.getMoney();
		//先把錢給吐出來
		while((!poorGuy.lossMoney(payment)) && poorGuy.getBlockNumber() > 0) {//當錢不夠並且還有地就繼續賣（賣到脫褲子
			moneyReceived = moneyReceived + sell(poorGuy);
		}
		//把錢給他
		if(moneyReceived > payment) {//夠還
			poorGuy.setMoney(moneyReceived - payment);
			return payment;
		}
		else {//不用賣直接還
			poorGuy.setMoney(0);
			return moneyReceived;
		}
	}
	private int sell(Role poorGuy) {
		System.out.println("該賣地囉，請問你要賣哪塊地");
		Block[] blockBelong = new Block[poorGuy.getBlockNumber()];
		//找有哪些地屬於他
		int pointer = 0;
		for(int a=1; a < 32; a=a+2) {
			if(blockList[a].getOwner().equals(poorGuy.getName())) {
				blockBelong[pointer] = blockList[a];
				pointer = pointer + 1;
			}
		}
		//問玩家要賣哪一塊
		System.out.println("你現在有這些地：");
		for(int b = 0; b < pointer; b++) {
			System.out.println(b+1 + "." + blockBelong[b].getName() + " 價值" + blockBelong[b].getPrice() + "元");
		}
		System.out.print("請問你要賣哪塊？");
		int whichSold = mapInput.nextInt()-1;
		int sellMoney = blockBelong[whichSold].getPrice();
		poorGuy.addMoney(sellMoney);//拿錢
		blockList[poorGuy.getPosition()].setOwner("");//失去土地（owner
		poorGuy.setBlockNumber(false);
		return sellMoney;
	}

	/**
	 * @brief print out the current condition of the map, including where the roles are
	 */
	public void print(Role[] roleList) {
		for (int i = 0; i < 32; i++) {
			System.out.println(blockList[i].getName() + "\t" + ((anyRoleHere(roleList, i) == -1) ? "" : ("<-" + roleList[anyRoleHere(roleList, i)].getName())));
		}
	}
	
	/**
	 * @brief check if there is any role here
	 * @return which roles is in this position
	 */
	private int anyRoleHere(Role[] roleList, int place) {
		for (int i = 0; i < 4; i++)
		{
			if (roleList[i].getPosition() == place)
				return i;
		}
		
		return -1;
	}
	
	/**
	 * @brief        Use the ability.
	 * @parame dice  The face of dice thrown.
	 */
	public void useAbility(Role[] roles, int curRole, int dice) {
		switch (dice) {
		case 1:  useAbility_1(roles[curRole]);
		case 2:  useAbility_2(roles, curRole);
		case 3:  useAbility_3(roles, curRole);
		case 4:  useAbility_4(roles, curRole);
		case 5:  useAbility_5(roles, curRole);
		case 6:
		default: useAbility_1(roles[curRole]);
		}
	}
	
	/**
	 * @brief get money from the bank
	 */
	protected void useAbility_1(Role luckyGuy) {
		luckyGuy.addMoney(3);
		System.out.println("Ability dice: Get 3 coins from the bank.");
	}
	
	/**
	 * @brief steal 2 coins from another role
	 * @parameter roles   the role list
	 * @parameter curRole the order of the current role
	 */
	protected void useAbility_2(Role[] roles, int curRole) {
		System.out.println("1st candidate: " + roles[(curRole + 1) % 4].getName());
		System.out.println("2nd candidate: " + roles[(curRole + 2) % 4].getName());
		System.out.println("3rd candidate: " + roles[(curRole + 3) % 4].getName());
		
		System.out.print("Please choose a poor guy for his/her money: ");
		int goal = (mapInput.nextInt() + curRole) % 4;
		
		giveMoney(roles[goal], roles[curRole]);
		System.out.println("Ability dice: " + roles[curRole].getName() + " get 3 coins from " + roles[goal].getName() + ".");
	}
	
	/**
	 * @brief let the forward poorGuy drop 3 coins on the block
	 */
	protected void useAbility_3(Role[] roles, int curRole) {
		int goal = findForwardRole(roles, curRole);
		int drop = dropMoney(roles[goal]);
		
		blockList[roles[goal].getPosition()].setMoneyDropped(blockList[roles[goal].getPosition()].getMoneyDropped() + drop);
		System.out.println("Ability dice: " + roles[goal].getName() + " dropped " + drop + " on " + blockList[roles[goal].getPosition()].getName());
		System.out.println("On the block " + blockList[roles[goal].getPosition()].getName() + " is $" + blockList[roles[goal].getPosition()].getMoneyDropped());
	}
	
	/**
	 * @brief let the backward poorGuy drop 3 coins on the block
	 */
	protected void useAbility_4(Role[] roles, int curRole) {
		int goal = findBackwardRole(roles, curRole);
		int drop = dropMoney(roles[goal]);
		
		blockList[roles[goal].getPosition()].setMoneyDropped(blockList[roles[goal].getPosition()].getMoneyDropped() + drop);
		System.out.println("Ability dice: " + roles[goal].getName() + " dropped " + drop + " on " + blockList[roles[goal].getPosition()].getName());
		System.out.println("On the block " + blockList[roles[goal].getPosition()].getName() + " is $" + blockList[roles[goal].getPosition()].getMoneyDropped());
	}
	
	/**
	 * @brief let the all other poorGuy drop 3 coins on the block
	 */
	protected void useAbility_5(Role[] roles, int curRole) {
		int drop = 0;
		for (int goal = 0; goal < 4; goal++)
		{
			drop = dropMoney(roles[goal]);
			blockList[roles[goal].getPosition()].setMoneyDropped(blockList[roles[goal].getPosition()].getMoneyDropped() + drop);
			System.out.println("Ability dice: " + roles[goal].getName() + " dropped " + drop + " on " + blockList[roles[goal].getPosition()].getName());
			System.out.println("On the block " + blockList[roles[goal].getPosition()].getName() + " is $" + blockList[roles[goal].getPosition()].getMoneyDropped());
		}
	}
	
	
	private int findForwardRole(Role[] roles, int curRole) {
		int[] diff = {0, 0, 0, 0};
		int minDiff = 32;
		int forwardPosition = 0;
		for (int i = 0; i < 4; i++)
		{
			diff[i] = roles[i].getPosition() - roles[curRole].getPosition();
			if (diff[i] <= 0) diff[i] = diff[i] + 32;
			if (diff[i] < minDiff) {
				minDiff = diff[i];
				forwardPosition = i;
			}
		}
		return forwardPosition;
	}
	
	private int findBackwardRole(Role[] roles, int curRole) {
		int[] diff = {0, 0, 0, 0};
		int minDiff = 32;
		int backwardPosition = 0;
		
		for (int i = 0; i < 4; i++)
		{
			diff[i] = roles[curRole].getPosition() - roles[i].getPosition();
			if (diff[i] <= 0) diff[i] = diff[i] + 32;
			if (diff[i] < minDiff) {
				minDiff = diff[i];
				backwardPosition = i;
			}
		}
		return backwardPosition;
	}
}
