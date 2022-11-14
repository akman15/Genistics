package genistics;

import io.jenetics.*;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Akman
 */
public class MainMenu extends javax.swing.JFrame {
    private static SpinnerNumberModel model;
    public MainMenu() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        Archipelago = new javax.swing.JPanel();
        ArchipelagoRun = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        IslandPopSpinner = new javax.swing.JSpinner();
        MigrationSpinner = new javax.swing.JSpinner();
        TFComboBox = new javax.swing.JComboBox<>();
        LimitsPanel = new javax.swing.JPanel();
        MaxReps = new javax.swing.JSpinner();
        MaxGens = new javax.swing.JSpinner();
        MinGens = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CGDspinner = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        MaxCyclesSpinner = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        ExitItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout(2, 2));

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setToolTipText("");
        jSplitPane2.setMinimumSize(new java.awt.Dimension(0, 0));
        jSplitPane2.setName(""); // NOI18N
        jSplitPane2.setOpaque(false);
        jSplitPane2.setPreferredSize(new java.awt.Dimension(400, 300));

        Archipelago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Archipelago.setMinimumSize(new java.awt.Dimension(20, 100));
        Archipelago.setOpaque(false);
        Archipelago.setPreferredSize(new java.awt.Dimension(20, 100));

        ArchipelagoRun.setText("Run");
        ArchipelagoRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArchipelagoRunActionPerformed(evt);
            }
        });

        jLabel6.setText("Island Population");

        jLabel7.setText("Migration Chance %");

        IslandPopSpinner.setModel(new javax.swing.SpinnerNumberModel(2, 1, 10, 1));
        IslandPopSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        IslandPopSpinner.setName(""); // NOI18N

        MigrationSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 0, 100, 1));
        MigrationSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MigrationSpinner.setName(""); // NOI18N

        TFComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rastrigin", "Rosenbrock", "TSP" }));

        javax.swing.GroupLayout ArchipelagoLayout = new javax.swing.GroupLayout(Archipelago);
        Archipelago.setLayout(ArchipelagoLayout);
        ArchipelagoLayout.setHorizontalGroup(
            ArchipelagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ArchipelagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ArchipelagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArchipelagoLayout.createSequentialGroup()
                        .addComponent(TFComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 609, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IslandPopSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArchipelagoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(ArchipelagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ArchipelagoRun, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArchipelagoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MigrationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        ArchipelagoLayout.setVerticalGroup(
            ArchipelagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArchipelagoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ArchipelagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(IslandPopSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ArchipelagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(MigrationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(ArchipelagoRun)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setTopComponent(Archipelago);

        MaxReps.setModel(new javax.swing.SpinnerNumberModel(500, 0, 1000, 1));
        MaxReps.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MaxReps.setName(""); // NOI18N

        MaxGens.setModel(new javax.swing.SpinnerNumberModel(100000, 1, 1000000, 1));
        MaxGens.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MaxGens.setName(""); // NOI18N
        MaxGens.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MaxGensStateChanged(evt);
            }
        });

        MinGens.setModel(new javax.swing.SpinnerNumberModel(1000, 0, 100000, 1));
        MinGens.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MinGens.setName(""); // NOI18N
        MinGens.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MinGensStateChanged(evt);
            }
        });

        jLabel8.setText("Gneration limit: Min");

        jLabel9.setText("Max");

        jLabel10.setText("Repetitions");

        CGDspinner.setModel(new javax.swing.SpinnerNumberModel(500, 1, null, 1));
        CGDspinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CGDspinner.setName(""); // NOI18N

        jLabel11.setText("Collect data every:");

        jLabel12.setText("Generations");

        MaxCyclesSpinner.setModel(new javax.swing.SpinnerNumberModel(20, 1, 1000, 1));
        MaxCyclesSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MaxCyclesSpinner.setName(""); // NOI18N

        jLabel13.setText("Max Tasks:");

        javax.swing.GroupLayout LimitsPanelLayout = new javax.swing.GroupLayout(LimitsPanel);
        LimitsPanel.setLayout(LimitsPanelLayout);
        LimitsPanelLayout.setHorizontalGroup(
            LimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LimitsPanelLayout.createSequentialGroup()
                .addContainerGap(465, Short.MAX_VALUE)
                .addGroup(LimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LimitsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MinGens, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MaxGens, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MaxReps, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LimitsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MaxCyclesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CGDspinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)))
                .addContainerGap())
        );
        LimitsPanelLayout.setVerticalGroup(
            LimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LimitsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxReps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(MaxGens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MinGens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CGDspinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(MaxCyclesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(249, Short.MAX_VALUE))
        );

        jSplitPane2.setBottomComponent(LimitsPanel);

        getContentPane().add(jSplitPane2, java.awt.BorderLayout.CENTER);

        FileMenu.setText("File");

        ExitItem.setText("Exit");
        ExitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitItemActionPerformed(evt);
            }
        });
        FileMenu.add(ExitItem);

        jMenuBar1.add(FileMenu);

        EditMenu.setText("Edit");

        jMenuItem2.setText("jMenuItem2");
        EditMenu.add(jMenuItem2);

        jMenuBar1.add(EditMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitItemActionPerformed

    private void ArchipelagoRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArchipelagoRunActionPerformed
        double d=(int)MigrationSpinner.getValue();
        ArchipelagoSettings ArSet=new ArchipelagoSettings((int)IslandPopSpinner.getValue(), d/100.0);
        GenLimits limit=new GenLimits((int)MaxGens.getValue(), (int)MinGens.getValue(), (int)MaxReps.getValue(),(int)CGDspinner.getValue(),(int)MaxCyclesSpinner.getValue());
        switch(TFComboBox.getSelectedIndex()){
            case 0:
                RastriginIsland.Results(limit,ArSet);
                break;
            case 1:
                RosenbrockIsland.Results(limit, ArSet);
                break;
            case 2:
                TSPIsland.Results(limit, ArSet);
                break;
            default:
                RastriginIsland.Results(limit,ArSet);
        }
    }//GEN-LAST:event_ArchipelagoRunActionPerformed

    private void MaxGensStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MaxGensStateChanged
        if((int)MaxGens.getValue()<=(int)MinGens.getValue()){
            if((int)MinGens.getValue()<=0){
                MaxGens.setValue(1);
            }else{
                MinGens.setValue((int)MinGens.getValue()-1);
            }
        }
    }//GEN-LAST:event_MaxGensStateChanged

    private void MinGensStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MinGensStateChanged
        if((int)MaxGens.getValue()<=(int)MinGens.getValue()){
            MaxGens.setValue((int)MinGens.getValue()+1);
        }
    }//GEN-LAST:event_MinGensStateChanged
    
    
   /* public Simsettings GetSettings(){
         /* Creates a new instance of Simsettings by
         /* taking variables from gui elements and returns it.
        
        Simsettings settings;
        Selectors s = (Selectors) jComboBoxSelector.getSelectedItem();
        int pop = PopSlider.getValue();
        double m = (int)MutationSpinner.getValue();
        double c = (int)CrossoverSpinner.getValue();
        int cp=jComboBoxCrossover.getSelectedIndex()+1;
        settings=new Simsettings(s,pop,m,c,cp);
        return settings;
    }
    */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Archipelago;
    private javax.swing.JButton ArchipelagoRun;
    private javax.swing.JSpinner CGDspinner;
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenuItem ExitItem;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JSpinner IslandPopSpinner;
    private javax.swing.JPanel LimitsPanel;
    private javax.swing.JSpinner MaxCyclesSpinner;
    private javax.swing.JSpinner MaxGens;
    private javax.swing.JSpinner MaxReps;
    private javax.swing.JSpinner MigrationSpinner;
    private javax.swing.JSpinner MinGens;
    private javax.swing.JComboBox<String> TFComboBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JSplitPane jSplitPane2;
    // End of variables declaration//GEN-END:variables
}
