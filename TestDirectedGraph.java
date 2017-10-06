package DirectedGraphPack;
/*
  Madhumita Ghude
  Course: CS 610852
  Programming Assignment # 3
  No special instructions or problems
 */
import java.util.List;

public class TestDirectedGraph {
    public static void main(String[] args){
        System.out.println("---------------------------------------------------------");
        System.out.println("Graph A");
        System.out.println("---------------------------------------------------------");
        String graphA = "[v4, v1][v5, v1][v5, v2][v6, v2][v6, v3][v7, v3][v8, v4][v9, v4][v9, v5][v10, v5][v10, v6]" +
                "[v10, v7][v11, v7][v12, v8][v12, v9][v12, v10][v13, v10][v13, v11][v14, v12][v14, v13][v15, v1][v15, v2]";
        DirectedGraph A = new DirectedGraph(graphA);

        System.out.println("\nGraph A "+(A.isDAG() ? "is ":"is not ")+"a directed graph.");
        System.out.println("\nRoots in A:");
        if(A.getRoots().size()>0) {
            for (GraphNode rootA : A.getRoots()) {
                System.out.println(rootA);
            }
        }else{
            System.out.println("-none-");
        }

        System.out.println("\nNumber of edges in A = "+A.edgeCount());
        System.out.println();

        System.out.println("Adjacency matrix of Graph A");
        System.out.println("1 at A[i, j] indicates v(i) has an outgoing edge connecting to v(j)");
        System.out.println();
        A.printAsAdjacencyMatrix();

        System.out.println("\nCycles in A");
        for(GraphNode g1:A.getNodes()) {
            List<GraphNode> cycle = A.findCycle(g1);
            System.out.print("Cycle from ");
            System.out.print(String.format("%2d%s", g1.getIndex(),": "));
            if(!cycle.isEmpty()) {
                for (GraphNode step : A.findCycle(g1)) {
                    System.out.print(step.getIndex() + " -> ");
                }
                System.out.println();
            }else{
                System.out.println("no cycle");
            }
        }
        System.out.println();

        System.out.println("---------------------------------------------------------");
        System.out.println("Graph B");
        System.out.println("---------------------------------------------------------");
        String graphB = "[v1, v4][v2, v5][v3, v6][v4, v8][v4, v9][v5, v1][v6, v2][v7, v3][v8, v9][v9, v5][v9, v10]" +
                "[v9, v11][v10, v5][v10, v6][v10, v7][v10, v11][v11, v7]";
        DirectedGraph B = new DirectedGraph(graphB);

        System.out.println("\nGraph B "+(B.isDAG() ? "is ":"is not ")+"a directed graph.");

        System.out.println("\nRoots in B:");
        if(B.getRoots().size()>0) {
            for (GraphNode rootB : B.getRoots()) {
                System.out.println(rootB);
            }
        }else{
            System.out.println("-none-");
        }

        System.out.println("\nNumber of edges in B = "+B.edgeCount());
        System.out.println();

        System.out.println("Adjacency matrix of Graph B");
        System.out.println("1 at A[i, j] indicates v(i) has an outgoing edge connecting to v(j)");
        System.out.println();
        B.printAsAdjacencyMatrix();

        System.out.println("\nCycles in B starting at all nodes");
        for(GraphNode g1:B.getNodes()) {
            List<GraphNode> cycle = B.findCycle(g1);
            System.out.print("Cycle from ");
            System.out.print(String.format("%2d%s", g1.getIndex(),": "));
            if(!cycle.isEmpty()) {
                for (int i=0; i<cycle.size()-1; i++) {
                    System.out.print(cycle.get(i).getIndex() + " -> ");
                }
                System.out.print(cycle.get(cycle.size()-1).getIndex());
                if(!cycle.get(cycle.size()-1).equals(cycle.get(0)))
                    System.out.print(" -> "+cycle.get(0).getIndex());
                System.out.println();
            }else{
                System.out.println("no cycle");
            }
        }
    }
}
