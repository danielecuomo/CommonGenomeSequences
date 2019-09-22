package interfaces;

import structures.Instance;
import java.io.*;
import java.util.Scanner;

public class Parser{
    private static Scanner scanner;

    
    private final String randomInstancesPath = "./instances/Set1/";
    private final String repsBoundedPath = "./instances/Set2/";
    private final String familySamplesPath = "./instances/family/";

    private final Instance []instances = new Instance[30];
    private final Instance []familySample = new Instance[10];
    {
        for(int i = 0; i<30 ; i++)
            instances[i] = new Instance();
        for(int i = 0; i<10 ; i++)
            familySample[i] = new Instance();
    }

    public Instance []getRandomInstances(int stringSize, int d) throws FileNotFoundException{
        String fileName = stringSize + "_n-div-" + d + ".";

        for(int i=0; i<30; i++)
            readInstance(instances[i], randomInstancesPath, fileName + i);
          
        return instances;
    }
    
    public Instance []getRandomInstances(int stringSize, int n, int d) throws FileNotFoundException{
        String fileName = stringSize + "_" + n + "n-div-" + d + ".";

        for(int i=0; i<30; i++)
            readInstance(instances[i], randomInstancesPath, fileName + i);
          
        return instances;
    }

    public Instance []getRepsBoundedInstances(int alphabetSize, int reps) throws FileNotFoundException{
        String fileName = alphabetSize + "_" + reps + "reps.";

        for(int i=0; i<30; i++)
            readInstance(instances[i], repsBoundedPath, fileName + i);
          
        return instances;
    }

    private void readInstance(Instance I, String path) throws FileNotFoundException{
        File file = new File(path);
        scanner = new Scanner(file);
        scanner.next(); //useless character

        I.setAlphabetEncoding(scanner.nextInt());
        I.setX(getArrayFormat(scanner.nextInt()));
        I.setY(getArrayFormat(scanner.nextInt()));
        
        scanner.close();
    }
    private void readInstance(Instance I, String path, String fileName)throws FileNotFoundException{
        readInstance(I, path + fileName);
    }

    private int []getArrayFormat(int size){
            int []dat = new int[size];
            for(int i=0; i<size; i++)
                    dat[i] = scanner.nextInt();		
            return dat;
    }

    public Instance getInstance(String path) throws FileNotFoundException{
        readInstance(instances[0], path);
        return instances[0];
    }
    
    public Instance[] getFamilyInstances(int size, int n) throws FileNotFoundException {
        String fileName = size + "_" + n + "_";

        for(int i=0; i<10; i++)
            readInstance(familySample[i], familySamplesPath, fileName + i + ".rflcs");
          
        return familySample;
    }
    
    public Instance[] getFamilyInstances(int size, int n, int m) throws FileNotFoundException {
        String fileName = size + "_" + n + "_" + m + "_";

        for(int i=0; i<10; i++)
            readInstance(familySample[i], familySamplesPath, fileName + i + ".rflcs");
          
        return familySample;
    }
}