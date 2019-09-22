package cmsa.elimination;

import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public abstract class EliminationCriterion {
    public abstract void free(Solution LCS);
}