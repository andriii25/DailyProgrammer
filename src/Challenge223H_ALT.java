import java.awt.*;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Scanner;

public class Challenge223H_ALT 
{
	public static int pow(int a, int power)
	{	
		int res = a;
		if (power == 0)
		{
			return 1;
		}
		else 
		{
			for (int i = 1; i < power; i++)
			{
				res *= res;
			}
		}
		return res;
	}
	
    public static Point rotatePoint(BigInteger rotationCenterNo, BigInteger toBeRotatedNo)
	{
    	Point rotationCenter = getPoint(rotationCenterNo);
    	Point toBeRotated = getPoint(toBeRotatedNo);
    	Point relativePoint = new Point(toBeRotated.x - rotationCenter.x, toBeRotated.y - rotationCenter.y);
		return(new Point(rotationCenter.x + relativePoint.y, rotationCenter.y - relativePoint.x));
	}
	
	public static Point getPoint(BigInteger k)
	{
		Point point = new Point();
		if (k.equals(BigInteger.ZERO))
		{
			point = new Point(0, 0);
			return point;
		} else if (k.equals(BigInteger.ZERO))
		{
			point = new Point(1, 0);
			return point;
		}
		else 
		{	
			int i = 0;
			BigInteger highestPower = BigInteger.ZERO;
			boolean hasFoundPower = false;
			while (!hasFoundPower)
			{
				if (k.compareTo(new BigInteger("2").pow(i)) == -1)
				{
					if (k.equals(new BigInteger("2").pow(i)))
					{
						return rotatePoint(k.divide(new BigInteger("2")), BigInteger.ZERO);
					}
					else 
					{
						highestPower = new BigInteger("2").pow(i - 1);
						hasFoundPower = true;
					}
				}
				else
				{
					i++;
				}
			}
			return rotatePoint(highestPower, highestPower.multiply(new BigInteger("2")).subtract(k));
		}
		
		
	}

	public static void main(String[] args)
    {
    	Scanner scanner = new Scanner(System.in);
    	//int n = scanner.nextInt();
    	int sumX = 0;
    	int sumY = 0;
    	long start = Calendar.getInstance().getTimeInMillis();
    	/*for (int i = 0; i < (int)Math.pow(2, n) + 1; i++)
    	{
    		Point point = getPoint(i);
    		System.out.printf("%d %d%n", point.x, point.y);
    		sumX += point.x;
    		sumY += point.y;
    	}*/
    	Point point = getPoint(new BigInteger("2").pow(71));
    	System.out.println(point.x + " " + point.y);
    	long end = Calendar.getInstance().getTimeInMillis();
    	//System.out.printf("Sum: X: %d Y: %d%n", sumX, sumY);
    	System.out.printf("Time (in ms) %d%n", end - start);
    }

}
