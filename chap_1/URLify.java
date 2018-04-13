//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// Chapter 1
// 1.3 URLify

class Rextester
{  
    public static void main(String args[])
    {
        String str = "A Good Day!    ";
        
        System.out.println(URLify_1(str, str.length()-4));
    }
    
    // String concatenation is implemented through the StringBuilder(or StringBuffer) class and its append method.
    // This method needs an extra String
    static String URLify_1(String s, int actualLen)
    {
        String t = "";
        
        if (s.length() == 0 || actualLen == 0) return t;
        
        for (int i = 0; i < actualLen; i++)
        {
            char c = s.charAt(i);
            
            if (c == ' ')
                t = t + "%20";
            else
                t = t + c;
        }
            
        return t;
    }
    
    
    // If implementing in Java, please use a character array so that you can perform this operation in place
    //
    // s.toCharArray(): a newly allocated character array whose length is the length of this string and whose contents are initialized to contain the character sequence represented by this string.
    static String URLify_2(String s, int actualLen)
    {
        char[] arr = s.toCharArray();
        
        
        
        return "";
     
    }
}
