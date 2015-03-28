package logicga;

import java.util.Vector;

import utility.CombinatoricException;

public class MultiCombinationsTester {
	 
    public MultiCombinationsTester() throws CombinatoricException {
//        Object[] objects = {1, 2, 3};
    	Vector<Object> objects = new Vector();
    	objects.add(1);
    	objects.add(2);
    	objects.add(3);
        MultiCombinations mc = new MultiCombinations(objects, 3);
        while (mc.hasMoreElements()) {
            for (int i = 0; i < mc.nextElement().length; i++) {
                System.out.print(Integer.parseInt(mc.nextElement()[i].toString()) + " ");
            }
            System.out.println();
        }
    } // constructor
 
    public static void main(String[] args) throws CombinatoricException {
        new MultiCombinationsTester();
    }
} // class