package planner.main;

import java.util.Random;

public class Mutator {
	
	private static Population population;
	private static int percentPossibilityOfChromosomeMutation;
	private static int percentPossibilityOfGeneMutation;
	private static final int MAX_PERCENT = 1000;
	private static final int MAX_DOUBLE_PERCENT = 2000; // This is needed because we use swap mutation, minimum mutation is two, so we double the possibilities
	private static Random rand;
	
	Mutator(Population population, int percentPossibilityOfChromosomeMutation, int percentPossibilityOfGeneMutation) {
		this.population = population;
		this.percentPossibilityOfChromosomeMutation = percentPossibilityOfChromosomeMutation;
		this.percentPossibilityOfGeneMutation = percentPossibilityOfGeneMutation;
		rand = new Random();
	}
	
	public static void swapMutation() {
		for(int i = 0; i < population.getPopulationSize(); i++) {
			// If we roll a chromosome mutation
			int outerRand = rand.nextInt(MAX_PERCENT) + 1; // +1 to make 0 not included
			if((outerRand <= percentPossibilityOfChromosomeMutation) && (percentPossibilityOfChromosomeMutation <= MAX_PERCENT)) {
				for(int j = 0; j < population.getChromosomeLength(); j++) {
					// if we roll a gene mutation
					int innerRand = rand.nextInt(MAX_DOUBLE_PERCENT) + 1; // +1 to make 0 not included
					if((innerRand <= percentPossibilityOfGeneMutation) && (percentPossibilityOfGeneMutation <= MAX_PERCENT)) {
						int rand1 = rand.nextInt((population.getChromosomeLength()-1));
						int rand2 = rand.nextInt((population.getChromosomeLength()-1));
						int temp = population.chromosomes[i].pointOrder[rand1];
						population.chromosomes[i].pointOrder[rand1] = population.chromosomes[i].pointOrder[rand2];
						population.chromosomes[i].pointOrder[rand2] = temp;
					} else if (innerRand > percentPossibilityOfGeneMutation) {
						// Do nothing
					} else {
						System.err.println("ERROR in swapMutation() Inner");
						System.exit(0);
					}
				}
			} else if (outerRand > percentPossibilityOfChromosomeMutation) {
				// Do nothing
			} else {
				System.err.println("ERROR in swapMutation() Outer");
				System.exit(0);
			}
		}
	}

	// *** Helper function ***
	
	// Chromosome mutation percent to number
}
