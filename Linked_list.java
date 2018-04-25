//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// 1.7 Rotate Matrix

class Rextester
{  
    public static void main(String args[])
    {
        Linked_list llist = new Linked_list();
        
        llist.push(1);
        llist.push(2);
        llist.push(3);
        
        llist.append(4);
        llist.append(5);
        
        llist.insertAfter(llist.head.next, 6);
        
        llist.deleteNode(6);
        llist.deleteNode(7);
        llist.deleteNode(3);
        llist.deleteNode(5);
            
        llist.printList();
    }

    static class Linked_list
    {
        Node head;
        
        class Node
        {
            int data;
            Node next;
            
            Node(int d) { data = d; next = null; }
        }
        
        // Insert a new Node at front of the list.
        // Time complexity: O(1)
        public void push(int new_data)
        {
            Node new_node = new Node(new_data);
            
            new_node.next = head;
            head = new_node;
        }

        // Append a new Node at the end of the list.
        // Time complexity: O(N)
        public void append(int new_data)
        {
            Node new_node = new Node(new_data);
            
            if (head == null) {
                head = new_node;
                return;
            }
            
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            
            temp.next = new_node;
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
        }
        
        // Given a key, deletes the first occurance of key in the linked list
        public void deleteNode(int key)
        {
            // empty list, abort deletion
            if (head == null) return;
            
            // the first occurance of key is the head, delete the head
            if (head.data == key) {
                head = head.next;
                return;
            }
                        
            Node temp = head;
            while (temp.next != null && temp.next.data != key)
                temp = temp.next;
            
            // no occurance of key found, abort deletion
            if (temp.next == null) return;
            
            // found the first occurance of key, delete the according node
            temp.next = temp.next.next;
        }
        
        // Print out the list
        public void printList()
        {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }
        
    }
}
