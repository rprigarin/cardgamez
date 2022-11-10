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


}
