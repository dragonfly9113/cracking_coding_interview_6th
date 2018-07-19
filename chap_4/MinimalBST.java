//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    private static final int COUNT = 10;
        
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

    static void postOrderTraversal(Node node) {
        if (node == null) return;
        
        postOrderTraversal(node.children[0]);
        postOrderTraversal(node.children[1]);
        System.out.print(node.value + " ");        
    }
    
    //4.2: Minimal bineary search tree (BST)
    //to create a tree of minimal height, we need to match the number of nodes in the left subtyree to the number of nodes in the right subtree
    //as much as possible.
    // One way: inserts the value v through a recrusive process that starts with the root node.
    static Node insertNode(int[] arr) {
        return insertNode(arr, 0, arr.length - 1);
    }
    
    static Node insertNode(int[] arr, int start, int end) {
        if (start > end) return null;
        
        int mid = (start + end) / 2;
        Node root = new Node(arr[mid]);
        if (start == end) return root;
        
        root.children[0] = insertNode(arr, start, mid - 1);
        root.children[1] = insertNode(arr, mid + 1, end);
        
        return root;
    }
    
    // Wrapper over print2DUtil()
    static void print2D(Node root)
    {
       // Pass initial space count as 0
       print2DUtil(root, 0);
    }
    
    // Function to print binary tree in 2D
    // It does reverse inorder traversal
    static void print2DUtil(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.children[1], space);

        // Print current node after space
        // count
        System.out.println();
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.println(root.value);

        // Process left child
        print2DUtil(root.children[0], space);
    }

    public static void main(String args[])
    {
        //System.out.println("A simple binary search tree");
        System.out.println("Create a minimal height binary search tree from an array");
        //int[] arr = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node root = insertNode(arr);
        
        print2D(root);
    }
}
