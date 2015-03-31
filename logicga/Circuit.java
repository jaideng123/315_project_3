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
 // need Comparable for natural ordering for a Priority Queue
public class Circuit implements Comparable<Circuit> {
    Vector<Gene> genes;
    String aFile;
    int numNots = 0;
    boolean simulated = false;
    int numGoalsReached = 0;
    int fitness;

    
    public Circuit(){
        genes = new Vector<Gene>();
    }
     //Mutates circuit by either adding a gate or mutating a gene
    public void mutate(){
    	Random r1 = new Random();
    	Random r2 = new Random();
    	int mutation = r1.nextInt(4)+1;
    	int output;
    	Vector<Integer> newInputs = new Vector<Integer>();
    	switch (mutation){
    	case 1:{
    		 output = r1.nextInt(genes.size())+1;
    		 
//    		 genes.add(new Gene(output, "AND", ))
//    	}
//    	case 2:{
//    		addGate(output, "OR", );
    	}
    	case 3:{
    		output = r1.nextInt(genes.size())+1;
    		newInputs.addElement(r2.nextInt(genes.size())+1);
    		addGate(output, "NOT", newInputs );
    		
    	}
    	//change a gene
    	case 4:{
    		int index = r1.nextInt(genes.size()) +1;
    		genes.get(index).mutate();
    	}
    	    		
    	
    	default:
    		System.out.println("Could not mutate...");
    	}
        simulated = false;
    }
    public int calculateFitness(Simulator s){
        //cache result if recalculation isnt needed
        if(!simulated) {
            testCircuit(s);
            fitness = 1000000 * (numGoalsReached) + 10000 * (numNots) + 10 * (genes.size() - numNots);
        }
        return fitness;
    }
    public void getFromFile(int populationIndex)throws IOException{
        //Fix for generic case
        // path should be change.
        String path = "./testcircuit/Circuit0.txt";
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        //Vector<Integer> inputGenes = new Vector<Integer>();
        String line;
        while( (line = textReader.readLine()) != null){
        	
//            inputGenes.add(Integer.parseInt(line));
            Scanner s = new Scanner(line);
            //inputGenes.add(s.nextInt());
            int outputNum = 0 ;
            String type = null;
            Vector<Integer> inputs = new Vector<Integer>();
            
            //Parsing each line for INT STRING [INT(S)]
            if(s.hasNextInt()){
                outputNum = s.nextInt();
                if(s.hasNext()){
                    type = s.next();
                    while(s.hasNextInt()){
                        inputs.add(Integer.parseInt(s.next()));
                   }
               }
           }
           
           //The genes will be stored in the order they appear in text
           addGate(outputNum,type,inputs);
//           genes.add(new Gene(outputNum,type,inputs));
//           if (type.equals("Not")) {
//        	   n_not ++;
//           }
       }
        textReader.close();
       
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
    public boolean addGate(int output, String gateType, Vector<Integer> input){
    	if (gateType.equals("Not") && numNots == 2) {
    		return false;
    	} else {
    		genes.add(new Gene(output, gateType, input));
        	if (gateType.equals("Not")) {
         	   numNots ++;
            }
        	return true;
    	}
    }
    
    public void removeLastGate(){
    	genes.removeElementAt(genes.size()-1);
    }
    
    public Vector<Integer> getInputLines(){
    	// I assumed input from first NONE lines are declared as inputs.
    	// Also, logically, it should be only one input for NONE gate.
    	// And one input and one output should be same number.
    	Vector<Integer> result = new Vector<Integer>();
    	if (genes.size() > 0) {
    		for (int i = 0; i < genes.size(); i++) {
    			if (genes.get(i).type.equals("None")) {
    				if (genes.get(i).outputNum == genes.get(i).inputs.firstElement()) {
    					result.add(genes.get(i).outputNum);
    				}
    			}
    		}
    	}
    	return result;
    }
    
    public Vector<Integer> getOutputLines() {
    	Vector<Integer> inputVec = new Vector<Integer>();
        inputVec = getInputLines();
        Vector<Integer> result = new Vector<Integer>();
        
        for (int i=0; i < inputVec.size(); i++) {
        	int cnt = 0;
        	for(int j = 0; j < genes.size(); j++){
        		for (int k = 0; k < genes.get(j).inputs.size(); k++) {
        			if (inputVec.get(i) == genes.get(j).inputs.get(k)) {
        				cnt ++;
        			}
        		}
        	}
        	if (cnt == 1) {
        		result.add(inputVec.get(i));
        	}
        }

        for (int i = 0; i < genes.size(); i ++) {
        	int cnt = 0;
        	for (int j = 0; j < genes.size() && cnt == 0; j ++) {
        		for (int k = 0; k < genes.get(j).inputs.size() && cnt == 0; k ++) {
        			if (genes.get(j).inputs.get(k) == genes.get(i).outputNum) {
        				cnt ++;
        			}
        		}
        	}
        	if (cnt == 0) {
        		result.add(genes.get(i).outputNum);
        	}
        }

    	return result;
    }
    //Establishes natural order
    public int compareTo(Circuit a) {
		int LESS = -1;
		int EQUAL = 0;
		int GREATER = 1;
		
		if(fitness < a.fitness)
			return LESS;
		if(fitness == a.fitness)
			return EQUAL;
		if(fitness > a.fitness)
			return GREATER;
		return 0;
	}

   
    
    public void testCircuit(Simulator s){
        numGoalsReached = s.simulate(this);
        simulated = true;
    }

}
