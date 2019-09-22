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
    public String getBias(){ return args[search("--bias")]; }
    public String getDistribution(){ return args[search("--dist")]; }
    public float getDeleteFactor(){ return Float.parseFloat(args[search("--dfac")]); }
    
    private int search(String param){
        for(int i=0; i<args.length-1; i++)
            if(args[i].equals(param)) return i+1;
        throw new RuntimeException(param + " doesn't exist");
    }
    
    public void output(Result R){ 
        System.out.println(-R.getObjectiveFunction()/* + " " + R.getElapsedTime()*/); 
    }
}