/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics;

import Grapher.Genographer;
import io.jenetics.DoubleGene;
import io.jenetics.Phenotype;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.ISeq;
import java.util.ArrayList;
import java.util.List;
import genistics.GenPO.GenPost;
import genistics.GenPO.MailDouble;
import genistics.GenPO.MailEnum;
import io.jenetics.EnumGene;
import io.jenetics.util.MSeq;
import java.util.Random;

/**
 *
 * @author Akman
 */
public class Archipelago {//A collection of islands
    private final int IslandPop;
    private int IslandInc=0;
    private Phenotype<DoubleGene, Double> BestPhenotypeDouble=null;
    private Phenotype<EnumGene<double[]>,Double> BestPhenotypeEnum=null;
    private List<Long> gen=null;
    private List<ISeq<Phenotype<DoubleGene, Double>>> populationDouble;
    private List<ISeq<Phenotype<EnumGene<double[]>,Double>>> populationEnum;
    //private Simsettings[] settings;
    private Genographer gngrphr;
    private GenPost post;
    private int MigrationInterval=0;
    private GenLimits limit;
    private int Type=1,MigrationRate=5;
    Archipelago(ArchipelagoSettings ArSet,GenLimits limit,Genographer gngrphr){
        this.gngrphr=gngrphr;
        this.IslandPop=ArSet.IslandPop-1;//from base 1 to base 0
        this.MigrationInterval=ArSet.MigrationInterval;
        this.limit=limit;
        this.Type=ArSet.migrationtype;
        this.MigrationRate=ArSet.MigrationRate;
        gen = new ArrayList();
        populationDouble = new ArrayList();
        populationEnum = new ArrayList();
        //fortransfer = new LinkedList();
        post=new GenPost(IslandPop);
        for(int i=0;i<=IslandPop;i++){
            gen.add((long)1);
        }
    }

    public Phenotype<DoubleGene, Double> getBestPhenotypeDouble() {
        return BestPhenotypeDouble;
    }
    public Phenotype<EnumGene<double[]>,Double> getBestPhenotypeEnum() {
        return BestPhenotypeEnum;
    }
    /*
        Saves the population of the result argument to a list
    */
    public void SaveDouble(EvolutionResult<DoubleGene, Double> result){
        ISeq<Phenotype<DoubleGene, Double>> pop1 = result.population();
        ISeq<Phenotype<DoubleGene, Double>> pop2 = ISeq.empty();
        //System.out.println("population 1:"+pop1.toString());
        //System.out.println("Adding last"+fortransfer.peekLast());
        
        try{//save the population in the population 
            populationDouble.set(IslandInc,pop1);
        }catch(Exception e){
            populationDouble.add(pop1);
        }
        
        if(BestPhenotypeDouble==null){//save best phenotype for limit purpose
            BestPhenotypeDouble=result.bestPhenotype();
        }else{
            if(BestPhenotypeDouble.fitness()>result.bestPhenotype().fitness()){
                BestPhenotypeDouble=result.bestPhenotype();
                limit.setRepGen(getgen());
            }
        }
                
        /*if((Math.random()+MigrationRate)>1 && gen.get(IslandInc)>0 && IslandPop>0){ //random chance for transfer between islands
            if(IslandPop==1){
                //gngrphr.Writeln("fortransfer to Island "+Inext()+":"+result.bestPhenotype().fitness());
                MailDouble mail = new MailDouble(result.bestPhenotype(), Inext() , IslandInc);
                post.sentDouble(mail);
            }
            else{
                int randisland=randIsland();
                //gngrphr.Writeln("fortransfer to Island "+randisland+":"+result.bestPhenotype().fitness());
                MailDouble mail = new MailDouble(result.bestPhenotype(), randisland , IslandInc);
                post.sentDouble(mail);
            }
            //population.set(IslandInc,pop2);//save the new ISeq without the mailed phenotype
        }*/
        
        if(IslandPop>0 && IslandInc==IslandPop && getgen()>1 && ((int)getgen()%MigrationInterval)==0){
            //Causes a migration event if the island is the last of archipelago,more than 1 islands exist, 
            //the gen more than 1 and the modulo of the current gen and the Migration Rate is 0
            //System.out.println("Migration event on generation "+getgen()+" with migration rate "+((int)getgen()%MigrationRate));
            MigrationD();
        }
        //System.out.println("population 2:"+pop2.toString());
    }
    
