/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic.programming;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author tony.tian
 */
public class DynamicProgramming {
       
    public static int lightSwitchDP(File file)
    {
        try
        {
            int[] store = new int[25];
            for(int i = 0; i < store.length; i++)
            {
                store[i] = -1;
            }
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            int size = sc.nextInt();
            int[] lights = new int[size];
            for(int i = 0; i < size; i++)
            {
                lights[i] = sc.nextInt(); 
            }
            System.out.println(lightSwitchHelperDP(lights, 0, size, store));
            return lightSwitchHelperDP(lights, 0, size, store);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    public static int lightSwitchHelperDP(int[] lights, int current, int size, int[] store)
    {  
        int minimumLightsOn = 4;
        store[current] = current;
        boolean isfindfirstlighton = false;
        int consecutive = 0;
        
        for (int i = 0; i < size; i++)
        {
            if (lights[i] == 1)
            {
                isfindfirstlighton = true;
            }
            if (isfindfirstlighton)
            {
                consecutive++;

                if (consecutive <= minimumLightsOn)
                {
                    if (lights[i] == 0)
                    {
                        store[current]++;
                        lights[i] = 1;
                    }
                }
                else
                {
                    if (lights[i] == 0)
                    {
                        if ( (i + minimumLightsOn) <= size)
                        {
                            int[] itemp = new int[size-i];
                            System.arraycopy(lights, i, itemp, 0, size - i);
                            store[current] = lightSwitchHelperDP(itemp, store[current], size - i, store);
                        }
                        else
                        {
                            store[current] = lastRecursionDP(lights, store[current], size - i, store);
                        }
                    }
                }
            }
        }
        return store[current];
    }
    
    public static int lastRecursionDP(int[] lights, int current, int size, int[] store)
    {
        store[current] = current;
        boolean isfindone = false;

        for (int i = lights.length - 1; (i + size ) >= lights.length; i--)
        {
            if (lights[i] == 1)
            {
                isfindone = true;
            }
            if (isfindone)
            {
                if (lights[i] == 0)
                {
                    store[current]++;
                    lights[i] = 1;
                }
            }
        }
        return store[current];
    }
    
    public static int lightSwitch(File file)
    {
        try
        {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            int size = sc.nextInt();
            int[] lights = new int[size];
            for(int i = 0; i < size; i++)
            {
                lights[i] = sc.nextInt(); 
            }
            System.out.println(lightSwitchHelper(lights, 0, size));
            return lightSwitchHelper(lights, 0, size);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    public static int lightSwitchHelper(int[] lights, int current, int size)
    {
        int minimumLightsOn = 4;
        int icount = current;
        boolean isfindfirstlighton = false;
        int consecutive = 0;
        
         for (int i = 0; i < size; i++)
            {
                if (lights[i] == 1)
                {
                    isfindfirstlighton = true;
                }
                if (isfindfirstlighton)
                {
                    consecutive++;
                    
                    if (consecutive <= minimumLightsOn)
                    {
                        if (lights[i] == 0)
                        {
                            icount++;
                            lights[i] = 1;
                        }
                    }
                    else
                    {
                        if (lights[i] == 0)
                        {
                            if ( (i + minimumLightsOn) <= size)
                            {
                                int[] itemp = new int[size-i];
                                System.arraycopy(lights, i, itemp, 0, size - i);
                                return icount = lightSwitchHelper(itemp, icount, size - i);
                            }
                            else
                            {
                                return icount = lastRecursion(lights, icount, size - i);
                            }
                        }
                    }
                }
            }
        return icount;
    }
    
    public static int lastRecursion(int[] lights, int current, int size)
    {
        int icount = current;
        boolean isfindone = false;

        for (int i = lights.length - 1; (i + size ) >= lights.length; i--)
        {
            if (lights[i] == 1)
            {
                isfindone = true;
            }
            if (isfindone)
            {
                if (lights[i] == 0)
                {
                    icount++;
                    lights[i] = 1;
                }
            }
        }
        return icount;
    }

    public static int mouse(File file)
    {
        try
        {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            String[] x = sc.nextLine().split(" ");            
            int r = Integer.parseInt(x[0]);
            int c = Integer.parseInt(x[1]);
            x = sc.nextLine().split(" ");
            int cats = Integer.parseInt(x[0]);
            int maxSize = 24;
            boolean[][] catcage = new boolean[maxSize][maxSize];
            for(int count = 0; count < cats; count++)
            {
                x = sc.nextLine().split(" ");
                int catR = Integer.parseInt(x[0]);
                int catC = Integer.parseInt(x[1]);
                catcage[catR - 1][catC - 1] = true;
            }
            System.out.println(mouseHelper(1, 1, r, c, catcage));
            return mouseHelper(1, 1, r, c, catcage);
        }       
        catch(IOException e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    public static int mouseHelper(int r, int c, int fr, int fc, boolean[][] catcage)
    {
        if(r > 25 || c > 25)
        {
            return 0;
        }
        else if(r == fr && c == fc)
        {
            return 1;
        }
        else
        {
            if(catcage[r - 1][c - 1])
            {
                return 0;
            }
            if(r <= fr && c <= fc)
            {
                return mouseHelper(r + 1, c, fr, fc, catcage) + mouseHelper(r, c + 1, fr, fc, catcage);
            }
            return 0;
        }
    }
    
    public static int mouseDP(File file)
    {
        try
        {
            int[][] store = new int[24][24];
            for(int r = 0; r < 24; r++)
            {
                for(int c = 0; c < 24; c++)
                {
                    store[r][c] = -1;
                }
            }
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            String[] x = sc.nextLine().split(" ");            
            int r = Integer.parseInt(x[0]);
            int c = Integer.parseInt(x[1]);
            x = sc.nextLine().split(" ");
            int cats = Integer.parseInt(x[0]);
            int maxSize = 24;
            boolean[][] catcage = new boolean[maxSize][maxSize];
            for(int count = 0; count < cats; count++)
            {
                x = sc.nextLine().split(" ");
                int catR = Integer.parseInt(x[0]);
                int catC = Integer.parseInt(x[1]);
                catcage[catR - 1][catC - 1] = true;
            }
            System.out.println(mouseDPHelper(1, 1, catcage, store));
            return mouseDPHelper(1, 1, catcage, store);
        }       
        catch(IOException e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    public static int mouseDPHelper(int r, int c, boolean[][] catcage, int[][] store)
    {
        if(store[r][c] != -1)
        {
            return store[r][c];
        }       
        else if(catcage[r][c])
        {
            store[r][c] = 0;
        }
        else if(r == 0 && c == 0)
        {
            store[r][c] = 1;
        }
        else if(r == 0)
        {
            store[r][c] = mouseDPHelper(r, c - 1, catcage, store);
        }
        else if(c == 0)
        {
            store[r][c] = mouseDPHelper(r - 1, c, catcage, store);
        }
        else
        {
            store[r][c] = mouseDPHelper(r, c - 1, catcage, store) + mouseDPHelper(r - 1, c, catcage, store);
        }
        return store[r][c];
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //File file = new File("F:\\School\\Programming 12\\TEST.txt");
        //mouseDP(file);
        File file = new File("E:\\School\\Programming 12\\LightTest.txt");
        //lightSwitch(file);
        lightSwitchDP(file);
    }
}
