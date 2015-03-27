package logicga;

import java.io.IOException;
import java.util.Vector;
/**
*
* @author Si Jine Roh
*/
public class LogicBF {

	public static void main(String[] args) {
		System.out.println("Logic Non-GA, Brute Force START");
		
		// for test only, AND gate
		boolean[][] fulladder_inputs = new boolean[4][2];
		fulladder_inputs[0][0] = false;
		fulladder_inputs[0][1] = false;
		fulladder_inputs[1][0] = false;
		fulladder_inputs[1][1] = true;
		fulladder_inputs[2][0] = true;
		fulladder_inputs[2][1] = false;
		fulladder_inputs[3][0] = true;
		fulladder_inputs[3][1] = true;
		
		
		// real full adder inputs
//		boolean[][] fulladder_inputs = new boolean[8][3];
//		fulladder_inputs[0][0] = false;
//		fulladder_inputs[0][1] = false;
//		fulladder_inputs[0][2] = false;
//		fulladder_inputs[1][0] = false;
//		fulladder_inputs[1][1] = false;
//		fulladder_inputs[1][2] = true;
//		fulladder_inputs[2][0] = false;
//		fulladder_inputs[2][1] = true;
//		fulladder_inputs[2][2] = false;
//		fulladder_inputs[3][0] = false;
//		fulladder_inputs[3][1] = true;
//		fulladder_inputs[3][2] = true;
//		fulladder_inputs[4][0] = true;
//		fulladder_inputs[4][1] = false;
//		fulladder_inputs[4][2] = false;
//		fulladder_inputs[5][0] = true;
//		fulladder_inputs[5][1] = false;
//		fulladder_inputs[5][2] = true;
//		fulladder_inputs[6][0] = true;
//		fulladder_inputs[6][1] = true;
//		fulladder_inputs[6][2] = false;
//		fulladder_inputs[7][0] = true;
//		fulladder_inputs[7][1] = true;
//		fulladder_inputs[7][2] = true;
		
		// for test, AND gate
		boolean[][] fulladder_outputs = new boolean[4][1];
		fulladder_outputs[0][0] = false;
		fulladder_outputs[1][0] = false;
		fulladder_outputs[2][0] = false;
		fulladder_outputs[3][0] = true;
		
		// real full adder outputs
//		boolean[][] fulladder_outputs = new boolean[8][2];
//		fulladder_outputs[0][0] = false;
//		fulladder_outputs[0][1] = false;
//		fulladder_outputs[1][0] = false;
//		fulladder_outputs[1][1] = true;
//		fulladder_outputs[2][0] = false;
//		fulladder_outputs[2][1] = true;
//		fulladder_outputs[3][0] = true;
//		fulladder_outputs[3][1] = false;
//		fulladder_outputs[4][0] = false;
//		fulladder_outputs[4][1] = true;
//		fulladder_outputs[5][0] = true;
//		fulladder_outputs[5][1] = false;
//		fulladder_outputs[6][0] = true;
//		fulladder_outputs[6][1] = false;
//		fulladder_outputs[7][0] = true;
//		fulladder_outputs[7][1] = true;

		// declare simulator with truth table
		Simulator sim = new Simulator(fulladder_inputs, fulladder_outputs);
		
		// declare empty circuit, which start with 1 input and ouput
		Circuit c = new Circuit();

//		Vector<>
		// build input lines
		while (c.getInputLines().size() < sim.inputs[0].length) {
			Vector<Integer> temp_inputs = new Vector();
			if (sim.inputs.length == 0) {
				temp_inputs.add(c.genes.size() + 1);
				c.addGate(c.genes.size() + 1, "None", temp_inputs);
			} else if (c.getInputLines().size() != sim.inputs[0].length) {
				temp_inputs.add(c.genes.size() + 1);
				c.addGate(c.genes.size() + 1, "None", temp_inputs);
			}
		}
		c.Print();
		
		int i = 0;
		Vector<Vector<Node>> nodes = new Vector<Vector<Node>>();
		Vector<Node> start_node = new Vector<Node>();
//		start_node.add(new Node())
		
		// start to build a circuit
		while (sim.simulate(c) != sim.inputs.length) {
			//
		}
		c.Print();
		
//        Circuit test = new Circuit();
//        try{
//        test.getFromFile(1);
//        }
//        catch(IOException e){
//            System.out.println(e.getMessage());
//        }
//        test.Print();
//        test.addGate(output, gateType, input);
        System.out.println("Logic Non-GA, Brute Force END");
	}

}
