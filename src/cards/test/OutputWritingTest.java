package cards.test;

import cards.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class OutputWritingTest {

	@Test
	void testPickingCard() {
		OutputWriting.pickingCard(new Card(1).toString(), 1);
		
		// check that the file was created
		try {
			Scanner reader = new Scanner(new File("./output_files/player1_output.txt"));
			String data = reader.nextLine();
			System.out.println(data);
			reader.close();
			
			assertNotNull(data);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
