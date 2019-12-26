package planner.main;

import java.awt.Point;

public class Evaluator {
	
	private static Population population;
	private static Map map;
	
	public Evaluator(Population population, Map map) {
		this.population = population;
		this.map = map;
	}
	
	// Calculates the fitness of the population (the length of all the paths)
	public static void calculateFitness() {
		int totalLength = 0;
		double length = 0;
		for(int i = 0; i < population.getPopulationSize(); i++) {
			for(int j = 0; j < population.getChromosomeLength() - 1; j++) {
				//population.chromosomes[i].pointOrder[j] = j;
				Point point1 = map.getMapPoint(population.chromosomes[i].pointOrder[j]);
				Point point2 = map.getMapPoint(population.chromosomes[i].pointOrder[j+1]);
				double xLength = Math.abs(point1.x - point2.x);
				double yLength = Math.abs(point1.y - point2.y);
				length = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
				//System.out.println("Two point dist: " + length);
				totalLength += length;
			}
			//System.out.println(totalLength);
			population.chromosomes[i].setFitness((int)totalLength);
			totalLength = 0;
		}
	}

}
