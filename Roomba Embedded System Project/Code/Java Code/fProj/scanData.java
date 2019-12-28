package fProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class scanData 
{
	ArrayList<detectedObject> holder = new ArrayList<detectedObject>();
	String data = "";

	public scanData(String s) 
	{
		String snippet = "";
		data = s;
		for (int i = 0; i < s.length(); i++) 
		{
			if (s.charAt(i) != '\n') 
			{
				snippet += s.charAt(i);
			} 
			else 
			{
				holder.add(new detectedObject(snippet));
				snippet = "";
			}
		}
	}

	public scanData(File file) throws FileNotFoundException 
	{
		Scanner scanner = new Scanner(file);
		String s = "";
		while (scanner.hasNextLine()) 
		{
			if (scanner.hasNextLine()) 
			{
				s += scanner.nextLine() + "\n";
			}
		}

		scanner.close();
		data = s;
		String snippet = "";
		for (int i = 0; i < s.length(); i++) 
		{
			if (s.charAt(i) != '\n') 
			{
				snippet += s.charAt(i);
			} 
			else 
			{
				holder.add(new detectedObject(snippet));
				snippet = "";
			}
		}
	}

	public void plotScan() 
	{
		Point[] obj = new Point[holder.size()];
		Segment[] segments = new Segment[obj.length];

		for (int i = 0; i < holder.size(); i++) 
		{
			obj[i] = holder.get(i).closestPoint();
		}
		for (int i = 0; i < obj.length; i++) 
		{
			segments[i] = new Segment(new Point(0, 0, 0, 0), obj[i]);
		}

		Plot.myFrame(obj, segments, "Objects in the Field");
	}

	public String toString() 
	{
		String out = "";
		for (int i = 0; i < holder.size(); i++) 
		{
			out += holder.get(i).toString() + '\n';
		}
		return out;
	}
}
