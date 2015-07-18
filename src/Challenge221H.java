import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.StringTokenizer;
    
    public class Challenge221H
    {
    	public static void main(String[] args)
    	{
    		long start = System.currentTimeMillis();
    		HashSet<String> hashset = new HashSet<String>();
    		try
    		{
    			/*Reading dictionary and adding each line to the HashSet
    			 *Each line of the dictionary contains one word
    			 */
    			String dictLine;
    			BufferedReader dictReader = new BufferedReader(new FileReader("dictionary.txt"));
    			while ( (dictLine = dictReader.readLine()) != null)
    			{
    				hashset.add(dictLine.toLowerCase());
    			}
    			dictReader.close();
    			BufferedReader challengeReader = new BufferedReader(new FileReader("challenge.txt"));
    			String challengeLine;
    			/*Reads the input, checks if words in input are contained in HashSet
    			 *If there 5 words in the line that is not contained in HashSet
    			 *then it throws that line away, and start checking the next
    			 */
    			int lineCounter = 0;
    			while ( (challengeLine = challengeReader.readLine()) != null)
    			{
    				StringTokenizer st = new StringTokenizer(challengeLine, " .,'`\t\n\r\f");
    				int gibberishCounter = 0;
    				while (gibberishCounter  < 5)
    				{
    					if (!st.hasMoreTokens())
    					{
    						System.out.println(challengeLine);
    						System.out.println(lineCounter);
    						break;
    					}
    					String nextWord = st.nextToken();
    					if (!hashset.contains(nextWord.toLowerCase()))
    					{
    						gibberishCounter++;
    					}
    				}
    				lineCounter++;
    			}
    			challengeReader.close();
    		} catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    }