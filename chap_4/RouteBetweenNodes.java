//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    // A simple graph node
    public static class Node {
        private int value;
        private Node[] neighbors;
        boolean visited;

        public Node(int value) {
            this.value = value;
            visited = false;
        }

        public Node(int value, int numOfEdges) {
            this.value = value;
            setNeighbors(new Node[numOfEdges]);
        }

        public int getValue() {
            return value;
        }

        public void print() {
            System.out.print("node " + value + " has routes to: ");
            for (Node n : getNeighbors()) {
                System.out.print(n.value + " ");
            }
            System.out.println();
        }

        public Node[] getNeighbors() {
            return neighbors;
        }

        public void setNeighbors(Node[] neighbors) {
            this.neighbors = neighbors;
        }
    }

    public static class Graph {
        private int V;
        private Node[] nodes;

        public Graph(int v) {
            V = v;
            nodes = new Node[V];
        }

        public void dfs(Node root) {
            clearVisited();
            dfsHelper(root);
        }
        
        public void dfsHelper(Node root) {
            visit(root);
            root.visited = true;

            for (Node n : root.getNeighbors()) {
                if (n.visited) continue;

                dfsHelper(n);
            }
        }

        // 4.1: Route Between Nodes: use dfs search
        // dfs search is usually easier to implement since we can use recursion. For this problem, it is as efficient as bfs. However, for some other problems
        // such as shorted path, bfs search is usually more efficient since it doesn't need to go too deep before visiting neighbors.
        public LinkedList<Node> dfsWithRoute(Node root) {
            clearVisited();
            LinkedList<Node> route = new LinkedList<Node>();
            
            dfsHelper1(root, route);
            return route;
        }
        
        public void dfsHelper1(Node root, LinkedList<Node> route) {
            //visit(root);
            route.add(root);
            root.visited = true;

            for (Node n : root.getNeighbors()) {
                if (n.visited) continue;

                dfsHelper1(n, route);
            }
        }
        
        public void bfs(Node root) {
            clearVisited();
            Queue<Node> q = new LinkedList<Node>();
            q.add(root);
            
            while (!q.isEmpty()) {
                Node node = q.remove();
                visit(node);
                node.visited = true;
                
                for (Node n : node.getNeighbors()) {
                    if (n.visited) continue;
                    
                    q.add(n);
                }
            }
        }

        // 4.1: Route Between Nodes: use bfs search
        // bfs is usually harder than dfs to implement since it is not actually recursion.
        // However for this problem, it is actually more intuitive to use bfs search.
        public LinkedList<Node> bfsWithRoute(Node root) {
            clearVisited();
            Queue<Node> q = new LinkedList<Node>();
            LinkedList<Node> route = new LinkedList<Node>();
            q.add(root);
            
            while (!q.isEmpty()) {
                Node node = q.remove();
                //visit(node);
                route.add(node);
                node.visited = true;
                
                for (Node n : node.getNeighbors()) {
                    if (n.visited) continue;
                    
                    q.add(n);
                }
            }
            
            return route;
        }

        private void visit(Node node) {
            System.out.print(node.getValue() + " ");
        }

        public Node[] getNodes() {
            return nodes;
        }

        public Node getNode(int index) {
            return nodes[index];
        }

        public void setNodes(int index, Node n) {
            nodes[index] = n;
        }
        
        public void clearVisited() {
            for (Node n : nodes) {
                n.visited = false;
            }
        }
        
        // 4.1: Route Between Nodes: record all the nodes that node1 can reach and check if node2 is in the list.
        // if there are lots of nodes that node1 can reach, this method can be very inefficient.
        public boolean routeBetweenNodes(int index1, int index2) {
            if (index1 < 0 || index1 >= V || index2 < 0 || index2 >= V) throw new IndexOutOfBoundsException();


            //return bfsWithRoute(getNode(index1)).contains(getNode(index2)) || bfsWithRoute(getNode(index2)).contains(getNode(index1));
            return dfsWithRoute(getNode(index1)).contains(getNode(index2)) || dfsWithRoute(getNode(index2)).contains(getNode(index1));
        }
        
        // 4.1: Route between nodes: a better way
        // Now assume: we only consider the route from node1 (start) to node2 (end) while not consider the vise verse way
        // No mater bfs or dfs, we don't need to go through and record all nodes that node1 can reach.
        public boolean searchByBfs(Node start, Node end) {
            if (start == null || end == null) return false;
            if (start == end) return true;
    
            clearVisited();
            Queue<Node> q = new LinkedList<Node>();
            q.add(start);
            
            while (!q.isEmpty()) {
                Node node = q.remove();
                if (node != null) {
                    //visit(node);
                    if (node == end) {
                        return true;
                    } else {
                        node.visited = true;
                    }
                    
                    for (Node n : node.getNeighbors()) {
                        if (n.visited) continue;
                        
                        q.add(n);
                    }
                }
            }
            
            return false;
        }
        
        public void print() {
            System.out.println("This graph has " + V + " nodes:");
            for (Node n : nodes) {
                n.print();
            }
        }
    }
    
    public static void main(String args[])
    {
        System.out.println("A simple directed graph");
        
        int numOfNodes = 6;
        Graph g = new Graph(numOfNodes);
        
        Node n0 = new Node(0, 3);
        Node n1 = new Node(1, 2);
        Node n2 = new Node(2, 1);
        Node n3 = new Node(3, 2);
        Node n4 = new Node(4, 0);
        Node n5 = new Node(5, 0);
        
        n0.neighbors[0] = n5;
        n0.neighbors[1] = n4;
        n0.neighbors[2] = n1;
        g.nodes[0] = n0;
        
        n1.neighbors[0] = n4;
        n1.neighbors[1] = n3;
        g.nodes[1] = n1;

        n2.neighbors[0] = n1;
        g.nodes[2] = n2;

        n3.neighbors[0] = n4;
        n3.neighbors[1] = n2;
        g.nodes[3] = n3;

        g.nodes[4] = n4;
        g.nodes[5] = n5;
        g.print();

        int nodeIdx = 0;
        System.out.println("DFS search from node " + nodeIdx + " :");
        g.dfs(g.getNode(nodeIdx));
        System.out.println();

        System.out.println("BFS search from node " + nodeIdx + " :");
        g.bfs(g.getNode(nodeIdx));
        System.out.println();

        for (int index1 = 0; index1 < numOfNodes; index1++) {
            for (int index2 = 0; index2 < numOfNodes; index2++) {
                //System.out.println("There is a route between node " + index1 + " and node " + index2 + " : " + g.routeBetweenNodes(index1, index2));
                Node node1 = g.getNode(index1), node2 = g.getNode(index2);
                System.out.println("There is a route from node " + index1 + " to node " + index2 + " : " + g.searchByBfs(node1, node2));
            }
        }
    }
}
