package com.rsm;

import java.util.ArrayList;

public class Facade
{
	public ArrayList<Table> tables = new ArrayList<>();
	public ArrayList<Person> persons = new ArrayList<>();
	public SatisfactionMatrix satisfactionMatrix;
	int tableCapacity;
	int numberOfTables;
	int numberOfGuests;

	public Facade(ArrayList<ArrayList<Double>> tab, int tableCapacity,
			int numberOfTables)
	{
		initiateGuests(tab.size());
		initiateTables(numberOfTables, tableCapacity);

		satisfactionMatrix = new SatisfactionMatrix(tab);
	}

	public Facade(SatisfactionMatrix matrix)
	{
		satisfactionMatrix = matrix;
		setNumberOfGuests(satisfactionMatrix.matrix.size());
	}

	public void initiateTables(int numberOfTables, int tableCapacity){
		setNumberOfTables(numberOfTables);
		setTableCapacity(tableCapacity);
		for (int i = 0; i < numberOfTables; i++)
			tables.add(new Table(i, tableCapacity));
	}
	
	public void initiateGuests(int numberOfGuests){
		setNumberOfGuests(numberOfGuests);
		for (int index = 0; index < numberOfGuests; ++index)
			persons.add(new Person(index));
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
						d += satisfactionMatrix.getValue(i, j) + satisfactionMatrix.getValue(j, i);
		}
		return d;
	}
	
	public int getNumberOfTables()
	{
		return numberOfTables;
	}

	public void setNumberOfTables(int numberOfTables)
	{
		this.numberOfTables = numberOfTables;
	}

	public int getNumberOfGuests()
	{
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests)
	{
		this.numberOfGuests = numberOfGuests;
	}

	public int getTableCapacity()
	{
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity)
	{
		this.tableCapacity = tableCapacity;
	}
}
