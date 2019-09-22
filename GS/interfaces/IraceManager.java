package interfaces;

import structures.Result;

/**
 *
 * @author Daniele Cuomo
 */
public class IraceManager{
    private final String []args;
    
    public IraceManager(String []args){ this.args = args; }
    
    public long getSeed(){ return Long.parseLong(args[2]); }
    public String getInstancePath(){ return args[3]; }
    public float getAgeBound(){ return Float.parseFloat(args[search("--ab")]); }
    //public float getEpsilon(){ return Float.parseFloat(args[search("--eps")]); }
    public String getBias(){ return args[search("--bias")]; }
    public String getDistribution(){ return args[search("--dist")]; }
    public float []getSteps(){ 
        float []steps = new float[4];
        for(int i = search("--step0"), j=0; j<steps.length; i+=2, j++)
            steps[j] = Float.parseFloat(args[i]);
        return steps;
    }
    
    private int search(String param){
        for(int i=0; i<args.length-1; i++)
            if(args[i].equals(param)) return i+1;
        throw new RuntimeException(param + " doesn't exist");
    }
    
    public void output(Result R){ 
        System.out.println(-R.getObjectiveFunction()/* + " " + R.getElapsedTime()*/); 
    }
}