import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Challenge223I
{
	public static boolean isProblem(String mysteryRaw, String problemRaw)
	{
		String mystery = mysteryRaw.toLowerCase();
		String problem = problemRaw.toLowerCase();
		int[] mysteryLetterCount = new int[26];
		int[] problemLetterCount = new int[26];
		boolean isProblem = false;
		int continuePoint = 0;
		boolean hasLetter = false;
		for (int i = 0; i < problem.length(); i++)
		{
			hasLetter = false;
			char problemLetter = problem.charAt(i);
			problemLetterCount[problemLetter - 97]++;
			while (!hasLetter)
			{	
				if (continuePoint == mystery.length())
				{
					break;
				}
				char mysteryLetter = mystery.toLowerCase().charAt(continuePoint);	
				if (problemLetter == mysteryLetter)
				{
					hasLetter = true;
					continuePoint++;
					if (i + 1 == problem.length())
					{
						for (int j = continuePoint; j < mystery.length(); j++)
						{
							mysteryLetterCount[mystery.charAt(j) - 97]++;
						}
					}
				}
				else 
				{
					continuePoint++;
				}
				
			}
			if (!hasLetter)
			{
				break;
			}
		}
		if (hasLetter)
		{	
			int sameAmountOfLetter = 0;
			for (int i = 0; i < problem.length(); i++)
			{
				if (problemLetterCount[problem.charAt(i) - 97] != mysteryLetterCount[problem.charAt(i) - 97])
				{
					break;
				}
				else
				{
					sameAmountOfLetter++;
				}
			}
			if (sameAmountOfLetter == problem.length())
			{
				isProblem = true;
			}
		}
		return isProblem;
		
	}
	public static int problemCount(File file, String problem)
	{
		int problemCount = 0;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = br.readLine()) != null)
			{
				if (isProblem(line, problem))
				{
					problemCount++;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return problemCount;
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		//1st line is mystery word, 2nd is problem word, 3rd is problem word you want the problem count of
		//System.out.println(isProblem(scanner.nextLine(), scanner.nextLine()));
		//System.out.println(problemCount(new File("enable1.txt"), scanner.nextLine()));

	}

}
