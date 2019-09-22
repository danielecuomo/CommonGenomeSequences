package structures;

import java.util.Arrays;

import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;

/**
 *
 * @author Daniele Cuomo
 */
public class LogicalList{    
    private final int letter;
    private final int [][]indices = new int[2][];

    public int length(short str){ return indices[str].length; }
        
    public LogicalList(int a, int [][][]map, double [][]g){
        letter = a;

        indices[X] = Arrays.copyOf(map[X][a], map[X][a].length);
        indices[Y] = Arrays.copyOf(map[Y][a], map[Y][a].length);

        insertionSort(X, g);
        insertionSort(Y, g);
    }
        
    private void insertionSort(short str, double[][] g) {
        int ind, j;
        for(int i=1; i<indices[str].length; i++){
            ind = indices[str][i];
            for(j=i; j>0 && g[str][indices[str][j-1]] > g[str][ind]; j--){
                indices[str][j] = indices[str][j-1];
           }
           indices[str][j] = ind;
        }
    }

    public int getIndex(short str, int i){ return indices[str][i]; }
    public int getLetter(){ return letter; }
    public int []getIndices(short str){ return indices[str]; }
}