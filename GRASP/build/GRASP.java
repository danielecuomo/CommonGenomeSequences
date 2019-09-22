package build;

import ls.SolutionManager;
import structures.Result;
import structures.SubInstance;

/**
 *
 * @author Daniele Cuomo
 */
public class GRASP{
    private SolutionBuilder sb;
    private SolutionTransformer st;
    private SolutionManager sm;
    
    private long T;
    
    public Result solve(){
        int OPT = 0;
        long t = System.currentTimeMillis();
        while(System.currentTimeMillis() - t < T){      
            SubInstance I = sb.construct();
            sm.setSolution(st.transform(I));
            
            sm.insertionPhase();
            OPT = Math.max(OPT, sm.length()); 
            //break;
        }  
        
        return new Result(OPT, (float)T/1000);
    }
    
    public GRASP setSolutionBuilder(SolutionBuilder sb){ this.sb = sb; return this; }
    public GRASP setSolutionManager(SolutionManager sm){ this.sm = sm; return this; }
    public GRASP setSolutionTransformer(SolutionTransformer st){ this.st = st; return this; }
    public GRASP setTimeLimit(long T){ this.T = T; return this; }
}