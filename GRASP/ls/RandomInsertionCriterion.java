package ls;

import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class RandomInsertionCriterion extends InsertionCriterion{
    private int [][][]map;
    
    @Override
    public boolean insert(Solution S, int i){
        S.swap(i, S.length());
        for(int j=0; j<S.length(); j++){
            if(!isValid(X, S, j) || !isValid(Y, S, j)) continue;
        
            for(int k=j; k<S.length(); k++) S.swap(k, S.length());
            S.increase();
            return true;
        }
        return false;
    }

    private boolean isValid(short str, Solution S, int j) {
        int lb = -1, k;
        
        for(int i=0; i<j; i++){
            for(k=0; k<map[str][S.get(i)].length; k++)
                if(map[str][S.get(i)][k] > lb){
                    lb = map[str][S.get(i)][k];
                    break;
                }
            if(k == map[str][S.get(i)].length) return false;
        }
        
        for(k=0; k<map[str][S.getCandidate()].length; k++)
            if(map[str][S.getCandidate()][k] > lb){
                    lb = map[str][S.getCandidate()][k];
                    break;
                }
        
        if(k == map[str][S.getCandidate()].length) return false;
        
        for(int i=j; i<S.length(); i++){
            for(k=0; k<map[str][S.get(i)].length; k++)
                if(map[str][S.get(i)][k] > lb){
                    lb = map[str][S.get(i)][k];
                    break;
                }
            if(k == map[str][S.get(i)].length) return false;
        }
        return true;
    }

    public RandomInsertionCriterion setAlphabetMap(int [][][]map){ this.map = map; return this; }
}