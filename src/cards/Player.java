package cards;

// need to thread this later
public class Player implements Runnable {

	protected CardDeck playerDeck;
	public CardDeck leftDeck;
	public CardDeck rightDeck;
	private int denomination;
	private boolean gameOver;
	
	public Player(int denomination) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
		
		gameOver = false;
	}
	
	public Player(int denomination, CardDeck leftDeck, CardDeck rightDeck) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
		this.leftDeck = leftDeck;
		this.rightDeck = rightDeck;
		
		gameOver = false;
	}
	
	//
	
	public void setLeftDeck(CardDeck ld)
	{
		leftDeck = ld;
	}
	
	public void setRightDeck(CardDeck rd)
	{
		rightDeck = rd;
	}
	
	public CardDeck getLeftDeck()
	{
		return leftDeck;
	}
	
	public CardDeck getRightDeck()
	{
		return rightDeck;
	}
	
	public int getDenomination() {
		return denomination;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public void cardsOfSameValue()
	{
		// check deck for cards of same value
		int counter = 0;
		for(int j = 1; j < CardDeck.SIZE; j++)
		{
			if(playerDeck.getCard(0).getValue() != playerDeck.getCard(j).getValue())
			{
				break;
			} else {
				counter += 1;
			}
		}
		
		if(counter == 3) {
			gameOver = true;
		}
		
	}
	
	public void run() {
		try {
			while(!gameOver)
			{
				// take and put cards from left and right decks
				takeAndPutCards();
				// check player hand for same value
				cardsOfSameValue();
				
				wait();
				notify();
			
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void takeAndPutCards()
	{
		playerDeck.sortDeckByPreference(denomination);
		String drawnCard = playerDeck.getCard(playerDeck.SIZE - 1).toString();
		System.out.println("Player deck: " + playerDeck.toString());
		
		rightDeck.addCard(playerDeck.takeLastCard());
		System.out.println("Right deck: " + rightDeck.toString());
		System.out.println("Player deck: " + playerDeck.toString());
		
		playerDeck.addCard(leftDeck.getCard(0));
		leftDeck.takeFirstCard();
		OutputWriting.pickingCard(leftDeck.getCard(0).toString(), denomination);
		OutputWriting.droppingCard(drawnCard, playerDeck.toString(), denomination);
		System.out.println("Left Deck: " + leftDeck.toString());
		System.out.println("Player deck: " + playerDeck.toString());
	}
	
	public CardDeck getHand()
	{
		return playerDeck;
	}
	
	public String toString()
	{
		return "Player #" + denomination + ", " + "Hand " + playerDeck;
	}

}
