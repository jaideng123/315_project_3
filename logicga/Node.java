package logicga;

import java.util.Vector;

/**
*
* @author Si Jine Roh
*/

public class Node {
	/**
	 * variables
	 * 	number of not gates
	 * 	whole circuit
	 * 	number of inputs // no need
	 *  number of outputs // no need
	 *  array or vector of input line number
	 *  	first few lines of NONE lines are input
	 *  array or vector of output line number
	 *  	//
	 */
	
	int nnot = 0; // number of not gates
	Circuit total = new Circuit();
	int ninput;
	int noutput;
	int nline;
	Vector<Integer> inputlines = new Vector();
	Vector<Integer> outputlines = new Vector();
	
	Node (Circuit c, int i, int o) {
		total = c;
		ninput = i;
		noutput = o;
	}
}
