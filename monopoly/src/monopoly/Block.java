package monopoly;

public class Block {
	
	/* variables */
	private String	name;
	private boolean	place;
	private boolean normal;
	private int		priceBuy;
	private int		pricePassBy;
	private int		color;
	private String	owner;
	private int		moneyDropped;
	
	/* methods */
	
	/**
	 * @brief The Constructor of the class.
	 */
	public Block(String name, boolean place, 
				boolean normal, int priceBuy, 
				int pricePassBy, int color) {
		this.name = name;
		this.place = place;
		this.normal = normal;
		this.priceBuy = priceBuy;
		this.pricePassBy = pricePassBy;
		this.color = color;
		this.owner = "";
		this.moneyDropped = 0;
	}
	/**
	 * @brief set moneyDropped 0 and return money get 
	 */
	public int		pickMoney()	{
		int money = this.moneyDropped;
		this.moneyDropped = 0;
		return money;
	}
	
	/* get function */
	public String 	getName() 		{return name;}
	public boolean 	getPlace()		{return place;}
	public boolean	getNormal()		{return normal;}
	public int		getPriceBuy()	{return priceBuy;}
	public int		getPricePassBy() {return pricePassBy;}
	public int		getColor()		{return color;}
	public String	getOwner()		{return owner;}
	public int		getMoneyDropped() {return moneyDropped;}
	
	/* set function */
	public void		setOwner(String owner)	{this.owner = owner;}
	public void		setMoneyDropped(int moneyIn)	{this.moneyDropped = this.moneyDropped+moneyIn;}
}