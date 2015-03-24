package logicga;

import java.util.Vector;

/**
 *
 * @author Eric C C
 */
public class Population {
    Circuit[] population;
    
    public Population(int populationSize){
        population = new Circuit[populationSize];
        
        for(int i =0 ; i <getSize() ; i++){
            //Mutate Circuit
            Circuit child = new Circuit();
            //child = reproduction();
        }
    }
    
    
    
//    public Circuit reproduction() {
//        
//    }
    
    public int getSize(){
        return population.length;
    }
   
    
    
}
