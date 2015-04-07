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
    int numNots;
    boolean simulated = false;
    int numGoalsReached=0;
    int fitness;

    
    public Circuit(){
        genes = new Vector<Gene>();
    }
     //Mutates circuit by either adding a gate or mutating a gene
     public void mutate() {
    	 
         int mutation = randInt(1, 5);
         int output;
         Vector<Integer> newInputs = new Vector<Integer>();
         switch (mutation) {
             //add AND gate
             case 1: {
                 output = genes.size() + 1;
                 newInputs.addElement(randInt(0, genes.size() - 1));
                 newInputs.addElement(randInt(0, genes.size() - 1));
                 genes.add(new Gene(output, "And", newInputs));
                 break;
             }
             //add OR gate
             case 2: {
                 output = genes.size() + 1;
                 newInputs.addElement(randInt(0, genes.size() - 1));
                 newInputs.addElement(randInt(0, genes.size() - 1));
                 genes.add(new Gene(output, "Or", newInputs));
                 break;
             }
             //add NOT gate
             case 3: {
                 output = genes.size() + 1;
                 newInputs.addElement(randInt(0, genes.size() - 1));
                 genes.add(new Gene(output, "Not", newInputs));
                 numNots++;
                 break;

             }
             //change a gene
             case 4: {
            	 calculateNots();
                 int index = randInt(2, genes.size() - 1);
                 if (genes.elementAt(index).type == "Not")
                     numNots--;
                 if (genes.elementAt(index).type != "None")//dont touch none gates
                     genes.get(index).mutate(numNots);//changed genes mutate
                 if (genes.elementAt(index).type == "Not")
                     numNots++;
                 if (genes.elementAt(index).type == "Or" || genes.elementAt(index).type == "And") {
                     if (genes.elementAt(index).inputs.size() < 2) {
                         genes.elementAt(index).inputs.add(LogicGA.randInt(1, index + 1));
                     }
                 }
                 break;
             }
             //swap gates
             case 5:{
            	 int r1 = 0;
            	 int r2 = 0;
            	 int num = randInt(1,50);
            	 for(int i = 0;i<num;i++){
	            	 r1 = randInt(2,genes.size()-1);
	            	 r2 = randInt(2,genes.size()-1);
	            
	            	 Gene one = genes.get(r1);
	            	 Gene two = genes.get(r2);
	            	 if(((one.type=="And")||(one.type=="Or")) &&
	            			 ((two.type == "And")||(two.type =="Or"))){
		            	 //genes.get(r1).inputs = two.inputs;
	            		
	            		 
		            	 genes.get(r1).type = two.type;
		            	// genes.get(r2).inputs = one.inputs;
		            	 genes.get(r2).type = one.type;
		            	 
		            	 
	            	 }
            	 }
//            	 }else if(one.type=="Not"&&( (two.type == "And")||(two.type =="Or"))){
//            		 
//            		 genes.get(r1).inputs = two.inputs;
//	            	 genes.get(r1).type = two.type;
//	            	 //update inputs
//	            	 int in = randInt(0,1);
//	            	 int temp = one.inputs.get(0); //randomly select input to change to
//		             one.inputs.clear();
//		             one.inputs.addElement(temp);
//		             
//		             //swap types
//            		 genes.get(r2).type = one.type;
//            		 genes.get(r2).inputs = one.inputs;
//            		 
//            	 }else if(two.type=="Not"&&( (one.type == "And")||(one.type =="Or"))){
//            		 genes.get(r2).inputs = one.inputs;
//            		 //swap types
//	            	 genes.get(r2).type = one.type;
//	            	 
//	            	 //update inputs
//	            	 int in = randInt(0,1);
//	            	 int temp = two.inputs.get(0); //randomly select input to change to
//		             two.inputs.clear();
//		             two.inputs.addElement(temp);
//		             
//		             //swap types
//            		 genes.get(r1).type = two.type;
//            		 genes.get(r1).inputs = two.inputs;
//            		 
//            	 }
            	
            	 break;
             }
                 default:
                     System.out.println("Could not mutate...");
                     break;
             }
             simulated = false;
     }
    public int calculateFitness(Simulator s, int choice){
        //cache result if recalculation isnt needed
        if(!simulated) {
        	
        	if(choice == 2){
        		
        		double nots = calculateNots();
        		testCircuit(s);
        		double solution = (s.outputs[0].length*s.outputs.length) - numGoalsReached;
        		int size = genes.size();
        		
        		
//        		if(solution<=2 && nots>2){
//        			nots = 10;
//        		}
//        		if(numNots ==2){
//        			nots = .02;
//        		}
//        		if(size<=30){
//        			size= 1;
//        		}
        		
        		
        		if(numNots != 2)
        			nots = numNots * 1.1;
        		if(numNots == 1){
        			nots = 3;
        		}
        		if(numNots == 0)
        			nots = 3;
        		
        		
//        		if(solution<=2 && nots>2){
//        			nots = 10;
//        		}
        		fitness = (int)(10000 * (solution) + (100000 * nots)+ (10000/(size/2)));
        		
//        		if(solution==0&&nots==2)
//        			Print();
        		
        	}else{
	            testCircuit(s);
	            fitness = 1000000 * (s.outputs[0].length*s.outputs.length-numGoalsReached) + 10000 * (calculateNots()) + 10 * (genes.size() - numNots);
        	}
        }
        return fitness;
    }
    public void getFromFile(int populationIndex)throws IOException{
        //Fix for generic case
        // path should be change.
        String path = "./testcircuit/Circuit"+populationIndex+".txt";
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

    public void writeToFile(int populationIndex){
        BufferedWriter writer = null;
        try {
            //create a temporary file
            String fileName = "Circuit"+populationIndex+".txt";
            File logFile = new File(fileName);

            // This will output the full path where the file will be written to...
            //System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));

            for(Gene gene : genes){
                writer.write(gene.getString()+'\n');
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }

    }
    
    public void Print(){
        for(int i =0 ; i < genes.size() ;i++ ){
            genes.elementAt(i).Print();
        }
    }
    public void addGene(Gene gene){
        genes.add(gene);
    }
    /**
     * I added functions below. Please revise them.
     * Sijine 
     */
    public boolean addGate(int output, String gateType, Vector<Integer> input){
    	if (gateType.equals("Not") && numNots == 2 || exists(gateType,input)) {
    		return false;
    	} else {
    		genes.add(new Gene(output, gateType, input));
        	if (gateType.equals("Not")) {
         	   numNots ++;
            }
        	return true;
    	}
    }
    public boolean exists( String gateType, Vector<Integer> input){
        for(Gene g : genes){
            if(g.type == gateType && g.inputs.equals(input)){
                return true;
            }
        }
        return false;
    }
    public int calculateNots(){
        int nots = 0;
        for(Gene g : genes){
            if(g.type == "Not")
                nots++;
        }
        numNots = nots;
        return nots;
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

    public  int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    public int testCircuit(Simulator s){
        numGoalsReached = s.simulate(this);
        simulated = true;
        return numGoalsReached;
    }
    public double getQuality(int fit, int fmax, int fmin){
    	
    	
    	double q = fit/(Math.sqrt(Math.pow(fmax, 2)+Math.pow(fmin, 2)));
    	
    	return q;
    	
    }

}
