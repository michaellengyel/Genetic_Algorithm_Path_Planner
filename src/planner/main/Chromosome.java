package planner.main;

public class Chromosome{
	
	public int[] pointOrder; // Is the order in which the points are crossed
	private int fitness; // Is equal to the length of the path
	private double rank; // This is relative rank of the chromosomes (ChromosomeLength)
	
	
	Chromosome(int chromosomeLength){
		pointOrder = new int[chromosomeLength];
		fitness = 0;
	}
	
	public int getFitness() {
		return fitness;
	}
	
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
	public double getRank() {
		return rank;
	}
	
	public void setRank(double rank) {
		this.rank = rank;
	}

}
