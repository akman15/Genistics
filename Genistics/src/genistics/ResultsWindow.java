/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genistics;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Akman
 */
public class ResultsWindow {
    public static void Results(GenLimits limit,ArchipelagoSettings ArSet,int TF) {
        /**
         * Creates a new window that displays the EvolutionStatistics results
         * as well as other information, of the last cycle.
         * todo remove altogether or change to full run statistics
         */
        JFrame f = new JFrame("frame");
        f.setAlwaysOnTop(true);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,1));
        
        Simsettings[] settings = new Simsettings[ArSet.IslandPop];
        for(int i=0;i<ArSet.IslandPop;i++){
            IslandMenu dialog = new IslandMenu(f,true);
            settings[i]=dialog.showDialog();
        }  
        
        JDialog d = new JDialog();//create new dialog window to display loading
        d.setAlwaysOnTop(true);
        d.setResizable(false);
        d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        JTextArea l = new JTextArea("Running...");
        l.setEditable(false);
        l.setFont(new Font("Arial", Font.PLAIN, 20));
        p1.add(l);
        d.add(p1);
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        
        Thread mainThread = new Thread(() -> {
            switch(TF){
                case 0:
                    l.setText(RastriginIsland.Start(limit,ArSet,settings));
                    break;
                case 1:
                    l.setText(RosenbrockIsland.Start(limit,ArSet,settings));
                    break;
                case 2:
                    l.setText(TSPIsland.Start(limit,ArSet,settings));
                    break;
                default:
                    l.setText(RastriginIsland.Start(limit,ArSet,settings));
                    break;
            }
            f.pack();
        });
        mainThread.start();
        Thread counterthread = new Thread(() -> {
            String ri[]={"-__","_-_","__-"};//running indicator
            int index=0;
            while(mainThread.isAlive()){
                l.setText("Running"+ri[index]);
                f.pack();
                index++;
                if(index>=ri.length){
                    index=0;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            l.setText("Finished");  
            f.pack();
            try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            d.dispose();
        });
        counterthread.start();
    }
}
