package logicga;
import java.util.Vector;

/**
 * Created by Jaiden on 3/23/2015.
 * This class will be initialized with a desired truth table
 * then fed a circuit to evalulate
 * it will test all combinations of input and return an integer
 * the integer represents how many lines of the truth table the best output
 * of the circuit lined up with
 * a 0 will be returned if a circuit contains too many or too few inputs
 */

public class Simulator {
    boolean[][] inputs;
    boolean[][] outputs;
    //desired inputs and corresponding outputs for truth table
    public Simulator(boolean[][] in,boolean[][] out ){
        if(in.length != out.length){
            throw new IllegalArgumentException("Truth table inputs and outputs do not match");
        }
        inputs = in;
        outputs = out;
    }

    public int simulate(Circuit circ){
        Vector<int[]> results = new Vector<int[]>();
        //run with all possible input combinations
        for (int i = 0; i < inputs.length; i++) {
            results.addElement(test(circ,i));
            if(results.elementAt(i).length == 0)
                return 0;
        }
        boolean matched[] = new boolean[outputs[0].length];
        int num_found[] = new int[outputs[0].length];

        java.util.Arrays.fill(matched,false);
        //look at each column of results
        for (int i = 0; i < circ.genes.size()*2; i++) {
            //check each output value we're looking for
            for (int j = 0; j < outputs[0].length; j++) {
                if(!matched[j]) {
                    boolean is_match = true;
                    int match_num = outputs.length;
                    for (int k = 0; k < results.size(); k++) {
                        if (results.elementAt(k)[i] != (outputs[k][j] ? 1 : 0)) {
                            //mismatch found
                            is_match = false;
                            match_num--;
                        }
                    }
                    matched[j] = is_match;
                    if(num_found[j] < match_num)
                        num_found[j] = match_num;
                }
            }
        }
        int sum = 0;
        for(int i : num_found){
            sum += i;
        }
        return sum;
    }
    private int[] test(Circuit circ, int input){
        int values[] = new int[circ.genes.size()*2];
        java.util.Arrays.fill(values,-1);
        int num_inputs = 0;
        for (int i = 0; i< circ.genes.size();++i) {
            Gene current = circ.genes.elementAt(i);
            //fill in missing inputs from truth table
            for (int j = 0; j < current.inputs.size(); ++j) {
                if (values[current.inputs.elementAt(j)] == -1) {
                    if (num_inputs+1 > inputs[0].length) {
                        return new int[]{};
                    }
                    values[current.inputs.elementAt(j)] = inputs[input][num_inputs] ? 1 : 0;
                    num_inputs++;
                }
            }
            //match with gate type
            if (current.type == "None") {
                values[current.outputNum] = values[current.inputs.firstElement()];
            }
            else if (current.type == "And") {
                int result = 1;
                for(int inp : current.inputs){
                    if(values[inp] == 0)
                        result = 0;
                }
                values[current.outputNum] = result;
            }
            else if (current.type == "Or") {
                int result = 0;
                for(int inp : current.inputs){
                    if(values[inp] == 1)
                        result = 1;
                }
                values[current.outputNum] = result;
            }
            else if (current.type == "Not") {
                if (values[current.inputs.firstElement()] == 1)
                    values[current.outputNum] = 0;
                else
                    values[current.outputNum] = 1;
            }
            else
                System.out.println("Bad Gate");
        }
        if (num_inputs != inputs[0].length) {
            return new int[]{};
        }
        return values;
    }

}
