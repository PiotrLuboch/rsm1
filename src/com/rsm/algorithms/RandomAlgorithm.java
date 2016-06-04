package com.rsm.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.rsm.Facade;
import com.rsm.Table;

public class RandomAlgorithm extends OptimizationAlgorithm
{

	public RandomAlgorithm(Facade facade)
	{
		super(facade);
	}

	@Override
	public void perform()
	{
		Iterator<Table> it = tables.iterator();

		Random generator = new Random();
		int personID;
		int index;
		ArrayList<Integer> tempPersons = new ArrayList<>();

		for (int i = 0; i < persons.size(); ++i)
		{
			tempPersons.add(i);
		}

		while (it.hasNext())
		{
			Table t = it.next();
			while (t.hasFreeSits())
			{
				index = generator.nextInt(tempPersons.size());
				personID = tempPersons.get(index);
				t.sitPerson(persons.get(personID));
				tempPersons.remove(index);

			}
		}
	}

}
