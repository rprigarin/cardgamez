package cards.test;

import cards.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OutputWritingTest {

	@BeforeAll
	static void before() {
		System.out.printf("%s%n", "=======< OUTPUT WRITING TEST EXECUTED >=======");
	}
	
	@Test
	void testOutputWriting() {
		System.out.printf("%s%n%n", ">> testOutputWriting executed");
		
		// create a folder for testing player
		OutputWriting.setOutputFolder("./output_files/test/");
		OutputWriting.makeFolder();
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(OutputWriting.getOutputFolder() + "player1_output.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// run the OutputWriting functions
		OutputWriting.pickingCard(new Card(1).toString(), 1);
		OutputWriting.droppingCard(new Card(1).toString(), "[some deck]", 1);

		// check if there is something in the file
		try {
			Scanner reader = new Scanner(new File("./output_files/test/player1_output.txt"));
			String data = reader.nextLine();
			System.out.println(data);
			reader.close();
			
			assertNotNull(data);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// check that the file has the expected number of lines
	}
	
}
