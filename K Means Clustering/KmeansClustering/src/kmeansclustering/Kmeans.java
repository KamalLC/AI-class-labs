//Kmeans.java 
 
 
package kmeansclustering; 
 
import java.util.Arrays; 
import java.util.Random; 
 
public class Kmeans { 
    int num;//number of data points 
    int k;//number of clusters 
    int x[], y[];//data points 
    double meanX[], meanY[];//current cluster centers 
 
    private final int MAXITER = 20; 
    private int iteration = 0; 
    int cAssign[]; 
    int prevCAssign[]; 
    double distance[] ; 
 
    Random random; 
 
    public Kmeans(int k, int num) 
    { 
        this.k = k; 
        this.num = num; 
        random = new Random(); 
        x = new int[num]; 
        y = new int [num]; 
        meanX = new double[k]; 
        meanY = new double[k]; 
        distance = new double[k]; 
        cAssign = new int[num]; 
        prevCAssign = new int[num]; 
 
        randomData(); 
         
        randomMean(); 
 
 
    } 
     
    public void randomData() 
    { 
        for (int i = 0;i<num;i++) 
         { 
 
             x[i]= random.nextInt(num); 
             y[i] = random.nextInt(num); 
          } 
    } 
    public void randomMean() 
    { 
        for (int i = 0;i<k;i++) 
        { 
           int data = random.nextInt(num); 
           meanX[i] = x[data]; 
           meanY[i] = y[data]; 
 
        } 
    } 
 
    public void assignCluster() 
    { 
        //COPYING PREVIOUS CASSIGN VALUE 
        prevCAssign = cAssign.clone(); 
 
        //CALCULATING DISTANCE BETWEEN CENTROID AND DATA AND STORING AS ARRAY 
        for (int i = 0;i<num;i++) 
        { 
            for (int j = 0;j<k;j++) 
            { 
                    distance[j] = Math.sqrt(Math.pow(meanX[j] - x[i],2) + Math.pow(meanY[j] - y[i],2)); 
            } 
            //ASSIGNING MINIMUM DISTANCE INDEX TO CASSIGN 
           double min = distance[0]; 
            for(int k = 0;k<distance.length;k++) 
            { 
               if(min>=distance[k]) 
               { 
                   min = distance[k]; 
                   cAssign[i] = k; 
               } 
            } 
 
        } 
    } 
     
    public void doClustering() 
    { 
 
        do { 
            assignCluster(); 
            updateMeans(); 
            iteration++; 
        }while(isDifferent() || iteration > MAXITER); 
         
        System.out.println(); 
        System.out.print("Data: "); 
        printData(); 
         
         
        System.out.println("\n"); 
        printCentroid(); 
         
        printCluster(); 
         
        System.out.println(); 
        System.out.println("\nNo of iteration = "+iteration); 
    } 
 
 
    public void updateMeans() 
    { 
        int[] count= new int[k];//stores no. of data that are in same cluster (cAssign) 
 
        // ASSIGNING ZERO TO CENTROID TO CALCULATE SUM 
        for (int i = 0; i<k;i++) 
        { 
            meanX[i] = 0; 
            meanY[i] = 0; 
        } 
        //ADDING DATA TO CENTROID THAT HAS SAME CASSIGN VALUE 
        for (int i = 0;i<num;i++) 
        { 
            for (int j = 0;j<k;j++) { 
 
 
                if (cAssign[i] == j) { 
                    meanX[j] += x[i]; 
                    meanY[j] += y[i]; 
                    count[j]++; 
                } 
            } 
        } 
        //DIVIDING CENTROID BY COUNT 
        for (int i = 0; i<k;i++) 
        { 
            meanX[i]  = meanX[i]/count[i]; 
            meanY[i]  = meanY[i]/count[i]; 
        } 
    } 
 
 
    boolean isDifferent() 
    { 
        if(Arrays.equals(cAssign,prevCAssign)) 
        { 
            return false; 
        } 
        return true; 
    } 
    public void printCentroid() 
    { 
        for (int i = 0;i<k;i++) 
        { 
            System.out.println("Centroid of cluster " + i + ": "+"(" + meanX[i] + ", " + meanY[i] + ")"); 
        } 
    } 
    public void printData() 
    { 
        for (int i = 0;i<num;i++) 
        { 
            System.out.print("(" + x[i] + ", " + y[i] + "), "); 
        } 
    } 
    public void printCluster() 
    { 
        for(int i = 0; i < k; i++) { 
            System.out.println(""); 
            System.out.print("Points lying in cluster " + i + " = "); 
            for(int j = 0; j < num; j++) { 
                if(cAssign[j] == i){ 
                    System.out.print("(" + x[j] + ", " + y[j] + "), "); 
                } 
            } 
        } 
    } 
 
}