/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathsearch;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author klami
 */
public class Graph {
    HashMap<Node, LinkedList<Node>> adjacencyMap;
    boolean directed;
    
    public Graph(boolean dir){
        adjacencyMap = new HashMap<Node, LinkedList<Node>>();
        directed = dir;
    }
    
    public void insertEdge(Node source, Node target){
        if(!adjacencyMap.keySet().contains(source)){
            LinkedList<Node> temp = new LinkedList<Node>();
            temp.add(target);
            adjacencyMap.put((source), temp);
        }else{
            adjacencyMap.get(source).add(target);
        }
        
        if(!adjacencyMap.keySet().contains(target)){
            LinkedList<Node> temp = new LinkedList<Node>();
            temp.add(source);
            adjacencyMap.put((target), temp);
        }else{
            adjacencyMap.get(target).add(source);
        }
    }
    
    public void printEdges(Node city){
        if(adjacencyMap.keySet().contains(city)){
            System.out.println("");
            System.out.print(city.name + " : ");
            for(Node cit : adjacencyMap.get(city)){
                System.out.print(cit.name + ", ");
            }
            System.out.println("");
        }
    }
    
    public void printPath(Node cit){
        Node city = cit;
        while(city != null){
            System.out.print(city.name + " <- ");
            city = city.parent;
        }
    }
}
