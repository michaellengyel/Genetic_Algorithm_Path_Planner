package planner.main;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Class for containing the 2D coordinates of the points
 */

public class Map {
	
	private static Scanner scanner;
	
	private static int chromosomeLenght;
	public static Point[] mapPoints;
	
	Map(){ //Map(final int chromosomeLenght){
		// TODO Remove this when user input added
		this.chromosomeLenght = getlinesInFile();
		mapPoints = new Point[chromosomeLenght];
		populate(this);
	}
	
	// Function for adding a point to the map
	public void setMapPoint(Point point, int index){
		this.mapPoints[index] = point;
	}
	
	// Function for setting a point from the map
	public Point getMapPoint(int index) {
		Point point = this.mapPoints[index];
		return point;
	}
	
	// 
	private void populate(Map map) {
		openFile();
		int index = 0;
		while(scanner.hasNext()) {
			String xCoord = scanner.next();
			String yCoord = scanner.next();
			map.setMapPoint(new Point(Integer.parseInt(xCoord), Integer.parseInt(yCoord)), index);
			++index;
		}
		closeFile();
	}
	
	private void openFile() {
		try {
			scanner = new Scanner(new File("data.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.print("data.txt not found!");
			System.exit(0);
		}
	}

	private void closeFile() {
		scanner.close();
	}
	
	private int getlinesInFile() {
		openFile();
		int linesInFile = 0;
		while(scanner.hasNextLine()) {
			linesInFile++;
			scanner.nextLine();
		}
		closeFile();
		return linesInFile;
	}

	int getChromsomeLength() {
		return chromosomeLenght;
	}

}
