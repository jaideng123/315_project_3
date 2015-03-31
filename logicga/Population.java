package logicga;

import java.util.PriorityQueue;
import java.util.Vector;

/**
 *
 * @author Eric C C
 */
public class Population {
//    Circuit[] population;
    PriorityQueue<Circuit> population;
    public Population(){
        population = new PriorityQueue<Circuit>();
        
        for(int i =0 ; i <getSize() ; i++){
            //Mutate Circuit
            Circuit child = new Circuit();
            //child = reproduction();
        }
    }
    
    public Circuit peekTopCircuit(){
    	return population.peek();
    }
    public Circuit getTopCircuit(){
    	return population.poll();
    }
    
    public void add(Circuit a){
    	population.add(a);
    }
//    public Circuit reproduction() {
//        
//    }
    
    public int getSize(){
        return population.size();
    }
   
    
    
}
