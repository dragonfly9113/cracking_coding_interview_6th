
public class testGraph {

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
		  
		n0.getNeighbors()[0] = n5;
		n0.getNeighbors()[1] = n4;
		n0.getNeighbors()[2] = n1;
		g.setNodes(0, n0);
  
		n1.getNeighbors()[0] = n4;
		n1.getNeighbors()[1] = n3;
		g.setNodes(1, n1);
		
		n2.getNeighbors()[0] = n1;
		g.setNodes(2, n2);
		
		n3.getNeighbors()[0] = n4;
		n3.getNeighbors()[1] = n2;
		g.setNodes(3, n3);
		
		g.setNodes(4, n4);
		g.setNodes(5, n5);
		g.print();
		  
		int nodeIdx = 0;
		System.out.println("DFS search from node " + nodeIdx + " :");
		    g.dfs(g.getNode(nodeIdx));
		    System.out.println();
		}
}
