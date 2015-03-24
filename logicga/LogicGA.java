
package logicga;

import java.io.IOException;

/**
 *
 * @author Eric C C
 */
public class LogicGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Circuit test = new Circuit();
        try{
        test.getFromFile(1);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        test.Print();
    }
    
}
