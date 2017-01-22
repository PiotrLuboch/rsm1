package com.rsm.algorithms;

import java.util.ArrayList;
import java.util.Random;

import com.rsm.Facade;
import com.rsm.FacadeComparator;
import com.rsm.Person;
import com.rsm.Table;

public class GeneticAlgorithm extends OptimizationAlgorithm
{
	private double mutationRate = 0.015;
	private double crossoverRate = 0.2;
	private final int eliteOffset = 2;
	private ArrayList<Facade> population = new ArrayList<>();

	private enum DiversityLevel
	{
		LOW(0.1, 0.01), GOOD(0.35, 0.0001), HIGH(0.6, -0.1);
		private double value;
		private double delta;

		private DiversityLevel(double value, double delta)
		{
			this.value = value;
			this.delta = delta;
		}

		public double getValue()
		{
			return value;
		}

		public double getDelta()
		{
			return delta;
		}
	}

	public GeneticAlgorithm()
	{
		super();
	}

	public GeneticAlgorithm(Facade facade)
	{
		super(facade);
	}

	@Override
	public void perform()
	{
		evolvePopulation();
	}

	private void evolvePopulation()
	{
		int populationSize = population.size();
		population.sort(new FacadeComparator());
		ArrayList<Facade> newPopulation = new ArrayList<>();

		parametersAdaptation();

		for (int i = 0; i < eliteOffset; ++i)
			newPopulation.add(population.get(i));

		for (int i = eliteOffset; i < populationSize; ++i)
		{
			Facade f1 = facadeSelection();
			Facade f2 = facadeSelection();
			Facade crossing = crossover(f1, f2);
			newPopulation.add(crossing);
		}

		for (Facade f : newPopulation)
			mutate(f);

		population = newPopulation;
	}

	private Facade crossover(Facade parent1, Facade parent2)
	{
		int numberOfGuests = parent1.persons.size();
		int numberOfTables = parent1.tables.size();
		int capacityOfTable = parent1.tables.get(0).capacity;
		Facade child = new Facade(parent1.satisfactionMatrix.matrix,
				capacityOfTable, numberOfTables);
		Random rand = new Random();

		for (int i = 0; i < numberOfGuests; ++i)
		{
			int tid = parent1.persons.get(i).tableId;
			child.tables.get(tid).sitPerson(child.persons.get(i));
		}

		for (int i = 0; i < numberOfGuests; ++i)
		{
			if (rand.nextDouble() < crossoverRate)
			{
				int tid_old = child.persons.get(i).tableId;
				int tid_new = parent2.persons.get(i).tableId;

				Table t1 = child.tables.get(tid_old);
				Table t2 = child.tables.get(tid_new);
				int tmp = Math.abs(rand.nextInt() % t1.capacity);

				Person p1 = child.persons.get(i);
				Person p2 = t2.persons.get(tmp);

				if (p1 != p2)
				{
					t1.persons.remove(p1);
					t2.persons.remove(p2);

					t2.sitPerson(p1);
					t1.sitPerson(p2);
				}
			}
		}

		return child;
	}

	private void parametersAdaptation()
	{
		long div = hammingDiversity();
		long maxDiv = maxDiversity();
		double val = ((double) div) / maxDiv;
		double delta = 0.0;
		if (val < DiversityLevel.LOW.getValue())
		{
			delta = DiversityLevel.LOW.getDelta();
		} else if (val < DiversityLevel.GOOD.getValue())
		{
			double x = div - DiversityLevel.LOW.getValue();
			double a = 1 / x;
			double b1 = DiversityLevel.LOW.getDelta();
			double b2 = DiversityLevel.GOOD.getDelta();
			delta = (a * val - b1) * DiversityLevel.LOW.getDelta()
					+ (-1 * a * val - b2) * DiversityLevel.GOOD.getDelta();
		} else if (val < DiversityLevel.HIGH.getValue())
		{
			double x = div - DiversityLevel.GOOD.getValue();
			double a = 1 / x;
			double b1 = DiversityLevel.GOOD.getDelta();
			double b2 = DiversityLevel.HIGH.getDelta();
			delta = (a * val - b1) * DiversityLevel.GOOD.getDelta()
					+ (-1 * a * val - b2) * DiversityLevel.HIGH.getDelta();
		} else
		{
			delta = DiversityLevel.HIGH.getDelta();
		}
		crossoverRate -= delta;
		mutationRate += delta;
	}

	private void mutate(Facade obj)
	{
		ArrayList<Person> p = obj.persons;
		ArrayList<Table> t = obj.tables;
		for (int i = 0; i < p.size(); ++i)
		{
			if (Math.random() < mutationRate)
			{
				Random rand = new Random();
				int index = 0;
				do
				{
					index = rand.nextInt(p.size());
				} while (index == i);
				Person p1 = p.get(i);
				Person p2 = p.get(index);
				int table1 = p1.tableId;
				int table2 = p2.tableId;
				p1.unsit();
				p2.unsit();
				t.get(table1).persons.remove(p1);
				t.get(table2).persons.remove(p2);
				t.get(table1).sitPerson(p2);
				t.get(table2).sitPerson(p1);
			}
		}
	}

	public Facade getBestSet()
	{
		Facade f = population.get(0);

		for (Facade it : population)
			if (f.goalFunction() < it.goalFunction())
				f = it;

		return f;
	}

	private Facade facadeSelection()
	{

		Random rand = new Random();
		int index = (int) (Math
				.abs(rand.nextGaussian() * (double) population.size()));
		// int index = (int)(Math.random()*population.size());
		if (index > population.size() - 1)
			index = population.size() - 1;

		return population.get(index);

	}

	private long hammingDiversity()
	{
		long diversity = 0;
		for (int n = 0; n < population.size() - 1; ++n)
		{
			for (int k = n + 1; k < population.size(); ++k)
			{
				for (int i = 0; i < population.get(0).getNumberOfGuests(); ++i)
				{
					Person p1 = population.get(n).persons.get(i);
					Person p2 = population.get(k).persons.get(i);
					if (p1.tableId != p2.tableId)
						++diversity;
				}
			}
		}
		return diversity;
	}

	private long maxDiversity()
	{
		long div = 0;

		div = population.size() * (population.size() - 1)
				* population.get(0).persons.size();

		return div;
	}

	public ArrayList<Facade> getPopulation()
	{
		return population;
	}

	public void setPopulation(ArrayList<Facade> population)
	{
		this.population = population;
	}
}
