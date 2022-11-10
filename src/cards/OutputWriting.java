package cards;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputWriting {
	
	// Can't properly test these yet. Need to make sure that the cards are being taken and dropped first.
	
	protected static void pickingCard(String cardValue, int denom) {
		try {
			String filename = "player" + denom + "_output.txt";
			String dir = CardGame.outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println("Player " + denom + " picked up " + cardValue + " from Deck " + denom);
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	protected static void droppingCard(String cardValue, String deck, int denom) {
		try {
			String filename = "player" + denom + "_output.txt";
			String dir = CardGame.outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println("Player " + denom + " dropped " + cardValue + " to Deck " + (denom + 1));
			printWriter.printf("%s %d %s %s%n%n", "Player" , denom , " current hand: " , deck);
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	protected static void writeInitialHand(String filename, Player player) {
		try {
			String dir = CardGame.outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %s%n%n", "Initial", player.toString());
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	protected static void writingToDeckFile(String filename, CardDeck deck, int deckNum) {
		try {
			String dir = CardGame.outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %d %s %s", "Deck", (deckNum + 1), "contents:", deck.toString());
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();	
		}
	}
	
	protected static void playerWonGame(String filename, Player player) {
		try {
			String dir = CardGame.outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %d %s%n%s %s%n%s %d %s%n%n", "Player", player.getDenomination(), "won.", "Final hand:", player.toString(), "Player",
					player.getDenomination(), "exits.");
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}

	protected static void playerLostGame(String filename, Player player, int winner) {
		try {
			String dir = CardGame.outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %d %s%n%s %s%n%s %d %s%n%n", "Player", winner, "won.", "Final hand:", player.toString(), "Player",
					player.getDenomination(), "exits.");
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
}
