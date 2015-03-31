/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicga;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Eric C C
 */
public class Gene {
    int outputNum;
    String type;
    Vector<Integer> inputs = new Vector<Integer>();

    public Gene(int output, String gateType, Vector<Integer> input){
        outputNum = output;
        type = gateType;
        inputs = input;
        
    }
     //changes the gate
    public void mutate(){
    	Random r1 = new Random();
    	int mutation = r1.nextInt(3)+1;
    	switch (mutation){
    	case 1:{
    		type = "AND";
    	}
    	case 2:{
    		type = "OR";
    	}
    	case 3:{
    		Random r2 = new Random();
    		int in = r2.nextInt(inputs.size());
    		while(inputs.get(in)==outputNum){ // Check for input and output equality
    			in = r2.nextInt(inputs.size());
    		}
    		int temp = inputs.get(in); //randomly select input to change to
    		inputs.clear();
    		inputs.addElement(temp);//set inputs to selected value
    		
    		type = "NOT";
    	}
    	default:
    		System.out.println("No Gene change...");
    	}
    	
    }
    
 
    
    public void Print(){
        System.out.println(outputNum + " " + type + " "+ inputs);
    }
}
