package main;

import interfaces.IraceManager;
import interfaces.Parser;
import java.io.IOException;
import structures.Result;

/**
 *
 * @author Daniele Cuomo
 */
public class TuningMain {
    public static void main(String[] args) throws IOException{
        IraceManager im = new IraceManager(args);
        Parser p = new Parser();
        ControlFlow cf = new ControlFlow(im, p);
        
        Result R = cf.solveInstance();
        im.output(R);
    }
}
