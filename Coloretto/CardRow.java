package coloretto;

import java.util.Vector;

public class CardRow{
	/* variables */
	private Vector<Card>	cardRow;
	private int 			row;

	/* functions */
	public CardRow(int inRow){
		cardRow = new Vector();
		row = inRow;
	}
	public CardRow	addCard(Card inCard){
		//inCard.setSeat("cardrow").setRow(this.getRow());
		cardRow.addElement(inCard);
		return this;
	}
	public CardRow	clearCard(){
		cardRow.clear();
		return this;
	}

	/* get functions */
	public String	getCard()	{
		String cardType = new String();
		for(int a=0; a < this.getNum(); a++){
			cardType = cardType + cardRow.elementAt(a).getType() + ",";
		}
		return cardType;
	}
	public int		getNum()	{return cardRow.size();}
	public int		getRow()    {return row;}

}