    // Save for EnumGene
    public void SaveEnum(EvolutionResult<EnumGene<double[]>,Double> result){
        ISeq<Phenotype<EnumGene<double[]>,Double>> pop1 = result.population();
        ISeq<Phenotype<EnumGene<double[]>,Double>> pop2 = ISeq.empty();
        try{//save the population in the population 
            populationEnum.set(IslandInc,pop1);
        }catch(Exception e){
            populationEnum.add(pop1);
        }
        
        if(BestPhenotypeEnum==null){//save best phenotype for limit purpose
            BestPhenotypeEnum=result.bestPhenotype();
        }else{
            if(BestPhenotypeEnum.fitness()>result.bestPhenotype().fitness()){
                BestPhenotypeEnum=result.bestPhenotype();
                limit.setRepGen(getgen());
            }
        }
                
        /*if((Math.random()+MigrationRate)>1 && gen.get(IslandInc)>0 && IslandPop>0){ //random chance for transfer between islands
            if(IslandPop==1){
                MailEnum mail = new MailEnum(result.bestPhenotype(), Inext() , IslandInc);
                post.sentEnum(mail);
            }
            else{
                int randisland=randIsland();
                MailEnum mail = new MailEnum(result.bestPhenotype(), randisland , IslandInc);
                post.sentEnum(mail);
            }
        }*/
        if(IslandPop>0 && IslandInc==IslandPop && getgen()>1 && ((int)getgen()%MigrationInterval)==0){
            //Causes a migration event if the island is the last of archipelago,more than 1 islands exist, 
            //the gen more than 1 and the modulo of the current gen and the Migration Rate is 0
            System.out.print("\nMigration event on generation "+getgen()+" with migration interval "+MigrationInterval);
            MigrationE();
        }
        //System.out.println("population 2:"+pop2.toString());
    }
    
