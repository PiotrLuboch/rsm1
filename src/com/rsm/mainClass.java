package com.rsm;

import java.util.ArrayList;

public class mainClass
{

	public static void main(String[] args)
	{
		String fileName = "data/in_16.csv";
		CSVFileReader fr = new CSVFileReader(fileName);
		ArrayList<ArrayList<Double>> tab = fr.readFile(",");
		
		Facade f = new Facade(tab,4,4);
		Algorithms algorithm = new Algorithms(f);
		double d = algorithm.performGreedy();
		for(Table t: f.tables)
			System.out.println(t.toString());
		System.out.println("Goal function value is: " + d);
		
		d = algorithm.performRandom();
		for(Table t: f.tables)
			System.out.println(t.toString());
		System.out.println("Goal function value is: " + d);
	}

}
