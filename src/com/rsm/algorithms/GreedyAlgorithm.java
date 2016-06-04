package com.rsm.algorithms;

import java.util.Iterator;

import com.rsm.Facade;
import com.rsm.Pair;
import com.rsm.Person;
import com.rsm.Table;

public class GreedyAlgorithm extends OptimizationAlgorithm
{
	

	public GreedyAlgorithm(Facade facade)
	{
		super(facade);
	}
	
	@Override
	public void perform()
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

}
