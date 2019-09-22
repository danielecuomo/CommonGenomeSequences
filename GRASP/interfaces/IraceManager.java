package interfaces;

import structures.Result;

/**
 *
 * @author Daniele Cuomo
 */
public class IraceManager{
    private final String []args;
    
    public IraceManager(String []args){ this.args = args; }
    
    public String getInstancePath(){ return args[3]; }
    public long getSeed(){ return Long.parseLong(args[2]); }
    public double getDeleteFactor(){ return Double.parseDouble(args[5]); }
    public String getDistribution(){ return args[7]; }
    public String getBias(){ return args[9]; }

    public void output(Result R){ 
        System.out.println(-R.getObjectiveFunction()); 
    }
    
    public void outputMinTime(Result R){ 
        System.out.println(-R.getObjectiveFunction() + " " + R.getElapsedTime()); 
    }
}
