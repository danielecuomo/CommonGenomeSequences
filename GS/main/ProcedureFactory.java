package main;

import cmsa.AgeManager;
import cmsa.CMSA;
import cmsa.elimination.BestEliminationCriterion;
import cmsa.adapt.InstanceAdapter;
import cmsa.LongestCommonSubsequence;
import cmsa.MergeBuilder;
import cmsa.PermutationBuilder;
import cmsa.PheromoneManager;
import cmsa.ProbabilisticPermutationBuilder;
import cmsa.TrivialPheromoneManager;
import cmsa.adapt.TrivialInstanceAdapter;
import cmsa.elimination.EliminationCriterion;
import java.io.FileNotFoundException;
import randomness.BiasFunction;
import randomness.ProbabilityDistribution;
import randomness.RandomManager;
import structures.AlphabetMap;
import structures.GlobalVariables;
import structures.GreedyCriterion;
import structures.Instance;
import structures.LogicalList;
import structures.Parameters;
import structures.Permutation;

/**
 *
 * @author Daniele Cuomo
 */
public class ProcedureFactory {
    private final int []x, y;
    private final int alphabetEncoding;
    private final AlphabetMap am;
    private final GreedyCriterion gc;
    private final Parameters P;
    
    public ProcedureFactory(Instance I, Parameters P) throws FileNotFoundException{        
        am = new AlphabetMap(I.getX(), I.getY(), I.getAlphabetEncoding());
        gc = new GreedyCriterion(I.getX(), I.getY(), am.getMap());
        x = I.getX(); y = I.getY();
        alphabetEncoding = I.getAlphabetEncoding();
        this.P = P;
        
        GlobalVariables.initAlphabet(am.getMap(), alphabetEncoding);
    }
        
    
    public CMSA makeCMSA(){
        LogicalList []lists = new LogicalList[alphabetEncoding];
        for(int a=0 ; a<lists.length ; a++)
            lists[a] = new LogicalList(a, am.getMap(), gc.getGreedyFunction());
        
        CMSA cmsa = new CMSA(alphabetEncoding, x.length, y.length);
        
        Permutation pi = new Permutation(alphabetEncoding);
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(x.length, y.length);
        
        RandomManager rm = new RandomManager(P.getSeed());
        rm.setProbabilityDistribution(ProbabilityDistribution.valueOf(P.getDistribution()))
                .setBiasFunction(BiasFunction.valueOf(P.getBias()));
        rm.init(lists, gc.getGreedyFunction());
        PermutationBuilder pb = new ProbabilisticPermutationBuilder()
                .setLists(lists).setPermutation(pi).setRandomManager(rm);
        MergeBuilder mb = new MergeBuilder();
        InstanceAdapter ia = new TrivialInstanceAdapter();
        EliminationCriterion dc = new BestEliminationCriterion(alphabetEncoding, x.length, y.length);
        PheromoneManager pm = new TrivialPheromoneManager(alphabetEncoding).setSteps(P.getSteps());
        
        return cmsa.setInstanceAdapter(ia).setLongestCommonSubsequence(lcs)
                .setMergeBuilder(mb).setPermutationBuilder(pb)
                .setEliminationCriterion(dc).setPheromoneManager(pm)
                .setAgeManager(new AgeManager()).setAgeBound(P.getAgeBound())
                .setTimeLimit(P.getTimeLimit());
    }
}