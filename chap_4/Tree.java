//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    // A simple tree node
    public static class Node {
        public int value;
        public Node[] children;
        
        public Node(int value) {
            this.value = value;
            children = new Node[2];
        }
    }

    static void inOrderTraversal(Node node) {
        if (node == null) return;
        
        inOrderTraversal(node.children[0]);
        System.out.print(node.value + " ");
        inOrderTraversal(node.children[1]);
    }
    
    static void preOrderTraversal(Node node) {
        if (node == null) return;
        
        System.out.print(node.value + " ");
        preOrderTraversal(node.children[0]);
        preOrderTraversal(node.children[1]);
    }
    
    public static void main(String args[])
    {
        System.out.println("A simple binary search tree");
        
        // layer 1
        Node root = new Node(7);
        
        // layer 2
        Node c1 = new Node(4), c2 = new Node(11);
        root.children[0] = c1;
        root.children[1] = c2;

        // layer 3
        Node c11 = new Node(2), c12 = new Node(6), c21 = new Node(9), c22 = new Node(12);
        c1.children[0] = c11;
        c1.children[1] = c12;
        c2.children[0] = c21;
        c2.children[1] = c22;
        
        // layer 4
        Node c111 = new Node(1), c112 = new Node(3), c121 = new Node(5), c211 = new Node(8), c212 = new Node(10);
        c11.children[0] = c111;
        c11.children[1] = c112;
        c12.children[0] = c121;
        c21.children[0] = c211;
        c21.children[1] = c212;
        
        System.out.println("inOrderTraversal:");
        inOrderTraversal(root);
        System.out.println();
        
        System.out.println("preOrderTraversal:");
        preOrderTraversal(root);
        System.out.println();
    }
}
