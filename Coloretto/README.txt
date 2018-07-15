///////////////////////////
class Card
variables:
	type		String
    seat		String
	player		int
	row 		int
functions:
	Card		(String)
	getType		()				String
    getSeat		()				String
    getPlayer	()				int
    getRow		()				int
	setSeat		(String)		Card
	setPlayer	(int)			Card
	setRow		(int)			Card
///////////////////////////
class CardPile
variables:
	cardPile	Stack<Card>
functions:
	CardPile	()
	addCard		(Card)			void
	topCard		()				Card
	takeCard	()				Card
///////////////////////////
class CardRow
variables:
	cardRow		Vector<Card>
	row			int
functions:
	CardRow		(int)
	addCard		(Card)			CardRow
	clearCard	()				CardRow
	getCard		()				String
	getNum		()				int
	getRow		()				int
///////////////////////////
class Player
variables:
	cardOwn		Vector<Card>
	playerN		int
	playerName	String
functions:
	Player		(int,String)
	addCard		(Card)			Player
	getCard		()				String
	getNum		()				int
	getPlayerN	()				int
	getPlayerName()				String
