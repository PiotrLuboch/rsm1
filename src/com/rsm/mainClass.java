package com.rsm;

import java.util.ArrayList;

public class mainClass
{

	public static void main(String[] args)
	{
		String fileName = "data/in_4.csv";
		CSVFileReader fr = new CSVFileReader(fileName);
		ArrayList<ArrayList<Double>> tab = fr.readFile(",");
		
		Facade f = new Facade(tab,1,1);
		GUI gui = new GUI();
		gui.start();
		
		
		
		
		Algorithms greedyAlgorithm = new Algorithms(f);
		double d = greedyAlgorithm.compute();
		for(Table t: f.tables)
			System.out.println(t.toString());
		System.out.println("Goal function value is: " + d);
	}

}
