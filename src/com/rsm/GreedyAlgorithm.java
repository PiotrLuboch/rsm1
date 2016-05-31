package com.rsm;

import java.util.ArrayList;
import java.util.Iterator;

public class GreedyAlgorithm
{
	ArrayList<Person> persons;
	ArrayList<Table> tables;
	SatisfactionMatrix matrix;

	public GreedyAlgorithm(Facade facade)
	{
		this.persons = facade.persons;
		this.tables = facade.tables;
		this.matrix = facade.satisfactionMatrix;
	}

	public double compute()
	{
		optimizePeopleSittingPlaces();
		return goalFunction();
	}

	public void optimizePeopleSittingPlaces()
	{
		Iterator<Table> it = tables.iterator();

		while (it.hasNext())
		{
			Table t = it.next();
			while (t.hasFreeSits())
			{
				if (t.isEmpty())
				{
					Pair<Person> p = matrix.findBestPairToSit(persons);
					t.sitPerson(p.item1);
					t.sitPerson(p.item2);
				} else
				{
					Person p = matrix.findBestPersonToSit(persons);
					t.sitPerson(p);
				}
			}
		}
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
