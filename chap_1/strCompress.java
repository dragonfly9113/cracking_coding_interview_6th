//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// 1.6 String Compression

// input: aabcccccaaa
// output: a2b1c5a3
// If the 'compressed' string would not become smaller than the original string, return the original one
// Assume the string has only uppercase and lowercase letters (a-z)

class Rextester
{  
    public static void main(String args[])
    {
        String s = "aabcccccaaa";
        String s1 = "abcd";
        
        System.out.println("strCompress(s) = " + strCompress(s));
        System.out.println("strCompress(s1) = " + strCompress(s1));
    }

    static String strCompress(String s)
    {
        String t = new String();
        
        int index = 0, count = 0;
        char preC = '\0';
        
        while (index < s.length()) {
            char c = s.charAt(index);
            
            if (c != preC) {
                if (preC != '\0')
                    t = t + count;
                t = t + c;
                count = 1;
                index++;
                preC = c;
                continue;
            }
            
            count++; index++;
        }
        
        t = t + count;
        
        return (t.length() < s.length()) ? t : s;
    }
}
