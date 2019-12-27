package fProj;

/**
 * Gets three different attributes for a scanned object:
 * 
 * 1) 			angle from 0 to object center 
 * 2) 			distance from cyBot to object 
 * 3) 			width (diameter) of object
 */
public class detectedObject 
{
	private int angle;
	private int distance;
	private int linearWidth;

	public detectedObject(String str) 
	{
		String holder = "";
		String number = "";
		boolean connected = false;

		for (int i = 0; i < str.length(); i++) 
		{
			char temp = str.charAt(i);
			if (temp != ' ') {
				if (Character.isAlphabetic(temp) || temp == ':') 
				{
					holder += temp;
				}
				if (holder.equals("Width:")) 
				{
					if (Character.isDigit(temp)) 
					{
						if (i != str.length() - 1 && Character.isDigit(str.charAt(i + 1))) 
						{
							connected = true;
							number += temp;
						} 
						else 
						{
							if (connected == true) 
							{
								number += temp;
								connected = false;
								linearWidth = Integer.parseInt(number);
								number = "";
								holder = "";
							} 
							else 
							{
								number += temp;
								linearWidth = Integer.parseInt(number);
								number = "";
								holder = "";
							}
						}
					}
				} 
				else if (holder.equals("Distance:")) 
				{
					if (Character.isDigit(temp))
					{
						if (i != str.length() - 1 && Character.isDigit(str.charAt(i + 1))) 
						{
							connected = true;
							number += temp;
						} 
						else 
						{
							if (connected == true) 
							{
								number += temp;
								connected = false;
								distance = Integer.parseInt(number);
								number = "";
								holder = "";
							} 
							else 
							{
								number += temp;
								distance = Integer.parseInt(number);
								number = "";
								holder = "";
							}
						}
					}
				} 
				else if (holder.equals("Angle:")) 
				{
					if (Character.isDigit(temp)) 
					{
						if (i != str.length() - 1 && Character.isDigit(str.charAt(i + 1))) 
						{
							connected = true;
							number += temp;
						} 
						else 
						{
							if (connected == true) 
							{
								number += temp;
								connected = false;
								angle = Integer.parseInt(number);
								number = "";
								holder = "";
							} 
							else 
							{
								number += temp;
								angle = Integer.parseInt(number);
								number = "";
								holder = "";
							}
						}
					}
				}
			}
		}
	}

	public String toString() 
	{
		String out = "";
		out = "Width: " + linearWidth + " Angle: " + angle + " Distance: " + distance;
		return out;
	}

	public int getDistance() 
	{
		return distance;
	}

	public int getAngle() 
	{
		return angle;
	}

	public int getWidth()
	{
		return linearWidth;
	}

	public Point closestPoint() 
	{
		double radians = angle * (Math.PI / 180);
		double x = distance * Math.cos(radians);
		double y = distance * Math.sin(radians);
		Point out = new Point(x, y, linearWidth, angle);

		return out;
	}
}
