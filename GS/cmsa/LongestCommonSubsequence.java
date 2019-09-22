package cmsa;

import structures.Solution;

import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;

/**
 *
 * @author Daniele Cuomo
 */
public class LongestCommonSubsequence {
    private final int []subDim = new int [2];
    private final int [][]shiftMap = new int[2][];
    private final int [][]DPMatrix;

    private final Solution LCS;
    
    public LongestCommonSubsequence(int lx, int ly) {
        shiftMap[X] = new int[lx];
        shiftMap[Y] = new int[ly];
        
        DPMatrix = new int[lx + 1][ly + 1];
        
        LCS = new Solution(lx, ly);
    }
    
    private void fillMatrix(Solution S){
        setShiftMap(S);
        
        int n = subDim[X], m = subDim[Y];
        int i = 0, j = 0;
        for(; i <= n; i++)
            for(; j <= m; j++) DPMatrix[i][j] = 0;
        
        for (i=0; i <= n; i++){ 
            for (j=0; j <= m; j++){ 
                if(i == 0 || j == 0) 
                    DPMatrix[i][j] = 0; 
                else if(S.get(X, shiftMap[X][i-1]) == S.get(Y, shiftMap[Y][j-1])) 
                    DPMatrix[i][j] = DPMatrix[i-1][j-1] + 1; 
                else
                    DPMatrix[i][j] = Math.max(DPMatrix[i-1][j], DPMatrix[i][j-1]); 
            } 
        }
    }
    
    private Solution retrieveSolution(Solution S){
        LCS.init();
        int n = subDim[X], m = subDim[Y];
        int i = n, j = m;
        
        while (i > 0 && j > 0){ 
            if(S.get(X, shiftMap[X][i-1]) == S.get(Y, shiftMap[Y][j-1])){ 
                LCS.length++;
                LCS.set(X, shiftMap[X][i-1], S.get(X, shiftMap[X][i-1]));
                LCS.set(Y, shiftMap[Y][j-1], S.get(Y, shiftMap[Y][j-1]));
                i--; j--;     
            }
            else if(DPMatrix[i-1][j] > DPMatrix[i][j-1]) i--;
            else j--;
        }        
        return LCS;
    }
    
    public Solution getLCS(Solution S){
        fillMatrix(S);
        return retrieveSolution(S);        
    }
    
    private void setShiftMap(Solution S){
        int curr = 0, jump;
        
        int lx = S.length(X), ly = S.length(Y);
        
        while(curr < lx && S.get(X, curr) != -1){
            shiftMap[X][curr] = curr;
            curr++;
        }
        jump = curr + 1;
        while(jump < lx && S.get(X, jump) == -1) jump++;
        for(; jump < lx ; curr++){
            shiftMap[X][curr] = jump++;
            while(jump < lx && S.get(X, jump) == -1)
                jump++;
        }
        subDim[X] = curr;
        
        curr = 0;
        while(curr < ly && S.get(Y, curr) != -1){
            shiftMap[Y][curr] = curr;
            curr++;
        }
        jump = curr + 1;
        while(jump < ly && S.get(Y, jump) == -1) jump++;
        for(; jump < ly ; curr++){
            shiftMap[Y][curr] = jump++;
            while(jump < ly && S.get(Y, jump) == -1)
                jump++;
        }
        subDim[Y] = curr;
    }
}