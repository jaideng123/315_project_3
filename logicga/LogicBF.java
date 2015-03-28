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
//		boolean[][] fulladder_inputs = new boolean[4][2];
//		fulladder_inputs[0][0] = false;
//		fulladder_inputs[0][1] = false;
//		fulladder_inputs[1][0] = false;
//		fulladder_inputs[1][1] = true;
//		fulladder_inputs[2][0] = true;
//		fulladder_inputs[2][1] = false;
//		fulladder_inputs[3][0] = true;
//		fulladder_inputs[3][1] = true;
		
		
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

		boolean[][] fulladder_outputs = new boolean[8][2];
		fulladder_outputs[0][0] = false;
		fulladder_outputs[1][0] = true;
		fulladder_outputs[2][0] = false;
		fulladder_outputs[3][0] = true;
		fulladder_outputs[4][0] = false;
		fulladder_outputs[5][0] = true;
		fulladder_outputs[6][0] = true;
		fulladder_outputs[7][0] = true;
		
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
		System.out.println("[TEST] Inputs added.");
		c.Print();
		
		// make variables to make rest of circuits.
		int level_id = 0;
		boolean is_done = false;
		Vector<Vector<Node>> nodes = new Vector<Vector<Node>>();
		Vector<Node> start_node = new Vector<Node>();
		start_node.add(new Node(c));
		Node result = null;
		
		// start to build a circuit
//		start_node.get(0).noutput = 3; // for AND test
		
//		System.out.println("OUTS!!! " + sim.outputs[0].length);

		// checking number of outputs
		if (start_node.get(0).noutput < sim.outputs[0].length) {
			// add more outputs
			int diff = sim.outputs[0].length - start_node.get(0).noutput;
			Vector<Node> temp_level = new Vector<Node>();
			// Original code
//			Vector<Integer> objects = start_node.get(0).outputlines;
			
			// for AND test ONLY. START
			Vector<Object> objects = new Vector();
			objects.add(1);
			objects.add(2);
			objects.add(3);
			// for AND test ONLY. END
			
	        MultiCombinations mc = new MultiCombinations(objects, diff);
	        while (mc.hasMoreElements()) {
	        	temp_level.add(new Node(copyCircuit(start_node.get(0).circuit)));
	            for (int i = 0; i < mc.nextElement().length; i++) {
//	                System.out.print(Integer.parseInt(mc.nextElement()[i].toString()) + " ");
	            	Vector<Integer> v = new Vector<Integer>();
	            	v.add(Integer.parseInt(mc.nextElement()[i].toString()));
	        		temp_level.get(temp_level.size()-1).circuit.addGate(
	        				temp_level.get(temp_level.size()-1).circuit.genes.size()+1,
	        				"None", v);
	            }
	        }
	        nodes.add(temp_level);
		} else if (start_node.get(0).noutput > sim.outputs[0].length) {
			// reduce some outputs			
			int diff = start_node.get(0).noutput - sim.outputs[0].length;
			Vector<Node> temp_level = new Vector<Node>();
			
			// make combinations of AND, OR
			Vector<Object> objects = new Vector();
			objects.add("And");
			objects.add("Or");
	        MultiCombinations mc_gate = new MultiCombinations(objects, diff - 1);
	        
	        // make combinations of inputs
	        Vector<Vector<Integer>> temp_inputset = new Vector<Vector<Integer>>();
	        for (int i = 0; i < start_node.get(0).ninput - 1; i++) {
	        	Vector<Integer> temp_inputs = new Vector<Integer>();
	        	for (int j = i + 1; j < start_node.get(0).ninput; j ++) {
	        		temp_inputs.add(start_node.get(0).inputlines.get(i));
	        		temp_inputs.add(start_node.get(0).inputlines.get(j));
	        	}
	        	temp_inputset.add(temp_inputs);
	        }

	        // adding gates to reduce outputs
	        while (mc_gate.hasMoreElements()) {
	            for (int i = 0; i < mc_gate.nextElement().length; i++) {
	            	String gatetype = mc_gate.nextElement()[i].toString();
	            	for (int j = 0; j < temp_inputset.size(); j ++) {
	            		temp_level.add(new Node(copyCircuit(start_node.get(0).circuit)));
	            		temp_level.get(temp_level.size()-1).circuit.addGate(
	            				temp_level.get(temp_level.size()-1).circuit.genes.size()+1,
	            				gatetype, temp_inputset.get(j));
//	            		temp_level.get(temp_level.size()-1).noutput = 2;
	            	}
	            }
	        }
	        nodes.add(temp_level);
		} // checking number of outputs END
		
		System.out.println(nodes.get(nodes.size()-1).size());
		
		// adding other parts
		while (!is_done){
			// check one of the circuit fits or not
			for (int node_id = 0; node_id < nodes.get(level_id).size() && !is_done; node_id ++) {
				if (sim.simulate(nodes.get(level_id).get(node_id).circuit) != sim.inputs.length) {
					is_done = true;
					result = nodes.get(level_id).get(node_id);
					result.circuit.Print();
				} else {
					System.out.println("ELSE");
				}
			}
			
//			System.out.println("DAMN");
			
			// build next level
			if (!is_done){
				Vector<Node> temp_level = new Vector<Node>();
				for (int node_id = 0; node_id < nodes.get(level_id).size(); node_id ++) {
					Node original_node = nodes.get(level_id).get(node_id); 
					
					// adding NOT gates
					if (original_node.canUseNot()) {
						for (int i = 0; i < original_node.noutput; i++) {
							Node new_node = original_node;
							Vector<Integer> temp_inputs = new Vector();
							temp_inputs.add(new_node.circuit.getOutputLines().get(i));
							new_node.circuit.addGate(temp_inputs.get(0), "Not", temp_inputs);
//							new_node.nnot ++;
							temp_level.add(new_node);
						}
					}
					
					// make a list of possible combination
					Vector<Object> objects = new Vector();
					for (int i=0;i<original_node.outputlines.size(); i++) {
						objects.add(original_node.outputlines.get(i));
					}
			        MultiCombinations mc = new MultiCombinations(objects, 2);

			        // adding AND gates
			        while (mc.hasMoreElements()) {
			        	temp_level.add(new Node(copyCircuit(start_node.get(0).circuit)));
			        	Vector<Integer> v = new Vector<Integer>();
			            for (int i = 0; i < mc.nextElement().length; i++) {
//			                System.out.print(Integer.parseInt(mc.nextElement()[i].toString()) + " ");
			            	v.add(Integer.parseInt(mc.nextElement()[i].toString()));
			            }
			            temp_level.get(temp_level.size()-1).circuit.addGate(
		        				temp_level.get(temp_level.size()-1).circuit.genes.size()+1,
		        				"And", v);
			        }
			        
			        // adding OR gates
			        while (mc.hasMoreElements()) {
			        	temp_level.add(new Node(copyCircuit(start_node.get(0).circuit)));
			        	Vector<Integer> v = new Vector<Integer>();
			            for (int i = 0; i < mc.nextElement().length; i++) {
//			                System.out.print(Integer.parseInt(mc.nextElement()[i].toString()) + " ");
			            	v.add(Integer.parseInt(mc.nextElement()[i].toString()));
			            }
			            temp_level.get(temp_level.size()-1).circuit.addGate(
		        				temp_level.get(temp_level.size()-1).circuit.genes.size()+1,
		        				"Or", v);
			        }
				}
			}
		}
		System.out.println("DONE!");
		result.circuit.Print();
		
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
