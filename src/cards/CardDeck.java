package cards;

import java.util.ArrayList;

public class CardDeck {
	
	private volatile ArrayList<Card> deck;
	private final int SIZE = 4;
	
	public CardDeck()
	{
		deck = new ArrayList<Card>();
	}
	
	public void addCard(Card c)
	{	
		// limit addition of cards to 4
		if(deck.size() < SIZE)
		{
			deck.add(c);
		}

	}
	
	public Card takeFirstCard()
	{
		// NOTE: need to add a check so as to not remove from empty array list
		return deck.remove(0);
	}
	
	public Card takeLastCard()
	{
		// NOTE: need to add a check so as to not remove from empty array list
		return deck.remove(deck.size() - 1);
	}
	
	public ArrayList<Integer> getCardValues()
	{
		return new ArrayList<Integer>();
	}
	
	
}
