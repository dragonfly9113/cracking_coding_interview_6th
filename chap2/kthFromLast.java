//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// 2.2 Return Kth node from the last node

class Rextester
{  
    static class Linked_list
    {
        private Node head;
        private int nodeCount;
        
        Linked_list() {
            head = null;
            nodeCount = 0;
        }
        
        class Node
        {
            private int data;
            private Node next;
            
            Node(int d) { data = d; next = null; }
        }
        
        // Insert a new Node at front of the list.
        // Time complexity: O(1)
        public void push(int new_data)
        {
            Node new_node = new Node(new_data);
            
            new_node.next = head;
            head = new_node;
            nodeCount++;
        }

        // Append a new Node at the end of the list.
        // Time complexity: O(N)
        public void append(int new_data)
        {
            Node new_node = new Node(new_data);
            
            if (head == null) {
                head = new_node;
                nodeCount++;
                return;
            }
            
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            
            temp.next = new_node;
            nodeCount++;
        }
        
        // Insert a new Node after the given Node
        // Assume prev_node is either null or a valid Node in the list
        // Time complexity: O(1)
        public void insertAfter(Node prev_node, int new_data)
        {
            if (prev_node == null) return;
            
            Node new_node = new Node(new_data);

            new_node.next = prev_node.next;
            prev_node.next = new_node;
            nodeCount++;
        }
        
        // Given a key, deletes the first occurance of key in the linked list
        public void deleteNode(int key)
        {
            // empty list, abort deletion
            if (head == null) return;
            
            // the first occurance of key is the head, delete the head
            if (head.data == key) {
                head = head.next;
                nodeCount--;
                return;
            }
                        
            Node temp = head;
            while (temp.next != null && temp.next.data != key)
                temp = temp.next;
            
            // no occurance of key found, abort deletion
            if (temp.next == null) return;
            
            // found the first occurance of key, delete the according node
            temp.next = temp.next.next;
            nodeCount--;
        }
        
        // Remove duplicate node(s)
        // Time complexity: O(n)
        public void deleteDups() 
        {
            HashSet<Integer> set = new HashSet<Integer>();
            
            Node prev = null;
            Node temp = head;
            while (temp != null) {
                // if this node has duplicate value, delete this node
                if (set.contains(temp.data)) {
                    prev.next = temp.next;
                    nodeCount--;
                }
                // if this node has unique value, add this value to set for recording purpose
                else {
                    set.add(temp.data);
                    prev = temp;
                }
                temp = temp.next;
            }
        }
        
        // Return the Kth node from the last node
        // Time complexity: O(n)
        public Node kthFromLast(int k)
        {     
            if (nodeCount == 0 || k < 0 || k >= nodeCount) return null;
            
            Node n1 = head, n2 = head;
            
            // n2's starting point is kth ahead of n1
            for (int i = 0; i < k; i++)
                n2 = n2.next;

            // move n1 and n2 together until n2 refers to the last node
            while (n2.next != null) {
                n1 = n1.next;
                n2 = n2.next;
            }

            // return n1 which refers to the kth from n2 (the last node)
            return n1;
        }
            
        // Return the size of the list by traversing through the list
        // Time complexity: O(n)
        public int getSize()
        {
            Node temp = head;
            int size = 0;
            
            while (temp != null) {
                size++;
                temp = temp.next;
            }
            
            return size;
        }
        
        // Do the same thing as getSize() but in O(1) time
        public int getCount()
        {
            return nodeCount;
        }
        
        public void buildList(int[] a)
        {
            for (int i = a.length - 1; i >= 0; i--) {
                push(a[i]);
            }
        }
        
        // Print out the list
        public void printList()
        {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            
            System.out.println();
        }
    }
    
    public static void main(String args[])
    {
        Linked_list llist = new Linked_list();
        int[] a = {1, 2, 3, 4, 5, 6};
        llist.buildList(a);
        llist.printList();
        
        llist.push(0);
        llist.printList();
        
        llist.append(7);
        llist.printList();
        
        llist.insertAfter(llist.head, 4);
        llist.append(4);
        llist.push(4);
        llist.printList();
        
        llist.deleteNode(6);
        llist.printList();
        System.out.println("getCount() = " + llist.getCount());
        
        int k = 5;
        Linked_list.Node n = llist.kthFromLast(k);
        if (n != null)
            System.out.println("The " + k + "th node from the last is: " + n.data);
        else
            System.out.println("The " + k + "th node from the last is an invalid node.");
    }
}
