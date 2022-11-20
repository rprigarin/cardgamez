package cards.test;

import cards.CardDeck;
import cards.Card;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CardDeckTest {

	@BeforeAll
	static void before() {
		System.out.printf("%s%n", "=======< CARD DECK TEST EXECUTED >=======");
	}
	
	@Test
	void testSortDeckByPreference() {
		System.out.printf("%s%n%n",">> testSortDeckByPreference executed");
		CardDeck d1 = new CardDeck();
		
		// fill the deck with cards to make [1,2,4,1]
		d1.loadDeck(new Card(1));
		d1.loadDeck(new Card(2));
		d1.loadDeck(new Card(4));
		d1.loadDeck(new Card(1));
		
		// sort deck by preference of 2 to make [2,1,1,4]
		d1.sortDeckByPreference(2);
		
		// sortedList shows how the deck should look like
		ArrayList<Integer> sortedList = new ArrayList<Integer>() {
			{
				add(2);
				add(1);
				add(1);
				add(4);
			}
		};
		
		for(int i = 0; i < CardDeck.SIZE; i++)
		{
			assertEquals(d1.getCard(i).getValue(), sortedList.get(i));
		}
		
	}

}
