package com.rsm.algorithms;

import java.util.ArrayList;

import com.rsm.Facade;
import com.rsm.Person;
import com.rsm.SatisfactionMatrix;
import com.rsm.Table;

public abstract class OptimizationAlgorithm
{

	ArrayList<Person> persons;
	ArrayList<Table> tables;
	SatisfactionMatrix matrix;

	public OptimizationAlgorithm()
	{

	}

	public OptimizationAlgorithm(Facade facade)
	{
		this.persons = facade.persons;
		this.tables = facade.tables;
		this.matrix = facade.satisfactionMatrix;
	}

	public abstract void perform();

}
