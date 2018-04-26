//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    static class LinkedListNode
    {
        int data;
        LinkedListNode next;
        
        LinkedListNode(int d) { 
            data = d;
            next = null;
        }
        
        public static LinkedListNode buildList(int[] a) {
            
            
        }
        
    }
    
    public static void main(String args[])
    {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode n = new LinkedListNode(2);
        head.next = n;
        
        n = new LinkedListNode(2);
        head.next.next = n;
        
        System.out.println("Hello, World!");
    }
    
    static void deleteDups(LinkedListNode n)
    {
        HashSet<Integer> set = new HashSet<Integer>();
        
        LinkedListNode prev = null;
        while (n != null) {
            // if this node has a duplicate data, delete this node
            if (set.contains(n.data)) {
                prev.next = n.next;
            } 
            // otherwise, add this node's data into the hash set.
            else {
                set.add(n.data);
                prev = n;
            }
            n = n.next;
        }
    }
    
    

}
