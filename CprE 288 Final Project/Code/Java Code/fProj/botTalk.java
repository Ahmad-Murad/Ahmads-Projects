package fProj;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.*;
import java.util.Scanner;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Operates all communication from cyBot to Java.
 */
public class botTalk 
{
	Socket socket;

	/**
	 * Generates connection to the cyBot.
	 */
	public botTalk() throws UnknownHostException, IOException 
	{
		socket = new Socket("192.168.1.1", 288);
	}

	/**
	 * Sends info to the cyBot.
	 */
	public void sendString(String string) 
	{
		try 
		{
			OutputStream outputStream = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII);
			byte[] bytes = string.getBytes(StandardCharsets.US_ASCII);
			byte[] end = new byte[1];
			end[0] = 0;

			for (int i = 0; i < bytes.length; i++) 
			{
				outputStream.write(bytes, i, 1);

				if (i == bytes.length - 1) 
				{
					outputStream.write(end, 0, 1);
				}
				outputStream.flush();
				Thread.sleep(100);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Reads from the cyBot.
	 */
	public String readString() 
	{
		try 
		{
			InputStream inputStream = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.US_ASCII));
			char[] bytes = new char[256];
			int size = reader.read(bytes);
			return new String(bytes);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets the raw data for each object detected during a scan.
	 */
	public String getData() throws UnknownHostException, IOException, InterruptedException 
	{
		boolean flag = true;
		String str = "";

		while (flag == true) 
		{
			str += this.readString() + '\n';
			if (str.contains("e")) 
			{
				flag = false;
			}
		}

		str = str.trim();
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * Formats the raw data in our specified manner.
	 */
	public String objectFormatter(String str) 
	{
		Scanner reader = new Scanner(str.trim());
		String result = "";

		while (reader.hasNextLine()) 
		{
			if (reader.hasNext()) 
			{
				result = result + "Distance: " + reader.next() + " Angle: " + reader.next() + " Width: " + reader.next() + "\n";
			}
			System.out.println(result);
		}

		reader.close();
		return result;
	}
}
