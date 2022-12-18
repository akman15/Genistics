package genistics;

import Grapher.*;
import io.jenetics.EnumGene;
import io.jenetics.Optimize;
import io.jenetics.PartiallyMatchedCrossover;
import io.jenetics.Selector;
import io.jenetics.SwapMutator;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.Problem ;
import static io.jenetics.engine.EvolutionResult.toBestEvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.util.ISeq;
import io.jenetics.util.MSeq;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Akman
 * An implementation of the TSP with Jenetics based on Jenetics TSP example solution
 */
public class TSPIsland implements Problem<ISeq<double[]>,EnumGene<double[]>,Double>{
    private final ISeq<double[]> _points;
    private final static int stops=20;
    private final static double radius=10;
    static Genographer gngphr;
    static StatGrapher stats;
    private static double bestphenotype=Double.MAX_VALUE;
    static EvolutionResult<EnumGene<double[]>,Double> Island;
    static Archipelago island;
    static GenLimits limits;
    private static Simsettings[] settings;
    private static int cycle;
    private static String filename;
    
    public TSPIsland(){
        final MSeq<double[]> points = MSeq.ofLength(stops);
        final double delta=2.0*Math.PI/stops;
        for (int i=0;i<stops;i++){
            final double alpha=delta*i;
            final double x = Math.cos(alpha)*radius+radius;
            final double y = Math.sin(alpha)*radius+radius;
            points.set(i, new double[]{x,y});
        }
        _points = points.toISeq();
    }
    
    @Override
    public Function<ISeq<double[]>, Double> fitness(){
        /**
         * TSP fitness function
         */
        return d -> IntStream.range(0, d.length()).mapToDouble(i->{
                final double[] p1=d.get(i);
                final double[] p2=d.get((i+1)%d.size());
                return Math.hypot(p1[0]-p2[0],p1[1]-p2[1]);})
        .sum();
    }
    
    @Override
    public  Codec<ISeq<double[]>,EnumGene<double[]>> codec(){
        return Codecs.ofPermutation(_points);
    }
    private static String main(GenLimits limit,ArchipelagoSettings ArSet){
        
        /**
         * The function where the Engine for a group of cycle is initiated
         * @param settings contains information regarding engine configuration
         */
        limits=limit;
        cycle=0;
        TSPIsland tsp = new TSPIsland();
        double minPathLength = 2.0*stops*radius*Math.sin(Math.PI/stops); //Absolute minimum based on the radius and stops
        //System.out.println("Min path length:"+minPathLength);
        
        filename="TSP_island_"+ArSet.IslandPop+"_"+(int)(ArSet.MigrationProb*100)+"-";
        Engine<EnumGene<double[]>,Double>[] engine=new Engine[ArSet.IslandPop];
        for(int i=0;i<ArSet.IslandPop;i++){ //create different engine object with different setting each according to each islands settings.
            if(settings[i].crossoverpoint==3){//sets number of crossover point
                settings[i].crossoverpoint=stops/2;
            }
            filename=filename+settings[i].population+"_"+(int)settings[i].mutationprobability+"_"+(int)settings[i].crossoverprobability+"_"+settings[i].crossoverpoint+"_"+settings[i].selector.toString().charAt(0)+"-";
            engine[i] = Engine
                    .builder(tsp)
                    .populationSize(settings[i].population)
                    .optimize(Optimize.MINIMUM)
                    .alterers(
                            new SwapMutator<>((double)settings[i].mutationprobability/100.0),//search mutation meaning
                            new PartiallyMatchedCrossover<>((double)settings[i].crossoverprobability/100.0)
                            )
                    .offspringFraction(1)
                    .selector((Selector<EnumGene<double[]>,Double>) settings[i].selector.getValue())
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
            Logger.getLogger(TSPIsland.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {//create StatGrapher object
            stats = new StatGrapher(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TSPIsland.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * Runs the engine for multiple cycles and records relevant information to 
         * the genographer instance.
        */
        String output;
        do{
            island=new Archipelago(ArSet,limit,gngphr);
            limit.setRepGen(0);
            cycle++;
            do{
                Island=engine[island.getIslandInc()].stream(island.LoadEnum(),island.getgen()).limit(1).peek(statistics).peek(TSPIsland::update).collect(toBestEvolutionResult());
                island.SaveEnum(Island);
                //System.out.println(finalbest.population().toString());
            }while(!island.limit() && island.getBestPhenotypeEnum().fitness()>minPathLength);//stops searching if the absolute best is found
            if(island.getBestPhenotypeEnum().fitness()<bestphenotype){
                bestphenotype=island.getBestPhenotypeEnum().fitness();
            }
            output="Limits: Min="+limit.getMinGens()
                    +" Max="+limit.getMaxGens()+" Reps="+limit.getMaxReps()+" Data collecting interval:"+limit.getCGD()+" Cycles:"+limit.getMaxCycles()
                    +"\nGenerations:"+island.getgen()+"\nBest Phenotype:"+island.getBestPhenotypeEnum().fitness();
            for(int i=0;i<ArSet.IslandPop;i++){
                output=output+"\nIsland "+i+":"+"\nSelector:"+settings[i].selector.toString()+" Population:"+settings[i].population+" "+engine[i].alterer().toString()
                        +"\nMutation Probability:"+(int)settings[i].mutationprobability+" Crossover probability:"+(int)settings[i].crossoverprobability+" Crossover Point:"+settings[i].crossoverpoint;
            }
            gngphr.Writeln(output);
            stats.Writeln(cycle,island.getgen(),island.getBestPhenotypeEnum().fitness());
        }while(limit.getMaxCycles()>cycle);
        
        gngphr.finish();
        stats.finish();
        
        output="";
        try {
            Scanner scanner = new Scanner(new File(stats.getfilename()));
            while(scanner.hasNextLine()){
                output = output+"\n"+scanner.nextLine();
            }
            scanner.close();
            } catch (FileNotFoundException ex) {
            Logger.getLogger(RastriginIsland.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (output);
    }
    private static void update(final EvolutionResult<EnumGene<double[]>,Double> result){
        if((result.totalGenerations())%limits.getCGD()<1 | result.totalGenerations()==1){
            gngphr.PooldumpEnum(result,"Cycle:"+cycle+",Gen:"+result.totalGenerations()+",Island:"+(island.getIslandInc()+1)+",");
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

        JTextArea l = new JTextArea("Running...");
        l.setEditable(false);
        JScrollPane sp = new JScrollPane(l);
        d.add(sp);
        f.add(d);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        Thread mainThread = new Thread(() -> {
            l.setText(TSPIsland.main(limit,ArSet));
            f.pack();
        });
        mainThread.start();
    }
}
