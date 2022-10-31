package card.test;
import cards.CardGame;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CardGameTest {
	
	@BeforeAll
	static void before() {
		System.out.printf("%s%n%n", "@BeforeAll executed");
	}

	@Test
	@Tag("UnitTest")
	void testSetup() {
		System.out.printf("%s%n%n", "=======SETUP TEST EXECUTED=======");
		CardGame.setup();
		Assertions.assertTrue(CardGame.getPlayerNumber() >= 2);
	}

	@Test
	@Tag("UnitTest")
	void testPackCreation() {
		System.out.printf("%n%n%s%n%n", "=======PACKCREATION TEST EXECUTED=======");
		CardGame.packCreation();
		int verifySize = 8 * CardGame.getPlayerNumber();
		Assertions.assertEquals(verifySize, CardGame.packCreation().size());
	}
	
	
	@AfterAll
	static void after() {
		System.out.printf("%s","@AfterAll executed");
	}

}
