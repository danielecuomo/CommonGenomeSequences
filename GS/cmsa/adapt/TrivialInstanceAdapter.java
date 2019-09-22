package cmsa.adapt;

import structures.Solution;

import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;

/**
 *
 * @author Daniele Cuomo
 */
public class TrivialInstanceAdapter extends InstanceAdapter{
    @Override
    public void adapt(Solution S, Solution RF, float AB, short [][]age) {        
        this.adaptString(S, RF, age, AB, X);
        this.adaptString(S, RF, age, AB, Y);
    }
    
    private void adaptString(Solution S, Solution RF, short [][]age, float AB, short str){
        int []v = S.get(str);
        for(int i=0; i<v.length; i++){
            if(v[i] == -1) continue;
            
            if(RF.get(str, i) != -1)
                age[str][i] = 0;
            else if(++age[str][i] >= AB)
                S.set(str, i, -1);
        }
    }
}