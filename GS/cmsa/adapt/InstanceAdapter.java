package cmsa.adapt;

import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public abstract class InstanceAdapter {    
    public abstract void adapt(Solution S, Solution RF, float AB, short [][]age);
}