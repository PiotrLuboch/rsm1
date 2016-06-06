package com.rsm;



import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter implements AutoCloseable
{
	private FileWriter writer;

	public CSVFileWriter(String fileName) throws IOException
	{

		writer = new FileWriter(fileName);
	}

	public boolean isOpened()
	{
		return writer != null;
	}

	public void write(String text) throws IOException
	{
		writer.append(text);
		writer.append(";");
	}

	public <T> void write(T[] text) throws IOException
	{
		for (int i = 0; i < text.length; ++i)
		{
			writer.append(text[i].toString());
			writer.append(";");
		}
		writer.append("\n");
	}

	@Override
	public void close() throws IOException
	{
		writer.flush();
		writer.close();
	}
}
