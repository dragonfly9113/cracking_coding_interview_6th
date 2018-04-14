//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// Chapter 1
// 1.4 Palindrome Permutation
// Given a tring, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase that is the same forwards and backwards.
// A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
// Example:
// Input: Tact Coa
// Output: true (permutations: "taco cat", "atco cta", etc.)
// Hints: 106, 121, 134, 136

class Rextester
{  
    public static void main(String args[])
    {
        String s1 = "Tact Coa";  // true
        System.out.println("s1 = " + isPermutationOfPalindrome_1(s1));

        String s2 = "Tact CoaA";  // false
        System.out.println("s2 = " + isPermutationOfPalindrome_1(s2));

        String s3 = "Tact Coa O";  // true
        System.out.println("s3 = " + isPermutationOfPalindrome_1(s3));
        
        String s4 = "Tact Coa coe";  // false
        System.out.println("s4 = " + isPermutationOfPalindrome_1(s4));
    }
    
    // Assume case insensitive, space is not significant, ASCII
    // If a string is a permutation of a palindrome, it must have the following characteristics:
    // 1. All letters in the string are in pairs (or in fours, sixes, etc.)
    // 2. Or except for one letter, all other letters in the string are in pairs (or in fours, sixes, etc.)
    // This version uses a hash-table like table
    static boolean isPermutationOfPalindrome(String str)
    {
        int[] count_table = new int[128]; // assume ASCII
        
        // convert all letters to lower case since we assume case insensitive
        String s = str.toLowerCase();
        
        // count each letter's occurrance number and save in a table
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == ' ') continue; // ignore space (don't count)
            
            int val = s.charAt(i);
            count_table[val]++;
        }
        
        // check the count_table: we can only have 0 or 1 letter with odd occurrance number
        int oddOccurrance = 0;
        for (int i = 0; i < 128; i++)
        {
            if (count_table[i] % 2 != 0) oddOccurrance++;
            
            if (oddOccurrance > 1) return false;
        }
        
        return true;
    }
    
    // Use a bit vector to reduce storage space
    // Assume letters only from a to z, thus one integer (32-bit) is good enough
    static boolean isPermutationOfPalindrome_1(String str)
    {
        int count_int = 0; // assume letters only from a - z
        
        // convert all letters to lower case since we assume case insensitive
        String s = str.toLowerCase();
        
        // count each letter's occurrance number and save in a table
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == ' ') continue; // ignore space (don't count)
            
            int val = s.charAt(i) - 'a';
            count_int ^= (1 << val); // exclusive OR (^)
        }
        
        // check the count_int: we can only have 0 or 1 bit set to one
        int oddOccurrance = 0;
        for (int i = 0; i < 32; i++)
        {
            if (((count_int >> i) & 1) == 1) oddOccurrance++;
            
            if (oddOccurrance > 1) return false;
        }
        
        return true;
    }

}
