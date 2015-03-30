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
	
	public static Circuit buildInputs(Circuit c, Simulator sim) {
		// should call this function before simulate.
		Circuit result = c;
		// while (result.getInputLines() < sim.inputs[0].length) {
		while (result.getInputLines().size() < sim.inputs[0].length) {
			Vector<Integer> temp_inputs = new Vector();
			if (sim.inputs.length == 0) {
				temp_inputs.add(c.genes.size() + 1);
				c.addGate(c.genes.size() + 1, "None", temp_inputs);
			} else if (c.getInputLines().size() != sim.inputs[0].length) {
				temp_inputs.add(c.genes.size() + 1);
				c.addGate(c.genes.size() + 1, "None", temp_inputs);
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws CombinatoricException {
		System.out.println("Logic Non-GA, Brute Force START");
		
		// for test only
//		boolean[][] fulladder_inputs = new boolean[4][2];
//		fulladder_inputs[0][0] = false;
//		fulladder_inputs[0][1] = false;
//		fulladder_inputs[1][0] = false;
//		fulladder_inputs[1][1] = true;
//		fulladder_inputs[2][0] = true;
//		fulladder_inputs[2][1] = false;
//		fulladder_inputs[3][0] = true;
//		fulladder_inputs[3][1] = true;
//
//		boolean[][] fulladder_outputs = new boolean[4][2];
//		fulladder_outputs[0][0] = false;
//		fulladder_outputs[0][1] = false;
//		fulladder_outputs[1][0] = false;
//		fulladder_outputs[1][1] = true;
//		fulladder_outputs[2][0] = false;
//		fulladder_outputs[2][1] = false;
//		fulladder_outputs[3][0] = true;
//		fulladder_outputs[3][1] = true;
		
		// XOR test
//		boolean[][] fulladder_inputs = new boolean[4][2];
//		fulladder_inputs[0][0] = false;
//		fulladder_inputs[0][1] = false;
//		fulladder_inputs[1][0] = false;
//		fulladder_inputs[1][1] = true;
//		fulladder_inputs[2][0] = true;
//		fulladder_inputs[2][1] = false;
//		fulladder_inputs[3][0] = true;
//		fulladder_inputs[3][1] = true;
//
//		boolean[][] fulladder_outputs = new boolean[4][1];
//		fulladder_outputs[0][0] = false;
//		fulladder_outputs[1][0] = true;
//		fulladder_outputs[2][0] = true;
//		fulladder_outputs[3][0] = false;
		
		// AND test
		boolean[][] fulladder_inputs = new boolean[4][2];
		fulladder_inputs[0][0] = false;
		fulladder_inputs[0][1] = false;
		fulladder_inputs[1][0] = false;
		fulladder_inputs[1][1] = true;
		fulladder_inputs[2][0] = true;
		fulladder_inputs[2][1] = false;
		fulladder_inputs[3][0] = true;
		fulladder_inputs[3][1] = true;

		boolean[][] fulladder_outputs = new boolean[4][1];
		fulladder_outputs[0][0] = false;
		fulladder_outputs[1][0] = false;
		fulladder_outputs[2][0] = false;
		fulladder_outputs[3][0] = true;
		
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
//
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
		Circuit result = new Circuit();
		boolean is_done = false;

		// to go deep
		Node start_node = new Node(c);
		Vector<Node> first_level = new Vector<Node>();
		Vector<Vector<Node>> all_nodes = new Vector<Vector<Node>>();
		first_level.add(start_node);
		all_nodes.add(first_level);
		int current_level;
		
		// build input lines
		c = buildInputs(c, sim);
		System.out.println("[LOGIC BF] Inputs added.");
		c.Print();
		
		// test for the first time.
		if (sim.simulate(c) == c.genes.size()) {
			is_done = true;
			result = c;
		} else {
			System.out.println("NOT YET..");
		}
		System.out.println("Current Level: " + all_nodes.size());

		// check the circuit has enough outputs
		int diff = c.genes.size() - sim.outputs[0].length;
		//         circuit          expected result
		System.out.println("DIFF = " + diff);
		// assume that all circuits from previous level are failed
		Vector<Node> temp_level_1 = new Vector<Node>();
//		temp_level_1.add(start_node);
		temp_level_1 = first_level;
		
//		while (!is_done) {
		for (int a = 0; a < 1; a ++){
//			System.out.println("In while loop");
			Vector<Node> temp_level_2 = new Vector<Node>();
			// number of outputs from the circuit and truth table are equal
			if (diff == 0) {
				// add gates first AND/OR
				// because they are all failed circuits
				for (int i = 0; i < temp_level_1.size(); i++) {
					Circuit original_circuit = copyCircuit(temp_level_1.get(i).circuit);
					Node original_node = new Node(original_circuit);
					Vector<Integer> original_output = original_circuit.getOutputLines();
//					temp_level_2.add(original_node);
					
					// add not gates
					if (original_node.blocked == false) { // if it's not blocked
						for (int j = 0; j < original_output.size(); j ++) {
							Node temp_node1 = new Node(copyCircuit(original_circuit));
							Vector<Integer> temp_inputs = new Vector<Integer>();
							temp_inputs.add(original_output.get(j));
							temp_node1.addGate(temp_node1.circuit.genes.size()+1, "Not", temp_inputs);
							temp_level_2.add(temp_node1);
						}
					}

					// add AND/OR gates
					Vector<Object> objects = new Vector();
					for (int j = 0; j < original_output.size(); j ++) {
						objects.add(original_output.get(j));
						System.out.println("OUTPUT: " + original_output.get(j));
					}
					// add AND
					MultiCombinations mc_and = new MultiCombinations(objects, 2);
//					mc.print();
					while (mc_and.hasMoreElements()) {
//						System.out.println("IN SECOND WHILE");
						Vector<Integer> temp_inputs = new Vector<Integer>();
						Node temp_node2 = new Node(copyCircuit(original_circuit));
						for (int j = 0; j < mc_and.nextElement().length; j++) {
							temp_inputs.add(Integer.parseInt(mc_and.nextElement()[j].toString()));
						}
						temp_node2.addGate(temp_node2.circuit.genes.size()+1, "And", temp_inputs);
						Vector<Gene> temp_genes = temp_node2.circuit.genes;

						if (temp_node2.circuit.getOutputLines().size() == sim.outputs[0].length) {
							// doesn't need extra output
//							System.out.println("EQUAL");
//							temp_node2.circuit.Print();	
							temp_level_2.add(temp_node2);
						} else {
							// does need extra output
							// add one more NONE
							for (int j = 0; j < temp_genes.size(); j++) {
								Node temp_node3 = new Node(copyCircuit(temp_node2.circuit));
								Vector<Integer> temp_inputs2 = new Vector<Integer>();
								temp_inputs2.add(temp_genes.get(j).outputNum);
								temp_node3.addGate(temp_genes.size() + 1, "None", temp_inputs2);
//								temp_node3.circuit.Print();
								temp_level_2.add(temp_node3);
							}
						}
					}
					
//					// add OR
					MultiCombinations mc_or = new MultiCombinations(objects, 2);
//					mc_or.print();
					while (mc_or.hasMoreElements()) {
//						System.out.println("IN SECOND WHILE");
						Vector<Integer> temp_inputs = new Vector<Integer>();
						Node temp_node2 = new Node(copyCircuit(original_circuit));
						for (int j = 0; j < mc_or.nextElement().length; j++) {
							temp_inputs.add(Integer.parseInt(mc_or.nextElement()[j].toString()));
						}
						temp_node2.addGate(temp_node2.circuit.genes.size()+1, "Or", temp_inputs);
						Vector<Gene> temp_genes = temp_node2.circuit.genes;

						if (temp_node2.circuit.getOutputLines().size() == sim.outputs[0].length) {
							// doesn't need extra output
//							System.out.println("EQUAL");
//							temp_node2.circuit.Print();	
							temp_level_2.add(temp_node2);
						} else {
							// does need extra output
							// add one more NONE
							for (int j = 0; j < temp_genes.size(); j++) {
								Node temp_node3 = new Node(copyCircuit(temp_node2.circuit));
								Vector<Integer> temp_inputs2 = new Vector<Integer>();
								temp_inputs2.add(temp_genes.get(j).outputNum);
								temp_node3.addGate(temp_genes.size() + 1, "None", temp_inputs2);
//								temp_node3.circuit.Print();
								temp_level_2.add(temp_node3);
							}
						}
					}
				}
				all_nodes.add(temp_level_2);
				
				/*************************************/
				/** test all circuits in this level **/
				/*************************************/
				current_level = all_nodes.size()-1;
				System.out.println("Current Level is: "+current_level + " has " + all_nodes.get(current_level).size());
				for (int i = 0; i < all_nodes.get(current_level).size() - 1; i++) {
//					System.out.println(i);
//					System.out.println(sim.simulate(all_nodes.get(current_level)).g)
					System.out.println("\nCircuit " + i + ":");
					all_nodes.get(current_level).get(i).circuit.Print();
					
					int sim_result = sim.simulate(all_nodes.get(current_level).get(i).circuit);
					System.out.println("RESULT: " + sim_result);
					
					if (sim_result == (sim.outputs.length * sim.outputs[0].length)) {
						System.out.println("YAY!!!");
						is_done = true;
						result = all_nodes.get(current_level).get(i).circuit;
//						i += all_nodes.get(current_level).size();
						break;
					}
				}
			} else {
				// make numbers of output fits!! 
//				while (diff != 0) {
					// when the number of outputs from circuit and simulator are different
				if (diff < 0) {
					// add output lines
					System.out.println("NEED MORE OUTPUTS");
					while (diff != 0) {
						for (int i = 0; i < temp_level_1.size(); i++) {
							Circuit original_circuit = copyCircuit(temp_level_1.get(i).circuit);
							Node original_node = new Node(original_circuit);
							Vector<Integer> original_output = original_circuit.getOutputLines();
							Vector<Gene> original_genes = original_circuit.genes;
							
							for (int j = 0; j < original_genes.size(); j ++) {
								Vector<Integer> temp_inputs = new Vector<Integer>();
								temp_inputs.add(original_genes.get(j).outputNum);
								Node temp_node1 = new Node(original_circuit);
								temp_node1.addGate(original_genes.size() + 1, "None", temp_inputs);
								temp_level_2.add(temp_node1);
							}
						}
//						diff = temp_level_2.get(0).circuit.genes.size() - sim.outputs[0].length;;
//						temp_level_1 = temp_level_2;
					}
				} else if (diff > 0) {
					// reduce output lines
					System.out.println("NEED LESS OUTPUTS");
					for (int i = 0; i < temp_level_1.size(); i++) {
						Circuit original_circuit = copyCircuit(temp_level_1.get(i).circuit);
						Node original_node = new Node(original_circuit);
						Vector<Integer> original_output = original_circuit.getOutputLines();
						
						// add AND/OR gates
						Vector<Object> objects = new Vector();
						for (int j = 0; j < original_output.size(); j ++) {
							objects.add(original_output.get(j));
							System.out.println("OUTPUT: " + original_output.get(j));
						}
						
						
						for (int j = 0; j < original_output.size() - 1; j ++) {
							for (int k = j + 1; k < original_output.size(); k ++) {
								Vector<Integer> temp_inputs = new Vector<Integer>();
								temp_inputs.add(original_output.get(j));
								temp_inputs.add(original_output.get(k));
								
								Node temp_node2 = new Node(copyCircuit(original_circuit));
								Node temp_node3 = new Node(copyCircuit(original_circuit));

								temp_node2.addGate(temp_node2.circuit.genes.size()+1, "And", temp_inputs);
								temp_node3.addGate(temp_node3.circuit.genes.size()+1, "Or", temp_inputs);
								
								temp_level_2.add(temp_node2);
								temp_level_2.add(temp_node3);
								
								temp_node2.circuit.Print();
								System.out.println(temp_node2.circuit.getOutputLines().size());
								temp_node3.circuit.Print();
								System.out.println(temp_node3.circuit.getOutputLines().size());
								
							}
						}
					}
				}
//					diff = 
//					temp_level_1 = temp_level_2;
//				}	
			}
			diff = temp_level_2.get(0).circuit.getOutputLines().size() - sim.outputs[0].length;
			System.out.println("NEW DIFF: " + diff);
			temp_level_1 = temp_level_2;
//			temp_level_2.clear();
//			System.out.println("one loop");
		}

		System.out.println("DONE");
		result.Print();
        System.out.println("Logic Non-GA, Brute Force END");
	}

}
