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
        String s = "Tact Coa";
        
        System.out.println("isPermutationOfPalindrome = " + isPermutationOfPalindrome(s));
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
}
