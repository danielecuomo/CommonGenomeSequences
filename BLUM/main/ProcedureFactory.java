package main;

import blum.constructor.Constructor;
import java.io.FileNotFoundException;
import structures.AlphabetMap;
import structures.GlobalVariables;
import structures.GreedyCriterion;
import structures.Instance;
import structures.LogicalList;
import structures.Parameters;

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
        
    
    public Constructor makeConstructor(){
        LogicalList []lists = new LogicalList[alphabetEncoding];
        for(int a=0 ; a<lists.length ; a++)
            lists[a] = new LogicalList(a, am.getMap(), gc.getGreedyFunction());
        
        
        return new Constructor().setLogicalLists(lists)
                .setSize(x.length, y.length).setAlphabetEncoding(alphabetEncoding)
                .setAlphabetMap(am.getMap());
    }
}