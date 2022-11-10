package cards;

// need to thread this later
public class Player implements Runnable {

	protected CardDeck playerDeck;
	private int denomination;
	
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
		playerDeck.sortDeckByPreference(denomination);
		String drawnCard = playerDeck.getCard(playerDeck.SIZE - 1).toString();
		System.out.println("Player deck: " + playerDeck.toString());
		dRight.addCard(playerDeck.takeLastCard());
		System.out.println("Right deck: " + dRight.toString());
		System.out.println("Player deck: " + playerDeck.toString());
		playerDeck.addCard(dLeft.getCard(0));
		dLeft.takeFirstCard();
		OutputWriting.pickingCard(dLeft.getCard(0).toString(), denomination);
		OutputWriting.droppingCard(drawnCard, playerDeck.toString(), denomination);
		System.out.println("Left Deck: " + dLeft.toString());
		System.out.println("Player deck: " + playerDeck.toString());
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
