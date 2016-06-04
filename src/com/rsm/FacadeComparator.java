package com.rsm;

import java.util.Comparator;

public class FacadeComparator implements Comparator<Facade>
{

	@Override
	public int compare(Facade o1, Facade o2)
	{
		return ((Double)o1.goalFunction()).compareTo(o2.goalFunction());
	}

}
