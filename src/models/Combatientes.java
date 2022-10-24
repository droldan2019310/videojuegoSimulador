/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.partnersCombatientes;
import java.util.ArrayList;

/**
 *
 * @author Davis
 */
public class Combatientes implements partnersCombatientes {
    private String Name;
    private double damage;
    private String typeCombatiente;
    private String ExperienceCombatiente;
    
    private ArrayList<Partner> partners = new ArrayList();
    
/**
 * constructor de combatientes
 * @param Name
 * @param damage
 * @param typeCombatiente
 * @param ExperienceCombatiente 
 */
    public Combatientes(String Name, double damage, String typeCombatiente, String ExperienceCombatiente) {
        this.Name = Name;
        this.damage = damage;
        this.typeCombatiente = typeCombatiente;
        this.ExperienceCombatiente = ExperienceCombatiente;
    }

    
    /**
     * se obiente experiencia
     * @return ExperienceCombatiente
     */
    
    public String getExperienceCombatiente() {
        return ExperienceCombatiente;
    }

    /**
     * se carga experiencia
     * @param ExperienceCombatiente 
     */
    public void setExperienceCombatiente(String ExperienceCombatiente) {
        this.ExperienceCombatiente = ExperienceCombatiente;
    }
    
    /**
     * se obiente el nombre
     * @return  name
     */
    public String getName() {
        return Name;
    }
    /**
     * se carga el nombre
     * @param Name 
     */
    public void setName(String Name) {
        this.Name = Name;
    }
    /**
     * obtener daño
     * @return damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * cargar daño
     * @param damage 
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }
    
    /**
     * obtener el tipo de combatiente
     * @return typeCombatiente
     */
    public String getTypeCombatiente() {
        return typeCombatiente;
    }

    /**
     * se carga el tipo de combatiente
     * @param typeCombatiente 
     */
    public void setTypeCombatiente(String typeCombatiente) {
        this.typeCombatiente = typeCombatiente;
    }

    /**
     * obtiene los acompañantes
     * @return partners
     */
    
    public ArrayList<Partner> getPartners() {
        return partners;
    }

    
    /**
     * guarda el nuevo acompañante asociado a este combatiente
     * @param partners 
     */
    public void setPartners(Partner partners) {
        this.partners.add(partners);
    }

    
    
    
    @Override
    public double damageMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
}
