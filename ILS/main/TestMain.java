package main;

import java.io.IOException;
import interfaces.Parser;
import structures.Instance;
import structures.Parameters;
import structures.Result;

/**
 *
 * @author Daniele Cuomo
 */
public class TestMain {
    static String path = "C:\\Users\\ASUS\\Desktop\\TESI\\NetBeans Projects\\LocalSearch\\instances\\Set1\\512_n-div-8.14";
    public static void main(String[] args) throws IOException{
        //ControlFlow.massiveExec(new Parameters());
        
        Parser p = new Parser();
        //Instance []instances = p.getRandomInstances(64, 2);
        //Instance []instances = p.getRepsBoundedInstances(128, 8);
        
        //Result R = ControlFlow.solveInstances(instances, new Parameters());
        //System.out.println(R);
        Instance []instances = p.getFamilyInstances(4096, 128);
        Result R = ControlFlow.solveInstances(instances, new Parameters());
        System.out.println(R);
    }
}