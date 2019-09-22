package ls;

import randomness.RandomManager;
import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class RandomSwapCriterion extends SwapCriterion{
    private RandomManager rm;
    private int [][][]map;
    
    @Override
    public void swap(Solution S){
        int pos1 = rm.nextInt(0, S.length());
        int pos2 = rm.nextInt(0, S.length());

        S.swap(pos1, pos2);
        if(!isValid(X, S) || !isValid(Y, S))
            S.swap(pos1, pos2);
    }

    private boolean isValid(short str, Solution S) {   
        int k;
        int lb = -1;
        for(int i=0; i<S.length(); i++){
            for(k=0; k<map[str][S.get(i)].length; k++)
                if(map[str][S.get(i)][k] > lb){
                    lb = map[str][S.get(i)][k];
                    break;
                }
            if(k == map[str][S.get(i)].length) return false;
        }
        return true;
    }
    
    public RandomSwapCriterion setRandomManager(RandomManager rm){ this.rm = rm; return this; }
    public RandomSwapCriterion setAlphabetMap(int [][][]map){ this.map = map; return this; }    
}
