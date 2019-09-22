package randomness;

import java.util.Arrays;
import static structures.GlobalVariables.X;
import static structures.GlobalVariables.Y;

/**
 *
 * @author Daniele Cuomo
 */
public enum ProbabilityDistribution{
        HEURISTIC_BIASED{
            @Override
            public void setEPD(double[] sum, double[][] epd, int[][] indices, double[][] g, BiasFunction bf) {
                for(int i : indices[X])
                    sum[X] += bf.bias(g[X][i]/g[X][indices[X][0]]);
                for(int j : indices[Y])
                    sum[Y] += bf.bias((g[Y][j]/g[Y][indices[Y][0]]));

                for(int i=0; i<indices[X].length; i++)
                    epd[X][i] = bf.bias(g[X][indices[X][i]]/g[X][indices[X][0]]) / sum[X];
                for(int j=0; j<indices[Y].length; j++)
                    epd[Y][j] = bf.bias(g[Y][indices[Y][j]]/g[Y][indices[Y][0]]) / sum[Y];
            }
        },
        ORDER_BIASED {
            @Override
            public void setEPD(double[] sum, double[][] epd, int[][] indices, double[][] g, BiasFunction bf) {
               for(int i=0; i<indices[X].length; i++)
                    sum[X] += bf.bias(i + 1);
                for(int j=0; j<indices[Y].length; j++)
                    sum[Y] += bf.bias(j + 1);

                for(int i=0; i<indices[X].length; i++)
                    epd[X][i] = bf.bias(i + 1) / sum[X];
                for(int j=0; j<indices[Y].length; j++)
                    epd[Y][j] = bf.bias(j + 1) / sum[Y];
            }
        };
        
        public abstract void setEPD(double []sum, double epd[][], int [][]indices, double [][]g, BiasFunction bf);
    }