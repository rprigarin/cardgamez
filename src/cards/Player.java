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
			CardGame.gameOver = true;
		}
		
	}
	
	public void run() {
		while(!CardGame.gameOver)
		{
			synchronized(this) {
				// take and put cards from left and right decks
				takeAndPutCards();
				// check player hand for same value
				cardsOfSameValue();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void takeAndPutCards()
	{
		playerDeck.sortDeckByPreference(denomination);
		String droppedCard = playerDeck.getCard(CardDeck.SIZE - 1).toString();
		String pickedCard = leftDeck.getCard(0).toString();
		System.out.println("Player " + denomination + " deck: " + playerDeck.toString());
		
		try {
			rightDeck.addCard(playerDeck.takeLastCard());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Right deck: " + rightDeck.toString());
		System.out.println("Player deck: " + playerDeck.toString());
		
		playerDeck.addCard(leftDeck.getCard(0));
		
		try {
			leftDeck.takeFirstCard();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		OutputWriting.pickingCard(pickedCard, denomination);
		OutputWriting.droppingCard(droppedCard, playerDeck.toString(), denomination);
		System.out.println("Left Deck: " + leftDeck.toString());
		System.out.println("Player " + denomination + " deck after take and put operations: " + playerDeck.toString());
		System.out.println();
		
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
