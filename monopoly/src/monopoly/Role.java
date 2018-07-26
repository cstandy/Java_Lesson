package monopoly;

public class Role {

	/* variables */
	private String name;
	private int point;
	private int money;
	private int position;    // the position this role is
	private int blockNumber; // How many block this role have

	/* methods */
	/**
	 * @brief The Constructor of the class.
	 */
	public Role(String name) {
		this.name = name;
		point       = 0;
		money       = 10;
		position    = 0;
		blockNumber = 0;
	}
	/**
	  @brief Use the skill with super star.
	 */
	public void useSuperStar() {}

	/**
	 * @brief        Use the ability.
	 * @parame dice  The face of dice thrown.
	 */
	public void useAbility(int dice) {}

	
	// /**
	//  * @brief get money from the bank
	//  */
	// protected void useAbility_1() {
	// 	this.addMoney(3);
	// 	System.out.println("Ability dice: Get 3 coins from the bank.");
	// }
	// 
	// /**
	//  * @brief steal 2 coins from another role
	//  */
	// protected void useAbility_2(Role poorGuy) {
	// 	Map.giveMoney(this, poorGuy);
	// }
	// 
	// protected void useAbility_3() {}
	// protected void useAbility_4() {}
	// protected void useAbility_5() {}
	

	/* get function */
	public String 	getName() 		 {return name;}
	public int		getPoint()		 {return point;}
	public int		getMoney()		 {return money;}
	public int		getPosition() 	 {return position;}
	public int		getBlockNumber() {return blockNumber;}

	/**
	 * @brief Add money to player.
	 */
	public void addMoney(int moneyAdded) {
		this.money = this.money + moneyAdded;
	}

	/**
	 * @brief if the player runs out any money and not enough, than return false
	 * @param moneyMinuse
	 */
	public boolean lossMoney(int moneyMinused) {
		//Role have enough money
		if(moneyMinused <= this.getMoney()) {
			//this.money = this.money - moneyMinused;
			return true;
		}
		//Role does not have enough money
		else {
			return false;
		}
	}

	public void addPoint(int addition)   {this.point += addition;}
	public void setMoney(int inputMoney) {this.money = inputMoney;}
	public void setBlockNumber(boolean buyOrSale) {//true ++, false --
		if(buyOrSale) {//買地
			this.blockNumber = this.blockNumber + 1;
		}else {//賣地
			this.blockNumber = this.blockNumber - 1;
		}
	}

	/**
	 * @brief Player's position +1.
	 */
	public void moveOn() {this.position = (this.position+1)%32;}
}
