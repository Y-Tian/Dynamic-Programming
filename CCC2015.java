/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccc.pkg2015.practice;

import java.util.Scanner;
import java.util.*;
/**
 *
 * @author evolution
 */
public class CCC2015Practice {
    
    public static void zerothatout()
    {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        Stack<Integer> sum = new Stack<Integer>();
        for(int i = 0; i < K; i++)
        {
            int n = sc.nextInt();
            if(n == 0)
            {
                sum.pop();
            }
            else
            {
                sum.push(n);
            }
        }
        int print = 0;
        while(!sum.isEmpty())
        {
            print += sum.pop();
        }
        System.out.println(print);
    }
    
    public static void jerseys()
    {
        Scanner sc = new Scanner(System.in);
        int jerseys = sc.nextInt();
        int atheletes = sc.nextInt();
        
        String[] data = new String[jerseys];
        for(int j = 0; j < jerseys; j++)
        {
            data[j] = sc.next();
        }
        int total = 0;
        int i = 0;
        while(i < atheletes)
        {
            String size = sc.next();
            int number = sc.nextInt();
            int index = number - 1;
            if("S".equals(size))
            {
                if(!" ".equals(data[index]))
                {
                    total++;
                    data[index] = " ";
                }
            }
            else if("M".equals(size))
            {
                if(!" ".equals(data[index]) && !"S".equals(data[index]))
                {
                    total++;
                    data[index] = " ";
                }
            }
            else
            {
                if(!" ".equals(data[index]) && !"S".equals(data[index]) && !"M".equals(data[index]))
                {
                    total++;
                    data[index] = " ";
                }
            }
            i++;
        }
        System.out.println(total);
    }
    
    public static void gates()
    {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();
        int p = sc.nextInt();
        int[] planes = new int[p];
        boolean[] gates = new boolean[g + 1];
        for(int i = 0; i < p; i++)
        {
            planes[i] = sc.nextInt();
        }
        
        int total = 0;
        for(int i = 0; i < p; i++)
        {
            if(!gates[planes[i]])
            {
                gates[planes[i]] = true;
                total++;
            }
            else
            {
                for(int j =planes[i]; j >= 1; j--)
                {
                    if(!gates[j])
                    {
                        gates[j] = true;
                        total++;
                        break;
                    }
                    else if(j <= 1)
                    {
                        System.out.println(total);
                        return;
                    }
                }
            }
        }
        System.out.println(total);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //zerothatout();
        //jerseys();
        gates();
    } 
}
