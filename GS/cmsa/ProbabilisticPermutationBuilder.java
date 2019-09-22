package cmsa;

import randomness.RandomManager;
import structures.LogicalList;
import structures.Permutation;

import static structures.GlobalVariables.*;

/**
 *
 * @author Daniele Cuomo
 */
public class ProbabilisticPermutationBuilder extends PermutationBuilder{    
    private RandomManager rm;
    private LogicalList []lists;    
    private Permutation pi;
    
    @Override
    public Permutation construct(float []tau){
        int []i, j;
        for(int a : alphabet){
            i = rm.sample(X, a, (int)Math.ceil(tau[a]*lists[a].length(X)));
            j = rm.sample(Y, a, (int)Math.ceil(tau[a]*lists[a].length(Y)));
            pi.setSample(X, a, i);
            pi.setSample(Y, a, j);
        }
        
        return pi;
    }
    
    public ProbabilisticPermutationBuilder setRandomManager(RandomManager rm){ this.rm = rm; return this; }
    public ProbabilisticPermutationBuilder setLists(LogicalList []lists){ this.lists = lists; return this; }
    public ProbabilisticPermutationBuilder setPermutation(Permutation pi){ this.pi = pi; return this;}      
}