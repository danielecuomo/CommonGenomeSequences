package cmsa;

import structures.Permutation;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public abstract class PheromoneManager {
    public abstract void update(Solution LCS, Permutation pi, float[] tau);

    public abstract boolean convergence(float []tau);
}