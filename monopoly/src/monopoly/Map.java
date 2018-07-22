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
					roleList[nowRole].lossMoney(blockList[roleList[nowRole].getPosition()].getPrice());//付錢
					blockList[roleList[nowRole].getPosition()].setOwner(roleList[nowRole].getName());//得到土地（owner
					System.out.println("買到了" + blockList[roleList[nowRole].getPosition()].getName());
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
			int payment = blockList[roleList[nowRole].getPosition()].getPrice();
			System.out.println("有人啦笨蛋，吐錢出來，請付" + payment + "$");
			//找owner
			for(int b=1; b<=3; b++) {
				if(blockList[roleList[nowRole].getPosition()].getOwner().equals(roleList[(nowRole+b)%4].getName()))
					roleList[(nowRole+b)%4].addMoney();
			}
		}
	}
}
