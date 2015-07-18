import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Challenge221H_AltSolution
{
	public static void Normalize(HashMap<String, Double> trigramMap)
	{
		double valuesSum = 0;
		for(double d : trigramMap.values())
		{
			valuesSum += d;
		}
		for(Map.Entry<String, Double> entry : trigramMap.entrySet())
		{
			double oldValue = entry.getValue();
			entry.setValue(oldValue / valuesSum);
		}
	}
	

	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		try 
		{
			System.out.println("Start " + (System.currentTimeMillis() - start));
			HashMap<String, Double> dictMap = new HashMap<String, Double>();
			BufferedReader dictReader = new BufferedReader(new FileReader("58kdictionary.txt"));
			String dictLine;
			int lineCounter = 0;
			while ( (dictLine = dictReader.readLine()) != null)
			{	
				/*if (lineCounter % 10 == 0)
				{
					System.out.println(lineCounter);
				}*/
				dictLine = " " + dictLine.toLowerCase() + " ";
				dictLine.replaceAll("[.,'`\t\n\r\f]", "");
				for (int i = 0; i < dictLine.length() - 2; i++)
				{
					String trigram = dictLine.substring(i, i + 3);
					if (!dictMap.containsKey(trigram))
					{
						dictMap.put(trigram, 1.0);
					} 
					else
					{
						dictMap.replace(trigram, dictMap.get(trigram) + 1);
					}
				}
				lineCounter++;
			}
			dictReader.close();
			System.out.println("Finished reading dict " + (System.currentTimeMillis() - start));
			Normalize(dictMap);
			System.out.println("Finished normalizing dict " + (System.currentTimeMillis() - start));
			BufferedReader challengeReader = new BufferedReader(new FileReader("challenge.txt"));
			String challengeLine;
			String rawChallengeLine;
			int challengeLineCounter = 0;
			while ( (rawChallengeLine = challengeReader.readLine()) != null)
			{
				
				if (challengeLineCounter % 5000 == 0)
				{
					System.out.println(challengeLineCounter + " " + (System.currentTimeMillis() - start));
				}
				HashMap<String, Double> challengeLineMap = new HashMap<String, Double>();
				challengeLine = rawChallengeLine.replaceAll("[.,'`\t\n\r\f]", "");
				for (int i = 0; i < challengeLine.length() - 2; i++)
				{
					String trigram = challengeLine.substring(i, i + 3);
					if (!challengeLineMap.containsKey(trigram))
					{
						challengeLineMap.put(trigram, 1.0);
					} 
					else
					{
						challengeLineMap.replace(trigram, challengeLineMap.get(trigram) + 1);
					}
					
				}
				Normalize(challengeLineMap);
				double totalDifference = 0;
				double dictValue = 0;
				double challengeLineValue = 0;
				//double sumDict = 0;
				//double sumChallenge = 0;
				for(Map.Entry<String, Double> entry : dictMap.entrySet())
				{
					dictValue = entry.getValue();
					//sumDict += dictValue;
					challengeLineValue = 0;
					if (challengeLineMap.get(entry.getKey()) != null)
					{
						challengeLineValue = challengeLineMap.get(entry.getKey());
						//sumChallenge += challengeLineValue;
					}
					totalDifference += Math.pow(dictValue - challengeLineValue, 2);
					
				}
				//System.out.println(totalDifference);
				if (totalDifference < 0.0042)
				{
					System.out.println(challengeLineCounter + " " + challengeLine);
					//System.out.println(totalDifference + " " + sumDict + " " + sumChallenge);
					System.out.println("Dictionary\n\n\n\n\n");
					for (Map.Entry<String, Double> entry : dictMap.entrySet())
					{
						
						System.out.println(entry.getKey() + " " + entry.getValue());
					}
					System.out.println("Challenge Line\n\n\n\n\n");
					for (Map.Entry<String, Double> entry : challengeLineMap.entrySet())
					{
						
						System.out.println(entry.getKey() + " " + entry.getValue());
					}
					break;
				}
				challengeLineCounter++;
				
			}
			System.out.println("Finished " + (System.currentTimeMillis() - start));
			challengeReader.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
