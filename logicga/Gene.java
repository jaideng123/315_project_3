/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicga;

import java.util.Vector;

/**
 *
 * @author Eric C C
 */
public class Gene {
    int outputNum;
    String type;
    Vector<Integer> inputs = new Vector<Integer>();

    public Gene(int output, String gateType, Vector input){
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
