package randomness;

import java.util.Arrays;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import structures.LogicalList;

import static structures.GlobalVariables.*;

/**
 *
 * @author Daniele Cuomo
 */
public class RandomManager{
    private final EnumeratedIntegerDistribution [][]eid = new EnumeratedIntegerDistribution[2][];
    private ProbabilityDistribution pd;
    private BiasFunction bf;
    private final RandomGenerator rg;
    
    public RandomManager(long seed){ rg = new Well19937c(seed); }

    public void init(LogicalList []lists, double [][]g){
        double [][]epd = new double[2][]; //empirical probability distribution
        double []sum = new double[2];
        int [][]indices = new int[2][];
        eid[X] = new EnumeratedIntegerDistribution[lists.length];
        eid[Y] = new EnumeratedIntegerDistribution[lists.length];
        //System.out.println("BEGIN");
        for(int a : alphabet){
            //System.out.println(a);
            Arrays.fill(sum, 0);
            indices[X] = lists[a].getIndices(X);
            indices[Y] = lists[a].getIndices(Y);
            
            epd[X] = new double[lists[a].length(X)];
            epd[Y] = new double[lists[a].length(Y)];
     
            pd.setEPD(sum, epd, indices, g, bf);
            
            eid[X][a] = new EnumeratedIntegerDistribution(rg, indices[X], epd[X]);
            eid[Y][a] = new EnumeratedIntegerDistribution(rg, indices[Y], epd[Y]);
            //System.out.println(java.util.Arrays.toString(epd[X]));
            //System.out.println(java.util.Arrays.toString(epd[Y]));
        }
        //System.out.println("END");
    }

    public int sample(short str, int a){ return eid[str][a].sample(); }
    public int []sample(short str, int a, int num){ return eid[str][a].sample(num); }
    
    public int nextInt(int min, int max){ return rg.nextInt(max - min) + min; }
    
    public RandomManager setProbabilityDistribution(ProbabilityDistribution pd){ this.pd = pd; return this; }
    public RandomManager setBiasFunction(BiasFunction bf){ this.bf = bf; return this; }
}