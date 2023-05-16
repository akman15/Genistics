package genistics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Akman
 */
public class ArchipelagoSettings {
    public int IslandPop=1;
    public int MigrationInterval=0;
    public int migrationtype=0;
    public int MigrationRate=1;
    ArchipelagoSettings(int IslandPop,int MigrationInterval,int migrationtype,int MigrationRate){
        this.IslandPop=IslandPop;
        this.MigrationInterval=MigrationInterval;
        this.migrationtype=migrationtype;
        this.MigrationRate=MigrationRate;
    }
}
