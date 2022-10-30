/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics;

import Grapher.Genographer;
import genistics.GenPO.GenMail;
import genistics.GenPO.GenPost;
import io.jenetics.DoubleGene;
import io.jenetics.Phenotype;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.ISeq;
import io.jenetics.util.MSeq;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import genistics.GenPO.GenPost;
import java.util.Random;

/**
 *
 * @author Akman
 */
public class Archipelago {//A collection of islands
    private final int IslandPop;
    private int IslandInc=0;
    private Phenotype<DoubleGene, Double> BestPhenotype=null;
    private List<Long> gen=null;
    private boolean inited=false;
    private List<ISeq<Phenotype<DoubleGene, Double>>> population;
    //private Simsettings[] settings;
    private Genographer gngrphr;
    private GenPost post;
    private double MigrationProb=0.0;
    private GenLimits limit;
    private int Cycles=1;
    Archipelago(ArchipelagoSettings ArSet,GenLimits limit,Genographer gngrphr){
        this.gngrphr=gngrphr;
        this.IslandPop=ArSet.IslandPop-1;//from base 1 to base 0
        this.MigrationProb=ArSet.MigrationProb;
        this.limit=limit;
        gen = new ArrayList();
        population = new ArrayList();
        //fortransfer = new LinkedList();
        post=new GenPost(IslandPop);
    }

    public Phenotype<DoubleGene, Double> getBestPhenotype() {
        return BestPhenotype;
    }
    /*
        Saves the population of the result argument to a list
    */
    public void Save(EvolutionResult<DoubleGene, Double> result){
        ISeq<Phenotype<DoubleGene, Double>> pop1 = result.population();
        ISeq<Phenotype<DoubleGene, Double>> pop2 = ISeq.empty();
        //System.out.println("population 1:"+pop1.toString());
        //System.out.println("Adding last"+fortransfer.peekLast());
        if(inited){//checks if the gen list is initiated,if yes it increments the int in the position, if not add the value 1.
            gen.set(getIslandInc(),gen.get(getIslandInc())+1);
        }else{
            gen.add((long)1);
        }
        
        try{//save the population in the population 
            population.set(IslandInc,pop1);
        }catch(Exception e){
            population.add(pop1);
        }
        
        if(BestPhenotype==null){//save best phenotype for limit purpose
            BestPhenotype=result.bestPhenotype();
        }else{
            if(BestPhenotype.fitness()>result.bestPhenotype().fitness()){
                BestPhenotype=result.bestPhenotype();
                limit.setRepGen(getgen());
            }
        }
                
        if((Math.random()+MigrationProb)>1 && gen.get(IslandInc)>0 && IslandPop>0){ //random chance for transfer between islands
            Boolean found=false;
            //gngrphr.Writeln("population 1:"+pop1.toString());
            for(int i=0;i<pop1.length();i++){//create a new ISeq without the best phenotype
                if(pop1.get(i)==result.bestPhenotype() && !found){
                  found=true;
                }else{
                    pop2=pop2.append(pop1.get(i));
                }
            }
            //gngrphr.Writeln("population 2:"+pop2.toString());
            if(IslandPop==1){
                //gngrphr.Writeln("fortransfer to Island "+Inext()+":"+result.bestPhenotype().fitness());
                GenMail mail = new GenMail(result.bestPhenotype(), Inext() , IslandInc);
                post.sent(mail);
            }
            else{
                int randisland=randIsland();
                //gngrphr.Writeln("fortransfer to Island "+randisland+":"+result.bestPhenotype().fitness());
                GenMail mail = new GenMail(result.bestPhenotype(), randisland , IslandInc);
                post.sent(mail);
            }
            population.set(IslandInc,pop2);//save the new ISeq without the mailed phenotype
        }
        
        IncrementI();
        //System.out.println("population 2:"+pop2.toString());
    }
    
    /*
     returns the population of the current island index or an empty one and splices into the population any
     migrating phenotype if there are any
    */
    public ISeq<Phenotype<DoubleGene, Double>> Load(){
        try{
            ISeq<Phenotype<DoubleGene, Double>> pop =population.get(getIslandInc());
                if(IslandPop>0){
                    GenMail incomingmail=post.recieve(IslandInc);
                    if(incomingmail!=null){
                        //System.out.println(incomingmail);
                        //gngrphr.Writeln("Appending to Island "+IslandInc+":"+incomingmail.getmail());
                        pop=pop.append(incomingmail.getmail());
                        //gngrphr.Writeln("population loading Island "+IslandInc+" population:"+pop.toString());
                    }
                }
            return pop;
        }
        catch(Exception e){
            return ISeq.empty();
        }
    }
    /**
     * @return the Generations
     */
    public long getgen(){
        try{
            return gen.get(getIslandInc())+1;}
        catch(Exception e){
            return 1;
        }
    }
    public boolean limit(){
        if(getgen()>(long)limit.getMinGens() && (getgen()-limit.getRepGen())>limit.getMaxReps() && limit.getMaxReps()>0){
            //Check if this reached min gens and if there have been more repetitions of the best
            //Phenotype than the max.
            return true;
        }
        if(getgen()>limit.getMaxGens() ){
            return true;
        }
        return false;
    }
    private void IncrementI(){
        IslandInc++;
        if(IslandInc>IslandPop){
            IslandInc=0;
            inited=true;
        }
    }
    /**
     * @return the next island increment
     */
    private int Inext(){
        int inext=IslandInc+1;
        if(inext>IslandPop){
            inext=0;
        }
        return inext;
    }
    /**
     * @return the IslandInc
     */
    public int getIslandInc() {
        return IslandInc;
    }
    public int randIsland(){//return the index of a random island that isn't the current one
        List<Integer> islandlist = new ArrayList<>();
        int randindex; 
        Random rand = new Random();
        for(int i=0;i<IslandPop;i++){
            if(i!=getIslandInc()){
                islandlist.add(i);
            }
        }
        randindex = islandlist.get(rand.nextInt(islandlist.size()));
        //System.out.println("Choosen Island #"+randindex+" for receiver.");
        return randindex;
    }
    }