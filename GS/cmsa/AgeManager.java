package cmsa;

import static structures.GlobalVariables.*;
import structures.Permutation;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class AgeManager {
    public void update(Solution S, Permutation pi, short [][]age){
        for(int a : alphabet){
                for(int sample : pi.getSample(X, a))
                    if(S.get(X, sample) == -1)
                        age[X][sample] = 0;
                
                for(int sample : pi.getSample(Y, a))
                    if(S.get(Y, sample) == -1)
                        age[Y][sample] = 0;
            }
    }
}