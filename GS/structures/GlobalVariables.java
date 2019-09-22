package structures;

/**
 *
 * @author Daniele Cuomo
 */
public class GlobalVariables{
    private GlobalVariables(){}
    
    public static short X = 0, Y = 1;
    public static int []alphabet = null;
    
    public static void initAlphabet(int [][][]map, int alphabetEncoding){
         int count = 0;
        for(int a = 0; a<alphabetEncoding; a++)
            if(map[X][a].length > 0 && map[Y][a].length > 0)
                count++;
        
        alphabet = new int[count];
        for(int a = 0, i = 0; a<alphabetEncoding; a++)
            if(map[X][a].length > 0 && map[Y][a].length > 0){
                alphabet[i] = a;
                i++;
            }
    }
}