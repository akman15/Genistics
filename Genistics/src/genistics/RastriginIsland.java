package genistics;

import Grapher.*;
import io.jenetics.DoubleGene;
import io.jenetics.MultiPointCrossover;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.Selector;
import io.jenetics.SinglePointCrossover;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import static io.jenetics.engine.EvolutionResult.toBestEvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.engine.InvertibleCodec;
import static io.jenetics.engine.Limits.bySteadyFitness;
import io.jenetics.util.DoubleRange;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Akman
 */
public class RastriginIsland {
    private final static double A=10,R=5.12;
    private final static int N=20;
    private static final InvertibleCodec<double[],DoubleGene > CODEC = Codecs.ofVector(DoubleRange.of(-R,R),N);
    static Genographer gngphr;
    static StatGrapher stats;
    private static double bestphenotype=Double.MAX_VALUE;
    static EvolutionResult<DoubleGene, Double> Island;
    static Archipelago island;
    static GenLimits limits;
    private static Simsettings[] settings;
    private static int cycle;
    private static String filename;
    public RastriginIsland(){
    }
    private static double fitness(double[] x){
        /**
         * Rastrigin fitness function
         * @param x is an array of two double variables
         */
        double y= A*N;
        for(int i=0;i<N; ++i){
            y+=(double)Math.pow(x[i], 2)-A*(double)Math.cos(2.0*Math.PI*x[i]);}
        return y;
    }
    private static String main(GenLimits limit,ArchipelagoSettings ArSet){
        
        /**
         * The function where the Engine for a group of cycle is initiated
         * @param settings contains information regarding engine configuration
         */
        limits=limit;
        cycle=0;
        
        filename="rastrigin_island_"+ArSet.IslandPop+"_"+(int)(ArSet.MigrationProb*100)+"-";
        Engine<DoubleGene, Double>[] engine=new Engine[ArSet.IslandPop];
        for(int i=0;i<ArSet.IslandPop;i++){ //create different engine object with different setting each according to each islands settings.
            if(settings[i].crossoverpoint==3){//sets number of crossover point
                settings[i].crossoverpoint=N/2;
            }
            filename=filename+settings[i].population+"_"+(int)settings[i].mutationprobability+"_"+(int)settings[i].crossoverprobability+"_"+settings[i].crossoverpoint+"_"+settings[i].selector.toString().charAt(0)+"-";
            engine[i] = Engine
                    .builder(RastriginIsland::fitness,
                    CODEC)
                    .populationSize(settings[i].population)
                    .optimize(Optimize.MINIMUM)
                    .alterers(
                            new Mutator<>((double)settings[i].mutationprobability/100.0),//search mutation meaning
                            new MultiPointCrossover<>((double)settings[i].crossoverprobability/100.0,settings[i].crossoverpoint)
                            )
                    .offspringFraction(1)
                    .selector((Selector<DoubleGene, Double>) settings[i].selector.getValue())
                    .build();
        }
        filename=filename.replaceAll(" ","_");
        filename=filename.substring(0,filename.length()-1);
        
        final EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();//evolution statistics, not really used,needs replacing
        /**
         * Creates a new string filename with information on the current run settings and
         * creates a genographer instance with the filename as a parameter.
         */
        try {//create Genographer object
            gngphr = new Genographer(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RastriginIsland.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {//create StatGrapher object
            stats = new StatGrapher(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RastriginIsland.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * Runs the engine for multiple cycles and records relevant information to 
         * the genographer instance.
         * todo made number of cycles modular
        */
        String output;
        do{
            island=new Archipelago(ArSet,limit,gngphr);
            limit.setRepGen(0);
            cycle++;
            do{
                Island=engine[island.getIslandInc()].stream(island.LoadDouble(),island.getgen()).limit(1).peek(statistics).peek(RastriginIsland::update).collect(toBestEvolutionResult());
                island.SaveDouble(Island);
                //System.out.println(finalbest.population().toString());
            }while(!island.limit());
            if(island.getBestPhenotypeDouble().fitness()<bestphenotype){
                bestphenotype=island.getBestPhenotypeDouble().fitness();
            }
            output="Limits: Min="+limit.getMinGens()
                    +" Max="+limit.getMaxGens()+" Reps="+limit.getMaxReps()+" Data collecting interval:"+limit.getCGD()+" Cycles:"+limit.getMaxCycles()
                    +"\nGenerations:"+island.getgen()+"\nBest Phenotype:"+island.getBestPhenotypeDouble().fitness();
            for(int i=0;i<ArSet.IslandPop;i++){
                output=output+"\nIsland "+i+":"+"\nSelector:"+settings[i].selector.toString()+" Population:"+settings[i].population+" "+engine[i].alterer().toString()
                        +"\nMutation Probability:"+(int)settings[i].mutationprobability+" Crossover probability:"+(int)settings[i].crossoverprobability+" Crossover Point:"+settings[i].crossoverpoint;
            }
            gngphr.Writeln(output);
            stats.Writeln(cycle,island.getgen(),island.getBestPhenotypeDouble().fitness());
        }while(limit.getMaxCycles()>cycle);
        
        gngphr.finish();
        stats.finish();
        
        output="Selector:"+settings[0].selector.toString()+"\nPopulation:"+settings[0].population+"\nLimits: Min="+limit.getMinGens()+" Max="+limit.getMaxGens()+" Reps="+limit.getMaxReps()+" Data collecting interval:"+limit.getCGD()+" Cycles:"+limit.getMaxCycles()+"\n"+engine[0].alterer().toString()+"\n"+statistics.toString()+"\nBest Phenotype overall:"+bestphenotype;
        return (output);//todo need to remove
    }
    private static void update(final EvolutionResult<DoubleGene, Double> result){
        if((result.totalGenerations())%limits.getCGD()<1 | result.totalGenerations()==1){
            gngphr.PooldumpDouble(result,"Cycle:"+cycle+",Gen:"+result.totalGenerations()+",Island:"+(island.getIslandInc()+1)+",");
        }
    }
    public static void Results(GenLimits limit,ArchipelagoSettings ArSet){
        /**
         * Creates a new window that displays the EvolutionStatistics results
         * as well as other information, of the last cycle.
         * todo remove altogether or change to full run statistics
         */
        JFrame f = new JFrame("frame");
        f.setAlwaysOnTop(true);
        JPanel d = new JPanel();
        d.setLayout(new GridLayout(1,1));
        
        settings=new Simsettings[ArSet.IslandPop];
        for(int i=0;i<ArSet.IslandPop;i++){
            IslandMenu dialog = new IslandMenu(f,true);
            settings[i]=dialog.showDialog();
        }

        JTextArea l = new JTextArea(RastriginIsland.main(limit,ArSet));
        d.add(l);
        f.add(d);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
