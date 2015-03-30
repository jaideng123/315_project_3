package logicga;

import java.util.Vector;

/**
 *
 * @author Eric C C
 */
public class Population {
    Circuit[] population;
    //    Priority_Queue<Circuit> population;
    public Population(int populationSize){
        population = new Circuit[populationSize];
        
        for(int i =0 ; i <getSize() ; i++){
            //Mutate Circuit
            Circuit child = new Circuit();
            //child = reproduction();
        }
    }
    //gets Circuit at index
     public Circuit getAt(int index){
    	return population[index];
    }
    //sets a Circuit at index
    public void setAt(int i, Circuit a){
    	population[i] = a;
    }
    
//    public Circuit reproduction() {
//        
//    }
    
    public int getSize(){
        return population.length;
    }
   
    
    
}
