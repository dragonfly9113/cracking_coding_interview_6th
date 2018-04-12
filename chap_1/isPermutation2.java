//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        String s1 = "abceA";
        String s2 = "bcaea";
                
        System.out.println("s1 and s2 are permutation of each other: " + isPermutation(s1, s2));    
    }
    
    static boolean isPermutation(String s1, String s2)
    {
        if (s1.length() != s2.length()) return false;
        
        return sort(s1).equals(sort(s2));
    }
 
    static String sort(String s)
    {
        char[] arr = s.toCharArray();
        
        java.util.Arrays.sort(arr);
        return new String(arr);
    }
}
