package cards.test;
import cards.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
	void testVerify() {
		System.out.printf("%n%n%s%n%n", ">> testVerify executed");
		
		// Cases where function should return true
		Assertions.assertTrue(CardGame.verify("valid.txt"));
		Assertions.assertTrue(CardGame.verify("no_win.txt"));
		
		// Cases where function should return false
		Assertions.assertFalse(CardGame.verify("negative_num.txt"));
		Assertions.assertFalse(CardGame.verify("too_many.txt"));
		Assertions.assertFalse(CardGame.verify("not_enough.txt"));
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
	 void testPlayerDeckAssignment() {
		 System.out.printf("%n%s%n%n",">> testPlayerDeckAssignment executed");
		 CardGame.playerDeckAssignment();
		 for(Player player : CardGame.playerList) {
			 Assertions.assertNotNull(player.getLeftDeck());
			 Assertions.assertNotNull(player.getRightDeck());
		 }
	 }

}
