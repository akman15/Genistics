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
    private List<GenMail> pobox;
    public GenPost(int Islandpop){
        this.Islandpop=Islandpop;
        pobox= new ArrayList();
        for(int i=0;i<=Islandpop;i++){
            pobox.add(null);
        }
    }
    public void sent(GenMail mail){
        pobox.set(mail.getreceiver(),mail);
    }
    public GenMail recieve(int Islandreciever){
        GenMail mail=pobox.get(Islandreciever);
        pobox.set(Islandreciever,null);
        return mail;
    }
}
