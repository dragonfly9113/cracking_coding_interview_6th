//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

// Chapter 1: Arrays and Strings

// MyArrayList: implement my own ArrayList
// need to implement the following methods:
// 1. get
// 2. insert
// 3. size
// 4. increaseSize

// An ArrayList is an array that resizes itself as needed while still providing O(1) access. A typical implementation
// is that when the array is full, the array doubles in size. Each doubling takes O(n) time, but happens so rarely that
// its amortized insertion time is still O(1).

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        System.out.println("Hello world!");
    }
}

class MyArrayList
{
    private Object[] storage_arr;
    private int actualSize;
    
    MyArrayList() {
        storage_arr = new Object[10];
    }

    Object get(int index) {
        Object obj = new Object();
        
        if (index < 0 || index >= storage_arr.length)
            throw new ArrayIndexOutOfBoundsException();
        
        return obj;
    }
    
}
 
