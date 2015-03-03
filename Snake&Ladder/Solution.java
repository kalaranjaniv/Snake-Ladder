import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Map;

class ValueComparator1 implements Comparator {
	 
	Map map;
 
	public ValueComparator1(Map map) {
		this.map = map;
	}
 
	public int compare(Object keyA, Object keyB) {
		Comparable valueA = (Comparable) map.get(keyA);
		Comparable valueB = (Comparable) map.get(keyB);
		return valueB.compareTo(valueA);
	}
}
public class Solution {

	Map<Integer, Integer> ladder = new HashMap<Integer, Integer>();
	Map<Integer, Integer> snake = new HashMap<Integer, Integer>();
	static int snakecount, laddercount;
	public static int START=1;
	public static int END = 100;
	public static int INDEXZERO=0;
	public static int INDEXONE=1;
	public static int SNAKEUPPERBND = 15;
	public static int SNAKELOWERBND = 1;
	public static int LADDERUPPERBND = 15;
	public static int LADDERLOWERBND = 1;
	Map<Integer, Integer> ladderdist = new HashMap<Integer,Integer>();
	Solution(String[] ladderinp, String[] snakeinp) {
		for (String i : ladderinp) {
			String pair[] = i.split(",");
			ladder.put(Integer.parseInt(pair[INDEXZERO]), Integer.parseInt(pair[INDEXONE]));
			int dist = Integer.parseInt(pair[INDEXONE]) - Integer.parseInt(pair[INDEXZERO]);
			ladderdist.put(Integer.parseInt(pair[INDEXZERO]), dist);
		}
		for (String i : snakeinp) {
			String pair[] = i.split(",");
			snake.put(Integer.parseInt(pair[INDEXZERO]), Integer.parseInt(pair[INDEXONE]));
		}
	}

	public int computeshortest() {
		int currpos = 1;
		int i = 0;
		ArrayList<Integer> dice = new ArrayList<Integer>();
		Map sortedladderMap = sortByValue(ladderdist);
	while (END - currpos > 6)
	{
		if(ladder.get(currpos) != null)
		{
			currpos = ladder.get(currpos);
		}
		else if(snake.get(currpos) != null)
		{
			currpos = snake.get(currpos);
		}
		else if(Integer.parseInt(sortedladderMap.keySet().iterator().next().toString()) > currpos && sortedladderMap.keySet().iterator().next()!=null)
		{
		int toroll = Integer.parseInt(sortedladderMap.keySet().iterator().next().toString()) - currpos;
		while(toroll > 6)
		{
			dice.add(6);
			toroll = toroll - 6;
			
			currpos = currpos + 6;
			
		}
		if(toroll > 0)
		{
			dice.add(toroll);
			currpos = currpos + toroll;
		}
		currpos=ladder.get(currpos);
		}		
		else
		{
			int toroll =6;
			int currpos1 = currpos + toroll;
			while(snake.get(currpos1) != null && toroll >0)
			{
				toroll --;
				currpos1 = currpos + toroll;
			}
			if(toroll != 0)
			{
			currpos = currpos + toroll;
			dice.add(toroll);
			i++;
			}
			else
			{
				currpos = currpos + 6;
				dice.add(6);
			}
		}
	}		
		if(END-currpos <=6 && currpos !=100)
		{
			dice.add(END - currpos);
		}
		else
		{
			
		}
		
		return dice.size();
	}
	
	public static Map sortByValue(Map unsortedMap) {
		Map sortedMap = new TreeMap(new ValueComparator1(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		String s,siter;
		siter = in.nextLine();
        int[] dice = new int[Integer.parseInt(siter)];
		for(int loop=0;loop < Integer.parseInt(siter);loop++)
		{
		Solution game = null;
				
		s = in.nextLine();
		String count[] = s.split(",");
		laddercount = Integer.parseInt(count[INDEXZERO]);
		snakecount = Integer.parseInt(count[INDEXONE]);
		if (snakecount >= SNAKELOWERBND && snakecount <= SNAKEUPPERBND) {
			if (laddercount >= LADDERLOWERBND && laddercount <= LADDERUPPERBND) {
				
				s = in.nextLine();
				String ladderinp[] = s.split(" ");
				
				if (ladderinp.length == laddercount) {
					
					s = in.nextLine();
					String snakeinp[] = s.split(" ");
					
					if (snakeinp.length == snakecount) {
						game = new Solution(ladderinp, snakeinp);
					} else {
						
						System.exit(0);
					}

				} else {
					
					System.exit(0);
				}
				dice[loop] = game.computeshortest();	
				
	
			}
			else
			{
				
				System.exit(0);
			}
		}
		else
		{
			
			System.exit(0);
		}
	}
		
		for(int loop:dice)
		{
			System.out.println(loop);
		}
       
}
    			
				
}