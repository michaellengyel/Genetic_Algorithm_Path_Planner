package planner.main;

public class Population implements Cloneable{
	
	public Chromosome[] chromosomes;
	public int fittestChromosome; // debug variable
	
	public int populationLength; // This is length of all paths in the population
	public int chromosomeLength; // This is the number of genes in each chromosome
	public int populationSize; // This is the number of chromosomes in the population

	Population(int populationSize, int chromosomeLength) {
		this.chromosomeLength = chromosomeLength;
		this.populationSize = populationSize;
		chromosomes = new Chromosome[populationSize];
		populationLength = 0;
		for(int i = 0; i < chromosomes.length; i++) {
			chromosomes[i] = new Chromosome(chromosomeLength);
		}
	}
	
	public int getPopulationLength() {
		return populationLength;
	}
	
	public void setPopulationLength(int populationLength) {
		this.populationLength = populationLength;
	}
	
	public int getChromosomeLength() {
		return  chromosomeLength;
	}
	
	public void setChromosomeLength(int chromosomeLength) {
		this.chromosomeLength = chromosomeLength;
	}
	
	public int getPopulationSize() {
		return  populationSize;
	}
	
	public void setPopulationSize(int populationSize) {
		this.chromosomeLength = populationSize;
	}


}
