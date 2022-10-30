package genistics;


import io.jenetics.ProbabilitySelector;
import io.jenetics.TournamentSelector;

/**
 *
 * @author Akman
 */
public class Selectors {//A way to store selector values and get it's string
    private final String key;
    private final Object value;
    public Selectors(String key, ProbabilitySelector value){
        this.key= key;
        this.value= value;
    }
    public Selectors(String key, TournamentSelector value){
        this.key= key;
        this.value=value;
    }
    @Override
    public String toString(){
        return key;
    }
    
    public Object getValue(){
        return value;
    }
}
