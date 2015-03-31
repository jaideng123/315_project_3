package logicga;

import java.util.Vector;

/**
*
* @author Si Jine Roh
*/

public class Node {
	
	Circuit circuit = new Circuit();
	int ninput;
	int noutput;
	boolean blocked = false; // if it's true, there are 2 NOT gates
	
	Vector<Integer> inputlines = new Vector();
	Vector<Integer> outputlines = new Vector();
	
	
	Node (Circuit c) {
		circuit = c;
		refresh();
	}
		
	public void refresh() {
		inputlines = circuit.getInputLines();
		outputlines = circuit.getOutputLines();
		ninput = inputlines.size();
		noutput = outputlines.size();
	}
	
    public void addGate(int output, String gateType, Vector input){
    	blocked = !circuit.addGate(output, gateType, input);
    }

    public Vector<Integer> getOutputlines(){
    	return circuit.getOutputLines();
    }
    public Vector<Integer> getIntputlines(){
    	return circuit.getInputLines();
    }
    
    public int getNots() {
    	return circuit.numNots;
    }
}
