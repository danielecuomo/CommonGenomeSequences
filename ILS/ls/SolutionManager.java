package ls;

import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class SolutionManager{
    private Solution S;
    private InsertionCriterion ic;
    private DestructionCriterion dc;
    private SwapCriterion sc;
    
    private float dFactor = 0.6f, sFactor = 0.6f;
    
    public void insertionPhase(){
        for(int i=S.length(); i<S.upperBound(); i++)
            ic.insert(S, i);
    }
    
    public void destructionPhase(){
        int dmax = Math.round(S.length()*dFactor);
        for(int d=0; d<dmax; d++)
            dc.destroy(S);
    }
    
    public void swapPhase(){
        for(int i=0; i<S.length()*sFactor; i++) sc.swap(S);
    }
    
    public SolutionManager setInsertionCriterion(InsertionCriterion ic){ this.ic = ic; return this; }
    public SolutionManager setDestructionCriterion(DestructionCriterion dc){ this.dc = dc; return this; }
    public SolutionManager setSwapCriterion(SwapCriterion sc){ this.sc = sc; return this; }
    public SolutionManager setDeleteFactor(float dFactor){ this.dFactor = dFactor; return this; }
    public SolutionManager setSwapFactor(float sFactor){ this.sFactor = sFactor; return this; }
    
    public void setSolution(Solution S) { this.S = S;}
    public int length(){ return S.length(); }

    public String check(int [][][]map){ return S.check(map); }
}