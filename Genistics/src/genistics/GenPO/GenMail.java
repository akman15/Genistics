/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics.GenPO;

import io.jenetics.DoubleGene;
import io.jenetics.Phenotype;
import java.util.List;

/**
 *
 * @author Akman
 */
public class GenMail {//Genetic mail class
    private Phenotype<DoubleGene, Double> mail=null;
    private boolean reply=false;
    private int receiver;
    private int senter;
    public GenMail(Phenotype<DoubleGene, Double> mail,int receiver,int senter){
        this.mail=mail;
        this.receiver=receiver;
        this.senter=senter;
        
    }
    public boolean getreply(){
        return reply;
    }
    public int getreceiver(){
        return receiver;
    }
    public int getsenter(){
        return senter;
    }
    public Phenotype<DoubleGene, Double> getmail(){
        return mail;
    }
}
