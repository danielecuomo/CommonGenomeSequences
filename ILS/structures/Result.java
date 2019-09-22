package structures;

/**
 *
 * @author Daniele Cuomo
 */
public class Result{
    float p;    //Objective function
    float t;    //Elapsed time
    float s;    //Standard deviation
    
    public Result(float p, float t){
        this.p = p;
        this.t = t;
    }
    
    public Result(float p, float s, float t){
        this.p = p;
        this.s = s;
        this.t = t;
    }
    
    public float getObjectiveFunction(){ return p; }
    
    public float getElapsedTime(){ return t; }
    
    @Override
    public String toString(){
        return String.format("%.2f", p) + "\t" + 
               String.format("%.2f", s) + "\t" + 
               String.format("%.5f", t);
    }

}
