package fProj;

public class Point implements Comparable<Point> 
{
	private double x;
	private double y;

	private double width;
	private int angle;

	public static boolean xORy;

	public Point() 
	{
		x = 0;
		y = 0;
	}

	public Point(double x, double y, int width, int angle) 
	{
		this.x = x;
		this.y = y;
		this.width = width;
	}

	public Point(Point p) 
	{
		x = p.getX();
		y = p.getY();
	}

	public int getAngle() 
	{
		return angle;
	}

	public double getX() 
	{
		return x;
	}

	public double getY() 
	{
		return y;
	}

	public static void setXorY(boolean xORy) 
	{
		Point.xORy = xORy;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null || obj.getClass() != this.getClass()) 
		{
			return false;
		}

		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	public int compareTo(Point q) 
	{
		if ((xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y)))
		 || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x))))
			return -1;
		else if (this.x == q.x && this.y == q.y)
			return 0;
		else
			return 1;
	}

	@Override
	public String toString() 
	{
		String str = "(" + x + "," + " " + y + ")";
		return str;
	}

	public double getWidth() 
	{
		return width;
	}
}
