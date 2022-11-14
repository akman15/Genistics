/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics.GenPO;

import io.jenetics.DoubleGene;
import io.jenetics.EnumGene;
import io.jenetics.Phenotype;

/**
 *
 * @author Akman
 */
public class MailEnum extends GenMail {
    private Phenotype<EnumGene<double[]>,Double> mail=null;
    public MailEnum(Phenotype<EnumGene<double[]>,Double> mail,int receiver,int senter){
        super(receiver,senter);
        this.mail=mail;
    }
    
    public Phenotype<EnumGene<double[]>,Double> getmail(){
        return mail;
    }
}
