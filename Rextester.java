//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

//Some array exercises

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        //double[] myList = new double[10];
        double[] myList = {0.0, 1.0, 2.0, 3.0, 4.0};

        for (double d : myList)
            System.out.print(d + " ");
        
        System.out.println();
        for (int i = 0; i < myList.length; i++)
            System.out.print(myList[i] + " ");
        
        System.out.println();
        double total = 0;
        for (int i = 0; i < myList.length; i++)
            total += myList[i];
        System.out.println("total = " + total);
        
        System.out.println();
        double max = myList[0];
        for (int i = 1; i < myList.length; i++)
            if (myList[i] > max) max = myList[i];
        System.out.println("max = " + max);
    }
}
