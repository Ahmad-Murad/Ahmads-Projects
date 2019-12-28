package fProj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fProj.Point;
import fProj.scanData;

/**
 * The top GUI for the final project.
 */

@SuppressWarnings("serial")
public class MapWithoutBot extends JPanel 
{
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double radians = Math.PI / 2;
	static final double origenX = screenSize.getWidth() / 2;
	static final double origenY = screenSize.getHeight() / 2;
	double x = origenX;
	double y = origenY;
	public double dx = 0;
	public double dy = 0;
	boolean grid = false;
	int degrees = 0;
	public Ellipse2D cyBot = new Ellipse2D.Double();
	private int count = 0;
	private long lastPressProcessed = 0;
	Line2D line;
	
	File file = new File("SampleTest.txt");
	File file2 = new File("SampleTest2.txt");
	File file3 = new File("SampleTest3.txt");
	
	scanData scanner;
	ArrayList<Ellipse2D> list = new ArrayList<Ellipse2D>();
	ArrayList<Boolean> cliff = new ArrayList<Boolean>();

	public MapWithoutBot() throws UnknownHostException, IOException 
	{
		this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		this.setFocusable(true);
		this.requestFocusInWindow();
		cyBot.setFrame(origenX - 108, origenY - 108, 216, 216);
		line = new Line2D.Double(cyBot.getCenterX(), cyBot.getCenterY(), cyBot.getCenterX(), cyBot.getCenterY() - 206);

		this.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_LEFT) 
				{
					
				} 
				else if (key == KeyEvent.VK_RIGHT) 
				{
					
				}
				else if (key == KeyEvent.VK_UP) 
				{
					dy = 0;
					dx = 0;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode(); 
				if (key == KeyEvent.VK_LEFT) // turn the bot left
				{
					if (System.currentTimeMillis() - lastPressProcessed > 500) 
					{
						radians -= 0.261799;
						degrees = degrees - 15;
						lastPressProcessed = System.currentTimeMillis();
					}
				} 
				else if (key == KeyEvent.VK_RIGHT) // turn the bot right
				{
					if (System.currentTimeMillis() - lastPressProcessed > 500) 
					{
						radians += 0.261799;
						degrees = degrees + 15;
						lastPressProcessed = System.currentTimeMillis();
					}
				} 
				else if (key == KeyEvent.VK_UP) // move forward
				{
					if (System.currentTimeMillis() - lastPressProcessed > 1000) 
					{
						dy += 6 * 5 * Math.sin(radians);
						dx += 6 * 5 * Math.cos(radians);
					}
				} 
				else if (e.getKeyChar() == 'j') // scan surroundings
				{
					if (System.currentTimeMillis() - lastPressProcessed > 500) 
					{
						try 
						{
							if (count == 0)
							{
								scan();
								count++;
							}
							else if (count == 1)
							{
								scan();
								count++;
							}
							else if (count == 2)
							{
								scan();
								count = 0;
							}
						} 
						catch (FileNotFoundException e1) 
						{
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) // paints obstacles into map
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.draw(cyBot);
		g2d.fill(cyBot);

		Line2D lineGrid;
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < 2 * origenY; i += 6) 
		{
			lineGrid = new Line2D.Double(0, i, origenX * 2, i);
			g2d.draw(lineGrid);
		}
		for (int i = 0; i < 2 * origenX; i += 6) 
		{
			lineGrid = new Line2D.Double(i, 0, i, 2 * origenY);
			g2d.draw(lineGrid);
		}

		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(degrees), line.getX1(), line.getY1());
		g2d.setColor(Color.red);
		g2d.draw(at.createTransformedShape(line));
		g2d.setColor(Color.green);
		int j = 0;
		for (int i = 0; i < list.size(); i++) 
		{
			if (list.get(i).getWidth() == 6) 
			{
				if (cliff.get(j) == true) 
				{
					g2d.setColor(Color.magenta);

				} 
				else 
				{
					g2d.setColor(Color.yellow);
				}
				if (j != cliff.size() - 1) 
				{
					j++;
				}
			} 
			else if (list.get(i).getWidth() < 30) 
			{
				g2d.setColor(Color.blue);
			} 
			else if (list.get(i).getWidth() <= 30) 
			{
				g2d.setColor(Color.green);
			} 
			else if (list.get(i).getWidth() <= 66 && list.get(i).getWidth() > 36) 
			{
				g2d.setColor(Color.red);
			} 
			else if (list.get(i).getWidth() > 66) 
			{
				g2d.setColor(Color.yellow);
			}
			
			double x = list.get(i).getCenterX() - list.get(i).getWidth() / 2;
			double y = list.get(i).getCenterY() - list.get(i).getWidth() / 2;
			Ellipse2D temp = list.get(i);
			list.set(i, new Ellipse2D.Double());
			list.get(i).setFrame(dx + x, dy + y, temp.getWidth(), temp.getWidth());

			g2d.draw(list.get(i));
			g2d.fill(list.get(i));
		}
		
		dx = 0;
		dy = 0;
	}

