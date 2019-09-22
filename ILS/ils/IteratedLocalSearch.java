package ils;

import build.SolutionBuilder;
import build.SolutionTransformer;
import ls.SolutionManager;
import structures.Result;
import structures.SubInstance;

/**
 *
 * @author Daniele Cuomo
 */
public class IteratedLocalSearch {
    private SolutionBuilder sb;
    private SolutionTransformer st;
    private SolutionManager sm;
    
    private long T;
    
    public Result solve(){
        SubInstance I = sb.construct();
        
        sm.setSolution(st.transform(I));
        int OPT = sm.length();
        long t = System.currentTimeMillis();        
        while(System.currentTimeMillis() - t < T){            
            sm.insertionPhase();
            OPT = Math.max(OPT, sm.length());
            sm.destructionPhase();
            //sm.swapPhase();
        }
        
        return new Result(OPT, T/1000f);
    }

    public IteratedLocalSearch setSolutionBuilder(SolutionBuilder sb){ this.sb = sb; return this; }
    public IteratedLocalSearch setSolutionManager(SolutionManager sm){ this.sm = sm; return this; }
    public IteratedLocalSearch setSolutionTransformer(SolutionTransformer st){ this.st = st; return this; }
    public IteratedLocalSearch setTimeLimit(long T){ this.T = T; return this; }
}
