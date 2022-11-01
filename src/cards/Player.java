package cards;

// need to thread this later
public class Player {

	public CardDeck playerDeck;
	private int denomination;
	
	public Player(int denomination) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
	}
	
	//
	
	public boolean cardsOfSameValue()
	{
		// add code here later
		return false;
	}
	
	public void takeAndPutCards(CardDeck dLeft, CardDeck dRight)
	{
		// need to edit this to consider denomination preference
		playerDeck.addCard(dLeft.takeFirstCard());
		dRight.addCard(playerDeck.takeLastCard());
	}
	
	public String toString()
	{
		return "Player #" + denomination;
	}

}
