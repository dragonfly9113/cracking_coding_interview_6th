//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        String s1 = "abcea";
        String s2 = "bcaea";
                
        System.out.println("s1 and s2 are permutation of each other: " + isPermutation(s1, s2));
    }
    
    // Assume ASCII char set
    public static boolean isPermutation(String s1, String s2) {
        int[] char_set = new int[128];
        
        if (s1.length() != s2.length()) return false;
        
        // first figure out the charset of s1
        for (int i = 0; i < s1.length(); i++) {
            int val = s1.charAt(i);
            char_set[val]++;
        }
        
        // compare s2 with s1's charset and see if they match
        for (int i = 0; i < s2.length(); i++) {
            int val = s2.charAt(i);
            
            // if this char is in s2 but not in s1, then s2 is not a permutation of s1
            if (char_set[val] == 0) return false;
            
            // this char is found in both s1 and s2, reduce the counter by 1
            char_set[val]--;
        }
        
        // all chars in s2 can be found in s1
        return true;
    }
    
    
}
