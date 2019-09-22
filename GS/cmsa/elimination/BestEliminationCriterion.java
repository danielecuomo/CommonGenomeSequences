package cmsa.elimination;

import java.util.Arrays;
import structures.DeltaFunction;
import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class BestEliminationCriterion extends EliminationCriterion{
    private final int [][]bests = new int[2][];
    
    private final DeltaFunction df;
    
    public BestEliminationCriterion(int alphabetEncoding, int lx, int ly){
        bests[X] = new int[alphabetEncoding];
        bests[Y] = new int[alphabetEncoding];
        
        Arrays.fill(bests[X], Integer.MAX_VALUE);
        
        if(lx > ly)
            df = (i,j) -> Math.abs(i - j*(lx/ly));
        else
            df = (i,j) -> Math.abs(j - i*(ly/lx));
    }
    
    @Override
    public void free(Solution CURR){
        int []x = CURR.get(X), y = CURR.get(Y);
        int a;
        for(int i=0, j=0; i<x.length; i++){
            if(x[i] == -1) continue;
            a = x[i];
            while(y[j] != a) j++;
            if(df.delta(i, j) < df.delta(bests[X][a], bests[Y][a])){
                bests[X][a] = i;
                bests[Y][a] = j;
            }
                
            j++;
        }        
        
        for(int i=0; i<x.length; i++)
            if(x[i] != -1 && i != bests[X][x[i]]) CURR.set(X, i, -1);
        for(int j=0; j<y.length; j++)
            if(y[j] != -1 && j != bests[Y][y[j]]) CURR.set(Y, j, -1);   
  
        Arrays.fill(bests[X], Integer.MAX_VALUE);
        Arrays.fill(bests[Y], 0);
        
        CURR.updateLength();
    }   
}