public class Graph {
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
    
    private void visit(Node node) {
        System.out.print(node.getValue() + " ");
    }
        
    public Node[] getNodes() {
        return nodes;
    }
    
    public Node getNode(int index) {
        return nodes[index];
    }
        
    public void print() {
        System.out.println("This graph has " + V + " nodes:");
        for (Node n : nodes) {
            n.print();
        }
    }
}
