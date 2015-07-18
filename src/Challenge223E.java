
public class Challenge223E
{
	public static int GarlandDegree(String input, String repeat)
	{
		int garlandDegree = 0;
		for (int i = 1; i < input.length(); i++)
		{
		
			String start = input.substring(0, repeat.length());
			if (repeat.equals(start))
			{
				garlandDegree = repeat.length();
				break;
			}
		}
		return garlandDegree;
	}

	public static void main(String[] args)
	{
		String repeat = "";
		String input = "onion";
		for (int i = 0; i < 5; i++)
		{
			if (i == 0)
			{
				System.out.println(GarlandDegree(input, repeat) + " -> " + input);
			}
			else 
			{
				System.out.print(repeat);
			}
		}
	}

}