	private void scan() throws FileNotFoundException 
	{
		scanData scan;
		try 
		{
			if (count == 0)
			{
				scan = new scanData(file);
				scan.plotScan();
				for (int i = 0; i < scan.holder.size(); i++) 
				{
					Point p = scan.holder.get(i).closestPoint();
					double width = p.getWidth() / 2;

					double mag = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
					double px = p.getX() + (width * (p.getX() / (mag)));
					double py = p.getY() + (width * (p.getY() / (mag)));
					double x = px;
					px = pixelCenterD(6 * (+px), 6 * (-py), p.getAngle(), true);

					py = pixelCenterDY(6 * (+x), 6 * (-py), p.getAngle(), true);
					Ellipse2D ellipse = new Ellipse2D.Double();

					ellipse.setFrame(px - 6 * width, py - 6 * width, 12 * width, 12 * width);
					System.out.println(ellipse.getHeight());

					list.add(ellipse);

				}
			}
			else if (count == 1)
			{
				scan = new scanData(file2);
				scan.plotScan();
				for (int i = 0; i < scan.holder.size(); i++) 
				{
					Point p = scan.holder.get(i).closestPoint();
					double width = p.getWidth() / 2;

					double mag = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
					double px = p.getX() + (width * (p.getX() / (mag)));
					double py = p.getY() + (width * (p.getY() / (mag)));
					double x = px;
					px = pixelCenterD(6 * (+px), 6 * (-py), p.getAngle(), true);

					py = pixelCenterDY(6 * (+x), 6 * (-py), p.getAngle(), true);
					Ellipse2D ellipse = new Ellipse2D.Double();

					ellipse.setFrame(px - 6 * width, py - 6 * width, 12 * width, 12 * width);
					System.out.println(ellipse.getHeight());

					list.add(ellipse);

				}
			}
			else if (count == 2)
			{
				scan = new scanData(file3);
				scan.plotScan();
				for (int i = 0; i < scan.holder.size(); i++) 
				{
					Point p = scan.holder.get(i).closestPoint();
					double width = p.getWidth() / 2;

					double mag = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
					double px = p.getX() + (width * (p.getX() / (mag)));
					double py = p.getY() + (width * (p.getY() / (mag)));
					double x = px;
					px = pixelCenterD(6 * (+px), 6 * (-py), p.getAngle(), true);

					py = pixelCenterDY(6 * (+x), 6 * (-py), p.getAngle(), true);
					Ellipse2D ellipse = new Ellipse2D.Double();

					ellipse.setFrame(px - 6 * width, py - 6 * width, 12 * width, 12 * width);
					System.out.println(ellipse.getHeight());

					list.add(ellipse);

				}
			}
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		JFrame frame = new JFrame("bot");
		MapWithoutBot map = new MapWithoutBot();
		frame.add(map);

		frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) 
		{
			map.repaint();
			Thread.sleep(30);
		}
	}

	private double pixelCenterD(double x, double y, int angle, boolean scan) 
	{
		double angleMain = Math.toRadians(degrees);
		if (angleMain > 2 * Math.PI) 
		{
			angleMain -= 2 * Math.PI;
		} 
		else if (angleMain < 0) 
		{
			angleMain += 2 * Math.PI;
		}
		double Ox;
		if (scan == true) 
		{
			Ox = (Math.cos(angleMain) * (x)) - (Math.sin(angleMain) * ((y - 66))) + cyBot.getCenterX();
		} 
		else 
		{
			Ox = (Math.cos(angleMain) * (x)) - (Math.sin(angleMain) * (y - 102)) + cyBot.getCenterX();
		}

		return Ox;
	}

	private double pixelCenterDY(double x, double y, int angle, boolean scan) 
	{
		double angleMain = Math.toRadians(degrees);
		if (angleMain > 2 * Math.PI) 
		{
			angleMain -= 2 * Math.PI;
		} 
		else if (angleMain < 0) 
		{
			angleMain += 2 * Math.PI;
		}
		double Oy;
		if (scan == true) 
		{
			Oy = (Math.sin(angleMain) * (x)) + (Math.cos(angleMain) * ((y - 80))) + cyBot.getCenterY();
		} 
		else 
		{
			Oy = (Math.sin(angleMain) * (x)) + (Math.cos(angleMain) * (y - 102)) + cyBot.getCenterY();
		}
		return Oy;
	}
}
