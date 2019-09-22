package structures;

/**
 *
 * @author Daniele Cuomo
 */
public class Parameters{
    private long T = 1000;
    private long seed = System.currentTimeMillis();
    private float AB = 5, EPSILON = 0.001f;
    private float []steps = new float[]{0.051f, 0.337f, 0.204f, -0.303f};
    private String D = "HEURISTIC_BIASED", B = "POLY";
    
    public Parameters setAgeBound(float AB){ this.AB = AB; return this; }
    //public Parameters setEpsilon(float EPSILON){ this.EPSILON = EPSILON; return this; }
    public Parameters setSteps(float []steps){ this.steps = steps; return this; }
    public Parameters setDistribution(String D){ this.D = D; return this; }
    public Parameters setBias(String B){ this.B = B; return this; }
    public Parameters setTimeLimit(long T){ this.T = T; return this; }
    public Parameters setSeed(long seed){ this.seed = seed; return this; }
    
    public long getTimeLimit() { return T; }
    public float getAgeBound(){ return AB; }
    //public float getEpsilon(){ return EPSILON; }
    public float []getSteps(){ return steps; }
    public String getDistribution(){ return D; }
    public String getBias(){ return B; }
    public long getSeed(){ return seed; }
}