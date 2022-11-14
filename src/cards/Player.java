package cards;

// need to thread this later
public class Player implements Runnable {

	protected CardDeck playerDeck;
	public CardDeck leftDeck;
	public CardDeck rightDeck;
	private int denomination;
	
	
	public Player(int denomination) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
		
	}
	
	public Player(int denomination, CardDeck leftDeck, CardDeck rightDeck) {
		playerDeck = new CardDeck();
		this.denomination = denomination;
		this.leftDeck = leftDeck;
		this.rightDeck = rightDeck;
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
			CardGame.gameWinner = denomination;
			CardGame.gameOver = true;
		}
		
	}
	
	public void run() {
		synchronized(this) {
			while(!CardGame.gameOver)
			{
				// take and put cards from left and right decks
				if(leftDeck.getSize() > 0) {
					takeAndPutCards();
				}
					// check player hand for same value
					cardsOfSameValue();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
		if(leftDeck.getCard(0) != null) {
			playerDeck.sortDeckByPreference(denomination);
			
			String droppedCard = playerDeck.getCard(CardDeck.SIZE - 1).toString();
			String pickedCard = leftDeck.getCard(0).toString();
			
			rightDeck.addCard(playerDeck.takeLastCard());
			
			playerDeck.addCard(leftDeck.getCard(0));
			
			leftDeck.takeFirstCard();
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
