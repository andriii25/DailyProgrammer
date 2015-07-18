import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Challenge223H
{
	public static ArrayList<Point> DragonFractal(int n)
	{
		ArrayList<Point> points = new ArrayList<Point>();
		if (n == 0)
		{
			points.add(new Point(0, 0));
			points.add(new Point(1, 0));
			return points;
		}
		else 
		{
			points.addAll(DragonFractal(n - 1));
			int size = points.size();
			for (int i = size - 2; i >= 0; i--)
			{
				Point relativePoint = new Point(points.get(i).x - points.get(size - 1).x, points.get(i).y - points.get(size - 1).y);
				points.add(new Point(points.get(size - 1).x + relativePoint.y, points.get(size - 1).y - relativePoint.x));
			}
			return points;
		}
	}
	
	public static void main(String[] args)
	{
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long start = Calendar.getInstance().getTimeInMillis();
		ArrayList<Point> points = DragonFractal(n);
		int sumX = 0;
		int sumY = 0;
		for (int i = 0; i < points.size(); i++)
		{
			System.out.println(points.get(i).x + " " + points.get(i).y);
			sumX += points.get(i).x;
			sumY += points.get(i).y;
		}
		System.out.println("Sum: " + sumX + " " + sumY);
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println(end - start);

	}

}

