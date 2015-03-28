package logicga;

import java.io.IOException;
import java.util.Vector;

import utility.CombinatoricException;
/**
*
* @author Si Jine Roh
*/
public class LogicBF {

	public static Circuit copyCircuit(Circuit c) {
		Circuit result = new Circuit();
		for (int j = 0; j<c.genes.size(); j++) {
    		result.addGate(c.genes.get(j).outputNum, c.genes.get(j).type, c.genes.get(j).inputs);	        		
    	}
		return result;
	}
	
	public static void main(String[] args) throws CombinatoricException {
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
		
//		boolean[][] fulladder_inputs = new boolean[8][4];
//		for (int i = 0; i < 8; i ++) {
//			for (int j = 0; j<4;j++) {
//				fulladder_inputs[i][j] = true;				
//			}
//			
//		}
		
		// for test, AND gate
//		boolean[][] fulladder_outputs = new boolean[4][1];
//		fulladder_outputs[0][0] = false;
//		fulladder_outputs[1][0] = false;
//		fulladder_outputs[2][0] = false;
//		fulladder_outputs[3][0] = true;
		
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

//		boolean[][] fulladder_outputs = new boolean[8][1];
//		fulladder_outputs[0][0] = false;
//		fulladder_outputs[1][0] = true;
//		fulladder_outputs[2][0] = false;
//		fulladder_outputs[3][0] = true;
//		fulladder_outputs[4][0] = false;
//		fulladder_outputs[5][0] = true;
//		fulladder_outputs[6][0] = true;
//		fulladder_outputs[7][0] = true;
		
		boolean[][] fulladder_outputs = new boolean[4][4];
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j<4;j++) {
				fulladder_outputs[i][j] = true;				
			}
			
		}
		
		// declare simulator with truth table
		Simulator sim = new Simulator(fulladder_inputs, fulladder_outputs);
		
		// declare empty circuit, which start with 1 input and ouput
		Circuit c = new Circuit();
		Circuit result = new Circuit();
		boolean is_done = false;

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
		System.out.println("[LOGIC BF] Inputs added.");
		c.Print();
		///////////////////////////////////////////////// WORKS!!
		
		if (sim.simulate(c) == c.genes.size()) {
			is_done = true;
			result = c;
		}
		
		// check the circuit has enough outputs
		int diff = c.genes.size() - sim.outputs[0].length;
		//         circuit          expected result
		
		Node start_node = new Node(c);
		Vector<Node> first_level = new Vector<Node>();
		Vector<Vector<Node>> all_nodes = new Vector<Vector<Node>>();
		first_level.add(start_node);
		all_nodes.add(first_level);
		
		if (diff < 0 && is_done == false) {
			System.out.println("<");
			// c should add output lines
			diff *= -1;
			System.out.println(diff);
			Vector<Node> temp_level = new Vector<Node>();
			
			Vector<Object> objects = new Vector();
			for (int i = 0; i < c.getInputLines().size(); i ++) {
				objects.add(c.getInputLines().get(i));
//				System.out.println(c.getInputLines().get(i));
			}
			
			MultiCombinations mc = new MultiCombinations(objects, diff);
//			mc.print();
			
			while (mc.hasMoreElements()) {
				Vector<Integer> temp_inputs = new Vector<Integer>();
				Node temp_node1 = new Node(copyCircuit(c));
				for (int i = 0; i < mc.nextElement().length; i++) {
					temp_inputs.add(Integer.parseInt(mc.nextElement()[i].toString()));
					temp_node1.circuit.addGate(temp_node1.circuit.genes.size()+1, "None", temp_inputs);
				}
				temp_level.add(temp_node1);
			}
			all_nodes.add(temp_level);
			
		} else if (diff > 0 && is_done == false) {
			System.out.println("> and diff = " + diff);
			// c should reduce output lines
			Vector<Node> temp_level = new Vector<Node>();
			
			Vector<Object> objects = new Vector();
			for (int i = 0; i < c.genes.size(); i ++) {
				objects.add(c.genes.get(i).outputNum);
			}
			// basically every gates will get two inputs
	        MultiCombinations mc = new MultiCombinations(objects, 2);
//	        mc.print(); // print out
			while (mc.hasMoreElements()) {
				Vector<Integer> temp_inputs = new Vector<Integer>();
				for (int i = 0; i < mc.nextElement().length; i++) {
					temp_inputs.add(Integer.parseInt(mc.nextElement()[i].toString()));
				}
				Node temp_node1 = new Node(copyCircuit(c));
				temp_node1.circuit.addGate(temp_node1.circuit.genes.size()+1, "And", temp_inputs);
				Node temp_node2 = new Node(copyCircuit(c));
				temp_node2.circuit.addGate(temp_node2.circuit.genes.size()+1, "Or", temp_inputs);
				
				temp_level.add(temp_node1);
				temp_level.add(temp_node2);
			}
			all_nodes.add(temp_level);
		}

		int current_level = all_nodes.size()-1;
		for (int i = 0; i < all_nodes.get(current_level).size() - 1; i++) {
			if (sim.simulate(all_nodes.get(current_level).get(i).circuit) == sim.outputs.length) {
				is_done = true;
				i += all_nodes.get(current_level).size();
				// break?? maybe??
			}
		}
		
		// adding other parts
		while (!is_done) {
			// add circuits into the level
			Vector<Node> temp_level = new Vector<Node>();
			for (int i = 0; i < all_nodes.get(current_level).size() - 1; i++) {
				Circuit original_circuit = copyCircuit(all_nodes.get(current_level).get(i).circuit);
				Node original_node = new Node(original_circuit);
				
				// add NOT cases
				if (original_circuit.n_not < 2) {
					for (int j = 0; j < original_node.outputlines.size(); j ++) {
						Node temp_node = new Node(copyCircuit(original_circuit));
						Vector<Integer> temp_inputs = new Vector<Integer>();
						temp_inputs.add(original_node.outputlines.get(i));
						temp_node.circuit.addGate(original_node.outputlines.size()+1, "Not", temp_inputs);
						temp_level.add(temp_node);
					}
				}
				
				
				// add AND cases
				Vector<Object> objects = new Vector();
				for (int j = 0; j < original_node.outputlines.size(); j ++) {
					objects.add(original_node.outputlines.get(j));
				}
				MultiCombinations mc = new MultiCombinations(objects, 2);
//				mc_and.print();
				while (mc.hasMoreElements()) {
					Vector<Integer> temp_inputs = new Vector<Integer>();
					Node temp_node1 = new Node(copyCircuit(c));
					for (int j = 0; j < mc.nextElement().length; j++) {
						temp_inputs.add(Integer.parseInt(mc.nextElement()[i].toString()));
					}
					temp_node1.circuit.addGate(temp_node1.circuit.genes.size()+1, "And", temp_inputs);
					temp_level.add(temp_node1);
				}
				
				// add OR cases
				while (mc.hasMoreElements()) {
					Vector<Integer> temp_inputs = new Vector<Integer>();
					Node temp_node1 = new Node(copyCircuit(c));
					for (int j = 0; j < mc.nextElement().length; j++) {
						temp_inputs.add(Integer.parseInt(mc.nextElement()[i].toString()));
					}
					temp_node1.circuit.addGate(temp_node1.circuit.genes.size()+1, "Or", temp_inputs);
					temp_level.add(temp_node1);
				}
				
			}
			all_nodes.add(temp_level);
			
			// test all circuits in the level
			current_level = all_nodes.size()-1;
			for (int i = 0; i < all_nodes.get(current_level).size() - 1; i++) {
				if (sim.simulate(all_nodes.get(current_level).get(i).circuit) == sim.outputs.length) {
					is_done = true;
					i += all_nodes.get(current_level).size();
				}
			}
			// passed?
		}
		
		System.out.println("DONE");
		result.Print();
        System.out.println("Logic Non-GA, Brute Force END");
	}

}
