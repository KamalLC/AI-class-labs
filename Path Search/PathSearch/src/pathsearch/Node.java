/*
 * Name: Kamal Lamichhane
 * Roll No.: 17
 * Practical No. 02
 */
package pathsearch;

/**
 *
 * @author klamichhane
 */
public class Node implements Comparable<Node> {
    int nodeID;
    String name;
    boolean visited;
    int hscore;
    Node parent;
    
    public Node(int id, String city, int h) {
        nodeID = id;
        name = city;
        
        visited = false;
        parent = null;
        
        hscore = h;
    }

    @Override
    public int compareTo(Node o) {
        if(hscore == o.hscore){
            return 0;
        } else if(hscore < o.hscore) {
            return -1;
        } else {
            return 1;
        }
    }
    
    
}
