package planner.main;

// https://towardsdatascience.com/evolution-of-a-salesman-a-complete-genetic-algorithm-tutorial-for-python-6fe5d2b3ca35

public class Selector {
	
	private static Population population;
	
	private static final int PERCENT = 100;
	
	Selector(Population population){
		this.population = population;
	}
	
	// Roulette-wheel Selection (changd to elithism!)
	public static void roulettWheelSelection(int percentOfBestChosen) {
		orderFitness();
		calculatePopulationFitness();
		calculateIndividualRankElitism(percentOfBestChosen); // works at 40
		//calculateIndividualRankRouletteWheel();
	}
	
	// Fitness-proportional Selection
	
	// Scaling Selection
	
	// Rank Selection
	
	////////// Helper Functions //////////
	
	/*
	// Gives each chromosome a fitness rank in the current population (using bubble sort)
	private static void orderFitness() {
		int i, j , temp;
		boolean swapped;
		for(i = 0; i < population.getPopulationSize(); i++) {
			swapped = false;
			//population.chromosomes[i].setFitness(333); //debug
			for(j = 0; j < population.getPopulationSize() - i - 1; j++) {
				if(population.chromosomes[j].getFitness() > population.chromosomes[j+1].getFitness()) {
					temp = population.chromosomes[j].getFitness();
					population.chromosomes[j].setFitness(population.chromosomes[j+1].getFitness());
					population.chromosomes[j+1].setFitness(temp);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	*/
	
	// Gives each chromosome a fitness rank in the current population (using bubble sort)
		private static void orderFitness() {
			int i, j;
			Chromosome temp;
			boolean swapped;
			for(i = 0; i < population.getPopulationSize(); i++) {
				swapped = false;
				//population.chromosomes[i].setFitness(333); //debug
				for(j = 0; j < population.getPopulationSize() - i - 1; j++) {
					if(population.chromosomes[j].getFitness() > population.chromosomes[j+1].getFitness()) {
						temp = population.chromosomes[j];
						population.chromosomes[j] = population.chromosomes[j+1];
						population.chromosomes[j+1] = temp;
						swapped = true;
					}
				}
				if(swapped == false) {
					break;
				}
			}
		}
	
	// Calculate population fitness
	private static void calculatePopulationFitness() {
		int populationFitness = 0;
		for(int i = 0; i < population.chromosomes.length; i++) {
			populationFitness += population.chromosomes[i].getFitness();
		}
		 population.setPopulationLength(populationFitness);
	}
	
	// roulettWheelSelection()
	// Calculate individual chromosome fitness rank (Inverse proportion in roulette wheel)
	private static void calculateIndividualRankRouletteWheel() {
		int i, j;
		double temp;
		for(i = 0, j = population.getPopulationSize() - 1; i < population.getPopulationSize(); i++, j--) {
			temp = ((double) population.chromosomes[i].getFitness() / (double)population.getPopulationLength());
			//System.out.println(population.chromosomes[i].getFitness());
			//System.out.println(population.getPopulationLength());
			//System.out.println(temp);
			population.chromosomes[j].setRank(temp);
		}
	}
	
	private static void calculateIndividualRankElitism(int percentOfBestChosen) {
		// Calculate percent to chromosomes relation
		int numberOfSelectedElits = (int) Math.round((double)population.getPopulationSize() * ((double)percentOfBestChosen / (double)PERCENT));
		// Calculate length of the selected pieces
		int chosenPopulationSize = 0;
		for (int i = 0; i < numberOfSelectedElits; i++) {
			chosenPopulationSize += population.chromosomes[i].getFitness();
		}	
		//set the rank of the chosen chromosome relating to their fitness
		for(int i = 0; i < population.getPopulationSize(); i++) {
			double temp = ((double) population.chromosomes[i].getFitness() / (double)chosenPopulationSize);
			if(i < numberOfSelectedElits) {
				population.chromosomes[i].setRank(temp);
			} else {
				population.chromosomes[i].setRank(0.0);
			}
		}
	}


}
