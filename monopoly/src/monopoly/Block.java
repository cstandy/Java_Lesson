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
	private int		sequence;
	
	/* methods */
	
	/**
	 * @brief The Constructor of the class.
	 */
	public Block(String name, boolean place, 
				boolean normal, int priceBuy, 
				int pricePassBy, int color, int sequence) {
		this.name = name;
		this.place = place;
		this.normal = normal;
		this.priceBuy = priceBuy;
		this.pricePassBy = pricePassBy;
		this.color = color;
		this.owner = "";
		this.sequence = sequence;
	}
	
	/* get function */
	public String 	getName() 		{return name;}
	public boolean 	getPlace()		{return place;}
	public boolean	getNormal()		{return normal;}
	public int		getPriceBuy()	{return priceBuy;}
	public int		getPricePassBy() {return pricePassBy;}
	public int		getColor()		{return color;}
	public String	getOwner()		{return owner;}
	public int		getSequence()		{return sequence;}
	
	/* set function */
	public void		setOwner(String owner) {this.owner = owner;}
}