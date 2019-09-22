package structures;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Daniele Cuomo
 */
public class Solution{
    private final int []substr;
    private int length;
    
    public Solution(int []alphabet){ 
        substr = new int[alphabet.length];
        System.arraycopy(alphabet, 0, substr, 0, alphabet.length);
        length = 0;
    }
    
    public int length(){ return length; }
    
    public void increase(){ length++; }
    public void decrease(){ length--; }

    public int get(int i){ return substr[i]; }
    
    public void swap(int i, int j){
        int a = substr[j];
        substr[j] = substr[i];
        substr[i] = a;
    }

    public void init(){ 
        length = 0; 
        shuffle(substr);
    }

    public int upperBound(){ return substr.length; }
   
    public String check(int [][][]map){
        for(int i=0, j=-1, k; i<length; i++){
            for(k=0; k<map[0][substr[i]].length; k++)
                if(map[0][substr[i]][k] > j){
                    j = map[0][substr[i]][k];
                    break;
                }
            if(k == map[0][substr[i]].length) return "Soluzione inesistente in x";
        }
        for(int i=0, j=-1, k; i<length; i++){
            for(k=0; k<map[1][substr[i]].length; k++)
                if(map[1][substr[i]][k] > j){
                    j = map[1][substr[i]][k];
                    break;
                }
            if(k == map[1][substr[i]].length) return "Soluzione inesistente in y";
        }
        
        int []count = new int[map[0].length];
        for(int i=0; i<length; i++)
            if(++count[substr[i]] > 1) return "Soluzione non repetition-free";
        
        return "Soluzione valida";
    }

    public int getIndex(int a){
        int i = 0;
        for(; substr[i] != a; i++);
        return i;
    }

    public int getCandidate(){ return substr[length]; }

    private void shuffle(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
          int index = rnd.nextInt(i + 1);
          // Simple swap
          int a = ar[index];
          ar[index] = ar[i];
          ar[i] = a;
        }
    }
}