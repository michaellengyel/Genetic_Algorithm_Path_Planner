package planner.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Main{
	
	// Declare user types
	public static Map map;
	public static Population population;		
	public static Artist artist;
	public static Window window;
	public static InputReader mapLengthInputReader;
	public static InputReader userSettingsInputReader;
	public static OutputWriter outputWriter;
			
	public static void main(String[] args) {
		
		mapLengthInputReader = new InputReader("data.txt");
		userSettingsInputReader = new InputReader("input.txt");
		outputWriter = new OutputWriter("output.txt");
		
		final int CHROMOSOME_LENGTH = mapLengthInputReader.getChromsomeSize(); // Equals to No. of points
		final int POPULATION_SIZE = userSettingsInputReader.getUserInput(0);
		final int NUMBER_OF_CYCLES = userSettingsInputReader.getUserInput(1);
		final int ELITISM_RATIO = userSettingsInputReader.getUserInput(2);
		final int CHROMOSOME_MUTATION_PROBABILITY = userSettingsInputReader.getUserInput(3);
		final int GENE_MUTATTION_PROBABILITY = userSettingsInputReader.getUserInput(4);
		final int DISPLAY_WIDTH = userSettingsInputReader.getUserInput(5);
		final int DISPLAY_HEIGHT = userSettingsInputReader.getUserInput(6);
		final int ANIMATION_ON = userSettingsInputReader.getUserInput(7);
		
		// Initialize user type object
		map = new Map();
		population = new Population(POPULATION_SIZE, CHROMOSOME_LENGTH);
		artist = new Artist(population, map);
		// Possibility to turn off the animation
		if(ANIMATION_ON == 1) {
			window = new Window(DISPLAY_WIDTH, DISPLAY_HEIGHT, "TSP with Genetic Algorithm | Unofficial Release | By Peter Lengyel", artist);
		}
		
		// Initialize random population of chromosomes
		init(population);
		
		int numberOfCycles = 0;
		while(numberOfCycles != NUMBER_OF_CYCLES + 2) {
		
			// Evaluate fitness
			Evaluator evaluator = new Evaluator(population, map);
			evaluator.calculateFitness();
			
			// Stop Condition
			// TODO Implement
			
			// Selection
			Selector selector = new Selector(population);
			selector.roulettWheelSelection(ELITISM_RATIO);
			
			// Crossover
			Crossover crossover = new Crossover(population);
			crossover.orderedCrossover(2);
			
			// Mutation
			Mutator mutator = new Mutator(population, CHROMOSOME_MUTATION_PROBABILITY, GENE_MUTATTION_PROBABILITY); // works at 100, 100 for 1500 population
			mutator.swapMutation();

			// Write Fittest Path to file
			outputWriter.writeCoords(printFittestPath(population));
			
			// Animation
			if(ANIMATION_ON == 1) {
				artist.getPopulation(population);
				render();
			}
			
			numberOfCycles++;
		}	
		
		outputWriter.closeFile();
	}
	
	
	
	/*
	 * Initial population of random chromosomes
	 * Takes a chromosome population and fills each chromosome with a
	 * randomly generated 
	 */
	public static void init(Population initialPopulation){		
		for(int i = 0; i < initialPopulation.chromosomes.length; i++) {
			for(int j = 0; j < initialPopulation.chromosomes[0].pointOrder.length; j++) {
				initialPopulation.chromosomes[i].pointOrder[j] = j;
				//System.out.print(initialPopulation.chromosomes[i].pointOrder[j] + " ");
			}
			shuffleArray(initialPopulation.chromosomes[i].pointOrder);
			//System.out.println();
		}

	}
	
	// Fisher–Yates shuffle array function
	private static void shuffleArray(int[] array)
	{
	    int index;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        if (index != i)
	        {
	            array[index] ^= array[i];
	            array[i] ^= array[index];
	            array[index] ^= array[i];
	        }
	    }
	}
	
	public static int printFittestPath(Population population) {
		population.fittestChromosome = population.chromosomes[0].getFitness();
		for(int i = 0; i < population.getPopulationSize(); i++) {
			if(population.fittestChromosome > population.chromosomes[i].getFitness()) {
				population.fittestChromosome = population.chromosomes[i].getFitness();
			} else {
				//DO nothing
			}
		}
		return population.fittestChromosome;
	}
	
	
	private static void render() {
		BufferStrategy bs = artist.getBufferStrategy();
		if(bs == null) {
			artist.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//g.setColor(Color.gray);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		
		artist.render(g);
		
		g.dispose();
		bs.show();
	}

}
