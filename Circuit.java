/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicga;

import java.io.*;
import java.util.Vector;
import java.util.Scanner;
/**
 *
 * @author Eric C C
 */
public class Circuit {
    Vector<Gene> genes = new Vector<Gene>();
    String aFile;
    
    public Circuit(){
        genes = new Vector();
        
    }
    
    public void getFromFile(int populationIndex)throws IOException{
        //Fix for generic case
        // path should be change.
        String path = "C:\\Users\\Eric C C\\Documents\\NetBeansProjects\\LogicGA\\src\\logicga\\Circuit0.txt" ;
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        Vector inputGenes = new Vector();
        String line;
        while( (line = textReader.readLine()) != null){
            inputGenes.add(line);
            Scanner s = new Scanner(line);
            int outputNum = 0 ;
            String type = null;
            Vector inputs = new Vector();
            
            //Parsing each line for INT STRING [INT(S)]
            if(s.hasNextInt()){
                outputNum = s.nextInt();
                if(s.hasNext()){
                    type = s.next();
                    while(s.hasNextInt()){
                        inputs.add(s.next());
                   }
               }
           }
            //The genes will be stored in the order they appear in text
           genes.add(new Gene(outputNum,type,inputs));
       }  
    }
    
    public void Print(){
        for(int i =0 ; i < genes.size() ;i++ ){
            genes.elementAt(i).Print();
        }
    }
}
