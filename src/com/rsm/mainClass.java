package com.rsm;

import java.util.ArrayList;

import com.rsm.algorithms.GeneticAlgorithm;
import com.rsm.algorithms.GreedyAlgorithm;
import com.rsm.algorithms.RandomAlgorithm;

public class mainClass
{

	public static void main(String[] args)
	{
		String fileName = "data/in_400.csv";
		CSVFileReader fr = new CSVFileReader(fileName);
		ArrayList<ArrayList<Double>> tab = fr.readFile(",");
		
		int tables = 10;
		int capacity = 40;
		Facade f = new Facade(tab,capacity,tables);
		Algorithms algorithm = new Algorithms(f);
		double d = algorithm.perform(new GreedyAlgorithm(f));
		for(Table t: f.tables)
			System.out.println(t.toString());
		System.out.println("Goal function value is: " + d);
		
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ArrayList<Facade> facades = ga.getPopulation();
		for(int i=0;i<50;++i)
		{
			Facade ff = new Facade(tab,capacity,tables);
			Algorithms alg = new Algorithms(ff);
			alg.perform(new RandomAlgorithm(ff));
			facades.add(ff);
		}
		
		for(int i=0;i<100;++i){
			algorithm.perform(ga);
			System.out.println("Score for " + i + " iteration: " + ga.getBestSet().goalFunction());
		}
		/*
		d = algorithm.performRandom();
		for(Table t: f.tables)
			System.out.println(t.toString());
		System.out.println("Goal function value is: " + d);*/
//		String fileName = "data/in_4.csv";
//		CSVFileReader fr = new CSVFileReader(fileName);
//		ArrayList<ArrayList<Double>> tab = fr.readFile(",");
//		
//		Facade f = new Facade(tab,4,1);
		
		GUI gui = new GUI();
		gui.start();	
				
//		Algorithms greedyAlgorithm = new Algorithms(f);
//		double d = greedyAlgorithm.compute();
//		for(Table t: f.tables)
//			System.out.println(t.toString());
//		System.out.println("Goal function value is: " + d);
	}

}
