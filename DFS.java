package DirectedGraphPack;
/*
  Madhumita Ghude
  Course: CS 610852
  Programming Assignment # 3
  No special instructions or problems
  Test case input(s) in TestDirectedGraph.java

  DFS class executes depth-first search of a graph to find cycle
  Algorithm from p. 330 in Algorithm Design by Goodrich & Tamassia
 */

import java.util.ArrayList;
import java.util.Collection;

public abstract class DFS {
    DirectedGraph G;

    private ArrayList<GraphNode> visited = new ArrayList<GraphNode>(){
        public boolean contains(Object o){
            for(GraphNode x:visited){
                if(x.equals((GraphNode) o)){
                    return true;
                }
            }
            return false;
        }
    };
    public Object execute(DirectedGraph G, GraphNode start){
        this.G=G;
        for(GraphNode g: G.getNodes()){
            visited.remove(g);
        }
        return null;
    }

    Object dfsTraversal(GraphNode v, GraphNode target){
        if(!isVisited(v))
            visit(v);
        GraphNode lastVisitedNode = v;
        Collection<GraphNode> allEdges = G.getIncomingEdges(v);
        for(GraphNode toNode:allEdges){
            if(!isVisited(toNode)){
                visit(toNode);
                traverseDiscovery(toNode, v);
                if(!isDone()){
                    lastVisitedNode = (GraphNode)dfsTraversal(toNode, target);
                }
            }
            else{   //w is explored, this is back node
                if(!toNode.equals(target)){
                    //proceed to next in for loop
                    continue;
                }
                traverseBack(toNode, v);
            }
        }

        finishVisit(v);

        return result(lastVisitedNode);
    }

    private Object result(GraphNode lastNode){
        return new GraphNode(lastNode.getIndex());
    }
    protected abstract void finishVisit(GraphNode v);

    protected abstract void traverseBack(GraphNode nextEdge, GraphNode v);

    protected abstract boolean isDone();

    protected abstract void traverseDiscovery(GraphNode nextEdge, GraphNode v);

    private void visit(GraphNode v) {
        visited.add(v);
    }

    private boolean isVisited(GraphNode v){
        return visited.contains(v);
    }
}
