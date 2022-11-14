/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics.GenPO;

import io.jenetics.DoubleGene;
import io.jenetics.Phenotype;

/**
 *
 * @author Akman
 */
public class MailDouble extends GenMail {
    private Phenotype<DoubleGene, Double> mail=null;
    public MailDouble(Phenotype<DoubleGene, Double> mail,int receiver,int senter){
        super(receiver,senter);
        this.mail=mail;
    }
    
    public Phenotype<DoubleGene, Double> getmail(){
        return mail;
    }
}
