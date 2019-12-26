package planner.main;

import java.util.Random;

public class Crossover {
	
	private static Population population;
	public static Population[] parentpopulations;
	//private static Population parentPopulation;
	private static Random rand;
	
	Crossover(Population population) {
		this.population = population;
		rand = new Random();
	}
	
	// Do ordered crossover because each city may only be visited once
	// Concept described here: https://towardsdatascience.com/evolution-of-a-salesman-a-complete-genetic-algorithm-tutorial-for-python-6fe5d2b3ca35
	public static void orderedCrossover(int numberOfParents) {
		makeCorssoverPopulation(numberOfParents);
		mergeTwoParents();
	}
	
	//////////Helper Functions //////////
	
	// Make two groups of parents to crossover, random chance of being selected is based on their fitness rank given in the Selector
	// This function contains the proportion chance logic (based on video below)
	// https://www.youtube.com/watch?v=MGTQWV1VfWk
	private static void makeCorssoverPopulation(int numberOfParents) {	
		parentpopulations = new Population[numberOfParents];
		for(int i = 0; i < parentpopulations.length; i++) {
			parentpopulations[i] = new Population(population.getPopulationSize(), population.getChromosomeLength());
			for(int j = 0; j < parentpopulations[i].getPopulationSize(); j++) {
				//for each chromosome, call the function which returns an ID based on a weighted random.
				//System.out.println(weightedRandom());
				int wr = weightedRandom();
				for(int k = 0; k < population.chromosomeLength; k++) {
					//System.out.print(population.chromosomes[wr].pointOrder[k] + " ");
					//Setting the parentsPopulations chromosomes equal to the weighted random chosen chromosomes
					parentpopulations[i].chromosomes[j].pointOrder[k] = population.chromosomes[wr].pointOrder[k];
				}
				//System.out.println();
			}
			
		}
	}
	
	// Select some individuals from the population to crossover (might need to be placed into the Selector class)
	// based on their rank. The higher the rank the higher the chance of getting selected
	private static int weightedRandom() {
		double randomDouble = rand.nextDouble();
		for(int i = 0; i < population.getPopulationSize(); i++) {
			if(randomDouble < population.chromosomes[i].getRank()) {
				//System.out.println(randomDouble);
				return i;
			}
			randomDouble -= population.chromosomes[i].getRank();
		}
		System.err.println("Error in weightedRandom()");
		return 666;
	}
	
	// Merge Parents(logic taken from link below, in this case we have to abide by some rules(ordered crossover))
	// Only implemented for 2 parents
	// https://towardsdatascience.com/evolution-of-a-salesman-a-complete-genetic-algorithm-tutorial-for-python-6fe5d2b3ca35
	private static void mergeTwoParents() {
		int rand1, rand2;
		for(int i = 0; i < population.getPopulationSize(); i++) {
			rand1 = rand.nextInt(population.getChromosomeLength());
			rand2 = rand.nextInt(population.getChromosomeLength());
			// This for copies in the random segment from parent 1
			//System.out.println(rand1 +" "+ rand2); // RANDOM RANGE DEBUG INFO!!!

			// Set all used number to invalid
			for(int j = 0; j < parentpopulations[1].getChromosomeLength(); j++) {
				for(int k = getLesser(rand1, rand2); k <= getGreater(rand1, rand2); k++) {
					if(parentpopulations[1].chromosomes[i].pointOrder[j] == parentpopulations[0].chromosomes[i].pointOrder[k]) {
						parentpopulations[1].chromosomes[i].pointOrder[j] = parentpopulations[1].chromosomes[i].pointOrder.length + 1;
					}
				}
				
			}
			
			// Set base population segment equal to parentPopulation[0]
			for(int j = 0; j < population.getChromosomeLength(); j++) {
				if((j >= getLesser(rand1, rand2) && (j <= getGreater(rand1, rand2)))) {
					population.chromosomes[i].pointOrder[j] = parentpopulations[0].chromosomes[i].pointOrder[j];
				} else {
					
				}
				
			}

			int parentPopulationID = 0;
			int populationID = 0;
			//(populationID < parentpopulations[1].getChromosomeLength()) || (parentPopulationID < parentpopulations[1].getChromosomeLength())
			while((populationID < parentpopulations[1].getChromosomeLength()) && (parentPopulationID < parentpopulations[1].getChromosomeLength())) {
				boolean isValid = !(parentpopulations[1].chromosomes[i].pointOrder[parentPopulationID] == parentpopulations[1].getChromosomeLength() + 1);
				boolean isInrange = ((populationID >= getLesser(rand1, rand2)) && (populationID <= getGreater(rand1, rand2)));
				// If invalid and not in range
				if(!isValid && !isInrange) {
					parentPopulationID++;
				}
				// if valid and not range
				else if(isValid && !isInrange) {
					population.chromosomes[i].pointOrder[populationID] = parentpopulations[1].chromosomes[i].pointOrder[parentPopulationID];
					populationID++;
					parentPopulationID++;		
				}
				// if valid and in range
				else if (isValid && isInrange){
					//population.chromosomes[i].pointOrder[populationID] = parentpopulations[1].chromosomes[i].pointOrder[parentPopulationID];
					populationID++;
					//parentPopulationID++;
				}
				// is invalid and inrange
				else if (!isValid && isInrange){
					parentPopulationID++;		
				}
				else {
					System.out.println("ERROR");
				}
			}
	
		}
			
		
	}
	
	
	// Return the smaller of two numbers
	private static int getLesser(int number1, int number2) {
		if(number1 > number2) {
			return number2;
		} else {
			return number1;
		}
	}
	
	// Return the larger of two number
	private static int getGreater(int number1, int number2) {
		if(number1 < number2) {
			return number2;
		} else {
			return number1;
		}
	}

	////////// Getter/Setter Functions //////////

}
