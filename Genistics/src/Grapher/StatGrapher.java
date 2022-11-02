/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher;

import genistics.Genistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Akman
 */
public class StatGrapher {
    private final PrintWriter writer;
    private ArrayList<Long> genlist= new ArrayList<>();//saves individual generations in a list
    private ArrayList<Double> phenlist= new ArrayList<>();//saves individual phenotypes in a list
    
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
        if ((genlist != null || genlist.size() != 0)&&(phenlist != null || phenlist.size() != 0)) {
            Average();
            Range();
            StandardDeviation();
            Best();
        }
        writer.close();
    }
    public void Writeln(String s){
        writer.println(s);
    }
    public void Write(String s){
        writer.print(s);
    }
    public void Writeln(int cycle,long generations,double bestphenotype){
        genlist.add(generations);
        phenlist.add(bestphenotype);
        writer.println(cycle+","+generations+","+bestphenotype);
    }
    private void Average(){
            double genaverage=genlist.stream().mapToLong(val -> val).average().orElse(0.0);
            double phenaverage=phenlist.stream().mapToDouble(val -> val).average().orElse(0.0);
            writer.println("Average,"+genaverage+","+phenaverage);
    }
    private void Range(){
        ArrayList<Long> sortedG=new ArrayList<>(genlist);
        ArrayList<Double> sortedP=new ArrayList<>(phenlist);
        long rangeG;
        double rangeP;
        Collections.sort(sortedG);
        Collections.sort(sortedP);
        rangeG=sortedG.get(sortedG.size()-1)-sortedG.get(0);
        rangeP=sortedP.get(sortedP.size()-1)-sortedP.get(0);
        writer.println("Range,"+rangeG+","+rangeP);
    }
    private void StandardDeviation(){
        double sdG=0,sdP=0,sumP=0,meanG,meanP;
        long sumG=0;
        for(long g:genlist){
            sumG+=g;
        }
        for(double p:phenlist){
            sumP+=p;
        }
        meanG=sumG/genlist.size();
        meanP=sumP/phenlist.size();
        for(long g:genlist){
            sdG+= Math.pow(g-meanG, 2);
        }
        for(double p:phenlist){
            sdP+= Math.pow(p-meanP, 2);
        }
        writer.println("Standard Deviation,"+sdG+","+sdP);
    }
    private void Best(){
        ArrayList<Long> sortedG=new ArrayList<>(genlist);
        ArrayList<Double> sortedP=new ArrayList<>(phenlist);
        Collections.sort(sortedG);
        Collections.sort(sortedP);
        writer.println("Best,"+sortedG.get(0)+","+sortedP.get(0));
    }
}
