package monopoly;

public class Boss {

	/* Variable */
	private String name;		//名字
	private int order;			// 順序
	private int cost;			// 參加打鬥花費
	private int point;			// 獲得分數
	private int requirement;	// 至少要擲到幾分才能贏
	
	/**
	  * @brief              The constructor of the class.
	  * @parame order       The order of the boss.
	  * @parame cost        The attending fee of player.
	  * @parame point       The prize of point.
	  * @parame requirement The low bound to win this boss.
	  */
	public Boss(String name, int order, int cost, int point, int requirement) {
		this.name        = name;
		this.order       = order;
		this.cost        = cost;
		this.point       = point;
		this.requirement = requirement;
	}
	
	/* Get Function */
	public String getName()		{return name;}
	public int getOrder()	 	{return order;}
	public int getCost()		{return cost;}
	public int getPoint()		{return point;}
	public int getRequirement()	{return requirement;}
}
