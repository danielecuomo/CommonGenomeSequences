package structures;

import static structures.GlobalVariables.*;

/**
 *
 * @author Daniele Cuomo
 */
public class Permutation {
    private final int [][][]index = new int[2][][];
    
    public Permutation(int alphabetEncoding){
        index[X] = new int[alphabetEncoding][];
        index[Y] = new int[alphabetEncoding][];
    }

    public int []getSample(short str, int a){ return index[str][a]; }

    public void setSample(short str, int a, int []sample){ index[str][a] = sample; }
}
