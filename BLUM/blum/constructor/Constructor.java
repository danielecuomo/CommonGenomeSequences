package blum.constructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;
import structures.LogicalList;
import structures.Match;
import structures.Result;
import structures.Solution;

/**
 *
 * @author ASUS
 */
public class Constructor {
    private List<Match> C = new LinkedList<>();
    
    private LogicalList []L;
    private int lx, ly;
    private int alphabetEncoding;
    private int [][][]map;
    
    
    
    public Result solve(){
        boolean []check = new boolean[alphabetEncoding];
        Arrays.fill(check, false);
        
        Solution S = new Solution(lx, ly);
        S.init();
        Match last = new Match();
        Match curr;
        
        int i, j;
        
        long t = System.currentTimeMillis();
        while(true){
            
            for(int a=0; a<L.length; a++){
                if(check[a]) continue;
                
                i = L[a].getFirstFrom(last.i(), X);
                j = L[a].getFirstFrom(last.j(), Y);
                if(i != -1 && j != -1 )
                    C.add(new Match(i, j, lx, ly, a));
            }
            if(C.isEmpty()) break;
            curr = this.getMin(C);
            
            S.set(X, curr.i(), curr.letter());
            S.set(Y, curr.j(), curr.letter());
            S.length++;
            C.clear();
            check[curr.letter()] = true;
            last = curr;
        }
        
        return new Result(S.length, (float)(System.currentTimeMillis() - t)/1000);
    }
    
    private Match getMin(List<Match> C){
        Match min = null;
        float minv = Float.POSITIVE_INFINITY;
        for(Match m : C)
            if(m.value() < minv){
                minv = m.value();
                min = m;
            }
        return min;
    }
    
    public Constructor setLogicalLists(LogicalList []L){ this.L = L; return this; }
    public Constructor setSize(int lx, int ly){ this.lx = lx; this.ly = ly; return this; }
    public Constructor setAlphabetEncoding(int alphabetEncoding){ this.alphabetEncoding = alphabetEncoding; return this; }
    public Constructor setAlphabetMap(int[][][] map){ this.map = map; return this; }
}
