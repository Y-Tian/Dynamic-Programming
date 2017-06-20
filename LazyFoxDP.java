package lazyfox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author tony.tian
 */
public class LazyFox {

    private Stack<Integer> visits;
    private int[][] distances;
    private int points;
    
    public LazyFox(int[][] locs)
    {
        /*visits = new Stack();
        distances = new int[locs.length][locs.length];
        for(int n = 0; n < locs.length; n++)
            distances[n][n] = Integer.MAX_VALUE;
        
        for(int i = 0; i < locs.length; i++)
        {            
            for(int j = i+1; j < locs.length; j++)
            {
                int dx = locs[i][0] - locs[j][0];
                int dy = locs[i][1] - locs[j][1];
                int dist = dx*dx + dy*dy;
                distances[i][j] = dist;
                distances[j][i] = dist;
            }                        
        }   
        visits.push(0);//first location is always (0,0)*/
        points = locs.length;
        int nPairs = points*(points - 1)/2;
        distances = new int[nPairs][3];
        int index = 0;
        for(int n = 0; n < points; n++)
        {
            for(int m = n+1; m < points; m++)
            {
                int dx = locs[n][0] - locs[m][0];
                int dy = locs[n][1] - locs[m][1];
                distances[index][0] = dx*dx + dy*dy;
                distances[index][1] = n;
                distances[index][2] = m;
                index++;
            }
        }
        
        //Java 8+ for this Arrays.sort(pairs, (int[] i, int[] j) -> i[0]-j[0]);
        Arrays.sort(distances, new Comparator<int[]>(){
            public int compare(int[] i, int[] j){
                return i[0] - j[0];
            }
        });
    }
    
    public boolean isValid(int next)
    {
        int size = visits.size();
        if(size < 2)//first visit can be anyone
            return true;
        
        int prev = visits.get(size-1);
        int prev2 = visits.get(size-2);
        return distances[prev][next] < distances[prev2][prev];
    }
    
    public List<Integer> getFrontier()
    {
        List<Integer> frontier = new ArrayList(distances.length);
        for(int n = 1; n < distances.length; n++)
        {
            if(isValid(n))
                frontier.add(n);
        }
        return frontier;        
    }
    
    public List<Integer> getFrontier2(int curr, int prev)
    {
        List<Integer> frontier = new ArrayList(distances.length);
        for(int n = 1; n < distances.length; n++)
        {
            if(distances[curr][prev] > distances[curr][n])
                frontier.add(n);
        }
        return frontier;        
    }
    
    public int solve()
    {
        int[][] dp = new int[distances.length][distances.length];
        for(int i = 0; i < dp.length; i++)
            for(int j = 0; j < dp[i].length; j++)
                dp[i][j] = -1;
        
        return solve(dp,0,0);   
    }
    
    private int solve(int[][] saved, int curr, int prev)
    {
        List<Integer> frnt = getFrontier();                
        if(frnt.isEmpty())
            saved[curr][prev] = 0;
        else if(saved[curr][prev] < 0)
        {
            int max = 0;
            ListIterator<Integer> choices = frnt.listIterator();
            while(choices.hasNext())
            {
                int next = choices.next();
                visits.push(next);
                int attempt = 1 + solve(saved, next, curr);
                visits.pop();
                if(attempt > max)
                    max = attempt;                      
            }
            saved[curr][prev] = max;
        }
        return saved[curr][prev];
    }
    
    public int solve2()
    {
        int[][] dp = new int[distances.length][distances.length];
        for(int i = 0; i < dp.length; i++)
            for(int j = 0; j < dp[i].length; j++)
                dp[i][j] = -1;
        
        return solve2(dp,0,0);   
    }
    
    private int solve2(int[][] saved, int curr, int prev)
    {
        List<Integer> frnt = getFrontier2(curr,prev);                
        if(frnt.isEmpty())
            saved[curr][prev] = 0;
        else if(saved[curr][prev] < 0)
        {
            int maxIndex = -1;
            int maxD = 0;
            ListIterator<Integer> choices = frnt.listIterator();
            List<Integer> best = new ArrayList();
            while(choices.hasNext())
            {
                int next = choices.next();
                if(distances[next][curr] < distances[curr][prev]) 
                {
                    if(distances[next][curr] > maxD)
                    {
                        best.clear();
                        maxIndex = next;
                        maxD = distances[next][curr];
                        best.add(maxIndex);
                    }
                    else if(distances[next][curr] == maxD)
                        best.add(next);                    
                }                      
            }
            if(maxIndex < 0)//no one smaller
                saved[curr][prev] = 0;
            else
            {
                int max = 0;
                ListIterator<Integer> li = best.listIterator();
                while(li.hasNext())
                {
                    int nextBest = li.next();
                    int attempt = Math.max(1+solve2(saved,nextBest,curr),solve2(saved,curr,nextBest));
                    if(attempt > max)
                        max = attempt;
                }
                saved[curr][prev] = max;
            }
        }
        return saved[curr][prev];
    }
    
    public int solve3()
    {
        //set up array to store solutions and last bound used
        int[][] solns = new int[points][2];
        int[] bounds = new int[points];
        for(int i = 0; i < solns.length; i++)
        {
            solns[i][0] = 0;
            solns[i][1] = 0;
            bounds[i] = 0;
        }        
        
        for(int pair = 0; pair < distances.length; pair++)
        {
            int a = distances[pair][1];
            int b = distances[pair][2];
            
            int aVal = solns[a][0];
            int paVal = solns[a][1];
            int bVal = solns[b][0];
            int pbVal = solns[b][1];
            
            if(a == 0)//origin, assumes stable sort
                solns[a][0] = Math.max(aVal,1+bVal);
            else
            {
                if(bounds[b] < distances[pair][0])//a can assume all paths through b
                {
                    if(aVal <= bVal)
                        solns[a][0] = 1 + bVal;
                    
                    solns[b][1] = bVal;
                }
                else
                {
                    if(aVal <= pbVal)
                        solns[a][0] = 1 + pbVal;                    
                }
                
                if(bounds[a] < distances[pair][0])//b can assume all paths through a
                {
                    if(bVal <= aVal)
                        solns[b][0] = 1 + aVal;
                    
                    solns[a][1] = aVal;
                }
                else
                {
                    if(bVal <= paVal)
                        solns[b][0] = 1 + paVal;
                }
            }
            bounds[a] = bounds[b] = distances[pair][0];
        }
        
        return solns[0][0];
    }
    
    public static int[][] load(Scanner scan)
    {
        int amount = Integer.valueOf(scan.nextLine());
        int[][] locs = new int[amount+1][2];
        locs[0][0] = 0;
        locs[0][1] = 0;
        for(int n = 1; n <= amount; n++)
        {
            String[] coords = scan.nextLine().split(" ");
            locs[n][0] = Integer.valueOf(coords[0]);
            locs[n][1] = Integer.valueOf(coords[1]);
        }
        
        return locs;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try
        {
            long start = System.currentTimeMillis();
            String prob = "15";
            String path = "s5\\s5."+prob+".in";
            File data = new File(path);
            Scanner scan = new Scanner(data);            
            int[][] points = load(scan);            
            LazyFox test = new LazyFox(points);
        
            path = "s5\\s5."+prob+".out";
            data = new File(path);
            scan = new Scanner(data);
            System.out.println("Answer: " + scan.nextLine());
            
            
            System.out.println(test.solve3());
            System.out.println("took " + (System.currentTimeMillis() - start));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }  
    }
    
}
