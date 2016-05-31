package com.rsm;

import java.util.ArrayList;

public class Table
{
	public ArrayList<Person> persons = new ArrayList<>();
	public int capacity;
	public int tableId;

	public Table(int tableId, int capacity)
	{
		this.tableId = tableId;
		this.capacity = capacity;
	}

	public boolean hasFreeSits()
	{
		return persons.size() < capacity;
	}

	public boolean isEmpty()
	{
		return persons.size() == 0;
	}

	public boolean sitPerson(Person person)
	{
		if (hasFreeSits())
		{
			person.sit();
			persons.add(person);

			return true;
		}
		return false;
	}

	public ArrayList<Integer> getSittingPeopleId()
	{
		ArrayList<Integer> list = new ArrayList<>();

		persons.forEach(x -> list.add(x.id));
		return list;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(tableId).append(": ");
		for (Person p : persons)
			sb.append(p.id).append("; ");

		return sb.toString();
	}
}
