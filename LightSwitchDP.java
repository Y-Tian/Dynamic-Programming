/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccc.practice;

import java.util.Scanner;

/**
 *
 * @author tony.tian
 */
public class CCCPractice {

    /**
     * @param args the command line arguments
     */
    public static int lightSwitchDP()
    {
        int[] store = new int[25];
        for(int i = 0; i < store.length; i++)
        {
            store[i] = -1;
        }
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] lights = new int[size];
        for(int i = 0; i < size; i++)
        {
            lights[i] = sc.nextInt(); 
        }
        System.out.println(lightSwitchHelperDP(lights, 0, size, store));
        return lightSwitchHelperDP(lights, 0, size, store);
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
    
    public static void main(String[] args) {
        // TODO code application logic here
        lightSwitchDP();
    }
}
