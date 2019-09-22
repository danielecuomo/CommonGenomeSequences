package main;

import blum.constructor.Constructor;
import interfaces.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import structures.Instance;
import structures.Parameters;
import structures.Result;

/**
 *
 * @author Daniele Cuomo
 */
public class ControlFlow{
    
    private final Constructor procedure;
   
    
    public ControlFlow(Instance I, Parameters P) throws FileNotFoundException{
        P.setTimeLimit(this.getTimeLimit(I));
        ProcedureFactory pf = new ProcedureFactory(I, P);
        
        procedure = pf.makeConstructor();
    }

    public static Result solveInstances(Instance []instances, Parameters P) throws FileNotFoundException{
        float SM = 0, SD = 0; double T = 0;
        Result R;

        for(Instance I : instances){
            ControlFlow cf = new ControlFlow(I, P);
            R = cf.solveInstance();
            SM += R.getObjectiveFunction();
            SD += (float)(Math.pow(R.getObjectiveFunction(), 2));
            T += R.getElapsedTime();
            System.out.println(R.getObjectiveFunction());
        }
        SM /= instances.length;
        SD = (float)(Math.sqrt((float)(1f/(instances.length-1f))*(SD - instances.length*SM*SM)));
        return new Result(SM , SD, (float) T / instances.length);
    }
    
    public Result solveInstance(){ return procedure.solve(); }
    
    private static final int []dims = {32, 64, 128, 256, 512, 1024, 2048, 4096};
    private static final int []reps = {3, 4, 5, 6, 7, 8};
    public static void execDims(Parameters P, String path, int d) throws IOException{
        Result R;
        Parser p = new Parser();
        try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            for(int dim : dims){
                R = solveInstances(p.getRandomInstances(dim, d), P);
                writer.println(dim + "\t" + R);
            }
        }
    }
    public static void execDims(Parameters P, String path, int n, int d) throws IOException{
        Result R;
        Parser p = new Parser();
        try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            for(int dim : dims){
                R = solveInstances(p.getRandomInstances(dim, n, d), P);
                writer.println(dim + "\t" + R);
            }
        }
    }
    public static void execReps(Parameters P, String path, int alphabet) throws IOException{
        Result R;
        Parser p = new Parser();
        try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            for(int r : reps){
                R = solveInstances(p.getRepsBoundedInstances(alphabet, r), P);
                writer.println(r + "\t" + R);
            }
        }
    }

    public static void massiveExec(Parameters P) throws IOException{
        execDims(P, "./results/n-div-8.txt", 8);
        execDims(P, "./results/n-div-4.txt", 4);
        execDims(P, "./results/n-div-2.txt", 2);
        
        execDims(P, "./results/3n-div-4.txt", 3, 4);
        execDims(P, "./results/3n-div-8.txt", 3, 8);
        execDims(P, "./results/5n-div-8.txt", 5, 8);
        execDims(P, "./results/7n-div-8.txt", 7, 8);
        
        execReps(P, "./results/4reps.txt", 4);
        execReps(P, "./results/8reps.txt", 8);
        execReps(P, "./results/16reps.txt", 16);
        execReps(P, "./results/32reps.txt", 32);
        execReps(P, "./results/64reps.txt", 64);
        execReps(P, "./results/128reps.txt", 128);
        execReps(P, "./results/256reps.txt", 256);
        execReps(P, "./results/512reps.txt", 512);
    }
    
    private long getTimeLimit(Instance I) {
        return 2*(I.getX().length + I.getY().length - I.getAlphabetEncoding());
    }
}