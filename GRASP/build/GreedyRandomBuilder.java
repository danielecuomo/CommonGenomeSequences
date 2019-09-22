package build;

import randomness.RandomManager;
import static structures.GlobalVariables.*;
import structures.LogicalList;
import structures.SubInstance;

/**
 *
 * @author Daniele Cuomo
 */
public class GreedyRandomBuilder extends SolutionBuilder{
    private LogicalList []lists;
    private RandomManager rm;
    private LongestCommonSubsequence lcs;
    
    private final SubInstance S;
    
    public GreedyRandomBuilder(int lx, int ly){ S = new SubInstance(lx, ly); }
    
    @Override
    public SubInstance construct() {
        S.init();
        
        short str; int i;
        for(int a : alphabet){
            //if(lists[a].getIndices(X).length <= lists[a].getIndices(Y).length)
            //    str = X;
            //else 
            //    str = Y;
            str = (short)Math.round(Math.random());
            
            i = rm.sample(str, a);
            S.set(str, i, a);
            
            for(int j : lists[a].getIndices((short)(1 - str)))
                S.set((short)(1 - str), j, a);
        }
        
        return lcs.getLCS(S);
    }
    
    public GreedyRandomBuilder setLists(LogicalList []lists){ this.lists = lists; return this; }
    public GreedyRandomBuilder setRandomManager(RandomManager rm){ this.rm = rm; return this; }
    public GreedyRandomBuilder setLongestCommonSubsequence(LongestCommonSubsequence lcs){ this.lcs = lcs; return this; }
}