/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author Davis
 */
public class Combatientes {
    private String Name;
    private double damage;
        private String typeCombatiente;
    private String ExperienceCombatiente;
    

    public Combatientes(String Name, double damage, String typeCombatiente, String ExperienceCombatiente) {
        this.Name = Name;
        this.damage = damage;
        this.typeCombatiente = typeCombatiente;
        this.ExperienceCombatiente = ExperienceCombatiente;
    }

    
    
    
    public String getExperienceCombatiente() {
        return ExperienceCombatiente;
    }

    public void setExperienceCombatiente(String ExperienceCombatiente) {
        this.ExperienceCombatiente = ExperienceCombatiente;
    }
    
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public String getTypeCombatiente() {
        return typeCombatiente;
    }

    public void setTypeCombatiente(String typeCombatiente) {
        this.typeCombatiente = typeCombatiente;
    }

 
    
    
}
