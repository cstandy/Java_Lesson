package monopoly;

public class Map {
	/* variables */
	Block[] blockList = new Block[32];
	
	/* methods */
	
	/**
	 * @brief The Constructor of the class.
	 */
	public Map() {
		blockList[0] = new Block("Start", true, false, 0, 0, 0, 0);
		blockList[1] = new Block("台南後站（遊俠聚集地）", false, true, 1, 0, 1, 1);
		blockList[2] = new Block("Tube", true, false, 0, 0, 0, 2);
		blockList[3] = new Block("後站大遠百", false, true, 1, 0, 1, 3);
		blockList[4] = new Block("Money", true, false, 0, 0, 0, 4);
		blockList[5] = new Block("消失的牛車伯", false, true, 1, 0, 2, 5);
		blockList[6] = new Block("Star", true, false, 0, 0, 0, 6);
		blockList[7] = new Block("育樂街紅樓", false, true, 1, 0, 2, 7);
		blockList[8] = new Block("Jail", true, false, 0, 0, 0, 8);
		blockList[9] = new Block("勝利早點", false, true, 2, 0, 3, 9);
		blockList[10] = new Block("Tube", true, false, 0, 0, 0, 10);
		blockList[11] = new Block("老邱", false, true, 2, 0, 3, 11);
		blockList[12] = new Block("Trap", true, false, 0, 0, 0, 12);
		blockList[13] = new Block("九乘九", false, true, 2, 0, 4, 13);
		blockList[14] = new Block("Star", true, false, 0, 0, 0, 14);
		blockList[15] = new Block("21世紀風味館", false, true, 2, 0, 4, 15);
		blockList[16] = new Block("Park", true, false, 0, 0, 0, 16);
		blockList[17] = new Block("電機三館麥當勞", false, true, 3, 0, 5, 17);
		blockList[18] = new Block("Tube", true, false, 0, 0, 0, 18);
		blockList[19] = new Block("7-11大學店", false, true, 3, 0, 5, 19);
		blockList[20] = new Block("Money", true, false, 0, 0, 0, 20);
		blockList[21] = new Block("Double Cheese", false, true, 3, 0, 6, 21);
		blockList[22] = new Block("Star", true, false, 0, 0, 0, 22);
		blockList[23] = new Block("錢鼠", false, true, 3, 0, 6, 23);
		blockList[24] = new Block("ToJail", true, false, 0, 0, 0, 24);
		blockList[25] = new Block("電機本館", false, true, 4, 0, 7, 25);
		blockList[26] = new Block("Tube", true, false, 0, 0, 0, 26);
		blockList[27] = new Block("奇美樓", false, true, 4, 0, 7, 27);
		blockList[28] = new Block("Trap", true, false, 0, 0, 0, 28);
		blockList[29] = new Block("東安路迷客夏", false, true, 5, 0, 8, 29);
		blockList[30] = new Block("Star", true, false, 0, 0, 0, 30);
		blockList[31] = new Block("世界彼端伊都", false, true, 5, 0, 8, 31);
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
		if(blockList[input].getOwner() == blockList[(input+2)%32].getOwner())	return true;
		else if(blockList[input].getOwner() == blockList[(input+30)%32].getOwner())	return true;
		else	return false;
	}
	/**
	 * @brief 一步一步走，每步都看有沒有錢可以拿或是要交過路費，到底之後看是不是水管，是的話就從下一個水管出去並且把中間的錢都拿走
	 * @param place:原本位置
	 * @param playerList:所有人的資料
	 * @param movement要走幾步
	 */
	public Block walk(int place, Role[] playerList, int movement, int nowPlayer) {
		//一步一步走（要看撿錢＆過路費）
		for(int a=1; a<=movement; a++) {
			if(blockList[(place+a)%32].getOwner().equals(playerList[nowPlayer]))
			blockList[(place+a)%32].getPricePassBy();
		}
		//如果最後是水管
		if() {
			for() {
				
			}
		}
		
	}
}
