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
        char[] arr = str.toCharArray();
        
        //System.out.println(URLify_1(str, str.length()-4));
        System.out.println("arr.length = " + arr.length);
        System.out.println(arr);
        
        System.out.println(URLify_2(arr, arr.length - 4));
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
    static char[] URLify_2(char[] s, int len)
    {
        if (s.length == 0 || len == 0) return s;
        
        // count number of spaces in s
        int count = 0;
        for (int i = 0; i < len; i++)
            if (s[i] == ' ') count++;
        
        int newLen = len + 2 * count + 1;
        char[] new_s = new char[newLen];
        System.arraycopy( s, 0, new_s, 0, len );
        
        System.out.println("new_s.length = " + new_s.length);
        System.out.println(new_s);
        
        int index = newLen - 2;
        for (int i = len - 1; i >= 0; i--)
        {
            if (new_s[i] != ' ')
            {
                new_s[index] = new_s[i];
                index--;
            }
            else
            {
                new_s[index] = '0';
                new_s[index - 1] = '2';
                new_s[index - 2] = '%';
                index = index - 3;
            }
        }

        new_s[newLen - 1] = '\0';
        return new_s;
    }
}
