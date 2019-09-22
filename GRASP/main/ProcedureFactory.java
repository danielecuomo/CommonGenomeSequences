package main;

import build.*;
import java.io.FileNotFoundException;
import ls.DestructionCriterion;
import ls.InsertionCriterion;
import ls.RandomDestructionCriterion;
import ls.RandomInsertionCriterion;
import ls.SolutionManager;
import randomness.BiasFunction;
import randomness.ProbabilityDistribution;
import randomness.RandomManager;
import structures.AlphabetMap;
import structures.GlobalVariables;
import static structures.GlobalVariables.alphabet;
import structures.GreedyCriterion;
import structures.Instance;
import structures.LogicalList;
import structures.Parameters;
import structures.Solution;

/**
 *
 * @author Daniele Cuomo
 */
public class ProcedureFactory {
    private final int []x, y;
    private final int alphabetEncoding;
    private final AlphabetMap am;
    private final GreedyCriterion gc;
    private final Parameters parameters;
    
    public ProcedureFactory(Instance I, Parameters parameters) throws FileNotFoundException{        
        am = new AlphabetMap(I.getX(), I.getY(), I.getAlphabetEncoding());
        gc = new GreedyCriterion(I.getX(), I.getY(), am.getMap());
        x = I.getX(); y = I.getY();
        alphabetEncoding = I.getAlphabetEncoding();
        this.parameters = parameters;
        
        GlobalVariables.initAlphabet(am.getMap(), alphabetEncoding);
    }
        
    
    public GRASP makeGRASP(){
        LogicalList []lists = new LogicalList[alphabetEncoding];
        for(int a=0 ; a<lists.length ; a++)
            lists[a] = new LogicalList(a, am.getMap(), gc.getGreedyFunction());
        
        GRASP algo = new GRASP();
        
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(x.length, y.length);
        RandomManager rm = new RandomManager(parameters.getSeed());
        rm.setProbabilityDistribution(ProbabilityDistribution.valueOf(parameters.getDistribution()))
                .setBiasFunction(BiasFunction.valueOf(parameters.getBias()));
        rm.init(lists, gc.getGreedyFunction());
        
        SolutionBuilder sb = new GreedyRandomBuilder(x.length, y.length)
                .setLongestCommonSubsequence(lcs).setLists(lists).setRandomManager(rm);
        
        InsertionCriterion ic = new RandomInsertionCriterion().setAlphabetMap(am.getMap());
        DestructionCriterion dc = new RandomDestructionCriterion().setRandomManager(rm);
        SolutionManager sm = new SolutionManager().setDestructionCriterion(dc)
                .setInsertionCriterion(ic);
        
 
        SolutionTransformer st = new SolutionTransformer(alphabetEncoding)
                .setSolution(new Solution(alphabet));
       
        return algo.setTimeLimit(parameters.getTimeLimit()).setSolutionBuilder(sb)
                .setSolutionManager(sm).setSolutionTransformer(st);
    }
}