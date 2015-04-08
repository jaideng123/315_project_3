package logicga;

import java.io.IOException;
import java.util.Scanner;
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
	
	public static void addNot(Circuit original_circuit, Vector<Integer> original_output, Vector<Node> temp_level){
		for (int j = 0; j < original_output.size(); j ++) {
			Node temp_node1 = new Node(copyCircuit(original_circuit));
			Vector<Integer> temp_inputs = new Vector<Integer>();
			temp_inputs.add(original_output.get(j));
			if(temp_node1.addGate(temp_node1.circuit.genes.size()+1, "Not", temp_inputs))
			    temp_level.add(temp_node1);
		}
	}
	
	public static void addGates(String gate_type, Circuit original_circuit, Simulator sim, Vector<Node> new_level) throws CombinatoricException {
		//set up objects for MultiCombinations
		Vector<Object> objects = new Vector<Object>();
		for (int j = 0; j < original_circuit.genes.size(); j ++) {
			objects.add(original_circuit.genes.get(j).outputNum);
		}

		// add AND gates
		MultiCombinations mc_and = new MultiCombinations(objects, 2);
		
		while (mc_and.hasMoreElements()) {
			Vector<Integer> temp_inputs = new Vector<Integer>();
			Node temp_node2 = new Node(copyCircuit(original_circuit));
			for (int j = 0; j < mc_and.nextElement().length; j++) {
                temp_inputs.add(Integer.parseInt(mc_and.nextElement()[j].toString()));
            }
			if(temp_node2.addGate(temp_node2.circuit.genes.size()+1, gate_type, temp_inputs)) {
                Vector<Gene> temp_genes = temp_node2.circuit.genes;

                if (temp_node2.circuit.getOutputLines().size() == sim.outputs[0].length) {
                    // No extra output needed
                	new_level.add(temp_node2);
                } else {
                    // add one more NONE
                    for (int j = 0; j < temp_genes.size(); j++) {
                        Node temp_node3 = new Node(copyCircuit(temp_node2.circuit));
                        Vector<Integer> temp_inputs2 = new Vector<Integer>();
                        temp_inputs2.add(temp_genes.get(j).outputNum);
                        temp_node3.addGate(temp_genes.size() + 1, "None", temp_inputs2);
                        new_level.add(temp_node3);
                    }
                }
            }
		}
	}
	
	public static void reduceOutputs(Vector<Node> prev_level, Vector<Node> new_level) {
		// reduce output lines
		for (int i = 0; i < prev_level.size(); i++) {
			Circuit original_circuit = copyCircuit(prev_level.get(i).circuit);
			Vector<Integer> original_output = original_circuit.getOutputLines();

			for (int j = 0; j < original_output.size() - 1; j ++) {
				for (int k = j + 1; k < original_output.size(); k ++) {
					Vector<Integer> temp_inputs = new Vector<Integer>();
					temp_inputs.add(original_output.get(j));
					temp_inputs.add(original_output.get(k));

					Node temp_node2 = new Node(copyCircuit(original_circuit));
					Node temp_node3 = new Node(copyCircuit(original_circuit));

					temp_node2.addGate(temp_node2.circuit.genes.size()+1, "And", temp_inputs);
					temp_node3.addGate(temp_node3.circuit.genes.size()+1, "Or", temp_inputs);

					new_level.add(temp_node2);
					new_level.add(temp_node3);
				}
			}
		}
	}
	
	public static void run(Simulator sim) throws CombinatoricException {
		System.out.println("Breadth First Search START");
		
		// variable sim is the Simulator to test with. 
		Circuit c = new Circuit();
		Circuit result = new Circuit();
		boolean is_done = false;

		Node start_node = new Node(c);
		Vector<Node> first_level = new Vector<Node>();
		Vector<Vector<Node>> all_nodes = new Vector<Vector<Node>>();
		first_level.add(start_node);
		all_nodes.add(first_level);
		int current_level;
		
		// build input lines
		c = buildInputs(c, sim);
		System.out.println("\n[LOGIC BF] Inputs added.");
		c.Print();
		
		// test for the first time.
		if (sim.simulate(c) == (sim.outputs.length * sim.outputs[0].length)) {
			is_done = true;
			result = c;
		}
		
		System.out.println("Current Level: " + (all_nodes.size() - 1) + " has " + all_nodes.get(0).size());

		// check that the circuit has enough outputs
		//         circuit          expected result
		int diff = c.genes.size() - sim.outputs[0].length;

		// assume that all circuits from first level have failed
		Vector<Node> temp_level_1 = first_level;
		GraphFrame graph = new GraphFrame("Breadt First Search","Level of Tree","Gates Checked");
		graph.drawGraph(all_nodes.size()-1,all_nodes.get(all_nodes.size()-1).size());
		while (!is_done) {
			System.out.println("\nEntering next level");
			Vector<Node> temp_level_2 = new Vector<Node>();
			// number of outputs from the circuit and truth table are equal
			if (diff == 0) {
				for (int i = 0; i < temp_level_1.size(); i++) {
					Circuit original_circuit = copyCircuit(temp_level_1.get(i).circuit);
					Node original_node = new Node(original_circuit);
					Vector<Integer> original_output = original_circuit.getOutputLines();
					
					// add NOT gates
					if (original_node.blocked == false) {
						addNot(original_circuit, original_output, temp_level_2);
					}
					addGates("And", original_circuit, sim, temp_level_2);
					addGates("Or", original_circuit, sim, temp_level_2);
				}
				all_nodes.add(temp_level_2);

			}
			else {
				// Fit number of outputs
				if (diff > 0) {
					// reduce output lines
					reduceOutputs(temp_level_1, temp_level_2);
					all_nodes.add(temp_level_2);
				}
			}
			//test all circuits in current level
			current_level = all_nodes.size()-1;
			System.out.println("Current Level: "+current_level + " has " + all_nodes.get(current_level).size());
			graph.drawGraph(all_nodes.size()-1,all_nodes.get(all_nodes.size()-1).size());
			for (int i = 0; i < all_nodes.get(current_level).size(); i++) {
				int sim_result = sim.simulate(all_nodes.get(current_level).get(i).circuit);
				if (sim_result == (sim.outputs.length * sim.outputs[0].length)) {
					System.out.println("Goal Circuit Found!!!");
					is_done = true;
					result = all_nodes.get(current_level).get(i).circuit;
					break;
				}
			}
			// update the difference between number of output from circuit and truth table
			diff = temp_level_2.get(0).circuit.getOutputLines().size() - sim.outputs[0].length;
			temp_level_1 = temp_level_2;
		}

		System.out.println("The result circuit is below");
		result.Print();
        System.out.println("\nBreadth First Search END\n");
	}
	
	public static void main(String[] args) throws CombinatoricException {
		// real full adder inputs
		boolean[][] fulladder_inputs = new boolean[8][3];
		fulladder_inputs[0][0] = false;
		fulladder_inputs[0][1] = false;
		fulladder_inputs[0][2] = false;
		fulladder_inputs[1][0] = false;
		fulladder_inputs[1][1] = false;
		fulladder_inputs[1][2] = true;
		fulladder_inputs[2][0] = false;
		fulladder_inputs[2][1] = true;
		fulladder_inputs[2][2] = false;
		fulladder_inputs[3][0] = false;
		fulladder_inputs[3][1] = true;
		fulladder_inputs[3][2] = true;
		fulladder_inputs[4][0] = true;
		fulladder_inputs[4][1] = false;
		fulladder_inputs[4][2] = false;
		fulladder_inputs[5][0] = true;
		fulladder_inputs[5][1] = false;
		fulladder_inputs[5][2] = true;
		fulladder_inputs[6][0] = true;
		fulladder_inputs[6][1] = true;
		fulladder_inputs[6][2] = false;
		fulladder_inputs[7][0] = true;
		fulladder_inputs[7][1] = true;
		fulladder_inputs[7][2] = true;

		boolean[][] fulladder_outputs = new boolean[8][2];
		fulladder_outputs[0][0] = false;
		fulladder_outputs[0][1] = false;
		fulladder_outputs[1][0] = false;
		fulladder_outputs[1][1] = true;
		fulladder_outputs[2][0] = false;
		fulladder_outputs[2][1] = true;
		fulladder_outputs[3][0] = true;
		fulladder_outputs[3][1] = false;
		fulladder_outputs[4][0] = false;
		fulladder_outputs[4][1] = true;
		fulladder_outputs[5][0] = true;
		fulladder_outputs[5][1] = false;
		fulladder_outputs[6][0] = true;
		fulladder_outputs[6][1] = false;
		fulladder_outputs[7][0] = true;
		fulladder_outputs[7][1] = true;
		
		// inverter
		boolean[][] inverse_inputs = new boolean[8][3];
		inverse_inputs[0][0] = false;
		inverse_inputs[0][1] = false;
		inverse_inputs[0][2] = false;
		inverse_inputs[1][0] = false;
		inverse_inputs[1][1] = false;
		inverse_inputs[1][2] = true;
		inverse_inputs[2][0] = false;
		inverse_inputs[2][1] = true;
		inverse_inputs[2][2] = false;
		inverse_inputs[3][0] = false;
		inverse_inputs[3][1] = true;
		inverse_inputs[3][2] = true;
		inverse_inputs[4][0] = true;
		inverse_inputs[4][1] = false;
		inverse_inputs[4][2] = false;
		inverse_inputs[5][0] = true;
		inverse_inputs[5][1] = false;
		inverse_inputs[5][2] = true;
		inverse_inputs[6][0] = true;
		inverse_inputs[6][1] = true;
		inverse_inputs[6][2] = false;
		inverse_inputs[7][0] = true;
		inverse_inputs[7][1] = true;
		inverse_inputs[7][2] = true;
		
		boolean[][] inverse_outputs = new boolean[8][3];
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 3; j ++) {
				inverse_outputs[i][j] = !inverse_inputs[i][j];
			}
		}

		// one AND circuit
		boolean[][] and_inputs = new boolean [4][2];
		and_inputs[0][0] = false;
		and_inputs[0][1] = false;
		and_inputs[1][0] = false;
		and_inputs[1][1] = true;
		and_inputs[2][0] = true;
		and_inputs[2][1] = false;
		and_inputs[3][0] = true;
		and_inputs[3][1] = true;

		boolean[][] and_outputs = new boolean [4][1];
		and_outputs[0][0] = false;
		and_outputs[1][0] = false;
		and_outputs[2][0] = false;
		and_outputs[3][0] = true;

		// one OR circuit
		boolean[][] or_inputs = new boolean [4][2];
		or_inputs[0][0] = false;
		or_inputs[0][1] = false;
		or_inputs[1][0] = false;
		or_inputs[1][1] = true;
		or_inputs[2][0] = true;
		or_inputs[2][1] = false;
		or_inputs[3][0] = true;
		or_inputs[3][1] = true;

		boolean[][] or_outputs = new boolean [4][1];
		or_outputs[0][0] = false;
		or_outputs[1][0] = true;
		or_outputs[2][0] = true;
		or_outputs[3][0] = true;

		// one NAND circuit
		boolean[][] nand_inputs = new boolean [4][2];
		nand_inputs[0][0] = false;
		nand_inputs[0][1] = false;
		nand_inputs[1][0] = false;
		nand_inputs[1][1] = true;
		nand_inputs[2][0] = true;
		nand_inputs[2][1] = false;
		nand_inputs[3][0] = true;
		nand_inputs[3][1] = true;

		boolean[][] nand_outputs = new boolean [4][1];
		nand_outputs[0][0] = true;
		nand_outputs[1][0] = true;
		nand_outputs[2][0] = true;
		nand_outputs[3][0] = false;

		// one NOR circuit
		boolean[][] nor_inputs = new boolean [4][2];
		nor_inputs[0][0] = false;
		nor_inputs[0][1] = false;
		nor_inputs[1][0] = false;
		nor_inputs[1][1] = true;
		nor_inputs[2][0] = true;
		nor_inputs[2][1] = false;
		nor_inputs[3][0] = true;
		nor_inputs[3][1] = true;

		boolean[][] nor_outputs = new boolean [4][1];
		nor_outputs[0][0] = true;
		nor_outputs[1][0] = false;
		nor_outputs[2][0] = false;
		nor_outputs[3][0] = false;
		

		// one XOR circuit
		boolean[][] xor_inputs = new boolean [4][2];
		xor_inputs[0][0] = false;
		xor_inputs[0][1] = false;
		xor_inputs[1][0] = false;
		xor_inputs[1][1] = true;
		xor_inputs[2][0] = true;
		xor_inputs[2][1] = false;
		xor_inputs[3][0] = true;
		xor_inputs[3][1] = true;

		boolean[][] xor_outputs = new boolean [4][1];
		xor_outputs[0][0] = false;
		xor_outputs[1][0] = true;
		xor_outputs[2][0] = true;
		xor_outputs[3][0] = false;
		
		
		// two OR one NOT custom circuit
		boolean[][] custom_inputs = new boolean [4][2];
		custom_inputs[0][0] = false;
		custom_inputs[0][1] = false;
		custom_inputs[1][0] = false;
		custom_inputs[1][1] = true;
		custom_inputs[2][0] = true;
		custom_inputs[2][1] = false;
		custom_inputs[3][0] = true;
		custom_inputs[3][1] = true;

		boolean[][] custom_outputs = new boolean [4][1];
		custom_outputs[0][0] = true;
		custom_outputs[1][0] = false;
		custom_outputs[2][0] = true;
		custom_outputs[3][0] = true;
		
		while (true) {
			// menu
			System.out.println("1. Full adder circuit");
			System.out.println("2. Inverse circuit");
			System.out.println("3. one AND circuit");
			System.out.println("4. one OR circuit");
			System.out.println("5. one NAND circuit");
			System.out.println("6. one NOR circuit");
			System.out.println("7. Custom circuit");
			System.out.println("8. one XOR circuit");
			
			System.out.print("Choose one: ");
			Scanner in = new Scanner(System.in);
			int num = in.nextInt();
			
			Simulator sim = null;
			
			switch(num) {
			case 1:
				sim = new Simulator(fulladder_inputs, fulladder_outputs);
				run(sim);
				break;
			case 2:
				sim = new Simulator(inverse_inputs, inverse_outputs);
				run(sim);
				break;
			case 3:
				sim = new Simulator(and_inputs, and_outputs);
				run(sim);
				break;
			case 4:
				sim = new Simulator(or_inputs, or_outputs);
				run(sim);
				break;
			case 5:
				sim = new Simulator(nand_inputs, nand_outputs);
				run(sim);
				break;
			case 6:
				sim = new Simulator(nor_inputs, nor_outputs);
				run(sim);
				break;
			case 7:
				sim = new Simulator(custom_inputs, custom_outputs);
				run(sim);
				break;
			case 8:
				sim = new Simulator(xor_inputs, xor_outputs);
				run(sim);
				break;
			default:
				System.out.println("Pick different number.");
			} // end of switch case 
		} // end of infinite while loop 
	} // end of main function
} // end of class
