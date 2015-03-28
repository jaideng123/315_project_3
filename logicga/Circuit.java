/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicga;

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
    	int i = 0;
    	if (genes.size() > 0 && i < genes.size()) {
	    	while (i < genes.size() && genes.elementAt(i).type == "None" &&
	    			genes.elementAt(i).outputNum == genes.elementAt(i).inputs.firstElement()) {
//	    		genes.elementAt(i).inputs.get(0);
//	    		result.add(genes.elementAt(i).inputs.get(0));
	    		result.add(genes.elementAt(i).outputNum);
	    		i ++;
	    	}
    	}
    	return result;
    }
    
    public Vector<Integer> getOutputLines() {
    	Vector<Integer> result = new Vector();
    	return result;
    }
}
