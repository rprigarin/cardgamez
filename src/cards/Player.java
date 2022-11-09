package cards;

// need to thread this later
public class Player implements Runnable {

	protected CardDeck playerDeck;
	private int denomination;
	private String playerFile;
	
	public Player(int denomination) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
		//this.playerFile
	}
	
	//
	
	public int getDenomination() {
		return denomination;
	}
	
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
		// writeToFile(getCard(0).toString());
		playerDeck.addCard(dLeft.takeFirstCard());
		playerDeck.sortDeckByPreference(denomination);
		// writeToFile(getCard(playerDeck.size() - 1).toString());
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
