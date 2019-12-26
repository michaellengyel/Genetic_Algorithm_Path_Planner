package planner.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Artist extends Canvas{
	
	private static Population population;
	private static Map map;
	
	private static final int POINT_DIAMETER = 5;
	
	// Draw Information variables
	private static int cycles = 0;
	private static int fittest = 0;
	
	Artist(Population population, Map map) {
		this.population = population;
		this.map = map;
	}

	public void render(Graphics g) {
		g.clearRect(0, 0, 1900, 1000);
		
		calculateInformation();
		drawInformation(g);
		
		//Drawing points and their IDs
		for(int i = 0; i < map.mapPoints.length; i++) {
			g.setColor(Color.red);
			g.fillOval(map.mapPoints[i].x - (POINT_DIAMETER/2), map.mapPoints[i].y - (POINT_DIAMETER/2), POINT_DIAMETER, POINT_DIAMETER);
			g.drawString(Integer.toString(i),map.mapPoints[i].x + POINT_DIAMETER, map.mapPoints[i].y + POINT_DIAMETER);
		}
		
		/*
		// Drawing lines of all population
		for(int i = 0; i < population.getPopulationSize(); i++) {
			for(int j = 0; j < population.getChromosomeLength()-1; j++) {
				g.drawLine(map.mapPoints[population.chromosomes[i].pointOrder[j]].x, map.mapPoints[population.chromosomes[i].pointOrder[j]].y, map.mapPoints[population.chromosomes[i].pointOrder[j+1]].x, map.mapPoints[population.chromosomes[i].pointOrder[j+1]].y);
			}
		}
		*/
		
		// Drawing lines for best population
		g.setColor(Color.black);
		for(int j = 0; j < population.getChromosomeLength()-1; j++) {
			g.drawLine(map.mapPoints[population.chromosomes[0].pointOrder[j]].x, map.mapPoints[population.chromosomes[0].pointOrder[j]].y, map.mapPoints[population.chromosomes[0].pointOrder[j+1]].x, map.mapPoints[population.chromosomes[0].pointOrder[j+1]].y);
		}
	}	
	
	private void calculateInformation() {
		calculateCycles();
		calculateFittest();
	}
	
	// Helper functions of calculateInformation()
	private void calculateCycles() {
		cycles += 1;
	}
	
	private void calculateFittest() {
		population.fittestChromosome = population.chromosomes[0].getFitness();
		for(int i = 0; i < population.getPopulationSize(); i++) {
			if(population.fittestChromosome > population.chromosomes[i].getFitness()) {
				population.fittestChromosome = population.chromosomes[i].getFitness();
			} else {
				//DO nothing
			}
		}
		fittest = population.fittestChromosome;
	}
	
	// End helper functions of calculateInformation()
	
	private void drawInformation(Graphics g) {
		g.drawString("Copyright 2019 Peter Lengyel All Rights Reserved", 10, 20);
		g.drawString("Cycle: " + Integer.toString(cycles), 10, 35);
		g.drawString("Fittest: " + Integer.toString(fittest), 10, 50);
	}
	
	public void getPopulation(Population population) {
		this.population = population;
	}

}
