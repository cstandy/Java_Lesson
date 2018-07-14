package coloretto;

public class Card{
    /* variables */
	protected String	type;		// color, rainbow, twice, finish
	protected String	seat;		// cardpile, cardrow, player
	protected int		num;		// cardpile or cardrow
	protected int		player;		// 12345
	protected int		row;		// in which row

    /* functions */
	public Card(int inNum, String inType){
		setType(inType);
		setSeat("cardpile");
		setNum(inNum);
		setPlayer(0);
		setRow(0);
	}

    /* get functions */
	public String   getType()   {return type;}
	public String	getSeat()   {return seat;}
	public int		getNum()    {return num;}
	public int		getPlayer() {return player;}
	public int		getRow()    {return row;}

    /* set functions */
	public Card		setType(String inType){
		type = inType;
		return this;
	}
	public Card		setSeat(String inSeat){
		seat = inSeat;
		return this;
	}
	public Card		setNum(int inNum){
		num = inNum;
		return this;
	}
	public Card		setPlayer(int inPlayer){
		player = inPlayer;
		return this;
	}
	public Card		setRow(int inRow){
		row = inRow;
		return this;
	}
}

class ColorCard extends Card{
    /* variables */
	private String color;
    /* functions */
	public ColorCard(int inNum, String inColor){
		super(inNum, "color");
		color = inColor;
	}
	public String	getColor(){
		return color;
	}
}

class RainbowCard extends Card{
	public RainbowCard(int inNum){
		super(inNum, "rainbow");
	}
}

class TwiceCard extends Card{
	public TwiceCard(int inNum){
		super(inNum, "twice");
	}
}

class FinishCard extends Card{
	public FinishCard(int inNum){
		super(inNum, "finish");
	}

}
