package cmsa;

import static structures.GlobalVariables.*;
import structures.Permutation;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class TrivialPheromoneManager extends PheromoneManager{
    private final int []reps;
    private final float []delta;
    
    private float []steps;
    
    private float EPSILON;
    
    public TrivialPheromoneManager(int alphabetEncoding){
        reps = new int[alphabetEncoding];
        delta = new float[alphabetEncoding];
    }
    
    @Override
    public void update(Solution LCS, Permutation pi, float[] tau) {
        float step;
        for(int a : alphabet){
            for(int sample : pi.getSample(X, a))
                if(LCS.get(X, sample) != -1) reps[a]++;
            if((step = steps[Math.min(reps[a], steps.length-1)]) > 0)
                delta[a] = step * (1 - tau[a]);
            else
                delta[a] = step * tau[a];
            tau[a] += delta[a];
        }
    }
    /*public void update(Solution LCS, Permutation pi, float[] tau) {
        float step;
        for(int a : LCS.get(X))
            if(a != -1)
                reps[a]++;
        for(int a : alphabet){
            if((step = steps[Math.min(reps[a], steps.length-1)]) > 0)
                delta[a] = step * (1 - tau[a]);
            else
                delta[a] = step * tau[a];
            tau[a] += delta[a];
        }
    }*/
        
    @Override
    public boolean convergence(float[] tau) {
        double sum = 0;
        for(int a : alphabet)
            sum += Math.abs(delta[a]);
        sum /= alphabet.length;
        return sum < EPSILON;
    }
    
    public TrivialPheromoneManager setEpsilon(float eps){ this.EPSILON = eps; return this; }
    public TrivialPheromoneManager setSteps(float []steps){ this.steps = steps; return this; }
}