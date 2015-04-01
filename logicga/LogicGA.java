
package logicga;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
import java.lang.Math;

/**
 *
 * @author Eric C C
 */
public class LogicGA {
    static int POP_SIZE = 1000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      

    }
    //returns 2 offspring
    static Circuit[] reproduce(Circuit momma, Circuit papa){
        Circuit children[] = new Circuit[2];//prepare offspring
        children[0] = new Circuit();
        children[1] = new Circuit();
        int cuttingPoint = randInt(0,Math.min(momma.genes.size(), papa.genes.size()));
        //now kith
        for (int i = 0; i < cuttingPoint; i++) {
            children[0].genes.addElement(papa.genes.elementAt(i));
            children[1].genes.addElement(momma.genes.elementAt(i));
        }
        for (int i = cuttingPoint; i < papa.genes.size(); i++) {
            children[1].genes.addElement(papa.genes.elementAt(i));
        }
        for (int i = cuttingPoint; i < momma.genes.size(); i++) {
            children[0].genes.addElement(momma.genes.elementAt(i));
        }
        return children;
    }
     public static Population select(Population oldPop,int cutoff){
    	Population newPop = new Population();
    	int portion = POP_SIZE * (cutoff/100);
    	
    	for(int i = 0;i<portion;i++){
    		newPop.add(oldPop.getTopCircuit());
    	}
    	oldPop.population.clear();
    	
    	return newPop;
    }
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
}
