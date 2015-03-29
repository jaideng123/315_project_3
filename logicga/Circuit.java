/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicga;

import java.util.*;
import java.io.*;
import java.util.Vector;
import java.util.Scanner;
/**
 *
 * @author Eric C C
 * 
 */
public class Circuit {
    Vector<Gene> genes = new Vector<Gene>();
    String aFile;
    int n_not = 0;
    
    public Circuit(){
        genes = new Vector();
    }
    
    public void getFromFile(int populationIndex)throws IOException{
        //Fix for generic case
        // path should be change.
        String path = "./testcircuit/Circuit0.txt";
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        Vector inputGenes = new Vector();
        String line;
        while( (line = textReader.readLine()) != null){
            inputGenes.add(line);
            Scanner s = new Scanner(line);
            int outputNum = 0 ;
            String type = null;
            Vector inputs = new Vector();
            
            //Parsing each line for INT STRING [INT(S)]
            if(s.hasNextInt()){
                outputNum = s.nextInt();
                if(s.hasNext()){
                    type = s.next();
                    while(s.hasNextInt()){
                        inputs.add(s.next());
                   }
               }
           }
           //The genes will be stored in the order they appear in text
           genes.add(new Gene(outputNum,type,inputs));
           if (type.equals("Not")) {
        	   n_not ++;
           }
       }  
    }
    
    public void Print(){
        for(int i =0 ; i < genes.size() ;i++ ){
            genes.elementAt(i).Print();
        }
    }

    /**
     * I added functions below. Please revise them.
     * Sijine 
     */
    public void addGate(int output, String gateType, Vector input){
    	genes.add(new Gene(output, gateType, input));
    	if (gateType.equals("Not")) {
     	   n_not ++;
        }
    }
    
    public void removeLastGate(){
    	genes.removeElementAt(genes.size()-1);
    }
    
    public Vector<Integer> getInputLines(){
    	// I assumed input from first NONE lines are declared as inputs.
    	// Also, logically, it should be only one input for NONE gate.
    	// And one input and one output should be same number.
    	Vector<Integer> result = new Vector();
//    	int i = 0;
    	System.out.println("genes: " + genes.size());
    	
    	// HERE!!
    	
    	if (genes.size() > 0) {
    		for (int i = 0; i < genes.size(); i++) {
    			if (genes.get(i).type.equals("None")) {
//    				if (genes.get(i).outputNum == genes.get(i).inputs.firstElement()) {
    					System.out.println("outputNum: " + genes.get(i).outputNum);
    					System.out.println(" inputNum: " + genes.get(i).inputs.firstElement());
    					result.add(genes.get(i).outputNum);
//    				}
    			}
    		}
//	    	while (genes.size() > 0 && i < genes.size() &&
//	    			genes.get(i).type.equals("None")//&&
////	    			genes.get(i).inputs.get(0).compareTo(genes.get(i).outputNum) == 0
//	    			){
//	    		System.out.println("outputNum: " + genes.get(i).outputNum);
//	    		System.out.println(" inputNum: " + genes.get(i).inputs.get(0));
////	    		genes.elementAt(i).inputs.get(0);
////	    		result.add(genes.elementAt(i).inputs.get(0));
////	    		result.add((java.lang.Integer) genes.elementAt(i).outputNum);
//	    		i ++;
//	    	}
    	}
    	return result;
    }
    
    public Vector<Integer> getOutputLines() {
        Set<Integer> inputSet = new HashSet<Integer>();
        Set<Integer> outputSet = new HashSet<Integer>();
    	Vector<Integer> inputVec = new Vector<Integer>();
        inputVec = getInputLines();
        Vector<Integer> result = new Vector<Integer>();
        
        System.out.println("getOutputLines " + inputVec.size());
        
        for (int i=0; i<inputVec.size(); i++) {
        	int cnt = 0;
        	for(int j = 0; j < genes.size(); j++){
        		System.out.print("inputs : ");
        		for (int k = 0; k < genes.get(j).inputs.size(); k++) {
        			System.out.print(genes.get(j).inputs.get(k) + " ");
        			if (inputVec.get(i) == genes.get(j).inputs.get(k)) {
        				cnt ++;
        			}
        		}
        		System.out.println();
        	}
        	if (cnt == 1) {
        		result.add(inputVec.get(i));
        	}
        	System.out.println("cnt == " + cnt);
        }
        
//        for(Integer temp : inputVec){
//            inputVec.add(temp);
//        }
        for(Gene gene : genes){
            //My new function
            if(gene.type != "None"){
                outputSet.add(gene.outputNum);
            }
        }
        for(Integer inputNum : inputSet){
            outputSet.remove(inputNum);
        }
        for(Integer finalOut : outputSet){
            result.add(finalOut);
        }


    	return result;
    }

}
