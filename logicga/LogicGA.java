
package logicga;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;

//Genetic Algorithm
public class LogicGA {
    static int POP_SIZE = 1000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Set up Truth tables for different circuits
        boolean[][] fulladder_inputs = new boolean[8][3];
        fulladder_inputs[0][0] = false;
        fulladder_inputs[0][1] = false;
        fulladder_inputs[0][2] = false;
        fulladder_inputs[1][0] = false;
        fulladder_inputs[1][1] = false;
        fulladder_inputs[1][2] = true;
        fulladder_inputs[2][0] = false;
        fulladder_inputs[2][1] = true;
        fulladder_inputs[2][2] = false;
        fulladder_inputs[3][0] = false;
        fulladder_inputs[3][1] = true;
        fulladder_inputs[3][2] = true;
        fulladder_inputs[4][0] = true;
        fulladder_inputs[4][1] = false;
        fulladder_inputs[4][2] = false;
        fulladder_inputs[5][0] = true;
        fulladder_inputs[5][1] = false;
        fulladder_inputs[5][2] = true;
        fulladder_inputs[6][0] = true;
        fulladder_inputs[6][1] = true;
        fulladder_inputs[6][2] = false;
        fulladder_inputs[7][0] = true;
        fulladder_inputs[7][1] = true;
        fulladder_inputs[7][2] = true;

        boolean[][] fulladder_outputs = new boolean[8][2];
        fulladder_outputs[0][0] = false;
        fulladder_outputs[0][1] = false;
        fulladder_outputs[1][0] = false;
        fulladder_outputs[1][1] = true;
        fulladder_outputs[2][0] = false;
        fulladder_outputs[2][1] = true;
        fulladder_outputs[3][0] = true;
        fulladder_outputs[3][1] = false;
        fulladder_outputs[4][0] = false;
        fulladder_outputs[4][1] = true;
        fulladder_outputs[5][0] = true;
        fulladder_outputs[5][1] = false;
        fulladder_outputs[6][0] = true;
        fulladder_outputs[6][1] = false;
        fulladder_outputs[7][0] = true;
        fulladder_outputs[7][1] = true;

        // inverter
        boolean[][] inverse_inputs = new boolean[8][3];
        inverse_inputs[0][0] = false;
        inverse_inputs[0][1] = false;
        inverse_inputs[0][2] = false;
        inverse_inputs[1][0] = false;
        inverse_inputs[1][1] = false;
        inverse_inputs[1][2] = true;
        inverse_inputs[2][0] = false;
        inverse_inputs[2][1] = true;
        inverse_inputs[2][2] = false;
        inverse_inputs[3][0] = false;
        inverse_inputs[3][1] = true;
        inverse_inputs[3][2] = true;
        inverse_inputs[4][0] = true;
        inverse_inputs[4][1] = false;
        inverse_inputs[4][2] = false;
        inverse_inputs[5][0] = true;
        inverse_inputs[5][1] = false;
        inverse_inputs[5][2] = true;
        inverse_inputs[6][0] = true;
        inverse_inputs[6][1] = true;
        inverse_inputs[6][2] = false;
        inverse_inputs[7][0] = true;
        inverse_inputs[7][1] = true;
        inverse_inputs[7][2] = true;

