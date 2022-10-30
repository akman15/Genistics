/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics;

/**
 *
 * @author Akman
 */
public class GenLimits {//Class which stores limit values
    private int MaxGens=1;
    private int MinGens=0;
    private int MaxReps=1;
    private int MaxCycles=1;
    private long RepGen=0; //the generation when the earliest instance of the best Phenotype was found
    private int CGD=1;//Collect generation data interval
    GenLimits (int MaxGens,int MinGens,int MaxReps,int CGD,int MaxCycles){
        this.MaxGens = MaxGens;
        this.MaxReps = MaxReps;
        this.MinGens = MinGens;
        this.CGD=CGD;
        this.MaxCycles=MaxCycles;
    }

    public int getMaxCycles() {
        return MaxCycles;
    }

    public int getCGD() {
        return CGD;
    }

    public long getRepGen() {
        return RepGen;
    }

    public void setRepGen(long RepGen) {
        this.RepGen = RepGen;
    }

    public int getMaxGens() {
        return MaxGens;
    }

    public int getMinGens() {
        return MinGens;
    }

    public int getMaxReps() {
        return MaxReps;
    }
    
}
