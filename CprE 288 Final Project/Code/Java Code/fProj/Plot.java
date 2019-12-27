package fProj;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Plot 
{
	private static class MyFrame extends JFrame 
	{
		private static final long serialVersionUID = 1L;

		public MyFrame(JPanel panel, String name) 
		{
			super(name);
			setContentPane(panel);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
	}

	public static class MyPanel extends JPanel 
	{
		private static final long serialVersionUID = 1L;

		// coordinates of the center pixel of the square representing the origin
		private int ox = (WINDOW_WIDTH - 1) / 2;
		private int oy = (WINDOW_WIDTH - 1) / 2;

		private Point[] points; // array of points to draw
		private Segment[] segments; // array of segments to draw

		public MyPanel(Point[] pts, Segment[] segs) 
		{
			int n = pts.length;
			points = new Point[n];
			for (int i = 0; i < n; i++)
			{
				points[i] = pts[i];
			}

			n = segs.length;
			segments = new Segment[n];
			for (int i = 0; i < n; i++)
			{
				segments[i] = segs[i];
			}

			setSize(WINDOW_WIDTH, WINDOW_WIDTH);
			setBackground(Color.WHITE);

			setVisible(true);
		}

		/**
		 * This method draws the x and y axes with the origin at the pixel position (ox, oy).
		 */
		private void drawAxes(Graphics2D g2) 
		{
			g2.setPaint(Color.BLACK);
			g2.drawLine(MARGIN, oy, WINDOW_WIDTH - MARGIN, oy);
			g2.drawLine(ox, MARGIN, ox, oy);

			int px, py;

			// draw tick marks on the x-axis
			for (int i = MIN_X; i <= MAX_X; i++) 
			{
				px = pixelCenter(i);
				if (i == 0);
				
				else if (i % 10 == 0) 
				{
					g2.drawLine(px, oy, px, oy - 8);
					if (i < 0) 
					{
						g2.drawString(String.valueOf(i), px - 9, oy + 15);
					} 
					else
					{
						g2.drawString(String.valueOf(i), px - 6, oy + 15);
					}
				} 
				else if (i % 5 == 0)
				{
					g2.drawLine(px, oy, px, oy - 4);
				}
			}

			// draw tick marks on the y axis
			for (int i = -MAX_Y; i <= MIN_Y; i++) 
			{
				py = pixelCenter(i);
				if (i == 0);
				
				else if (i % 10 == 0) 
				{
					g2.drawLine(ox, py, ox + 8, py);
					if (i < 0)
					{
						g2.drawString(String.valueOf(-i), ox - 20, py + 5);
					}
					else
					{
						g2.drawString(String.valueOf(-i), ox - 25, py + 5);
					}
				} 
				else if (i % 5 == 0)
				{
					g2.drawLine(ox, py, ox + 4, py);
				}
			}
			for (int i = MIN_X; i <= MAX_X; i++) 
			{
				px = pixelCenter(i);
				if (i == 0);
				
				else if (i % 10 == 0) 
				{
					g2.drawLine(px, oy, px, oy - 8);
					if (i < 0) 
					{
						g2.drawArc(px, px, 2 * Math.abs(ox - px), 2 * Math.abs(ox - px), 0, 180);
					} 
					else
					{
						g2.drawString(String.valueOf(i), px - 6, oy + 15);
					}
				} 
				else if (i % 5 == 0)
				{
					if (i < 0) 
					{
						g2.drawArc(px, px, 2 * Math.abs(ox - px), 2 * Math.abs(ox - px), 0, 180);
					}
				}
			}
		}

		/**
		 * Draw a point with Cartesian coordinates (x, y).
		 */
		private void drawPoint(Graphics2D g2, Point p) 
		{
			// pixel coordinates of the upper left corner of the square representing the
			// point
			double width = p.getWidth() / 2;
			double mag = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
			double px = p.getX() + (width * (p.getX() / mag));
			double py = p.getY() + (width * (p.getY() / mag));
			px = pixelCenterD(+px - width);
			py = pixelCenterD(-py - width);
			double temp = pixelCenterD(p.getX() + (width * (p.getX() / mag)));
			Ellipse2D ellipse = new Ellipse2D.Double();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			g2.setStroke(new BasicStroke());
			ellipse.setFrame(px, py, 2 * Math.abs(temp - px), 2 * Math.abs(temp - px));
			g2.draw(ellipse);
			g2.fill(ellipse);
		}

		/**
		 * Draw a line segment connecting (x1, y1) and (x2, y2). Both points are
		 * represented by the center pixels of their squares.
		 */
		private void drawSegment(Graphics2D g2, Segment s) 
		{
			g2.setPaint(Color.BLUE);
			Point p = s.getP();
			Point q = s.getQ();
			g2.draw(new Line2D.Double(pixelCenterD(p.getX()), pixelCenterD(-p.getY()), pixelCenterD(q.getX()), pixelCenterD(-q.getY())));
		}

		/**
		 * This method is called automatically when a window is created. Think of
		 * Graphics object as a pen.
		 */
		@Override
		public void paint(Graphics g) 
		{
			super.paint(g);

			Graphics2D g2 = (Graphics2D) g; // pen for 2D drawing
			g2.scale(3.0, 3.0);
			drawAxes(g2); // draw coordinate axes

			for (int i = 0; i < points.length; i++)
			{
				drawPoint(g2, points[i]);
			}

			for (int i = 0; i < segments.length; i++)
			{
				drawSegment(g2, segments[i]);
			}
		}

		/**
		 * x or y coordinate of the center pixel in the square representing a point with
		 * Cartesian x coordinate or y coordinate i.
		 */
		private int pixelCenter(int i) 
		{
			if (i < MIN_X || i > MAX_X)
			{
				return WINDOW_WIDTH + 1;
			}

			return ox + i * POINT_WIDTH;
		}

		private double pixelCenterD(double i) 
		{
			if (i < MIN_X || i > MAX_X)
			{
				return WINDOW_WIDTH + 1;
			}

			return ox + i * POINT_WIDTH;
		}
	}

	private static final HashMap<String, MyFrame> map = new HashMap<>();

	private static final int MAX_X = 50; // maximum x- and y-coordinates
	private static final int MAX_Y = 50; // minimum Cartesian x- and y-coordinates
	private static final int MIN_X = -50; // maximum x- and y-coordinates
	private static final int MIN_Y = 0;
	// a point is plotted as a square with dimension POINT_WIDTH by POINT_WIDTH
	private static final int POINT_WIDTH = 5;

	// window is a square with dimension WINDOW_WIDTH by WINDOW_WIDTH.
	private static final int WINDOW_WIDTH = 601;

	// margin for plotting the x- and y-axes
	private static final int MARGIN = 45;

	public static void myFrame(final Point[] pts, final Segment[] segs, final String name) 
	{
		if (map.containsKey(name))
		{
			map.get(name).dispose();
		}
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				map.put(name, new MyFrame(new MyPanel(pts, segs), name));
			}
		});
	}
}
