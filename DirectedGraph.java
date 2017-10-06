package DirectedGraphPack;
/*
  Madhumita Ghude
  Course: CS 610852
  Programming Assignment # 3
  No special instructions or problems
  Test case input(s) in TestDirectedGraph.java

  Main DirectedGraph class contains assignment methods:
     constructor
     getIncomingEdges
     isDAG
     getRoots
     edgeCount
     printAsAdjacencyMatrix
     findCycle
 */

import java.util.*;

class DirectedGraph {
    private Collection<GraphNode> nodes = new ArrayList<GraphNode>(){
        @Override
        public boolean contains(Object o){
            for(GraphNode g:nodes){
                if(g.equals((GraphNode) o)){
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean add(GraphNode graphNode) {
            int i=0;
            //add to empty list
            if(nodes.size()==0){
                add(0, graphNode);
                return true;
            }
            //add to not-empty list, checks if nodes already contains in constructor
            for(GraphNode g: nodes){
                if(g.getIndex()<graphNode.getIndex()){
                    i++;
                }else{
                    add(i, graphNode);
                    return true;
                }
            }
            //adds to end of nodes if largest, or if graphnode already contains it returns false
            if(!contains(graphNode)) {
                add(nodes.size(), graphNode);
                return true;
            }else {
                return false;
            }
        }
    };

    /**
     * Initializes a directed graph from a string.
     * The string representing the graph is in the following format: “[v1, v2][v3, v4][v2, v3]…” where each “[vi, vj]”
     * pair represents an edge between two vertices vi and vj.
     * The constructor will parse the string and initialize a set of Graph Node objects.
     */
    public DirectedGraph(String str) {
        while(str.length()>0){
            int start = new Integer(str.substring(2, str.indexOf(',')));
            GraphNode s = new GraphNode(start);
            int end = new Integer(str.substring(str.indexOf(',') + 3, str.indexOf(']')));
            GraphNode e = new GraphNode(end);
            //if nodes contains s or g, replace variables with pointers to that node for modification
            if(!nodes.contains(s)){
                nodes.add(s);
            }else{
                for(GraphNode g: nodes){
                    if(g.equals(s)){
                        s=g;
                        break;
                    }
                }
            }
            if(!nodes.contains(e)){
                nodes.add(e);
            }else{
                for(GraphNode g: nodes){
                    if(g.equals(e)){
                        e=g;
                        break;
                    }
                }
            }
            s.addOutgoingEdge(e);
            try {
                str = str.substring(str.indexOf('[', 1));
            }catch(Exception x){
                break;
            }
        }
    }

    /**
     * @return collection of nodes that have an edge directed to the given node v.
     */
    public Collection<GraphNode> getIncomingEdges(GraphNode v) {
        Collection<GraphNode> inEdges = new ArrayList<>();
        for(GraphNode g: nodes){
            for(GraphNode h: g.getOutgoingEdges()){
                if(h.equals(v)){
                    inEdges.add(g);
                }
            }
        }
        return inEdges;
    }

    /**
     * @return true if the graph is a directed acyclic graph.
     * Topological sort algorithm from p. 326 in Algorithm Design by Goodrich & Tamassia
     */
    public boolean isDAG() {
        //digraph has a topological ordering if and only if it is acyclic.
        //So if a topological ordering exists, the graph must be acyclic.
        Stack<GraphNode> S = new Stack<>();
        for(GraphNode g: nodes){
            if(getIncomingEdges(g).size()==0){
                S.push(g);
            }
        }
        if(S.empty()){
            return false;
        }
        while(!S.empty()){
            GraphNode u = S.pop();
            for(GraphNode e: u.getOutgoingEdges()){
                if(getIncomingEdges(e).size()==1){
                    S.push(e);
                }
            }
        }
        return true;
    }

    /**
     * @return roots of the graph (i.e., nodes with no incoming edges).
     */
    public Collection<GraphNode> getRoots() {
        Collection<GraphNode> roots = new ArrayList<>();
        for(GraphNode g: nodes){
            if(getIncomingEdges(g).size()==0){
                roots.add(g);
            }
        }
        return roots;
    }

    /**
     * @return the total number of edges in the graph.
     */
    public int edgeCount() {
        int numEdges = 0;
        for(GraphNode g: nodes){
            numEdges += g.getOutgoingEdges().size();
        }
        return numEdges;
    }

    /**
     * Prints the graph as an adjacency matrix.
     */
    public void printAsAdjacencyMatrix() {
        System.out.print("   ");
        for(GraphNode k: nodes){
            System.out.print(String.format("%3d", k.getIndex()));
        }
        System.out.print("\n   ");
        for(int i=0; i<nodes.size(); i++){
            System.out.print("---");
        }
        System.out.println();
        for(GraphNode i: nodes){
            System.out.print(String.format("%2d%s", i.getIndex(), "|"));
            for(GraphNode j: nodes){
                if(i.getOutgoingEdges().contains(j)){
                    System.out.print(String.format("%3d", 1));
                }else{
                    System.out.print(String.format("%3d", 0));
                }
            }
            System.out.println();
        }
    }

    /**
     * @return a cycle in connected component of v, if one exists.
     * Note that multiple cycles may exist, but this method will only return one, represented as a list of nodes.
     */
    public List<GraphNode> findCycle(GraphNode v) {
        FindCycleDFS cycle = new FindCycleDFS();
        return cycle.execute(this, v);
    }

    public Collection<GraphNode> getNodes(){
        return nodes;
    }


    public GraphNode opposite(GraphNode v, GraphNode e){
        if(v.getOutgoingEdges().contains(e)){
            return e;
        }
        return v;
    }
}

class GraphNode {
    private final int index;
    private final Collection<GraphNode> outgoingEdges = new ArrayList<GraphNode>() {
        @Override
        public boolean contains(Object o){
            for(GraphNode g:outgoingEdges){
                if(g.index==((GraphNode) o).index){
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean remove(Object o) {
            for(GraphNode g: outgoingEdges){
                if(g.equals(o)){
                    return super.remove(o);
                }
            }
            return false;
        }
    };

    public GraphNode(int index) {
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public void addOutgoingEdge(GraphNode edge){
        outgoingEdges.add(edge);
    }

    public boolean equals(GraphNode g){
        return g.index==index;
    }

    public Collection<GraphNode> getOutgoingEdges(){
        return outgoingEdges;
    }

    public String toString(){
        String out;
        out = "v"+index+" -> [ ";
        for(GraphNode g: outgoingEdges){
            out+=g.index+" ";
        }
        out += "]";
        return out;
    }
}
