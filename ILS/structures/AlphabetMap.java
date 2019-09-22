package structures;

import java.util.Arrays;

import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;

/**
 *
 * @author Daniele Cuomo
 */
public class AlphabetMap{
    private final int [][][]map = new int [2][][];
    
    public AlphabetMap(int []x, int []y, int alphabetEncoding){
        map[X] = new int[alphabetEncoding][];
        map[Y] = new int[alphabetEncoding][];
        
        int countX, countY;
        for(int a=0 ; a<alphabetEncoding ; a++){
            countX = countY = 0;
            
            for(int i=0 ; i<x.length ; i++)
                if(x[i] == a) countX++;
            map[X][a] = new int[countX];
  
            for(int j=0 ; j<y.length ; j++)
                if(y[j] == a) countY++;
            map[Y][a] = new int[countY];
        }
        
        int []curr = new int[alphabetEncoding]; //tiene traccia dell'ultimo indice usato per ogni lettera
        for(int i=0 ; i<x.length ; i++)
            map[X][x[i]][curr[x[i]]++] = i;
        
        Arrays.fill(curr, 0);
        for(int j=0 ; j<y.length ; j++)
            map[Y][y[j]][curr[y[j]]++] = j;
    }
   
    public int [][][]getMap(){ return map; }
}
