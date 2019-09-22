package structures;

import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;

/**
 *
 * @author Daniele Cuomo
 */
public class GreedyCriterion{
    private final DeltaFunction df;

    private double [][]g = new double[2][];
    
    public double [][]getGreedyFunction(){ return g; }
    
    
    private void initFunction(int []v, int []w, int [][]wMap, int str){
        float sum;
        for(int i=0 ; i<v.length ; i++){
            sum = 0;
            for(int j : wMap[v[i]])
                sum += df.delta(i,j);
            if(wMap[v[i]].length > 0) g[str][i] = sum / (wMap[v[i]].length);
            else g[str][i] = Double.POSITIVE_INFINITY;
        }
    }
    
    public GreedyCriterion(int []x, int []y, int [][][] map){
        if(x.length > y.length)
            df = (i,j) -> Math.abs(i - j*(x.length/y.length)) + 1f;
        else
            df = (i,j) -> Math.abs(j - i*(y.length/x.length)) + 1f;
        
        g[X] = new double[x.length];
        g[Y] = new double[y.length];
        initFunction(x, y, map[Y], X);
        initFunction(y, x, map[X], Y);
        
    }

}