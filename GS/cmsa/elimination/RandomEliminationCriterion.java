package cmsa.elimination;

import java.util.Arrays;
import java.util.Random;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class RandomEliminationCriterion extends EliminationCriterion{
    private final Random r = new Random();
    private final int []reps;
    private final int []curr;
    
    public RandomEliminationCriterion(int alphabetEncoding){
        reps = new int[alphabetEncoding];
        curr = new int[alphabetEncoding];
    }
    
    @Override
    public void free(Solution CURR) {
        short str = (short)r.nextInt(1);
        int []v = CURR.get(str);
        
        for(int a : v)
            if(a != -1) reps[a]++;
        for(int a=0; a<reps.length; a++)
            if(reps[a] > 0) reps[a] = r.nextInt(reps[a]) + 1;
        
        for(int i=0; i<v.length; i++)
            if(v[i] != -1 && ++curr[v[i]] != reps[v[i]]) CURR.set(str, i, -1);
        
        Arrays.fill(reps, 0);
        Arrays.fill(curr, 0);
        CURR.updateLength();
    }   
}