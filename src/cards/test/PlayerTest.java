package cards.test;

import cards.Player;
import cards.CardDeck;
import cards.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testTakeAndPutCards() {
		Player somePlayer = new Player(1, new CardDeck(), new CardDeck());
		
		// add cards to decks
		for(int i = 0; i < CardDeck.SIZE; i++) {
			somePlayer.leftDeck.addCard(new Card(i));
			somePlayer.rightDeck.addCard(new Card(i));
			somePlayer.getHand().addCard(new Card(i));
		}
		
		somePlayer.takeAndPutCards();
		
		Assertions.assertEquals(4, somePlayer.getHand().getSize());
		Assertions.assertEquals(3, somePlayer.leftDeck.getSize());
		Assertions.assertEquals(5, somePlayer.rightDeck.getSize());
	}
	
	@Test
	void testCardOfSameValue() {
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		
		// add same cards to player1 and different cards to player2
		for(int i = 0; i < CardDeck.SIZE; i++) {
			player1.getHand().addCard(new Card(6));
			player2.getHand().addCard(new Card(i));
		}
		
		// and three of the same cards and one different card to player3
		player3.getHand().addCard(new Card(2));
		player3.getHand().addCard(new Card(3));
		player3.getHand().addCard(new Card(2));
		player3.getHand().addCard(new Card(2));
		
		player1.cardsOfSameValue();
		player2.cardsOfSameValue();
		player3.cardsOfSameValue();
		
//		Assertions.assertTrue(player1.getGameOver());
//		Assertions.assertFalse(player2.getGameOver());
//		Assertions.assertFalse(player3.getGameOver());
		
	}

}
