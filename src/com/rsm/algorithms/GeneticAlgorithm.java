package com.rsm.algorithms;

import java.util.ArrayList;
import java.util.Random;

import com.rsm.Facade;
import com.rsm.FacadeComparator;
import com.rsm.Person;
import com.rsm.Table;

public class GeneticAlgorithm extends OptimizationAlgorithm
{
	private static final double mutationRate = 0.015;
	private static final double crossoverRate = 0.5;
	private static final int eliteOffset = 3;
	private ArrayList<Facade> population = new ArrayList<>();

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
		ArrayList<Facade> newPopulation = new ArrayList<>();
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

	private static Facade crossover(Facade parent1, Facade parent2)
	{
		int numberOfGuests = parent1.persons.size();
		int numberOfTables = parent1.tables.size();
		int capacityOfTable = parent1.tables.get(0).capacity;
		Facade child = new Facade(parent1.satisfactionMatrix.matrix,
				capacityOfTable, numberOfTables);
		Random rand = new Random();

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < numberOfGuests; ++i)
			list.add(i);

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

	private static void mutate(Facade obj)
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
		population.sort(new FacadeComparator());
		int index = (int) (Math.abs(rand.nextGaussian())
				* (double) population.size());
		if (index > population.size() - 1)
			index = population.size() - 1;

		return population.get(index);
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
