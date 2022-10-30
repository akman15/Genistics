/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher;

import genistics.Genistics;
import io.jenetics.DoubleGene;
import io.jenetics.Phenotype;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.ISeq;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 *
 * @author Akman
 */
public class Genographer {
    private String CurrentCycle="Cycle 1";
    private String Phenorecord="Phenotype:,";
    private String Genrecord="Generation:,";
    private double BestPhenotype=0.0;
    private double ABSBestPhenotype=0.0;
    private long Generations=0;
    private final PrintWriter writer;
    private boolean check=true;
    
    public Genographer(String filename) throws FileNotFoundException{
        /**
         * Create a new Genographer instance.
         * Checks if a file with the same name exists, if it does it increments the filename.
         * @param filename is used to indicate what the parameters that the current run of the engine runs on,
         * such as the selector,mutation probability etc.
         */
        int i=1;
        File file =new File(Genistics.GetGFolder().toString()+"\\"+filename+".csv");
        while(file.exists()){
            file= new File(Genistics.GetGFolder().toString()+"\\"+filename+"("+i+").csv");
            i++;
        }
        writer = new PrintWriter(file);
    }
    public void reset(){
        //resets the following variable to their original values.
        CurrentCycle="Cycle 1";
        Genrecord="Generation:,";
        Phenorecord="Phenotype:,";
        BestPhenotype=0;
    }
    public void add(String Cycle,int gen,double phenotype){
        /**
         * Add generational information to local variables,
         * if it detects that a cycle has ended, it writes the current values 
         * to a file and resets them.
         * @param Cycle is the name of the current cycle.
         * @param gen is the current generation.
         * @param phenotype is the current generation's best phenotypes' fitness.
         */
        check=true;
        if(CurrentCycle.isBlank() || !CurrentCycle.equals(Cycle)){
            writer.println(CurrentCycle+",Generations,"+Generations+",Best Phenotype,"+BestPhenotype);
            writer.println(Genrecord);
            writer.println(Phenorecord);
            reset();
            CurrentCycle=Cycle;
        }
        Genrecord=Genrecord+gen+",";
        Phenorecord=Phenorecord+phenotype+",";
    }
    public void setBestPhenotype(double phenotype){
        /**
         * Records the best phenotype in a cycle and the best in all cycles.
         * @param phenotype is a phenotypes' fitness.
         */
        this.BestPhenotype=phenotype;
        if(BestPhenotype<ABSBestPhenotype){
            this.ABSBestPhenotype=phenotype;
        }
    }
    public void setGenerations(long generations){
        //Sets the number of generations in a cycle.
        this.Generations=generations;
    }
    public void finish(){
        /**
         * Called when all cycles are ended, writes the final stored values and
         * closes the writer.
         */
        if(check){
            add("", 0, 0);
        }
        writer.close();
    }
    public void Pooldump(EvolutionResult<DoubleGene, Double> result,String Info){
        check=false;
        writer.print(Info);
        ISeq<Phenotype<DoubleGene, Double>> pop = result.population();
        for(int i=0;i<pop.length();i++){//Write only fitness value of phenotype
            writer.print(pop.get(i).fitness()+",");
        }
        writer.println();
        //writer.println(result.population());
    }
    public void Writeln(String s){
        writer.println(s);
    }
    public void Write(String s){
        writer.print(s);
    }
}
