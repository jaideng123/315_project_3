
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //tests for simulator
        Circuit test = new Circuit();
        Integer arr[] = new Integer[]{1};
        test.genes.add(new Gene(1,"NONE",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{2};
        test.genes.add(new Gene(2,"NONE",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{1};
        test.genes.add(new Gene(3,"NOT",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{2};
        test.genes.add(new Gene(4,"NOT",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{1,4};
        test.genes.add(new Gene(5,"AND",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{2,3};
        test.genes.add(new Gene(6,"AND",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{5,6};
        test.genes.add(new Gene(7,"OR",new Vector(Arrays.asList(arr))));
        Circuit test2 = new Circuit();
        arr = new Integer[]{1};
        test2.genes.add(new Gene(1,"NONE",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{2};
        test2.genes.add(new Gene(2,"NONE",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{1};
        test2.genes.add(new Gene(3,"AND",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{2};
        test2.genes.add(new Gene(4,"OR",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{1,4};
        test2.genes.add(new Gene(5,"AND",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{2,3};
        test2.genes.add(new Gene(6,"OR",new Vector(Arrays.asList(arr))));
        arr = new Integer[]{5,6};
        test2.genes.add(new Gene(7,"OR",new Vector(Arrays.asList(arr))));
       // Simulator s = new Simulator(new boolean[][]{{false,false},{false,true},{true,false},{true,true}},
        //        new boolean[][]{{false},{true},{true},{false}});
        //System.out.println(s.simulate(test));
       /* try{
        test.getFromFile(1);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }*/
        Circuit children[] = reproduce(test, test2);

    }
    //returns 2 offspring
    static Circuit[] reproduce(Circuit momma, Circuit papa){
        Circuit children[] = new Circuit[2];//prepare offspring
        int cuttingPoint = randInt(0,Math.min(momma.genes.size(), papa.genes.size()));
        //now kith
        if(momma.genes.size() <  papa.genes.size()) {
            for (int i = 0; i < cuttingPoint; i++) {
                children[0].genes.addElement(momma.genes.elementAt(i));
                children[1].genes.addElement(momma.genes.elementAt(i));
            }
            int j = 0;
            for (int i = cuttingPoint; i < momma.genes.size(); i++) {
                children[0].genes.addElement(papa.genes.elementAt(i));
                children[1].genes.add(j,papa.genes.elementAt(i));
                ++j;
            }
        }
        if (momma.genes.size() >  papa.genes.size()) {
            for (int i = 0; i < cuttingPoint; i++) {
                children[0].genes.addElement(papa.genes.elementAt(i));
                children[1].genes.addElement(papa.genes.elementAt(i));
            }
            int j = 0;
            for (int i = cuttingPoint; i < momma.genes.size(); i++) {
                children[0].genes.addElement(momma.genes.elementAt(i));
                children[1].genes.add(j,momma.genes.elementAt(i));
                ++j;
            }
        }
        return children;
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
