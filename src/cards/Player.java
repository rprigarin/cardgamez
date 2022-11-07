package cards;

// need to thread this later
public class Player implements Runnable {

	protected CardDeck playerDeck;
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
	
	public void run() {
		// do stuff here
	}
	
	public synchronized void takeAndPutCards(CardDeck dLeft, CardDeck dRight)
	{
		// need to edit this to consider denomination preference
		playerDeck.addCard(dLeft.takeFirstCard());
		playerDeck.sortDeckByPreference(denomination);
		dRight.addCard(playerDeck.takeLastCard());
	}
	
	public CardDeck getDeck()
	{
		return playerDeck;
	}
	
	public String toString()
	{
		return "Player #" + denomination + ", " + "Hand " + playerDeck;
	}

}
