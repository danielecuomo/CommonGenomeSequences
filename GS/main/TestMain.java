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
    static String path = "C:\\Users\\ASUS\\Desktop\\irace-sample\\instances\\256-1_0.625.rflcs";
    public static void main(String[] args) throws IOException{
        //ControlFlow.massiveExec(new Parameters());

        Parser p = new Parser();
  
        /*ControlFlow cf = new ControlFlow(p.getInstance(path), new Parameters());
        Result R = cf.solveInstance();*/
        /*Instance []instances = p.getRandomInstances(512, 8);
        Result R = ControlFlow.solveInstances(instances, new Parameters());
        
        System.out.println(R);*/
        Instance []instances = p.getFamilyInstances(5760, 60 , 8);
        Result R = ControlFlow.solveInstances(instances, new Parameters());
        //ControlFlow.massiveExec(new Parameters());
        System.out.println(R);
    }
}