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
    Vector<Integer> inputs = new Vector();
        
    public Gene(int output, String gateType, Vector input){
        outputNum = output;
        type = gateType;
        inputs = input;
        
    }
    
    public void Print(){
        System.out.println(outputNum + " " + type + " "+ inputs);
    }
}
