package DirectedGraphPack;
/*
  Madhumita Ghude
  Course: CS 610852
  Programming Assignment # 3
  No special instructions or problems
  Test case input(s) in TestDirectedGraph.java

  FindCycleDFS class extends DFS class which does main depth-first search
  method terminates when a visited graphnode (indicating a cycle) is found
  Algorithm from p. 334 in Algorithm Design by Goodrich & Tamassia
 */

import java.util.*;

public class FindCycleDFS extends DFS {
    private NodeSequence cycle;
    private boolean done;
    private GraphNode cycleStart;

    public List<GraphNode> execute(DirectedGraph G, GraphNode start) {
        super.execute(G, start);
        cycle = new NodeSequence();

        done = false;
        dfsTraversal(start, start);
        ArrayList<GraphNode> arr = new ArrayList<>();
        if (!cycle.isEmpty() && !start.getOutgoingEdges().contains(cycleStart)) {
            for (GraphNode k : cycle) {
                arr.add(k);
            }
            Iterator<GraphNode> pos = arr.listIterator();
            while (pos.hasNext()) {   //remove edges from start to cyclestart
                GraphNode p = pos.next();
                pos.remove();
                if (G.getIncomingEdges(cycleStart).contains(p)) {
                    break;
                }
            }
        } else {
            for (GraphNode k : cycle) {
                arr.add(k);
            }
        }
        Collections.reverse(arr);
        return arr;
    }

    protected void finishVisit(GraphNode v) {
        if ((!cycle.isEmpty()) && (!done)) {
            cycle.remove(cycle.lastElement());
        }
    }

    protected void traverseDiscovery(GraphNode e, GraphNode from) {
        if (!done) {
            cycle.insertLast(e);
        }
    }

    protected void traverseBack(GraphNode e, GraphNode from) {
        if (!done) {
            cycle.insertLast(e);
            cycleStart = G.opposite(from, e);
            done = true;
        }
    }
    protected boolean isDone(){
        return done;
    }
}


class NodeSequence extends Vector<GraphNode> {
    @Override
    public Enumeration<GraphNode> elements() {
        return super.elements();
    }

    void insertLast(GraphNode e) {
        insertElementAt(e, size());
    }

}