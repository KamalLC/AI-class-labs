//KmeansClustering.java 
 
/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
package kmeansclustering; 
 
import java.util.Scanner; 
 
/** 
 * 
 * @author Kamal_2 
 */ 
public class KmeansClustering { 
 
    /** 
     * @param args the command line arguments 
     */ 
    public static void main(String[] args) { 
        // TODO code application logic here 
        int k,num; 
        Scanner scan = new Scanner(System.in); 
        System.out.println("Enter no. of cluster: "); 
        k = scan.nextInt(); 
        System.out.println("Data Range: "); 
        num = scan.nextInt(); 
        Kmeans kMeans = new Kmeans(k,num);//Initialization of data and centroid 
        kMeans.doClustering(); 
    } 
     
} 
