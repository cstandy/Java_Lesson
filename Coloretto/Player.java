package coloretto;

import java.util.Vector;

public class Player{
	/* variables */
	private Vector<Card>	cardOwn;
	private int 			playerN;
	private String			playerName;

	/* functions */
	public Player(int inPlayerN, String inName){
		cardOwn = new Vector();
		playerN = inPlayerN;
		playerName = inName;
	}
	public Player addCard(Card inCard){
		inCard.setSeat("player").setPlayer(this.getPlayerN());
		cardOwn.addElement(inCard);
		return this;
	}

    /* get functions */
	public String	getCard()	{
		String cardType = new String();
		for(int a=0; a < this.getNum(); a++){
			cardType = cardType + cardOwn.elementAt(a).getType() + ",";
		}
		return cardType;
	}
	public int		getNum()		{return cardOwn.capacity();}
	public int		getPlayerN()    {return playerN;}
	public String	getPlayerName()	{return playerName;}

}
