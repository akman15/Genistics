/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher;

import genistics.Genistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Akman
 */
public class StatGrapher {
    private final PrintWriter writer;
    public StatGrapher(String filename) throws FileNotFoundException{
        /**
         * Create a new StatGrapher instance.
         * Checks if a file with the same name exists, if it does it increments the filename.
         * @param filename is used to indicate what the parameters that the current run of the engine runs on,
         * such as the selector,mutation probability etc.
         */
        int i=1;
        File file =new File(Genistics.GetGFolder().toString()+"\\"+filename+"stats.csv");
        while(file.exists()){
            file= new File(Genistics.GetGFolder().toString()+"\\"+filename+"("+i+")stats.csv");
            i++;
        }
        writer = new PrintWriter(file);
        writer.println("Cycle,Generations,Best Phenotype fitness");
    }
    public void finish(){
        /**
         * Called when all cycles are ended, writes the final stored values and
         * closes the writer.
         */
        /*if(check){
            add("", 0, 0);
        }
        */
        writer.close();
    }
    public void Writeln(String s){
        writer.println(s);
    }
    public void Write(String s){
        writer.print(s);
    }
}
