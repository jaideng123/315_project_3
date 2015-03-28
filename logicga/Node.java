package logicga;

import java.util.Vector;

/**
*
* @author Si Jine Roh
*/

public class Node {
	
	int nnot = 0; // number of not gates
	Circuit circuit = new Circuit();
	int ninput;
	int noutput;

	Vector<Integer> inputlines = new Vector();
	Vector<Integer> outputlines = new Vector();
	
//	Node () {
//		
//	}
	
	Node (Circuit c) {
		circuit = c;
		refresh();
//		inputlines = c.getInputLines();
//		outputlines = c.getOutputLines();
//		ninput = inputlines.size();
//		noutput = outputlines.size();
		// can get number of inputs from Circuit class?
		// can get number of outputs from Circuit class?
	}
	
//	Node (Circuit c, int i, int o) {
//		circuit = c;
//		ninput = i;
//		noutput = o;
//	}
	
	public void refresh() {
		inputlines = circuit.getInputLines();
		outputlines = circuit.getOutputLines();
		ninput = inputlines.size();
		noutput = outputlines.size();
	}
	
	public void addGate(int output, String gateType, Vector input){
    	circuit.genes.add(new Gene(output, gateType, input));
    	refresh();
    	if (gateType.endsWith("Not")) {
    		nnot ++;
    	}
    }
	
	public boolean canUseNot(){
		if (nnot >= 2) {
			return false;
		} else {
			return true;
		}
	} 
}
