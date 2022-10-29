package cards;
import java.util.*;
import java.io.*;
public class CardGame {
	private static int playerNumber = 0;
	private static String packLocation = "";
	static Scanner input = new Scanner(System.in);
	
	public static void setup() {
		System.out.print("Please enter the number of players you would like: ");
		playerNumber = input.nextInt();
		while (!(playerNumber >= 2)) {
			System.out.print("Minimum number of players is 2. Please enter a valid number: ");
			playerNumber = input.nextInt();
		}
		
		System.out.print("Please enter location of pack to load: ");
		packLocation = input.next();
		while (!(packLocation.contains(".txt"))) {
			System.out.print("Pack location must be a plaintext file ('.txt'). Please enter a valid pack location: ");
			packLocation = input.next();
		}
		input.close();
	}
	
	public static void packCreation() {
		ArrayList<Integer> cardValues = new ArrayList<Integer>();
		int packSize = playerNumber * 8, maxValue = playerNumber * 2, denomination = 1;
		for(int i = 0; i < maxValue; i++) {
			for(int j = 0; j < 4; j++) {
				cardValues.add(denomination);
			}
			denomination +=1;
		}
		
		if(packSize == cardValues.size()) {
			System.out.println("They are the same size");
			Collections.shuffle(cardValues);
			for(int i = 0; i < cardValues.size(); i++) {
				System.out.println(cardValues.get(i)); 
			}
		} else {
			System.out.println("Pack size: " + packSize);
			System.out.println("Actual size: " + cardValues.size());
		}
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardGame.setup();
		CardGame.packCreation();

	}

}
