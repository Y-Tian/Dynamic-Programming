/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccc.pkg2014.practice;

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author tony.tian
 */
public class CCC2014Practice {

    /**
     * @param args the command line arguments
     */
    public static void partyInv()
    {
        Scanner sc = new Scanner(System.in);
        int people = sc.nextInt();
        int[] invitation = new int[people];
        for(int i = 0; i < people; i++)
        {
            invitation[i] = i + 1;
        }
        int m = sc.nextInt();
        int[] output = invitation;
        for(int i = 0; i < m; i++)
        {
            output = partInvHelper(output, m, sc.nextInt());
        }
        print(output);
    }
    
    public static int[] partInvHelper(int[] data, int m, int r)
    {
        int EMPTY = -1;
        int multiples = r;
        int newSize = data.length;
        for(int i = 0; i < data.length; i++)
        {
            if(i == multiples - 1)
            {
                data[i] = EMPTY;
                multiples = multiples + r;
                newSize--;
            }
        }
        return rearrange(data, EMPTY, newSize);
    }
    
    public static int[] rearrange(int[] data, int EMPTY, int newSize)
    {
        int[] output = new int[newSize];
        int j = 0;
        for(int i = 0; i < data.length; i++)
        {
            if(data[i] != EMPTY)
            {
                output[j] = data[i];
                j++;
            }
        }
        return output;
    }
    
    public static void assignPartners()
    {
        Scanner sc = new Scanner(System.in);
        int people = sc.nextInt();
        String[] original1 = new String[people];
        String[] original2 = new String[people];
        
        //String[] thisLine = sc.nextLine().split(" ");
    
        for(int i = 0; i < people; i++)
        {
            original1[i] = sc.next();      
        }
        
        //String[] nextLine = sc.nextLine().split(" ");
        for(int i = 0; i < people; i++)
        {
            original2[i] = sc.next();
        }
        if(partnerHelper(original1, original2))
        {
            System.out.println("good");
        }
        else
        {
            System.out.println("bad");
        }
    }
    
    public static boolean partnerHelper(String[] data1, String[] data2)
    {
        for(int i = 0; i < data1.length; i++)
        {
            String name = data1[i];
            String name2 = data2[i];
            if(name.equals(name2))
            {
                return false;
            }
            boolean found = false;
            for(int n = 0; n < data1.length; n++)
            {
                if(name.equals(data2[n]))
                {
                    if(!found)
                    {
                        if(name2.equals(data1[n]))
                        {
                            found = true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            if(!found)
            {
                return false;
            }
        }
        return true;
    }
    
    public static void genevaConfectionStacks()
    {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for(int i = 0; i < tests; i++)
        {
            int n = sc.nextInt();
            Stack mountain = new Stack();
            for(int x = 0; x < n; x++)
            {
                mountain.push(sc.nextInt());
            }
            Stack<Integer> branch = new Stack<Integer>();
            int index = 1;
            genevaHelper(branch, mountain, index);
        }
    }
    
    public static void genevaHelper(Stack<Integer> branch, Stack<Integer> data, int index)
    {
        if(data.isEmpty() && branch.isEmpty())
        {
            System.out.println("Y");
        }
        else if(data.isEmpty() && !branch.isEmpty())
        {
            if(branch.peek() == index)
            {
                branch.pop();
                index++;
                genevaHelper(branch, data, index);
            }
            else
            {
                System.out.println("N");
            }
        }
        else if(!data.isEmpty())
        {
            if(data.peek() == index)
            {
                data.pop();
                index++;
                genevaHelper(branch, data, index);
            }
            else if(!branch.isEmpty() && branch.peek() == index)
            {
                branch.pop();
                index++;
                genevaHelper(branch, data, index);
            }
            else
            {
                branch.push(data.pop());
                genevaHelper(branch, data, index);
            }
        }
    }
    
    public static void genevaConfection()
    {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for(int i = 0; i < tests; i++)
        {
            int n = sc.nextInt();
            int[] data = new int[n];
            
            for(int x = 0; x < n; x++)
            {
                data[x] = sc.nextInt();
            }
            
            int[] branch = new int[n];
            int[] lake = new int[n];
            
            int branchIndex = 0;
            int lakeIndex = 0;
            
            for(int wat = n - 1; wat >= 0; wat--)
            {
                if(largestHelper(data[wat], data))
                {
                    //System.out.println(largestHelper(data[wat], data));
                    branch[branchIndex] = data[wat];
                    data[wat] = -1;
                    branchIndex++;
                }
                else
                {
                    //System.out.println(largestHelper(data[wat], data));
                    lake[lakeIndex] = data[wat];
                    data[wat] = -1;
                    lakeIndex++;
                }
            }
            for(int kappa = branchIndex - 1; kappa >= 0; kappa--)
            {
                lake[lakeIndex] = branch[kappa];
                lakeIndex++;
            }
            if(checkConfection(lake))
            {
                System.out.println("Y");
            }
            else
            {
                System.out.println("N");
            }
            //print(lake);
        }
    }
    
    public static boolean checkConfection(int[] data)//wrong check order;
    {
        int smallest = data[0];
        for(int i = 1; i < data.length; i++)
        {
            if(data[i] < smallest)
            {
                return false;
            }
        }
        return true;
    }
    
    public static boolean largestHelper(int n, int[] data)
    {
        int largest = n;
        for(int i = 0; i < data.length; i++)
        {
            if(data[i] > largest)
            {
                return false;
            }
        }
        return true;
    }
    
    public static void print(int[] data)
    {
        for(int i = 0; i < data.length; i++)
        {
            System.out.println(data[i]);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //partyInv();
        //assignPartners();
        genevaConfectionStacks();
    }
}
