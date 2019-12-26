package planner.main;

import java.util.Formatter;

public class OutputWriter {
	
	private Formatter formatter;
	
	public OutputWriter(String filename) {
		try {
			formatter = new Formatter(filename);
		} catch (Exception e) {
			System.out.println("Could not make output file.");
			System.exit(0);
		}
	}
	
	public void writeCoords(int bestChromosomeLength) {
		formatter.format("%d%n", bestChromosomeLength, '\n');
		formatter.flush();
	}
	
	public void closeFile() {
		formatter.close();
	}

}
