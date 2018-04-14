//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        System.out.println("Hello, World!");
    }
    
    boolean isOneAway(String s1, String s2)
    {
        int len1 = s1.length();
        int len2 = s2.length();
        
        if (s1.equals(s2)) return true;
        
        if (Math.abs(len1 - len2) > 1) return false;
        
        // if s1 and s2 are of the same length, check if only one char is different (one replace case)
        if (len1 == len2) {
            int diffCount = 0;
            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) 
                    diffCount++;
                
                if (diffCount > 1) return false;
            }
            
            return true;
        }
            
        // Now consider the case of s1 and s2 are of different length
        // First let's make sure s1 and len1 refer to the longer string
        if (len1 < len2) {
            int tmp = len1;
            String t = s1;
            
            // swap s1 and s2
            len1 = len2; len2 = tmp;
            s1 = s2; s2 = t;
        }

        // now check s1 and s2 one char a time
        boolean oneInsert = false;
        int i1 = 0, i2 = 0;
        
        while (i1 < len1 && i2 < len2) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                i1++; i2++; continue;
            }
            
            if (oneInsert) return false;
            
            i1++;
            oneInsert = true;
        }
        return true;
    }
}
