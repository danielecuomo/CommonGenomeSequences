package ls;

import randomness.RandomManager;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class RandomDestructionCriterion extends DestructionCriterion{
    private RandomManager rm;
    
    @Override
    public void destroy(Solution S){
        int i = rm.nextInt(0, S.length());
        S.decrease();
        for(int j=i; j<S.length(); j++)
            S.swap(j, j+1);
    }  

    public RandomDestructionCriterion setRandomManager(RandomManager rm){ this.rm = rm; return this; }
}