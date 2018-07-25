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

    static void dfs(Node root) {
        if (root == null) return;
        
        visit(root);

        for (Node n : root.children) {
            //if (n == null || n.visited) continue;
            dfs(n);
        }
    }

    static void bfs(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        while (!q.isEmpty()) {
            Node node = q.remove();
            visit(node);

            for (Node n : node.children) {
                if (n == null) continue;
                
                q.add(n);
            }
        }
    }

    static void visit(Node node) {
        System.out.print(node.value + " ");
    }    
    
    //4.3: List of Depths: Given a binary tree, design an alogrimthm which creates a linked list of all the nodes at each depth (e.g., if you have
    // a tree with depth D, you'll have D linked lists)
    static ArrayList<LinkedList<Node>> listOfDepths(Node root) {
        ArrayList<LinkedList<Node>> arr = new ArrayList<LinkedList<Node>>();
        
        dfsUtil(root, 0, arr);
        
        return arr;
    }

    static void dfsUtil(Node root, int depth, ArrayList<LinkedList<Node>> arr) {
        if (root == null) return;

        addNodeToList(root, depth, arr);

        for (Node n : root.children) {
            dfsUtil(n, depth + 1, arr);
        }
    }

    static void addNodeToList(Node root, int depth, ArrayList<LinkedList<Node>> arr) {
        if (depth < 0) return;
        
        LinkedList<Node> ll = null;
        if (depth < arr.size()) {
            ll = arr.get(depth);
            
            ll.add(root);
        } else {
            ll = new LinkedList<Node>();
            ll.add(root);
            arr.add(depth, ll);
        }
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
        System.out.println("Create a linked list of all the nodes at each depth");
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node root = insertNode(arr);
        //print2D(root);
        
        System.out.println("dfs search result:");
        dfs(root);
        System.out.println();
        
        System.out.println("bfs search result:");
        bfs(root);
        System.out.println();
        
        System.out.println("Create list of depths:");
        ArrayList<LinkedList<Node>> al = listOfDepths(root);
        for (LinkedList<Node> ll : al) {
            //System.out.println(ll);
            for (Node n : ll) {
                System.out.print(n.value + " ");
            }
            System.out.println();
        }
        
    }
}
