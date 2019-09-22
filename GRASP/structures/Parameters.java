package structures;

/**
 *
 * @author Daniele Cuomo
 */
public class Parameters{
    private long seed = System.currentTimeMillis();
    private long T = 1000;
    private String D = "HEURISTIC_BIASED", B = "I";
    
    public Parameters setSeed(long seed){ this.seed = seed; return this; }
    public Parameters setDistribution(String D){ this.D = D; return this; }
    public Parameters setBias(String B){ this.B = B; return this; }
    public Parameters setTimeLimit(long T){ this.T = T; return this; }
    
    public long getSeed() { return seed; }    
    public long getTimeLimit() { return T; }
    public String getDistribution(){ return D; }
    public String getBias(){ return B; }
}