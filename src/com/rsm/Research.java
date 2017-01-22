package com.rsm;

import java.io.IOException;
import java.util.ArrayList;

import com.rsm.algorithms.GeneticAlgorithm;
import com.rsm.algorithms.GreedyAlgorithm;
import com.rsm.algorithms.RandomAlgorithm;

public class Research
{

	public static void main(String[] args)
	{

		// RANDOM
		try (CSVFileWriter fw = new CSVFileWriter("outputRandom1.csv"))
		{
			String fileName = "data/in_400.csv";
			CSVFileReader fr = new CSVFileReader(fileName);
			ArrayList<ArrayList<Double>> tab = fr.readFile(",");

			Facade f = new Facade(tab, 20, 20);

			Algorithms algorithm = new Algorithms(f);

			// ITERATIONS
			for (int i = 0; i < 500; i++)
			{
				double d = algorithm.perform(new RandomAlgorithm(f));
				double dr = Math.round(d * 100.0) / 100.0;
				fw.write(String.valueOf(dr));
			}

		} catch (IOException e)
		{

		}

		// GENETIC
		try (CSVFileWriter fw = new CSVFileWriter("outputGenetic1.csv"))
		{
			String fileName = "data/in_400.csv";
			CSVFileReader fr = new CSVFileReader(fileName);
			ArrayList<ArrayList<Double>> tab = fr.readFile(",");

			Facade f = new Facade(tab, 20, 20);
			Algorithms algorithm = new Algorithms(f);

			GeneticAlgorithm ga = new GeneticAlgorithm();
			ArrayList<Facade> facades = ga.getPopulation();

			for (int j = 0; j < 500; j++)
			{

				for (int i = 0; i < 100; ++i)
				{
					Facade ff = new Facade(tab, 20, 20);
					Algorithms alg = new Algorithms(ff);
					alg.perform(new RandomAlgorithm(ff));
					facades.add(ff);
				}

				for (int i = 0; i < 100; ++i)
				{
					algorithm.perform(ga);

				}
				double d = ga.getBestSet().goalFunction();
				double dr = Math.round(d * 100.0) / 100.0;
				fw.write(String.valueOf(dr));
				System.out.println(j);
			}

		} catch (IOException e)
		{

		}

	}
}
