package cards.test;
import cards.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.File;

class CardGameTest {
	
	@BeforeAll
	static void before() {
		System.out.printf("%s%n", "=======< CARD GAME TEST EXECUTED >=======");
	}

	@Test
	@Tag("UnitTest")
	void testSetup() {
		System.out.printf("%s%n%n", ">> testSetup executed");
		CardGame.setup();
		Assertions.assertTrue(CardGame.getPlayerNumber() >= 2);
		Assertions.assertNotNull(CardGame.getPackLocation());
		Assertions.assertTrue(CardGame.getPackLocation().contains(".txt"));
	}

	@Test
	@Tag("UnitTest")
	void testPackCreation() {
		System.out.printf("%n%n%s%n%n", ">> testPackCreation executed");
		int verifySize = 8 * CardGame.getPlayerNumber();
		Assertions.assertEquals(verifySize, CardGame.packCreation().size());
	}
	
	
	@Test
	@Tag("UnitTest")
	void testDeckCreation() {
		System.out.printf("%n%s%n%n", ">> testDeckCreation executed");
		CardGame.deckCreation();
		Assertions.assertEquals(CardGame.getPlayerNumber(), CardGame.deckList.size());
		System.out.println("Test was successfully carried out.");
	}
	
	@Test
	@Tag("UnitTest")
	void testPlayerCreation() {
		System.out.printf("%n%s%n%n", ">> testPlayerCreation executed");
		CardGame.playerCreation();
		Assertions.assertEquals(CardGame.getPlayerNumber(), CardGame.playerList.size());
		System.out.println("Test was successfully carried out.");
	}
	
	
	
	 @Test
	 @Tag("UnitTest") 
	 void testDistributeCards() { 
		 System.out.printf("%n%s%n%n",">> testDistributeCards executed");
		 System.out.println(CardGame.deckList.size());
		 CardGame.distributeCards();
		 System.out.println(CardGame.deckList.size());
		 for(CardDeck deck: CardGame.deckList) { 
			 System.out.println(deck.getSize());
			 Assertions.assertEquals(4, deck.getSize()); 	 
		 }  
	 }
	 
	 @Test
	 @Tag("UnitTest")
	 void testPlayerDeckAssignment() {
		 System.out.printf("%n%s%n%n",">> testPlayerDeckAssignment executed");
		 CardGame.playerDeckAssignment();
		 for(Player player : CardGame.playerList) {
			 Assertions.assertNotNull(player.getLeftDeck());
			 Assertions.assertNotNull(player.getRightDeck());
		 }
	 }

}