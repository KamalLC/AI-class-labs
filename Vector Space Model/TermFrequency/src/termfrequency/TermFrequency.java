/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termfrequency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kamal_2
 */
public class TermFrequency {

    /**
     * @param args the command line arguments
     */
    
    public void printList(List<String> doc) {
        for(String word: doc) {
            System.out.print(word + " ");
        }
        
        System.out.println("\n");
    }
    
    public void printListOfLists(List<List<String>> docs) {
        for(List<String> doc : docs){
            for(String word : doc) {
                System.out.print(word + " ");
            }
            System.out.println("\n");
        }
    }
    
    public void printListOfArrayLists(ArrayList<ArrayList<String>> docs) {
        for(ArrayList<String> doc : docs){
            for(String word : doc) {
                System.out.print(word + " ");
            }
            System.out.println("\n");
        }
    }
    
    public double idf(List<List<String>> docs, String term){
        int count = 0;
        for(List<String> doc : docs){
            if(doc.contains(term)) {
                count++;
            }
        }
        if(count == 0){
            return 0;
        }
//        System.out.println(term + " count docs = " + count);
        double result = Math.log(docs.size() * 1.0 / count);
//        System.out.println(term + " idf = " + result);
        return result;
//        Math.l
    }
    
    public double tf(List<String> doc, String term) {
        int count = 0; 
        for(String str: doc){
            if(str == term){
                count++;
            }
        }
        
        double result = (count * 1.0 / doc.size());
        
        return result;
    }
    
    public double tf_idf(double tf, double idf) {
        return tf * idf;
    }
    
    public double cosineSimilarity(ArrayList<Double> a, ArrayList<Double> b) {
       double nume = 0, suma = 0, sumb = 0, result;
       for(int i = 0; i < a.size(); i++) {
           nume += (a.get(i) * b.get(i));
           suma += a.get(i);
           sumb += b.get(i);
       }
       
       result = nume / (Math.sqrt(suma) + Math.sqrt(sumb));
       
       return result;
    }
    
    
    public double score(List<String> q, List<String> doc, List<List<String>> docs){
        double result = 0;
        double temp;
        for(String term : q){
//            System.out.println("tf = " + tf(doc, term) + "idf = " + idf(docs, term));
            temp = tf_idf(tf(doc, term), idf(docs, term));
//            if(temp != NaN){
                result += temp;
//            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        TermFrequency freq = new TermFrequency();
        List<String> doc1 = Arrays.asList("the", "sky", "is", "blue");
        List<String> doc2 = Arrays.asList("the", "sun", "is", "bright");
        List<String> doc3 = Arrays.asList("the", "sun", "in", "the", "sky", "is", "bright");
        List<String> search = Arrays.asList("which",  "star", "in", "sky", "is", "bright", "blue");
        
        List<List<String>> corpus = Arrays.asList(doc1, doc2, doc3, search);
        freq.printList(doc1);
        freq.printList(doc2);
        System.out.println("\nDocument Collection:");
        freq.printListOfLists(corpus);
        
        
//        documentVector.add(doc3);
//        doc1.add("hell0");
        ArrayList<String> uniqueWords = new ArrayList<>();
        List<String> zeros = new ArrayList<>();
        for(List<String> doc : corpus){
            for(String word : doc) {
                if(!uniqueWords.contains(word)) {
                    uniqueWords.add(word);
                    zeros.add("0");
                }
            }
        }
        System.out.println("\n\n" + uniqueWords);
        
//        List<List<String>> documentVector = Arrays.asList(uniqueWords, zeros, zeros, zeros);
        ArrayList<ArrayList<Double>> documentVector = new ArrayList<ArrayList<Double>>();
        

        for(List<String> doc : corpus){
            ArrayList<Double> temp = new ArrayList<Double>();
            
//            System.out.println("a");
            for(String word : uniqueWords) {
                temp.add(freq.tf_idf(freq.tf(doc, word), freq.idf(corpus, word)));
            }
            documentVector.add(temp);
            System.out.println(temp);
        }

        
        int recommended = 0;
        double high = -999999;
        double scr;
        
        for(int i = 0; i < corpus.size() - 1; i++){
            scr = freq.cosineSimilarity(documentVector.get(i), documentVector.get(corpus.size() - 1));
            System.out.println(scr);
            if(scr > high){
                high = scr;
                recommended = i;
            }
        }
        
        System.out.println("recommended file is: " + corpus.get(recommended));
    }
    
}