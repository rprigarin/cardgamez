package cards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputWriting {
	
	private static String outputPath = "./output_files/";
	
	// Create output folders for storing player and deck logs
	public static void makeFolder() {
		new File(outputPath).mkdirs();	
	}
	
	public static String getOutputFolder()
	{
		return outputPath;
	}
	
	public static void setOutputFolder(String path)
	{
		outputPath = path;
	}
	
	public static void pickingCard(String cardValue, int denom) {
		try {
			String filename = "player" + denom + "_output.txt";
			String dir = outputPath + filename;
			FileWriter fileWriter = new FileWriter(dir, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println("Player " + denom + " picked up " + cardValue + " from Deck " + denom);
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void droppingCard(String cardValue, String deck, int denom) {
		try {
			String filename = "player" + denom + "_output.txt";
			String dir = outputPath + filename;
			FileWriter fileWriter = new FileWriter(dir, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			if(denom == CardGame.playerNumber) {
				printWriter.println("Player " + denom + " dropped " + cardValue + " to Deck " + 1);
			} else {
				printWriter.println("Player " + denom + " dropped " + cardValue + " to Deck " + (denom + 1));
			}
			printWriter.printf("%s %d %s %s%n%n", "Player" , denom , "current hand: " , deck);
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void writeInitialHand(String filename, Player player) {
		try {
			String dir = outputPath + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %s%n%n", "Initial", player);
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void writingToDeckFile(String filename, CardDeck deck, int deckNum) {
		try {
			String dir = outputPath + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %d %s %s", "Deck", (deckNum + 1), "contents:", deck);
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();	
		}
	}
	
	public static void playerWonGame(String filename, Player player) {
		try {
			String dir = outputPath + filename;
			FileWriter fileWriter = new FileWriter(dir, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %d %s%n%s %s%n%s %d %s%n%n", "Player", player.getDenomination(), "won.", "Final hand:", player, "Player",
					player.getDenomination(), "exits.");
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}

	public static void playerLostGame(String filename, Player player, int winner) {
		try {
			String dir = outputPath + filename;
			FileWriter fileWriter = new FileWriter(dir, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %d %s %d %s%n%s %s%n%s %d %s%n%n", "Player", player.getDenomination(), "has been informed Player", winner, "won.", "Final hand:", player, "Player",
					player.getDenomination(), "exits.");
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
}
