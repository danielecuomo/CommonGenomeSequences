package structures;

/**
 *
 * @author Daniele Cuomo
 */
public class Instance {
    private int alphabetEncoding;
    
    private int []x, y;
    
    public int getAlphabetEncoding(){ return alphabetEncoding; }
    
    public int []getX(){ return x; }
    public int []getY(){ return y; }
    
    
    public void setAlphabetEncoding(int alphabetEncoding){ this.alphabetEncoding = alphabetEncoding; }
    
    public void setX(int []x){ this.x = x; }
    public void setY(int []y){ this.y = y; }
}