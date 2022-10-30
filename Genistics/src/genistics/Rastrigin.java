package genistics;

import Grapher.Genographer;
import io.jenetics.DoubleGene;
import io.jenetics.MultiPointCrossover;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.Selector;
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
public class Rastrigin {
    private final static double A=10,R=5.12;
    private final static int N=20;
    private static final InvertibleCodec<double[],DoubleGene > CODEC = Codecs.ofVector(DoubleRange.of(-R,R),N);
    static Genographer gngphr;
    private static int cycle;
    private static double bestphenotype=Double.MAX_VALUE;
    static EvolutionResult<DoubleGene, Double> finalbest;
    public Rastrigin(){
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
    private static String main(Simsettings settings){
        /**
         * The function where the Engine for a group of cycle is initiated
         * @param settings contains information regarding engine configuration
         */
        if(settings.crossoverpoint==3){//sets number of crossover point
            settings.crossoverpoint=N/2;
        }
        final Engine<DoubleGene, Double> engine = Engine
                .builder(
                Rastrigin::fitness,
                CODEC)
                .populationSize(settings.population)
                .optimize(Optimize.MINIMUM)
                .alterers(
                        new Mutator<>(settings.mutationprobability/100),//search mutation meaning
                        new MultiPointCrossover<>(settings.crossoverprobability/100,settings.crossoverpoint)
                        )
                .survivorsFraction(0).offspringFraction(1)//survivor chance 0%
                .selector((Selector<DoubleGene, Double>) settings.selector.getValue())
                .build();
        
        final EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();//evolution statistics, not really used,needs replacing
        /**
         * Creates a new string filename with information on the current run settings and
         * creates a genographer instance with the filename as a parameter.
         */
        String filename="rastrigin_"+settings.selector.toString()+"_m"+settings.mutationprobability+"_c"+settings.crossoverprobability+"_cp"+settings.crossoverpoint;
        filename=filename.replaceAll(" ","_");
        try {
            gngphr = new Genographer(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Rastrigin.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         * Runs the engine for multiple cycles and records relevant information to 
         * the genographer instance.
         * todo made number of cycles modular
         */
        for(cycle=1;cycle<=50;cycle++){
            finalbest = engine.stream().limit(bySteadyFitness(500)).limit(5000).peek(statistics).peek(Rastrigin::update).collect(toBestEvolutionResult());
            gngphr.setBestPhenotype(finalbest.bestFitness());
            gngphr.setGenerations(finalbest.totalGenerations());
            if(finalbest.bestFitness()<bestphenotype){
                bestphenotype=finalbest.bestFitness();
            }
        }
        gngphr.finish();
        return ("Selector:"+settings.selector.toString()+"\nPopulation:"+settings.population+"\n"+engine.alterer().toString()+"\n"+statistics.toString()+"\n"+bestphenotype);//todo need to remove
    }
    private static void update(final EvolutionResult<DoubleGene, Double> result){
        /**
         * Records relevant information from each generation.
         * todo record a limited amount of generations instead of every generation.
         */
        gngphr.add("Cycle "+cycle, (int)result.generation(), result.bestPhenotype().fitness());
    }
    public static void Results(Simsettings settings){
        /**
         * Creates a new window that displays the EvolutionStatistics results
         * as well as other information, of the last cycle.
         * todo remove altogether or change to full run statistics
         */
        JFrame f = new JFrame("frame");
        f.setAlwaysOnTop(true);
        JPanel d = new JPanel();
        d.setLayout(new GridLayout(1,1));
        JTextArea l = new JTextArea(Rastrigin.main(settings));
        d.add(l);
        f.add(d);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
