package cards;
import java.util.*;
import java.io.*;
public class CardGame {
	protected static int playerNumber = 0;
	protected static String packLocation = "";
	static Scanner input = new Scanner(System.in);
	
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
	
	public static ArrayList<Integer> packCreation() {
		ArrayList<Integer> cardValues = new ArrayList<Integer>();
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
			
			// Ensuring that this the only file with this name
			if(packFile.createNewFile()) {
				System.out.printf("%n%s %s", "Pack was successfully stored in:", packLocation);
			} else {
				System.out.printf("%n%s", "File already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Writing the pack to the file to be stored 
		try {
			FileWriter fWriter = new FileWriter(packLocation);
			
			// Gets every value and converts it to String to make it readable in the file
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



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardGame.setup();
		CardGame.packCreation();
		CardGame.storingPack(CardGame.packCreation());

	}

}