    /*
     returns the population of the current island index or an empty one and splices into the population any
     migrating phenotype if there are any
    */
    public ISeq<Phenotype<DoubleGene, Double>> LoadDouble(){
        try{
            /*
            ISeq<Phenotype<DoubleGene, Double>> pop =populationDouble.get(getIslandInc());
                if(IslandPop>0){
                    MailDouble incomingmail=post.recieveDouble(IslandInc);
                    if(incomingmail!=null){
                        //System.out.println(incomingmail);
                        //gngrphr.Writeln("Appending to Island "+IslandInc+":"+incomingmail.getmail());
                        pop=pop.append(incomingmail.getmail());
                        //gngrphr.Writeln("population loading Island "+IslandInc+" population:"+pop.toString());
                    }
                }
            return pop;
        }*/
            MSeq<Phenotype<DoubleGene, Double>> Mpop =populationDouble.get(getIslandInc()).asMSeq();
                if(IslandPop>0){
                    MailDouble[] incomingmail=post.recieveDouble(IslandInc);
                    if(incomingmail!=null){
                        Mpop.sort();
                        Phenotype<DoubleGene, Double> bestPhenotype=Mpop.get(0);
                        int bestPhenotypeIndex=0;
                        
                        int randindex=Mpop.length()-1; 
                        for(MailDouble m:incomingmail){
                            Mpop.set(randindex, m.getmail());
                            randindex--;
                        }
                        return Mpop.asISeq();
                    }
            }
            return Mpop.asISeq();
        }
        catch(Exception e){
            return ISeq.empty();
        }
    }
    public ISeq<Phenotype<EnumGene<double[]>,Double>> LoadEnum(){
        try{
            MSeq<Phenotype<EnumGene<double[]>,Double>> Mpop =populationEnum.get(getIslandInc()).asMSeq();
            if(IslandPop>0){
                MailEnum[] incomingmail=post.recieveEnum(IslandInc);
                if(incomingmail!=null){
                    
                    Mpop.sort();
                    
                    /*System.out.print("\nGeneration:"+getgen()+"\npopulation 1:");
                    for(Phenotype<EnumGene<double[]>,Double> p:Mpop){
                        System.out.print(p.fitness()+" ");
                    }*/
                    int randindex=Mpop.length()-1; 
                    
                    //System.out.println("Replacing with phenotype "+incomingmail.getmail().fitness()+" at index "+randindex);
                    for(MailEnum m:incomingmail){
                        Mpop.set(randindex, m.getmail());
                        randindex--;
                    }
                    
                    /*System.out.print("\npopulation 2:");
                    for(Phenotype<EnumGene<double[]>,Double> p:Mpop){
                        System.out.print(p.fitness()+" ");
                    }
                    System.out.println("");*/
                    
                    return Mpop.asISeq();
                }
            }
            return Mpop.asISeq();
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
            return gen.get(getIslandInc());}
        catch(Exception e){
            return 1;
        }
    }
    public boolean limit(){
        if(getgen()>(long)limit.getMinGens() && (getgen()-limit.getRepGen())>=limit.getMaxReps() && limit.getMaxReps()>0){
            //Check if this reached min gens and if there have been more repetitions of the best
            //Phenotype than the max.
            return true;
        }
        if(getgen()>=limit.getMaxGens() ){
            return true;
        }
        return false;
    }
    public void IncrementI(){
        IslandInc++;
        if(IslandInc>IslandPop){
            IslandInc=0;
        }
    }
    public void IncrementGen(){
        gen.set(getIslandInc(),gen.get(getIslandInc())+1);
    }
    public void SetGen(long generations){
        gen.set(getIslandInc(),generations);
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
    public void MigrationD(){
        if(IslandPop>0){
            MSeq<Phenotype<DoubleGene, Double>> pop;
            List<Phenotype<DoubleGene, Double>> bestPhenotype=new ArrayList();
            
            int randindex=0; 
            Random rand = new Random();
            randindex=rand.nextInt(IslandPop+1);
            
            pop=populationDouble.get(randindex).asMSeq();
            pop.sort();
            for(int i=0;i<MigrationRate;i++){
                bestPhenotype.add(pop.get(i));
            }
            
            switch(Type){
                case 0://type 0 is to all other islands
                    for(int i=0;i<=IslandPop;i++){
                        if(i!=randindex){
                            MailDouble mail[]=new MailDouble[MigrationRate];
                            for(int j=0;j<MigrationRate;j++){
                                try{
                                    mail[j] = new MailDouble(bestPhenotype.get(j), i , randindex);
                                }catch(Exception e){
                                    System.out.println(e.toString());
                                }
                            }
                            post.sentDouble(mail);
                        }
                    }
                    break;
                case 1://type 1 is to a random island
                    int randsent=rand.nextInt(IslandPop);
                    if(randsent>=randindex){
                        randsent=randsent+1;              
                    }
                    MailDouble mail[]=new MailDouble[MigrationRate];
                    for(int j=0;j<MigrationRate;j++){
                        mail[j] = new MailDouble(bestPhenotype.get(j), randsent , randindex);
                    }
                    post.sentDouble(mail);
                    break;
                case 2://type 2 is Round Robin
                    for(int i=0;i<=IslandPop;i++){
                        MailDouble mailR[]=new MailDouble[MigrationRate];
                        pop=populationDouble.get(i).asMSeq();
                        pop.sort();
                        for(int j=0;j<MigrationRate;j++){
                            mailR[j] = new MailDouble(pop.get(j), (i+1)%(IslandPop+1) , i);
                        }
                        post.sentDouble(mailR);
                    }
                    break;
                }
        }
    }
    public void MigrationE(){
        if(IslandPop>0){
            MSeq<Phenotype<EnumGene<double[]>,Double>> pop;
            List<Phenotype<EnumGene<double[]>,Double>> bestPhenotype=new ArrayList();
            
            int randindex=0; 
            Random rand = new Random();
            randindex=rand.nextInt(IslandPop+1);
            
            pop=populationEnum.get(randindex).asMSeq();
            pop.sort();
            //System.out.println("\nSelected senter Island "+randindex+" migration type "+Type);
            for(int i=0;i<MigrationRate;i++){
                bestPhenotype.add(pop.get(i));
            }
            switch(Type){
                case 0://type 0 is to all other islands
                    for(int i=0;i<=IslandPop;i++){
                        if(i!=randindex){
                            MailEnum mail[] = new MailEnum[MigrationRate];
                            for(int j=0;j<MigrationRate;j++){
                                try{
                                    mail[j] = new MailEnum(bestPhenotype.get(j), i , randindex);
                                    //System.out.println("Sent phenotype "+bestPhenotype.get(j).fitness()+" to island "+i+" from island "+randindex+" on generation "+getgen());
                                }catch(Exception e){
                                    System.out.println(e.toString());
                                }
                            }
                            post.sentEnum(mail);
                        }
                    }
                    break;
                case 1://type 1 is to a random island
                    int randsent=rand.nextInt(IslandPop);
                    if(randsent>=randindex){
                        randsent=randsent+1;              
                    }
                    MailEnum mail[]=new MailEnum[MigrationRate];
                    for(int j=0;j<MigrationRate;j++){
                        mail[j] = new MailEnum(bestPhenotype.get(j), randsent , randindex);
                        //System.out.println("\nSent phenotype "+bestPhenotype.get(j).fitness()+" to island "+randsent+" from island "+randindex+" on generation "+getgen());
                    }
                    post.sentEnum(mail);
                    break;
                case 2://type 2 is Round Robin
                for(int i=0;i<=IslandPop;i++){
                    MailEnum mailR[]=new MailEnum[MigrationRate];
                    pop=populationEnum.get(i).asMSeq();
                    pop.sort();
                    for(int j=0;j<MigrationRate;j++){
                        mailR[j] = new MailEnum(pop.get(j), (i+1)%(IslandPop+1) , i);
                        //System.out.println("\nSent phenotype "+bestPhenotype.get(j).fitness()+" to island "+(i+1)%(IslandPop+1)+" from island "+i+" on generation "+getgen());
                    }
                    post.sentEnum(mailR);
                }
                break;
                }
        }
    }
}