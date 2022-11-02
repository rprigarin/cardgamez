package cards;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public void sortDeck()
	{
		// brute force reconstruction of the deck
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < SIZE; i++)
		{
			a.add(deck.get(i).getValue());
		}
		
		Collections.sort(a);
		
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		for(int i = 0; i < SIZE; i++)
		{
			tempDeck.add(new Card(a.get(i)));
		}
		
		deck = tempDeck;
		
	}
	
	public void sortDeckByPreference(int preference)
	{
		sortDeck();
		
		if(preference != 1)
		{
			for(int i = 0; i < SIZE; i++)
			{
				if(deck.get(i).getValue() % preference == 0)
				{
					deck.add(0, deck.remove(i));
				}
			}

		}
	}
	
	public ArrayList<Integer> getCardValues()
	{
		return new ArrayList<Integer>();
	}
	
	public int getSize() {
		return deck.size();
	}
	
	public String toString()
	{
		String cards = "";
		
		for(int i = 0; i < deck.size(); i++)
		{
			cards += deck.get(i).getValue() + " ";
		}
		
		return "[ " +  cards + "]";
	}
	
}
