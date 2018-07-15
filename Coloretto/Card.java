package coloretto;

public class Card{
    /* variables */
	private String	type;		// pink, brown, blue, green, gray, red, gold, rainbow, twice, finish
	private String	seat;		// cardpile, cardrow, player
	private int		player;		// 12345
	private int		row;		// in which row

    /* functions */
	public Card(String inType){
		type = inType;
		setSeat("cardpile");
		setPlayer(0);
		setRow(0);
	}

    /* get functions */
	public String   getType()   {return type;}
	public String	getSeat()   {return seat;}
	public int		getPlayer() {return player;}
	public int		getRow()    {return row;}

    /* set functions */
	public Card		setSeat(String inSeat){
		seat = inSeat;
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

//class ColorCard extends Card{
//    /* variables */
//	private String color;
//    /* functions */
//	public ColorCard(int inNum, String inColor){
//		super(inNum, "color");
//		color = inColor;
//	}
//	public String	getColor(){
//		return color;
//	}
//}
//
//class RainbowCard extends Card{
//	public RainbowCard(int inNum){
//		super(inNum, "rainbow");
//	}
//}
//
//class TwiceCard extends Card{
//	public TwiceCard(int inNum){
//		super(inNum, "twice");
//	}
//}
//
//class FinishCard extends Card{
//	public FinishCard(int inNum){
//		super(inNum, "finish");
//	}
//
//}
