    import java.util.Scanner;
    
    public class Challenge222E
    {
    	static int Weight(String input, boolean isLeft)
    	{
    		int weight = 0;
    		for (int i = 0; i < input.length(); i++)
    		{
    			if (isLeft)
    			{
    				weight += ((input.charAt(i) - 64) * (input.length() - i));
    			}
    			else 
    			{
    				weight += ((input.charAt(i) - 64) * (i + 1));
    			}
    		}
    		return weight;
    	}
    	
    	public static void main(String[] args)
    	{
    		//Gets input word
    		Scanner scanner = new Scanner(System.in);
    		String input = scanner.nextLine();
    		boolean isBalanced = false;
    		if (input.length() > 2)
    		{
    			for (int i = 1; i < input.length() - 1; i++)
    			{
    				//Calculates weight for the 2 sides of every possible balance point 
    				String left = input.substring(0, i);
    				String right = input.substring(i + 1);
    				int leftWeight = Weight(left, true);
    				int rightWeight = Weight(right, false);
    				if (leftWeight == rightWeight )
    				{
    					System.out.println(left + " " + input.charAt(i) + " " + right + " - " + leftWeight);
    					isBalanced = true;
    					break;
    				}
    				
    			}
    		
    		} 
    		else if (input.length() == 1)
    		{
    			System.out.println(input + " - " + (input.charAt(0) - 64) );
    			isBalanced = true;
    		}
    		if (!isBalanced)
    		{
    			System.out.println(input + " DOES NOT BALANCE");
    		}
    
    	}
    
    }
    