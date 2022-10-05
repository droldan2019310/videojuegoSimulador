/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Davis
 */
public class SuperCombatiente extends Combatientes {
    private String habilityExtra;
    private int plusDamage;

    public SuperCombatiente(String habilityExtra, int plusDamage, String Name, double damage, String typeCombatiente, String ExperienceCombatiente) {
        super(Name, damage, typeCombatiente, ExperienceCombatiente);
        this.habilityExtra = habilityExtra;
        this.plusDamage = plusDamage;
    }

    public String getHabilityExtra() {
        return habilityExtra;
    }

    public void setHabilityExtra(String habilityExtra) {
        this.habilityExtra = habilityExtra;
    }

    public int getPlusDamage() {
        return plusDamage;
    }

    public void setPlusDamage(int plusDamage) {
        this.plusDamage = plusDamage;
    }
    
    
    
}
