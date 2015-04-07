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
    public void mutate(int numNots){
    	int mutation = 0;
    	int mutNot = 0;
        if(numNots == 2){//If circuit has two Not gates, less likely of Not gate mutation
        	mutNot = randInt(1,10);
        	 if(mutNot==1){
             	int in = 0;
                 if(inputs.size()!=1){
                     in= randInt(0,1);
                 }
                 int temp = inputs.get(in); //randomly select input to change to
                 inputs.clear();
                 inputs.addElement(temp);//set inputs to selected value

                 type = "Not";
        	 }else{
             	mutation = randInt(1,2);
    	        switch (mutation){
    	            //changes to AND
    	            case 1:{
    	                type = "And";
    	                break;
    	            }
    	            //changes to OR
    	            case 2:{
    	                type = "Or";
    	                break;
    	            }
    	            default:
    	            	break;
    	        }
        	 }
        }else{
        	mutation = randInt(1,3);
        

	        switch (mutation){
	            //changes to AND
	            case 1:{
	                type = "And";
	                break;
	            }
	            //changes to OR
	            case 2:{
	                type = "Or";
	                break;
	            }
	            //changes to NOT
	            case 3:{
	
	                int in = 0;
	                if(inputs.size()!=1){
	                    in= randInt(0,1);
	                }
	                int temp = inputs.get(in); //randomly select input to change to
	                inputs.clear();
	                inputs.addElement(temp);//set inputs to selected value
	
	                type = "Not";
	                break;
	            }
	            default:
	                break;
	        }
        }

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



    public void Print(){
        System.out.print(outputNum + " " + type.toUpperCase());
		for(int i : inputs){
			System.out.print(" " + i);
		}
		System.out.println("");
    }

    public String getString(){
        String geneLine = outputNum + " " + type + " "+ inputs;
        return geneLine;
    }
}
