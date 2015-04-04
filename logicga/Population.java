package logicga;

import java.util.Arrays;
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
    public Circuit getTopCircuit(){return population.poll();}
    
    public int highestFitness(){
    	return population.peek().fitness;
    }
    public int lowestFitness(){
    	Circuit [] newArray = new Circuit[population.size()];
    	population.toArray(newArray);
    	Arrays.sort(newArray);
    	return newArray[newArray.length-1].fitness;
    }
    //determines quality of population
    public double getQuality(){
    	int fmax = highestFitness();
    	int fmin = lowestFitness();
    	
    	double q = fmin/(Math.sqrt(Math.pow(fmax, 2)+Math.pow(fmin, 2)));
    	
    	return q;
    	
    }
    public double getStdev(){
    	Circuit [] newArray = new Circuit[population.size()];
    	population.toArray(newArray);
    	double mean = Mean(newArray);
    	double s = 0;
    	for(int i = 0; i < newArray.length; i++){
    		   s = Math.pow((newArray[i].fitness-mean),2);
    		}
    	return  Math.sqrt(s/(newArray.length -1));
    	
    }
    public double Mean(Circuit[] a){
    	
    	double m = 0.0;
    	long sum = 0;
    	for(int i = 0;i<a.length;i++){
    		sum += a[i].fitness;
    	}
    	m = sum/a.length;
    	return m;
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
