package structures;

public class Match{
    private int i, j;
    private int a;
    private float value;

    public Match(){ init(-1,-1, 1, 1, -1); }
    
    public Match(int i, int j, int lx, int ly, int a){
        this.i = i; this.j = j;
        this.a = a;
        value = (float)i/(float)lx + (float)j/(float)ly;
    }
    
    public void init(int i, int j, int lx, int ly, int a){
        this.i = i; this.j = j;
        this.a = a;
        value = (float)i/(float)lx + (float)j/(float)ly;
    }
    
    public int i(){ return i; }
    public int j(){ return j; }
    public int letter(){ return a; }
    public float value(){ return value; }
}