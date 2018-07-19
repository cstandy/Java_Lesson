package monopoly;

public class Boss {
	/* Variable */
	private int order;			//順序
	private int cost;			//參加打鬥花費
	private int point;			//獲得分數
	private int requirement;	//至少要擲到幾分才能贏
	
	/* Constructor */
	public Boss(int order, int cost, int point, int requirement) {
		this.order = order;
		this.cost = cost;
		this.point = point;
		this.requirement = requirement;
	}
	
	/* Get Function */
	public int getOrder()	 	{return order;}
	public int getCost()		{return cost;}
	public int getPoint()		{return point;}
	public int getRequirement()	{return requirement;}
}
