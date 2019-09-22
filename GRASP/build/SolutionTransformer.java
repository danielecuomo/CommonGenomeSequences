package build;

import java.util.Arrays;
import static structures.GlobalVariables.X;
import structures.Solution;
import structures.SubInstance;

/**
 *
 * @author Daniele Cuomo
 */
public class SolutionTransformer {
    boolean []decisionVars;
    Solution solution;
    
    public SolutionTransformer(int alphabetEncoding){
        decisionVars = new boolean[alphabetEncoding];
    }
    
    public Solution transform(SubInstance I){
        Arrays.fill(decisionVars, false);
        for(int a : I.get(X))
            if(a != -1) decisionVars[a] = true;
        
        int []substr = I.get(X);
        
        solution.init();
        int k;
        for(int i=0, j=0; i<I.length; i++){
            while(substr[j] == -1) j++;
            
            k = solution.getIndex(substr[j]);
            
            solution.swap(k, solution.length());
            solution.increase();
            j++;
        }
        
        return solution;
    }
    
    public SolutionTransformer setSolution(Solution str){ this.solution = str; return this; }
}