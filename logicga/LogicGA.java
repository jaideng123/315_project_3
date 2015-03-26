
package logicga;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

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
        Simulator s = new Simulator(new boolean[][]{{false,false},{false,true},{true,false},{true,true}},
                new boolean[][]{{false},{true},{true},{false}});
        System.out.println(s.simulate(test));
       /* try{
        test.getFromFile(1);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }*/
        test.Print();
    }
    
}
