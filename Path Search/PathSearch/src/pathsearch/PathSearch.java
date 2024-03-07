package pathsearch;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author klami
 */
public class PathSearch {

    /**
     * @param args the command line arguments
     */
    
    public void dfs(Graph g, Node nd){
        nd.visited = true;
        System.out.print(nd.name + " -> ");
        
        for(Node n : g.adjacencyMap.get(nd)){
            if(n.visited == false){
                dfs(g, n);
            }
        }
    }
    
    public void bfs(Graph g, Node nd){
        Queue<Node> q = new LinkedList<>();
        q.offer(nd);
        
        System.out.println("");
        while(q.size() != 0){
            Node curNode = q.poll();
            if(!curNode.visited){
                curNode.visited = true;
                System.out.print(curNode.name + " -> ");
            }            
            for(Node n : g.adjacencyMap.get(curNode)){
                if(!n.visited && !q.contains(n)){
                    q.offer(n);
                }
            }
        }
    }
    
    public void bestFirstSearch(Graph g, Node start, Node end){
        Queue<Node> closeList = new LinkedList<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();        
        openList.offer(start);
        
        while(openList.size() != 0){
            Node n = openList.poll();
            
            if(n == end){
                return;
            }            
            for(Node no : g.adjacencyMap.get(n)){
                if(!openList.contains(no) && !closeList.contains(no)){
                    no.parent = n;
                    openList.offer(no);
                }
            }            
            openList.remove(n);
            closeList.offer(n);
            System.out.print(n.name + ", ");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Node n1 = new Node(1, "Arad", 366);
        Node n2 = new Node(2, "Zerind", 374);
        Node n3 = new Node(3, "Oradea", 380);
        Node n4 = new Node(4, "Timisoara", 329);
        Node n5 = new Node(5, "Lugoj", 244);
        Node n6 = new Node(6, "Mehadia", 241);
        Node n7 = new Node(7, "Dobreta", 242);
        Node n8 = new Node(8, "Sibiu", 253);
        Node n9 = new Node(9, "Rimnicu Vilcea", 193);
        Node n10 = new Node(10, "Craiova", 160);
        Node n11 = new Node(11, "Fagaras", 178);
        Node n12 = new Node(12, "Pitesti", 98);
        Node n13 = new Node(13, "Bucharest", 0);
        Node n14 = new Node(14, "Giurgiu", 77);
        Node n15 = new Node(15, "Urziceni", 80);
        Node n16 = new Node(16, "Vasiui", 199);
        Node n17 = new Node(17, "Lasi", 226);
        Node n18 = new Node(18, "Neamt", 234);
        Node n19 = new Node(19, "Hirsova", 151);
        Node n20 = new Node(20, "Eforie", 161);
        
        Graph grp = new Graph(false);
        grp.insertEdge(n1, n2);
        grp.insertEdge(n1, n4);
        grp.insertEdge(n1, n8);
        grp.insertEdge(n2, n3);
        grp.insertEdge(n3, n8);
        grp.insertEdge(n4, n5);
        grp.insertEdge(n5, n6);
        grp.insertEdge(n6, n7);
        grp.insertEdge(n7, n10);
        grp.insertEdge(n8, n9);
        grp.insertEdge(n9, n10);
        grp.insertEdge(n8, n11);
        grp.insertEdge(n9, n12);
        grp.insertEdge(n10, n12);
        grp.insertEdge(n11, n13);
        grp.insertEdge(n12, n13);
                
        grp.insertEdge(n13, n14);
        grp.insertEdge(n13, n15);
        grp.insertEdge(n15, n16);
        grp.insertEdge(n15, n19);
        grp.insertEdge(n16, n17);
        grp.insertEdge(n17, n18);
        grp.insertEdge(n19, n20);
                
        grp.printEdges(n1);
        PathSearch ps = new PathSearch();
        
//        System.out.println("");
//        ps.dfs(grp, n1);
//        ps.bfs(grp, n1);
        ps.bestFirstSearch(grp, n1, n13);
        grp.printPath(n13);        
    }
}
