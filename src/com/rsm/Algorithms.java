package com.rsm;

import java.util.ArrayList;

import com.rsm.algorithms.OptimizationAlgorithm;

public class Algorithms
{
	ArrayList<Person> persons;
	ArrayList<Table> tables;
	SatisfactionMatrix matrix;
	OptimizationAlgorithm algorithm;
	
	public Algorithms(Facade facade)
	{
		this.persons = facade.persons;
		this.tables = facade.tables;
		this.matrix = facade.satisfactionMatrix;
	}

	public double perform(OptimizationAlgorithm algorithm)
	{
		this.algorithm = algorithm;
		unsitPersons();
		algorithm.perform();
		return goalFunction();
	}

	private void unsitPersons()
	{
		persons.forEach(x -> x.isSitted = false);
		tables.forEach(x-> x.persons.clear());
	}

	public double goalFunction()
	{
		double d = 0;
		for (Table t : tables)
		{
			ArrayList<Integer> list = t.getSittingPeopleId();
			for (Integer i : list)
				for (Integer j : list)
					if (i != j)
						d += matrix.getValue(i, j) + matrix.getValue(j, i);
		}
		return d;
	}
}
