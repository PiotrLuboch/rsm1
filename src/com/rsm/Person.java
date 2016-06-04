package com.rsm;

public class Person
{
	public int id = 0;
	public int tableId = -1;
	public boolean isSitted = false;

	public Person(int id)
	{
		this.id = id;
	}

	public Person(Person p)
	{
		this.id = p.id;
		this.tableId = p.tableId;
		this.isSitted = p.isSitted;
	}

	public void sit(int tableId)
	{
		isSitted = true;
		this.tableId = tableId;
	}

	public void unsit()
	{
		isSitted = false;
		this.tableId = -1;
	}
}
