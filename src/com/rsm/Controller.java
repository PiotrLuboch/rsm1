package com.rsm;

public class Controller {
	
	String checkAreTablesDataValid(int numberOfPersons, int numberOfTables, int tableCapacity)
	{
		String msg = "Ok!";
		
		if(numberOfPersons < numberOfTables*tableCapacity) 
			msg= "There is not enough place.";
		
		return msg;
	}

}
