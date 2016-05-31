package com.rsm;

public class Person
{
	public int id = 0;
	public boolean isSitted = false;

	public Person(int id)
	{
		this.id = id;
	}
	
	public void sit(){
		isSitted = true;
	}
	
}
