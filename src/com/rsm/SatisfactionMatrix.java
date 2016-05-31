package com.rsm;

import java.util.ArrayList;

public class SatisfactionMatrix
{
	private ArrayList<ArrayList<Double>> matrix;
	public SatisfactionMatrix(ArrayList<ArrayList<Double>> matrix)
	{
		this.matrix=matrix;
	}
	
	public double getValue(int i, int j){
		return matrix.get(i).get(j);
	}
	
	public Person findBestPersonToSit(ArrayList<Person> persons){
		Person p = null;
		double d = Double.NEGATIVE_INFINITY;
		int cap = persons.size();
		
		for(int i=0;i<cap;++i)
			for(int j=0;j<cap;++j)
			{
				double tmp = getValue(i, j)+getValue(j, i);
				boolean canSit = persons.get(i).isSitted && !persons.get(j).isSitted ;
				if(i!=j && tmp > d && canSit)
				{
					p = persons.get(j);
					d = tmp;
				}
			}
		
		return p;
	}
	
	public Pair<Person> findBestPairToSit(ArrayList<Person> persons){
		Person p1 = null, p2 = null;
		double d = Double.NEGATIVE_INFINITY;
		int cap = persons.size();
		
		for(int i=0;i<cap;++i)
			for(int j=i+1;j<cap;++j)
			{
				double tmp = getValue(i, j)+getValue(j, i);
				boolean areNotSitted = !persons.get(i).isSitted && !persons.get(j).isSitted ;
				if(tmp > d && areNotSitted)
				{
					p1 = persons.get(i);
					p2 = persons.get(j);
					d = tmp;
				}
			}
		
		return new Pair<>(p1,p2);
	}
}
