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

}
