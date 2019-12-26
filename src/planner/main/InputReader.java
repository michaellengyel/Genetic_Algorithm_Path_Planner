package planner.main;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputReader {

	private Scanner scanner;
	private String fileName;
	
	public InputReader(String fileName){
		this.fileName = fileName;
	}
	
	// Functional Operations
	
	// Read and Return the No. of points in the file, same as no of chromosomes (data.txt)
	public int getChromsomeSize() {
		openFile();
		int linesInFile = 0;
		while(scanner.hasNextLine()) {
			linesInFile++;
			scanner.nextLine();
		}
		closeFile();
		return linesInFile;
	}
	
	// Read and Return the population size (input.txt)
	public int getUserInput(int index) {
		openFile();
		int i = 0;
		int[] userInput = new int[8];
		while(scanner.hasNext()) {
			String inputName = scanner.next();
			String inputValue = scanner.next();
			userInput[i] = Integer.parseInt(inputValue);
			++i;
		}
		closeFile();
		return userInput[index];
	}
	
	//General File Operations
	private void openFile() {
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.print("data.txt not found!");
			System.exit(0);
		}
	}

	private void closeFile() {
		scanner.close();
	}
	
	// Getters and Setters
}
