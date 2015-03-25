package logicga;

import java.io.IOException;

public class LogicBF {

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
