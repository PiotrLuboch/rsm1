package com.rsm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFileReader
{
	private String fileName;

	public CSVFileReader(String fileName)
	{
		this.fileName = fileName;
	}

	public ArrayList<ArrayList<Double>> readFile(String delimeter)
	{
		ArrayList<ArrayList<Double>> arr = new ArrayList<ArrayList<Double>>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String s;
			
			while ((s = br.readLine()) != null)
			{
				ArrayList<Double> subArr = new ArrayList<>();
				String[] tmp = s.split(delimeter);
				for(String el: tmp)
					subArr.add(Double.parseDouble(el));
				
				arr.add(subArr);				
			}
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;

	}

}
