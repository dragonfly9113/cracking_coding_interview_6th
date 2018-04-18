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
        
        System.out.println("strCompress(s) = " + strCompress_1(s));
        System.out.println("strCompress(s1) = " + strCompress_1(s1));
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
    
    // String concatenation in Java is of time complexity of O(n^2) because of the fact that a String object
    // is immutable and thus many intermediate String objects need to be created to hold intermediate results.
    // Therefore, it is more efficient to use StringBuilder or StringBuffer, which is of time complexity of O(n).
    static String strCompress_1(String s)
    {
        StringBuilder t = new StringBuilder();
        
        int count = 0;
        char preC = '\0';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c != preC) {
                if (preC != '\0')
                    t.append(count);
                t.append(c);
                count = 1;
                preC = c;
                continue;
            }
            
            count++;
        }
        
        t.append(count);
        
        return (t.length() < s.length()) ? t.toString() : s;
    }

}
