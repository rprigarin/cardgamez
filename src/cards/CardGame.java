package cards;
import java.util.*;
import java.io.*;
public class CardGame {
	protected static int playerNumber = 0;
	protected static String packLocation = "";
	static Scanner input = new Scanner(System.in);
	public static List<Player> playerList = new ArrayList<Player>();
	public static List<CardDeck> deckList = new ArrayList<CardDeck>();
	private static ArrayList<Integer> cardValues = new ArrayList<Integer>();
	public static List<String> playerFiles = new ArrayList<String>();
	public static List<String> deckFiles = new ArrayList<String>();
	
	// take player input
	public static void setup() {
		
		// Getting user input for number of players 
		System.out.print("Please enter the number of players you would like: ");
		playerNumber = input.nextInt();
		
		/* Verification to ensure that there are two or more players.
		 * Repeats until the user enters a valid number 
		 */
		while (!(playerNumber >= 2)) {
			System.out.print("Minimum number of players is 2. Please enter a valid number: ");
			playerNumber = input.nextInt();
		}
		
		// Gets the name of the file the user would like to store the pack in 
		System.out.print("Please enter location of pack to load: ");
		packLocation = input.next();
		
		/* Verification to ensure that the pack file is a plain text file.
		 * Repeats until the user enters a valid location 
		 */
		while (!(packLocation.contains(".txt"))) {
			System.out.print("Pack location must be a plaintext file ('.txt'). Please enter a valid pack location: ");
			packLocation = input.next();
		}
		input.close();
	}
	
	public static int getPlayerNumber() {
		return playerNumber;
	}
	
	public static String getPackLocation() {
		return packLocation;
	}
	
	public static ArrayList<Integer> packCreation() {
		int packSize = playerNumber * 8, maxValue = playerNumber * 2, denomination = 1;
		
		// Generating and storing values of the cards in the packs 
		for(int i = 0; i < maxValue; i++) {
			for(int j = 0; j < 4; j++) {
				cardValues.add(denomination);
			}
			denomination +=1;
		}
		
		// Making sure that the size of the pack is 8n 
		if(packSize == cardValues.size()) {
			Collections.shuffle(cardValues);
			for(int i = 0; i < cardValues.size(); i++) {
				System.out.println(cardValues.get(i)); 
			}
		}
		return cardValues;
	}
	
	private static void storingPack(ArrayList<Integer> pack) {
		
		// Creating the file to store the pack
		try {
			File packFile = new File(packLocation);
			if(packFile.createNewFile()) {
				System.out.printf("%n%s %s", "Pack was successfully stored in:", packLocation);
			} else {
				System.out.printf("%n%s", "File already exists; overwriting pack file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileWriter fWriter = new FileWriter(packLocation);
			for(int i = 0; i < pack.size(); i++) {
				String value = Integer.toString(pack.get(i));
				fWriter.write(value);
				if(i != pack.size() - 1) {
					fWriter.write('\n');
				}
			}
			
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void playerCreation() {
		for(int i = 1; i <= CardGame.getPlayerNumber(); i++) {
			playerList.add(new Player(i));
		}
	}
	
	public static void deckCreation() {
		for(int i = 1; i <= CardGame.getPlayerNumber(); i++) {
			deckList.add(new CardDeck());
		}
	}
	
	public static void distributeCards()
	{
		if(cardValues.size() != 0) {
			// distribute to players
			for(int i = 0; i <= cardValues.size()/2; i++) {
				playerList.get(i % playerNumber).getDeck().addCard(new Card(cardValues.get(i)));
			}
			
			// distribute to decks
			for(int i = cardValues.size()/2; i < cardValues.size(); i++) {
				deckList.get(i % playerNumber).addCard(new Card(cardValues.get(i)));
			}
			
		}

	}
	
	protected static String outputFolder() {
		String directory = "./output_files/";
		new File(directory).mkdirs();	
		return directory;
	}
	
	public static void createPlayerFiles() {
		
		for(int i = 1; i <= playerNumber; i++) {
			try {
				File pFile = new File(outputFolder() + "player" + i  + "_output.txt");
				if(pFile.createNewFile()) {
					playerFiles.add(pFile.getName());
					System.out.printf("%n%s %s%n", "File directory: ", pFile.getAbsolutePath());
				} else {
					System.out.println("File already exists. Contents being overwritten.");
					playerFiles.add(pFile.getName());
				}
			} catch(IOException e) {
				System.out.println("An error occured.");
				e.printStackTrace();
			}
		}
	}
	
	

	private static void writingToPlayerFile(String filename, Player player) {
		try {
			String dir = outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %s", "Initial", player.toString());
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void createDeckFiles() {
			
			for(int i = 1; i <= playerNumber; i++) {
				try {
					File dFile = new File(outputFolder() + "deck" + i  + "_output.txt");
					if(dFile.createNewFile()) {
						deckFiles.add(dFile.getName());
						System.out.printf("%n%s %s%n", "File directory: ", dFile.getAbsolutePath());
					} else {
						System.out.println("File already exists. Contents being overwritten.");
						deckFiles.add(dFile.getName());
					}
				} catch(IOException e) {
					System.out.println("An error occured.");
					e.printStackTrace();
				}
			}
		}
	

	private static void writingToDeckFile(String filename, CardDeck deck, int deckNum) {
		try {
			String dir = outputFolder() + filename;
			FileWriter fileWriter = new FileWriter(dir);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s %d %s %s", "Deck", (deckNum + 1), "contents:", deck.toString());
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();	
		}
	}

	public static void main(String[] args) {
		// input; pack creation
		setup();
		storingPack(packCreation());
		
		// create of players and decks
		playerCreation();
		deckCreation();
		
		// fill decks with cards from pack
		distributeCards();
		
		
		// Remove from the final product. Not needed
		for(int i = 0; i < playerList.size(); i++) {
			System.out.printf("%n%s%n",playerList.get(i).toString());
		}
		
		for(int i = 0; i < deckList.size(); i++) {
			System.out.printf("%n%s %d %s%n","Deck", (i + 1), deckList.get(i).toString());
		}
		
		// Creating player files and adding initial hands
		createPlayerFiles();
		
		 for(int i = 0; i < playerFiles.size(); i++) {
		 	writingToPlayerFile(playerFiles.get(i), playerList.get(i));
		 }
		 
		 CardGame.createDeckFiles();
		 
		 for(int i = 0; i < deckFiles.size(); i++) {
			 writingToDeckFile(deckFiles.get(i), deckList.get(i), i);
		}
		 
		
	}

}
