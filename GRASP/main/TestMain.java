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
    static String path = "C:\\Users\\ASUS\\Desktop\\TESI\\NetBeans Projects\\[!] GRASP + ls\\instances\\Set1\\4096_7n-div-8.14";
    public static void main(String[] args) throws IOException{
        //Parser p = new Parser();
  
        //ControlFlow cf = new ControlFlow(p.getInstance(path), new Parameters());
        //Result R = cf.solveInstance();
        //Instance []instances = p.getFamilyInstances(768, 24);
        //Instance []instances = p.getFamilyInstances(5760, 60 , 8);
        //Result R = ControlFlow.solveInstances(instances, new Parameters());
        ControlFlow.massiveExec(new Parameters());
        //System.out.println(R);
    }
}