        boolean[][] inverse_outputs = new boolean[8][3];
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 3; j ++) {
                inverse_outputs[i][j] = !inverse_inputs[i][j];
            }
        }

        // one AND circuit
        boolean[][] and_inputs = new boolean [4][2];
        and_inputs[0][0] = false;
        and_inputs[0][1] = false;
        and_inputs[1][0] = false;
        and_inputs[1][1] = true;
        and_inputs[2][0] = true;
        and_inputs[2][1] = false;
        and_inputs[3][0] = true;
        and_inputs[3][1] = true;

        boolean[][] and_outputs = new boolean [4][1];
        and_outputs[0][0] = false;
        and_outputs[1][0] = false;
        and_outputs[2][0] = false;
        and_outputs[3][0] = true;

        // one OR circuit
        boolean[][] or_inputs = new boolean [4][2];
        or_inputs[0][0] = false;
        or_inputs[0][1] = false;
        or_inputs[1][0] = false;
        or_inputs[1][1] = true;
        or_inputs[2][0] = true;
        or_inputs[2][1] = false;
        or_inputs[3][0] = true;
        or_inputs[3][1] = true;

        boolean[][] or_outputs = new boolean [4][1];
        or_outputs[0][0] = false;
        or_outputs[1][0] = true;
        or_outputs[2][0] = true;
        or_outputs[3][0] = true;

        // one NAND circuit
        boolean[][] nand_inputs = new boolean [4][2];
        nand_inputs[0][0] = false;
        nand_inputs[0][1] = false;
        nand_inputs[1][0] = false;
        nand_inputs[1][1] = true;
        nand_inputs[2][0] = true;
        nand_inputs[2][1] = false;
        nand_inputs[3][0] = true;
        nand_inputs[3][1] = true;

        boolean[][] nand_outputs = new boolean [4][1];
        nand_outputs[0][0] = true;
        nand_outputs[1][0] = true;
        nand_outputs[2][0] = true;
        nand_outputs[3][0] = false;

        // one OR circuit
        boolean[][] nor_inputs = new boolean [4][2];
        nor_inputs[0][0] = false;
        nor_inputs[0][1] = false;
        nor_inputs[1][0] = false;
        nor_inputs[1][1] = true;
        nor_inputs[2][0] = true;
        nor_inputs[2][1] = false;
        nor_inputs[3][0] = true;
        nor_inputs[3][1] = true;

        boolean[][] nor_outputs = new boolean [4][1];
        nor_outputs[0][0] = true;
        nor_outputs[1][0] = false;
        nor_outputs[2][0] = false;
        nor_outputs[3][0] = false;


        // one XOR circuit
        boolean[][] custom_inputs = new boolean [4][2];
        custom_inputs[0][0] = false;
        custom_inputs[0][1] = false;
        custom_inputs[1][0] = false;
        custom_inputs[1][1] = true;
        custom_inputs[2][0] = true;
        custom_inputs[2][1] = false;
        custom_inputs[3][0] = true;
        custom_inputs[3][1] = true;

        boolean[][] custom_outputs = new boolean [4][1];
        custom_outputs[0][0] = true;
        custom_outputs[1][0] = false;
        custom_outputs[2][0] = true;
        custom_outputs[3][0] = true;

        // menu
        System.out.println("1. Full adder circuit");
        System.out.println("2. Inverse circuit");
        System.out.println("3. one AND circuit");
        System.out.println("4. one OR circuit");
        System.out.println("5. one NAND circuit");
        System.out.println("6. one NOR circuit");
        System.out.println("7. one XOR circuit");

        System.out.print("Choose one: ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        GraphFrame graph = new GraphFrame("Genetic Algorithm","Number of Runs","Lowest Fitness Value");

        Simulator sim = null;

        switch(num) {
            case 1:
                sim = new Simulator(fulladder_inputs, fulladder_outputs);
                break;
            case 2:
                sim = new Simulator(inverse_inputs, inverse_outputs);
                break;
            case 3:
                sim = new Simulator(and_inputs, and_outputs);
                break;
            case 4:
                sim = new Simulator(or_inputs, or_outputs);
                break;
            case 5:
                sim = new Simulator(nand_inputs, nand_outputs);
                break;
            case 6:
                sim = new Simulator(nor_inputs, nor_outputs);
                break;
            case 7:
                sim = new Simulator(custom_inputs, custom_outputs);
                break;
        }
        Population p = initialPopulation(sim.inputs[0].length,POP_SIZE,sim,num);
        boolean solutionFound = false;
        double percent = .99;
        int cutoff = (int) (POP_SIZE * percent);
        int last_result = 0;
        int current_result;
        int numRuns = 0;
        int totalRuns = 0;
        while (!solutionFound){
            if(randInt(1,1000) == 1 && numRuns > 1000) {//Doomsday
                System.out.println("\nCountry road take me home!");
                p = select(p, (int) (POP_SIZE * .05));
                numRuns = 0;
            }
            else
                p = select(p,cutoff);
            //Repopulate ( ͡° ͜ʖ ͡°)
            while(p.getSize()  < POP_SIZE){
                int r = randInt(1,4);
                //reproduce from existing circuits
                if(r == 1) {
                    int mama = randInt(0, p.getSize() - 1);
                    int papa = randInt(0, p.getSize() - 1);
                    Circuit c1 = (Circuit) p.population.toArray()[mama];
                    Circuit c2 = (Circuit) p.population.toArray()[papa];
                    Circuit offspring[] = reproduce(c1, c2);
                    offspring[0].simulated = false;
                    offspring[0].calculateFitness(sim,num);
                    offspring[1].simulated = false;
                    offspring[1].calculateFitness(sim,num);
                    p.add(offspring[0]);
                    p.add(offspring[1]);
                    //p.setBounds();
                }
                //Introduce new random circuit
                else if(r == 2) {
                    Circuit c = randomCircuit(sim.inputs[0].length,sim,num);
                    c.simulated = false;
                    c.calculateFitness(sim,num);
                    p.add(c);
                    // p.setBounds();
                }
                //repopulate with top 10%
                else if(r==3){
                    int mama = randInt(0, (int)(p.getSize()*.2));
                    int papa = randInt(0, p.getSize() - 1);
                    Circuit c1 = (Circuit) p.population.toArray()[mama];
                    Circuit c2 = (Circuit) p.population.toArray()[papa];
                    Circuit offspring[] = reproduce(c1, c2);
                    offspring[0].simulated = false;
                    offspring[0].calculateFitness(sim,num);
                    offspring[1].simulated = false;
                    offspring[1].calculateFitness(sim,num);
                    p.add(offspring[0]);
                    p.add(offspring[1]);
                    //p.setBounds();
                }
                else if(r==4){
                    int mama = 0;
                    int papa = p.getSize()-1;
                    Circuit c1 = (Circuit) p.population.toArray()[mama];
                    Circuit c2 = (Circuit) p.population.toArray()[papa];
                    Circuit offspring[] = reproduce(c1, c2);
                    offspring[0].simulated = false;
                    offspring[0].calculateFitness(sim,num);
                    offspring[1].simulated = false;
                    offspring[1].calculateFitness(sim,num);
                    p.add(offspring[0]);
                    p.add(offspring[1]);
                }
            }
            //Mutate a random number of times
            int numMutations = randInt(0,POP_SIZE)*(int)(1/percent)/20;
            for (int i = 0; i <numMutations; i++) {
                int target = randInt(1,p.getSize()-1);
                Circuit c = (Circuit)p.population.toArray()[target];
                p.population.remove(c);
                c.mutate();
                c.calculateFitness(sim,num);
                p.add(c);
            }
            //recalculate cutoff and update i/o
            if(percent > 0.20)
                percent -= 0.01;
            cutoff = (int) (POP_SIZE * percent);
            current_result = p.peekTopCircuit().calculateFitness(sim,num);
            if(last_result != current_result){
                last_result = current_result;
                System.out.println("");
                System.out.println(current_result);
                graph.drawGraph(totalRuns,current_result);
                numRuns = 0;
            }
            //check if we've found the correct circuit
            if(p.peekTopCircuit().testCircuit(sim) == sim.outputs.length * sim.outputs[0].length &&
                    p.peekTopCircuit().calculateNots() <= 2){
                System.out.println("Circuit Found!");
                p.peekTopCircuit().Print();
                solutionFound = true;
            }
            if(numRuns % 50 == 0) {
                System.out.print("*");
            }
            if(numRuns % 200 == 0) {
                graph.drawGraph(totalRuns,current_result);
            }
            numRuns++;
            totalRuns++;
        }
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
        int portion = cutoff;
        for(int i = 0;i<portion/2;i++){
            newPop.add(oldPop.getTopCircuit());
        }
        for(int i = 0;i<portion/2;i++){
            int target = randInt(1,oldPop.getSize()-1);
            newPop.add((Circuit)oldPop.population.toArray()[target]);
        }
        oldPop.population.clear();

        return newPop;
    }
    public static int randInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    public static Population initialPopulation(int inputs, int size, Simulator s,int num){
        Population initial = new Population();
        for (int j = 0; j < size; j++) {
            //set up base for circuit
            Circuit c = randomCircuit(inputs,s,num);
            initial.add(c);
        }
        return initial;
    }
    public static Circuit randomCircuit(int inputs,Simulator s,int num){
        //set up base for circuit
        Circuit c = new Circuit();
        for (int i = 1; i < inputs+1; i++) {
            Vector<Integer> in = new Vector<Integer>();
            in.addElement(i);
            c.genes.add(new Gene(i, "None", in));
        }
        int r = randInt(1,inputs*5);
        for (int i = 0; i < r; i++) {
            Gene g = randomGate(c);
            if(g.type == "Not")
                c.numNots++;
            c.genes.add(g);
        }
        c.calculateFitness(s,num);
        return c;
    }
    public static Gene randomGate(Circuit c){
        int r = randInt(1,10);
        Vector<Integer> in = new Vector<Integer>();
        Gene g = new Gene(c.genes.size()+1,"None",in);
        if(r <= 4){
            g.type = "And";
            g.inputs.add(randInt(1,c.genes.size()));
            g.inputs.add(randInt(1, c.genes.size()));
        }
        else if(r <= 8){
            g.type = "Or";
            g.inputs.add(randInt(1,c.genes.size()));
            g.inputs.add(randInt(1, c.genes.size()));
        }
        else if(r <= 10){
            g.type = "Not";
            g.inputs.add(randInt(1,c.genes.size()));
        }
        return g;
    }
}
