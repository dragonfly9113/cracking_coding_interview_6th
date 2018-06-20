
  // A simple graph node
  public class Node {
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

	/**
	 * @return the neighbors
	 */
	public Node[] getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors the neighbors to set
	 */
	public void setNeighbors(Node[] neighbors) {
		this.neighbors = neighbors;
	}
  }
