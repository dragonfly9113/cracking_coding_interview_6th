//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// 2.1: remove duplicates in a linked list
// This version uses a self-defined class LinkedListNode to represent a linked list

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

        // O(n)
        public static LinkedListNode buildList(int[] a) {
            if (a.length == 0) return null;
            
            LinkedListNode head = null;
            for (int i = a.length-1; i >= 0; i--)
                head = LinkedListNode.push(a[i], head);
            
            return head;
        }
        
        // O(1)
        public static LinkedListNode push(int d, LinkedListNode head) {
            LinkedListNode node = new LinkedListNode(d);
            
            if (head == null) return node;
            
            node.next = head;
            return node;
        }
        
        // O(n)
        public static LinkedListNode append(int d, LinkedListNode head) {
            LinkedListNode node = new LinkedListNode(d);
            
            if (head == null) return node;
            
            LinkedListNode prev = null;
            LinkedListNode temp = head;
            while (temp != null) {
                prev = temp;
                temp = temp.next;
            }

            prev.next = node;
            return head;
        }
        
        // O(1)
        public static LinkedListNode insertAfter(int d, LinkedListNode n, LinkedListNode head) {
            LinkedListNode node = new LinkedListNode(d);
            
            if (n == null) return node;
            
            node.next = n.next;
            n.next = node;
            return head;
        }
        
        // O(n)
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

        // O(n)
        public static int size(LinkedListNode head) {
            int count = 0;
            while (head != null) {
                count++;
                head = head.next;
            }
            
            return count;
        }
            
        public static void printList(LinkedListNode head) {
            while (head != null) {
                System.out.print(head.data + " ");
                head = head.next;
            }
            System.out.println();
        }
    }
    
    public static void main(String args[])
    {
        // create a sample linked list
        int[] a = {1, 2, 3, 4, 5, 6};
        LinkedListNode head = LinkedListNode.buildList(a);
        
        head = LinkedListNode.push(0, head);
        head = LinkedListNode.append(7, head);
        head = LinkedListNode.insertAfter(5, head.next, head);
        head = LinkedListNode.push(4, head);
        
        System.out.println("The list is:");
        LinkedListNode.printList(head);
        System.out.println("The size of the list is: " + LinkedListNode.size(head));
        
        LinkedListNode.deleteDups(head);
        
        System.out.println("After deleteDups(), the list is:");
        LinkedListNode.printList(head);
        System.out.println("The size of the list is: " + LinkedListNode.size(head));
    }
}
