package com.rsm;

import java.util.ArrayList;

public class Facade
{
	ArrayList<Table> tables = new ArrayList<>();
	ArrayList<Person> persons = new ArrayList<>();
	SatisfactionMatrix satisfactionMatrix;
	int tableCapacity;
	int numberOfTables;
	int numberOfGuests;

	public Facade(ArrayList<ArrayList<Double>> tab, int tableCapacity,
			int numberOfTables)
	{
		this.tableCapacity = tableCapacity;
		this.numberOfTables = numberOfTables;
		this.numberOfGuests = tab.size();

		for (int index = 0; index < numberOfGuests; ++index)
			persons.add(new Person(index));

		for (int i = 0; i < numberOfTables; i++)
			tables.add(new Table(i, tableCapacity));

		satisfactionMatrix = new SatisfactionMatrix(tab);
		
	}
	
}
