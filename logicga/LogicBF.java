package logicga;

import java.io.IOException;
/**
*
* @author Si Jine Roh
*/
public class LogicBF {
	
	/**
	 * 
	 * @param args
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) {
		System.out.println("Logic Non-GA, Brute Force START");
        Circuit test = new Circuit();
        try{
        test.getFromFile(1);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        test.Print();
        System.out.println("Logic Non-GA, Brute Force END");
	}

}
