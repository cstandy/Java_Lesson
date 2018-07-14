package Coloretto;

import java.util.Stack;

public class CardPile{
//variables
	private Stack<Card>	cardPile;
//functions
	public CardPile(){
		cardPile = new Stack;
	}
	public void addCard(Card inCard){
		cardPile.push(inCard);
	}
	public Card topCard(){
		return cardPile.peek();
	}
	public Card takeCard(){
		return cardPile.pop();
	}
}
