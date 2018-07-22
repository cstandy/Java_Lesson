package monopoly;

public class Role {

	/* variables */
	private String name;
	private int point;
	private int money;
	private int position;

	/* methods */
	/**
	 * @brief The Constructor of the class.
	 */
	public Role(String name) {
		this.name = name;
		point = 0;
		money = 0;
		position = 0;
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

	/**
	 * @brief functions called by useAbility()
	 */
	protected void useAbility_1() {}
	protected void useAbility_2() {}
	protected void useAbility_3() {}
	protected void useAbility_4() {}
	protected void useAbility_5() {}

	/* get function */
	public String 	getName() 		{return name;}
	public int		getPoint()		{return point;}
	public int		getMoney()		{return money;}
	public int		getPosition() 	{return position;}

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
			this.money = this.money - moneyMinused;
			return true;
		}
		//Role does not have enough money
		else {
			return false;
		}
	}
	/**
	 * @brief Player's position +1.
	 */
	public void moveOn() {this.position = (this.position+1)%32;}
}
