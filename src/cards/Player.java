package cards;

public class Player implements Runnable {

	protected CardDeck playerDeck;
	public CardDeck leftDeck; // the deck from which cards are taken
	public CardDeck rightDeck; // the deck to which cards are put
	private int denomination;
	
	// player constructor - left and right decks are set later after creation
	public Player(int denomination) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
		
	}
	
	// player constructor - assigns left and right decks upon creation (testing use only)
	public Player(int denomination, CardDeck leftDeck, CardDeck rightDeck) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
		this.leftDeck = leftDeck;
		this.rightDeck = rightDeck;
	}
	
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
		
		// end the game based on counter value
		if(counter == 3) {
			CardGame.gameWinner = denomination;
			CardGame.gameOver = true;
		}
		
	}
	
	// multi-threading implementation for player
	public void run() {
		synchronized(this) {
			while(!CardGame.gameOver)
			{
				// take and put cards from left and right decks
				if(leftDeck.getSize() > 0) {
					takeAndPutCards();
				}

				// check player hand for same value (end the game if true)
				cardsOfSameValue();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			
			if(denomination == CardGame.gameWinner) {
				OutputWriting.playerWonGame("player" + denomination + "_output.txt", this);
				System.out.printf("%n%n%s %d %s", "Player", denomination, "won the game!");
			} else {
				OutputWriting.playerLostGame("player" + denomination + "_output.txt", this, CardGame.gameWinner);
			}
		}
	}
	
	public void takeAndPutCards()
	{
		// check if left deck has at least one card
		if(leftDeck.getCard(0) != null) {
			// sort deck to pick out cards based on player preference
			playerDeck.sortDeckByPreference(denomination);
			
			String droppedCard = playerDeck.getCard(CardDeck.SIZE - 1).toString();
			String pickedCard = leftDeck.getCard(0).toString();
			
			// perform take and put card operations
			rightDeck.addCard(playerDeck.takeLastCard());
			playerDeck.addCard(leftDeck.getCard(0));
			leftDeck.takeFirstCard();

			// write output to respective player file
			OutputWriting.pickingCard(pickedCard, denomination);
			OutputWriting.droppingCard(droppedCard, playerDeck.toString(), denomination);
		}
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
