/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics.GenPO;

import io.jenetics.DoubleGene;
import io.jenetics.Phenotype;
import io.jenetics.util.ISeq;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Akman
 */
public class GenPost {//Genetic post office
    private int Islandpop;
    private List<MailDouble[]> poboxDouble;
    private List<MailEnum[]> poboxEnum;
    public GenPost(int Islandpop){
        this.Islandpop=Islandpop;
        poboxDouble= new ArrayList();
        poboxEnum= new ArrayList();
        for(int i=0;i<=Islandpop;i++){
            poboxDouble.add(null);
            poboxEnum.add(null);
        }
    }
    public void sentDouble(MailDouble mail[]){
        poboxDouble.set(mail[0].getreceiver(),mail);
    }
    public void sentEnum(MailEnum mail[]){
        poboxEnum.set(mail[0].getreceiver(),mail);
    }
    public MailDouble[] recieveDouble(int Islandreciever){
        MailDouble mail[]=poboxDouble.get(Islandreciever);
        poboxDouble.set(Islandreciever,null);
        return mail;
    }
    public MailEnum[] recieveEnum(int Islandreciever){
        MailEnum mail[]=poboxEnum.get(Islandreciever);
        poboxEnum.set(Islandreciever,null);
        return mail;
    }
}
