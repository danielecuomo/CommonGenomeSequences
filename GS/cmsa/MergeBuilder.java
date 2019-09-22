package cmsa;

import structures.Permutation;
import structures.Solution;

import static structures.GlobalVariables.*;

/**
 *
 * @author Daniele Cuomo
 */
public class MergeBuilder {
    public void merge(Solution S, Permutation pi){
        for(int a : alphabet){
            for(int sample : pi.getSample(X, a))
                S.set(X, sample, a);
            for(int sample : pi.getSample(Y, a))
                S.set(Y, sample, a);
        }
    }
}