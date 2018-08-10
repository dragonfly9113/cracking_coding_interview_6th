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

    // trying to use BFS to create the lists of depths, but I how to set the correct value for depth?
    // The book has an implementation of this method (using a modified BFS), but I don't understand it.
    static void bfsUtil(Node root, int depth, ArrayList<LinkedList<Node>> arr) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        while (!q.isEmpty()) {
            Node node = q.remove();
            //visit(node);
            addNodeToList(node, depth, arr);

            for (Node n : node.children) {
                if (n == null) continue;
                
                q.add(n);
            }
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
    
    // 4.4: Check Balanced: implement a function to check if a binary tree is balanced. For the purpose of this question,
    // a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
    // Note:
    // The height of a node is the number of edges on the longest path between that node and a leaf.
    // The height of a tree is the height of its root node.
    // The level of a node is defined as: 1 + the number of edges between the node and the root.
    
    // Solution 1:
    // This DFS version is not working...
    static boolean checkBalancedDFS(Node root) {
        if (root == null) return false;
        
        //visit(root);
        int h1 = getHeightOfNode(root.children[0]);
        int h2 = getHeightOfNode(root.children[1]);
        if (Math.abs(h1 - h2) > 1) return false;
            
        for (Node n : root.children) {
            dfs(n);
        }
        
        return true;
    }
    
    // Time complexity: O(N * log(N))
    static boolean checkBalancedBFS(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        while (!q.isEmpty()) {
            Node node = q.remove();
            //visit(node);
            if (false == checkNodeBalance(node)) return false;

            for (Node n : node.children) {
                if (n == null) continue;
                
                q.add(n);
            }
        }
        
        return true;
    }
    
    static boolean checkNodeBalance(Node root) {
        if (root == null) return true;
        
        int h1 = getHeightOfNode(root.children[0]);
        int h2 = getHeightOfNode(root.children[1]);
        if (Math.abs(h1 - h2) > 1) return false;
        
        return true;
    }
    
    // The brute force way: for each node, try to calculate the height of each of its subtrees and then compare.
    // How to calculate the height of a node (the root of a substree)? - easiest way is to use a recursive function.
    static int getHeightOfNode(Node root) {
        if (root == null || (root.children[0] == null && root.children[1] == null)) return 0;

        int h1 = getHeightOfNode(root.children[0]);
        int h2 = getHeightOfNode(root.children[1]);
        
        return Math.max(h1, h2) + 1;
    }
    
    // Solution 2:
    // The above way of checking balance is not efficient, because for each node, we will repeatlly call getHeightOfNode() to calculate
    // the height of its two subtrees. Therefore for some nodes, its height will be calculated multiple times which is a waste.
    // Please note that in method getHeightOfNode(), since we will calculate the heights of its two substrees, we are in a good position
    // to judge if this tree (with the node as its root) is balanced or not. If this tree is unbalanced, we will return an error code
    // right away, thus avoiding unnecessarily recursing more into other nodes.
    // Error code: -1 ---- the node is null
    //             MIN_VALUE ---- the tree (with the node as root) is unbalanced
    // Time complexity: O(N)
    static int getHeight(Node root) {
        if (root == null) return -1;
        
        int h1 = getHeight(root.children[0]);
        if (h1 == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        
        int h2 = getHeight(root.children[1]);
        if (h2 == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        
        if (Math.abs(h1 - h2) > 1)
            return Integer.MIN_VALUE;
        else
            return Math.max(h1, h2) + 1;
    }
    
    static boolean isBalanced(Node root) {
        return getHeight(root) != Integer.MIN_VALUE;
    }
    
    // 4.5: Validate BST: Implement a function to check if a binary tree is a binary search tree
    // A binary search tree is a binary tree in which every node fits a specific ordering property:
    // all left descendents <= n < all right descendents. This must be tree for each node n.
    // Hints: 35, 57, 86, 113, 128
    
    
    
    
    
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

        // change the tree to make it unbalanced:
        Node c2122 = new Node(13), c21222 = new Node(14);
        //c212.children[1] = c2122;
        //c2122.children[1] = c21222;
        
        //print2D(root);
        
        System.out.println("dfs search result:");
        dfs(root);
        System.out.println();
        
        System.out.println("bfs search result:");
        bfs(root);
        System.out.println();
        
        System.out.println("Check balanced:");
        //System.out.println("The height of root: " + getHeightOfNode(root));
        //System.out.println("The binary tree is balanced: " + checkBalancedBFS(root));
        System.out.println("The height of root: " + getHeight(root));
        System.out.println("The binary tree is balanced: " + isBalanced(root));
    }
}
