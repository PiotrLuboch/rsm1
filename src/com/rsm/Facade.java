package com.rsm;

import java.util.ArrayList;

public class Facade {
	ArrayList<Table> tables = new ArrayList<>();
	ArrayList<Person> persons = new ArrayList<>();
	SatisfactionMatrix satisfactionMatrix;
	
	int tableCapacity;
	int numberOfTables;
	int numberOfGuests;
		

	public Facade(ArrayList<ArrayList<Double>> tab, int tableCapacity, int numberOfTables) {
		this.tableCapacity = tableCapacity;
		this.numberOfTables = numberOfTables;
		this.numberOfGuests = tab.size();

		for (int index = 0; index < numberOfGuests; ++index)
			persons.add(new Person(index));

		for (int i = 0; i < numberOfTables; i++)
			tables.add(new Table(i, tableCapacity));

		satisfactionMatrix = new SatisfactionMatrix(tab);

	}	
	
	public Facade(ArrayList<ArrayList<Double>> tab)
	{
		this.numberOfGuests = tab.size();
		
		for (int index = 0; index < numberOfGuests; ++index)
			persons.add(new Person(index));
		
		satisfactionMatrix = new SatisfactionMatrix(tab);
	}

	public void addTablesToFacade()
	{
		for (int i = 0; i < numberOfTables; i++)
			tables.add(new Table(i, tableCapacity));
	}
	
	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	

	public int getTableCapacity() {
		return tableCapacity;
	}

	public int getNumberOfTables() {
		return numberOfTables;
	}


	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

	public void setNumberOfTables(int numberOfTables) {
		this.numberOfTables = numberOfTables;
	}

}
