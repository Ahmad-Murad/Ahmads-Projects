package fProj;

import java.io.File;
import java.io.FileNotFoundException;

public class Test 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File f = new File("SampleTest.txt");
		scanData s = new scanData(f);
		
		s.plotScan();
	}
}
