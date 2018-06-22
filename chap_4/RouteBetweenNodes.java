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
            visit(root);
            root.visited = true;

            for (Node n : root.getNeighbors()) {
                if (n.visited) continue;

                dfs(n);
            }
        }
        
        public void bfs(Node root) {
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
        
        public boolean routeBetweenNodes(int index1, int index2) {
            if (index1 < 0 || index1 >= V || index2 < 0 || index2 >= V) throw new IndexOutOfBoundsException();
            
            
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

/*        
        int nodeIdx = 0;
        System.out.println("DFS search from node " + nodeIdx + " :");
        g.dfs(g.getNode(nodeIdx));
        System.out.println();

        System.out.println("BFS search from node " + nodeIdx + " :");
        g.clearVisited();
        g.bfs(g.getNode(nodeIdx));
        System.out.println();
*/
        int index1 = 0, index2 = 5;
        System.out.println("There is a route between node " + index1 + " and node " + index2 + " : " + g.routeBetweenNodes(index1, index2));
        
    }
}
