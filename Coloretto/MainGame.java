package coloretto;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class MainGame{
	public static void main(String[] args){
		/* variables, useful tools */
		Scanner	scan = new Scanner(system.in);
		Random	rand = new Random();
		ArrayList<Card>	allCard = new ArrayList();		//1 type has only 1 
		int	playerNum;
		//int	aINum;
		CardPile cardPile = new CardPile();

		/* Choose game mode (playerNum, AINum) */
		do{
			System.out.print("How many player:");
			playerNum = scan.nextInt();
		}while(playerNum<3||playerNum>5);
		
		/* Create Cards(77), CardPile(1), CardRows(2,3,4,5), Players(2,3,4,5) */
		allCard.add(new Card("pink"));
		allCard.add(new Card("brown"));
		allCard.add(new Card("blue"));
		allCard.add(new Card("green"));
		allCard.add(new Card("gray"));
		allCard.add(new Card("red"));
		allCard.add(new Card("gold"));
		allCard.add(new Card("rainbow"));
		allCard.add(new Card("twice"));
		allCard.add(new Card("finish"));
		if(playerNum == 3){
			int deleted = rand.nextInt(7);
			allCard.remove(deleted);
		}
		
		/* Deal Cards (Every Player has different color card) */
		
		/* Game Loop */
		
		/* End Game and Count Grade */
		
	}
}
