package structures;

/**
 *
 * @author Daniele Cuomo
 */
public class Parameters{
    private long T = 1000;
    private long seed = System.currentTimeMillis();
    
    public Parameters setTimeLimit(long T){ this.T = T; return this; }
    public Parameters setSeed(long seed){ this.seed = seed; return this; }
    
    public long getTimeLimit() { return T; }
    public long getSeed(){ return seed; }
}