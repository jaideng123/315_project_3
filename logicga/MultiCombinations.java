package logicga;

//import com.objectwave.utility.*;
import java.util.*;

import utility.Combinations;
import utility.Combinatoric;
import utility.CombinatoricException;
 
public class MultiCombinations {
 
    private HashSet<String> set = new HashSet<String>();
    private Combinations comb = null;
    private Object[] nextElem = null;
 
    public MultiCombinations(Vector<Object> objects, int k) throws CombinatoricException {
        k = Math.max(0, k);
        Object[] myObjects = new Object[objects.size() * k];
        for (int i = 0; i < objects.size(); i++) {
            for (int j = 0; j < k; j++) {
                myObjects[i * k + j] = objects.get(i);
            }
        }
        comb = new Combinations(myObjects, k);
    } // constructor
 
    public void print() {
		while (hasMoreElements()) {
			for (int i = 0; i < nextElement().length; i++) {
				System.out.print(Integer.parseInt(nextElement()[i].toString())
						+ " ");
			}
			System.out.println();
		}
    }
    
    boolean hasMoreElements() {
        boolean ret = false;
        nextElem = null;
        int oldCount = set.size();
        while (comb.hasMoreElements()) {
            Object[] elem = (Object[]) comb.nextElement();
            String str = "";
            for (int i = 0; i < elem.length; i++) {
                str += ("%" + elem[i].toString() + "~");
            }
            set.add(str);
            if (set.size() > oldCount) {
                nextElem = elem;
                ret = true;
                break;
            }
        }
        return ret;
    } // hasMoreElements()
 
    Object[] nextElement() {
        return nextElem;
    }

    static java.math.BigInteger c(int n, int k) throws CombinatoricException {
        return Combinatoric.c(n + k - 1, k);
    }
    
    static int intc(int n, int k) throws CombinatoricException {
        return Combinatoric.c(n + k - 1, k).intValue();
    }
    
    
} // class