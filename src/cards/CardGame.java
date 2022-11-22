package cards;
import java.util.*;
import java.io.*;
import java.nio.file.*;
public class CardGame {
	
	// Attributes
	static Scanner input = new Scanner(System.in);
	protected static volatile boolean gameOver = false;

	protected static int playerNumber = 0;
	protected static String packLocation = "";
	protected static int gameWinner = 0;
	
	private static ArrayList<Integer> cardValues = new ArrayList<Integer>();
	
	public static List<Player> playerList = new ArrayList<Player>();
	public static List<CardDeck> deckList = new ArrayList<CardDeck>();
	public static List<String> playerFiles = new ArrayList<String>();
	public static List<String> deckFiles = new ArrayList<String>();
	
	// verify path of the card pack
	public static boolean verify(String filename) {
		try {
			Path filePath = Paths.get(filename);
            List<String> lines = Files.readAllLines(filePath);
            if(lines.size() == 8 * playerNumber) {
                for(String line: lines) {
	                try {
	                    if(Integer.parseInt(line) < 0) {
	                    	System.out.printf("%n%s%n", "Negative number inside pack.");
	                        return false;
	                    }
	                    cardValues.add(Integer.parseInt(line));
	                } catch(NumberFormatException e) {
	                	System.out.printf("%n%s%n", "Not all of the values are numbers.");
	                	cardValues.clear();
	                    return false;
	                }
                }
            } else { 
            	System.out.printf("%n%s%n", "Pack size must (8 x number of players you entered).");
                return false;
            }
            
        } catch(IOException e) {
        	System.out.println("Error in the IO. Please restart program.");
        	System.exit(0);
        }     

		return true;
    }
	
	// check if the card pack specified can produce a winning hand
	private static boolean canProduceWinningHand() {
		Collections.sort(cardValues);
		for(int i: cardValues) {
			if(Collections.frequency(cardValues, i) == 4) {
				return true;
			}
		}
		
		return false;
	}
	
	// take player input
	public static void setup() {
		
		String tempInput = "";
		boolean validInput = false;
		boolean validBoundary = false;
		
		// get the number of players
		System.out.print("Please enter the number of players you would like: ");
		tempInput = input.nextLine();
		while(!validInput) {
			try
			{
				playerNumber = Integer.parseInt(tempInput);
				validInput = true;
			} catch(NumberFormatException e)
			{
				//e.printStackTrace();
				validInput = false;
				System.out.print("Invalid input, please enter a number: ");
				tempInput = input.nextLine();
			}
		}

		if(validInput) {
			if(playerNumber < 2 || playerNumber > 100) {
				validBoundary = false;
			}
			
			/* Verification to ensure that there are two or more players.
			 * Repeats until the user enters a valid number 
			 */
			
			while(!validBoundary) {
				if(playerNumber < 2) {
					System.out.println("Minimum number of players required is 2. Please enter a valid number: ");
					playerNumber = input.nextInt();
					continue;
				} else if(playerNumber > 100) {
					System.out.println("Maximum number of players is 100. Please enter a valid number: ");
					playerNumber = input.nextInt();
					continue;
				}
				validBoundary = true;
			}
		}
		
		// Gets the name of the file the user would like to store the pack in 
		System.out.print("Please enter location of pack to load: ");
		packLocation = input.next();
		
		Path filePath = Paths.get(packLocation);
		boolean fakeFile = Files.notExists(filePath);
		
		while(fakeFile) {
            System.out.print("Pack location entered does not exist. Please enter a valid pack location: ");
            packLocation = input.next();
            filePath = Paths.get(packLocation);
            fakeFile = Files.notExists(filePath);
        }
		/* Verification to ensure that the pack file is a plain text file and that it contains a valid pack.
		 * Repeats until the user enters a location that is a plain text file with a valid pack
		 */
		while ((packLocation.length() < 4) || (!(packLocation.substring(packLocation.length() - 4,
				packLocation.length()).equals(".txt"))) || (verify(packLocation) == false)) {
			System.out.print("Invalid pack location. Please enter a valid pack location: ");
			packLocation = input.next();
		}
		
		
		if(canProduceWinningHand() == false) {
			String response = "";
			System.out.println();
			System.out.println("The pack that you requested cannot produce a winning hand, leading to the game running infinitely.\nWould you like to continue? (y/n)");
			response = input.next();
			
			if(response.equals("n")) {
				// terminate program
				System.out.println("Restart program to try again.");
				System.exit(0);
			}
		}
		input.close();
	}
	
