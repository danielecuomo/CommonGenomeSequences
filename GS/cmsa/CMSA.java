package cmsa;

import cmsa.adapt.InstanceAdapter;
import cmsa.elimination.EliminationCriterion;
import java.util.Arrays;
import structures.Permutation;
import structures.Result;
import structures.Solution;

import static structures.GlobalVariables.*;

/**
 *
 * @author Daniele Cuomo
 */
public class CMSA{
    private long T = 1000;
    private float AB = 4;
    
    private final short [][]age = new short[2][];
    private final float []tau;
    
    private Solution S, CURR;
    private Permutation pi;
    
    private PermutationBuilder pb;
    private MergeBuilder mb;
    private InstanceAdapter ia;
    private PheromoneManager pm;
    private LongestCommonSubsequence lcs;
    private EliminationCriterion ec;
    private AgeManager am;
    
    public CMSA(int alphabetEncoding, int lx, int ly){
        age[X] = new short[lx];
        age[Y] = new short[ly];
        tau = new float[alphabetEncoding];
        
        S = new Solution(lx, ly);
    }
    
    public Result solve(){
        int OPT = 0;
        S.init();
        
        Arrays.fill(tau, 0.5f);
        long t = System.currentTimeMillis();
        while(System.currentTimeMillis() - t < T){
            pi = pb.construct(tau);
            //am.update(S, pi, age);
            mb.merge(S, pi);
            
            CURR = lcs.getLCS(S);
            pm.update(CURR, pi, tau);
            ec.free(CURR);
            
            if(CURR.length > OPT) OPT = CURR.length;
            ia.adapt(S, CURR, AB, age);
            
            /*if(pm.convergence(tau)){
                Arrays.fill(tau, 0.5f);
                Arrays.fill(age[X], (short)0);
                Arrays.fill(age[Y], (short)0);
                S.init();
            }*/
        }
        t = System.currentTimeMillis() - t;
        return new Result(OPT, (float)t/1000);
    }
    
    public CMSA setTimeLimit(long T){ this.T = T; return this; }
    public CMSA setAgeBound(float AB){ this.AB = AB; return this; }
    public CMSA setPermutationBuilder(PermutationBuilder pb){ this.pb = pb; return this; }
    public CMSA setMergeBuilder(MergeBuilder mb){ this.mb = mb; return this; }
    public CMSA setInstanceAdapter(InstanceAdapter ia){ this.ia = ia; return this;}
    public CMSA setPheromoneManager(PheromoneManager pm){ this.pm = pm; return this; }
    public CMSA setLongestCommonSubsequence(LongestCommonSubsequence lcs){ this.lcs = lcs; return this; }
    public CMSA setEliminationCriterion(EliminationCriterion dc){ this.ec = dc; return this; }
    public CMSA setAgeManager(AgeManager am){ this.am = am; return this; }
}