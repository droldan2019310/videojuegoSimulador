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
    
    /**
     * constructor
     * @param habilityExtra
     * @param plusDamage
     * @param Name
     * @param damage
     * @param typeCombatiente
     * @param ExperienceCombatiente 
     */
    public SuperCombatiente(String habilityExtra, int plusDamage, String Name, double damage, String typeCombatiente, String ExperienceCombatiente) {
        super(Name, damage, typeCombatiente, ExperienceCombatiente);
        this.habilityExtra = habilityExtra;
        this.plusDamage = plusDamage;
    }
    
    /**
     * obtener habilidad extra
     * @return habilityExtra
     */
    public String getHabilityExtra() {
        return habilityExtra;
    }
    
    /**
     * se carga la habilidad extra
     * @param habilityExtra 
     */
    

    public void setHabilityExtra(String habilityExtra) {
        this.habilityExtra = habilityExtra;
    }

    /**
     * se obtiene el dano que se anade
     * @return plusDamage
     */
    public int getPlusDamage() {
        return plusDamage;
    }
    
    /**
     * se carga el dano que se anade
     * @param plusDamage 
     */
    public void setPlusDamage(int plusDamage) {
        this.plusDamage = plusDamage;
    }
    
    
    
}
