/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics;

/**
 *
 * @author Akman
 */
public class Simsettings {//Engine settings template for islands
    public Selectors selector;
    public int population;
    public double mutationprobability;
    public double crossoverprobability;
    public int crossoverpoint;
    Simsettings(Selectors selector,int population,double mutationprobability,double crossoverprobability,int crossoverpoint){
        this.selector=selector;
        this.population=population;
        this.mutationprobability=mutationprobability;
        this.crossoverprobability=crossoverprobability;
        this.crossoverpoint=crossoverpoint;
    }
}