	public static int getPlayerNumber() {
		return playerNumber;
	}
	
	public static String getPackLocation() {
		return packLocation;
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
	
	public static void playerDeckAssignment()
	{
		for(int i = 0; i < playerNumber; i++)
		{
			playerList.get(i).setLeftDeck(deckList.get(i));
			
			if(i + 1 < playerNumber)
			{
				playerList.get(i).setRightDeck(deckList.get(i+1));
			}
			else {
				playerList.get(i).setRightDeck(deckList.get(0));
			}
		}
	}
	
	private static void distributeCards()
	{
		if(cardValues.size() != 0) {
			// distribute cards to players
			for(int i = 0; i <= cardValues.size()/2; i++) {
				playerList.get(i % playerNumber).getHand().loadDeck(new Card(cardValues.get(i)));
			}
			
			// distribute cards to decks
			for(int i = cardValues.size()/2; i < cardValues.size(); i++) {
				deckList.get(i % playerNumber).loadDeck(new Card(cardValues.get(i)));
			}
			
		}

	}
	
	public static boolean isGameOver() {
		return gameOver;
	}
	
	public static void createFiles() {
		
		for(int i = 1; i <= playerNumber; i++) {
			try {
				File pFile = new File(OutputWriting.getOutputFolder() + "player" + i  + "_output.txt");
				File dFile = new File(OutputWriting.getOutputFolder() + "deck" + i + "_output.txt");
				
				// Creating player files and adding them to a list 
				if(pFile.createNewFile()) {
					playerFiles.add(pFile.getName());
				} else {
					playerFiles.add(pFile.getName());
				}
				
				// Creating deck files and adding them to a list 
				if(dFile.createNewFile()) {
					deckFiles.add(dFile.getName());
				} else {
					deckFiles.add(dFile.getName());
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("%n%n%s %s", "All files can be found in:", OutputWriting.getOutputFolder());
	}
	
	protected static void startGame() {
		 for(int i = 0; i < playerList.size(); i++) {
			 if(gameWon(i) == true) {
				 OutputWriting.writingToDeckFile(deckFiles.get(i), deckList.get(i), i);
				 if(i != gameWinner) {
					 OutputWriting.playerLostGame(playerFiles.get(i), playerList.get(i), gameWinner);
				 }
			 } else {
				 new Thread(playerList.get(i)).start();
			 }
		 }
	}
	
	protected static boolean gameWon(int index) {
		playerList.get(index).cardsOfSameValue();
		  if(gameOver) {
			  OutputWriting.playerWonGame(playerFiles.get(index), playerList.get(index));
		  } 
		  
		  return false;
	}

	public static void main(String[] args) {
		// take user input for the number of players and the location of the pack
		setup();
		
		
		// create a folder for output files
		OutputWriting.makeFolder();
		
		// creation of players and decks
		playerCreation();
		deckCreation();
		
		// fill decks with cards from pack
		distributeCards();
		
		// assign decks to players
		playerDeckAssignment();
		
		
		// creating player files and adding initial hands
		createFiles();
		
		 for(int i = 0; i < playerFiles.size(); i++) {
		 	OutputWriting.writeInitialHand(playerFiles.get(i), playerList.get(i));
		 }
		 
		 
		 /* Iterate through the list of players and check if any of them have a winning hand to start the game.
		  * If they do, the game will end and the output will be written to the files. Otherwise, the Player 
		  * threads will start
		  */
		  startGame();
		  
		  
		  // iterate through the list of deck filenames and output the final decks to the files 
		  for(int i = 0; i < deckFiles.size(); i++) {
			 	OutputWriting.writingToDeckFile(deckFiles.get(i), deckList.get(i), i);
			 }
		  

	}

}
