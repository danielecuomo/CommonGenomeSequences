package structures;

import java.util.Arrays;

import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;

/**
 *
 * @author Daniele Cuomo
 */
public class SubInstance{
    private final int [][]Z = new int[2][];
    
    public int length = 0;
    
    public SubInstance(int lx, int ly){
        Z[X] = new int[lx];
        Z[Y] = new int[ly];
    }
    
    public void init(){
        Arrays.fill(Z[X], -1);
        Arrays.fill(Z[Y], -1);
        length = 0;
    }
    
    public void set(short str, int ind, int a){ Z[str][ind] = a; }
    
    public int get(short str, int ind){ return Z[str][ind]; }
    
    public int []get(short str){ return Z[str]; }
    
    public int length(int str){ return Z[str].length; }

    public void updateLength() {
        int l = 0;
        for(int a : Z[X])
            if(a != -1)
                l++;
        this.length = l;
    }

    public String check(int[][][] map) {
        /*istanza originale*/
        for(int a=0; a<map[X].length; a++){
            for(int i : map[X][a])
                if(Z[X][i] != -1 && Z[X][i] != a)
                    return "istanza diversa dall'originale";
            for(int i : map[Y][a])
                if(Z[Y][i] != -1 && Z[Y][i] != a)
                    return "istanza diversa dall'originale";
        }
        
        return this.check(map[X].length);
    }

    public String check(int l) {
        /*no cross*/
        for(int i=0, j=0; i<Z[X].length; i++){
            if(Z[X][i] == -1) continue;
  
            while(Z[Y][j] == -1) j++;
            if(Z[X][i] != Z[Y][j]) return "la soluzione presenta dei cross";
            j++;
        }
             
        /*repetition free*/
        int count;
        for(int a=0 ; a<l; a++){
            count = 0;
            for(int b : Z[Y]){
                if(b == a) count++;
                if(count > 1) return "la soluzione non è repetition-free";
            }
            count = 0;
            for(int b : Z[X]){
                if(b == a) count++;
                if(count > 1) return "la soluzione non è repetition-free";
            }
        }
        
        /*lunghezza effettiva*/
        int sum = 0;
        for(int i=0; i<Z[X].length; i++)
            if(Z[X][i] != -1) sum++;
        
        if(sum != length) return "il valore non è allineato con la lunghezza effettiva";
        
        sum = 0;
        for(int i=0; i<Z[Y].length; i++)
            if(Z[Y][i] != -1) sum++;
        
        if(sum != length) return "il valore non è allineato con la lunghezza effettiva";
        
        return "OK";
    }

    public void copy(SubInstance S){
        System.arraycopy(S.Z[X], 0, this.Z[X], 0, this.Z[X].length);
        System.arraycopy(S.Z[Y], 0, this.Z[Y], 0, this.Z[Y].length);
        this.length = S.length;
    }

    public void merge(SubInstance CURR){
        int a;
        for(int i=0; i<CURR.length(X); i++)
            if((a = CURR.get(X, i)) != -1)
                this.Z[X][i] = a;
        for(int j=0; j<CURR.length(Y); j++)
            if((a = CURR.get(Y, j)) != -1)
                this.Z[Y][j] = a;
    }    
}