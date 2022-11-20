package cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
	
	private volatile ArrayList<Card> deck;
	public static final int SIZE = 4;
	
	public CardDeck()
	{
		deck = new ArrayList<Card>();
	}
	
	// adds cards based on size limit
	public void loadDeck(Card c)
	{	
		if(deck.size() < SIZE)
		{
			deck.add(c);
		}

	}
	
	// adds cards (deck use only)
	public void addCard(Card c) {
		deck.add(c);
	}
	
	public Card getCard(int index)
	{
		return deck.get(index);
	}
	
	public Card takeFirstCard() throws IndexOutOfBoundsException
	{
		return deck.remove(0);
	}
	
	public Card takeLastCard() throws IndexOutOfBoundsException
	{
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
		// sort deck normally before applying preference
		sortDeck();
		
		// perform preference sort
		if(preference != 1)
		{
			for(int i = 0; i < SIZE; i++)
			{
				if(deck.get(i).getValue() - preference == 0)
				{
					deck.add(0, deck.remove(i));
				} else {
					deck.add(deck.remove(i));
				}
			}

		}
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
