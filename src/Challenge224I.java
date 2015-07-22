import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Challenge224I
{
    public static void main(String[] args)
    {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File("challenge224I_input6.txt"));
            ArrayList<String> art = new ArrayList<>();
            ArrayList<Point> intersections = new ArrayList<>();
            int lineCounter = 0;
            while (scanner.hasNext())
            {
                String input = scanner.nextLine();
                for (int i = 0; i < input.length(); i++)
                {
                    if (input.charAt(i) == '+')
                    {
                        intersections.add(new Point(i, lineCounter));
                    }
                }
                art.add(input);
                lineCounter++;
            }
            int rectangles = 0;
            long start = Calendar.getInstance().getTimeInMillis();
            for (int i = 0; i < intersections.size(); i++)
            {
                ArrayList<Point> verticallyConnected = verticalConnect(art, intersections.get(i));
                ArrayList<Point> horizontallyConnected = horizontalConnect(art, intersections.get(i));
                for (int j = 0; j < verticallyConnected.size(); j++)
                {
                    for (int k = 0; k < horizontallyConnected.size(); k++)
                    {
                        ArrayList<Point> vertHorizon = horizontalConnect(art, verticallyConnected.get(j));
                        ArrayList<Point> horVertical = verticalConnect(art, horizontallyConnected.get(k));
                        ArrayList<Point> overlap = vertHorizon;
                        overlap.retainAll(horVertical);
                        rectangles += overlap.size();
                    }
                }
            }
            rectangles /= 4;
            System.out.println(rectangles);
            long end = Calendar.getInstance().getTimeInMillis();
            System.out.println("Time (in ms):" + (end - start));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Point> horizontalConnect(ArrayList<String> art, Point point)
    {
        ArrayList<Point> horizontalConnect = new ArrayList<>();
        boolean shouldGoLeft = true;
        boolean shouldGoRight = true;
        String line = art.get(point.y);
        if (point.x == 0)
        {
            shouldGoLeft = false;
        }
        else if (point.x == line.length() - 1)
        {
            shouldGoRight = false;
        }
        int leftCounter = point.x - 1;
        while (shouldGoLeft)
        {
            if (leftCounter == -1 || (line.charAt(leftCounter) != '+' && line.charAt(leftCounter) != '-'))
            {
                shouldGoLeft = false;
                continue;
            }
            if (line.charAt(leftCounter) == '+')
            {
                horizontalConnect.add(new Point(leftCounter, point.y));
            }
            leftCounter--;
        }
        int rightCounter = point.x + 1;
        while (shouldGoRight)
        {
            if (rightCounter == line.length() || (line.charAt(rightCounter) != '+' && line.charAt(rightCounter)!= '-'))
            {
                shouldGoRight = false;
                continue;
            }
            if (line.charAt(rightCounter) == '+')
            {
                horizontalConnect.add(new Point(rightCounter, point.y));
            }
            rightCounter++;
        }
        return horizontalConnect;
    }
    public static ArrayList<Point> verticalConnect(ArrayList<String> art, Point point)
    {
        ArrayList<Point> verticalConnect = new ArrayList<>();
        boolean shouldGoUp = true;
        boolean shouldGoDown = true;
        if (point.y == 0)
        {
            shouldGoUp = false;
        }
        else if (point.y == art.size() - 1)
        {
            shouldGoDown = false;
        }
        int upCounter = point.y - 1;
        while (shouldGoUp)
        {
            if (upCounter == -1 || (art.get(upCounter).charAt(point.x) != '|' && art.get(upCounter).charAt(point.x) != '+'))
            {
                shouldGoUp = false;
                continue;
            }
            if (art.get(upCounter).charAt(point.x) == '+')
            {
                verticalConnect.add(new Point(point.x, upCounter));
            }
            upCounter--;
        }
        int downCounter = point.y + 1;
        while (shouldGoDown)
        {
            if (downCounter == art.size() || (art.get(downCounter).charAt(point.x) != '|' && art.get(downCounter).charAt(point.x) != '+'))
            {
                shouldGoDown = false;
                continue;
            }
            if (art.get(downCounter).charAt(point.x) == '+')
            {
                verticalConnect.add(new Point(point.x, downCounter));
            }
            downCounter++;
        }
        return verticalConnect;

    }

}
