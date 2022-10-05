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
public class Player {
    private String name;
    private double life;
    private int coins;
    private ArrayList<Combatientes> combatientes = new ArrayList();

    
    /**
     * constructor
     * @param name
     * @param life
     * @param coins 
     */
    public Player(String name, double life, int coins) {
        this.name = name;
        this.life = life;
        this.coins = coins;
    }

    /**
     * se obtiene el nombre
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * se carga el nombre
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
/**
 * se obtiene la vida
 * @return life
 */
    public double getLife() {
        return life;
    }

    /**
     * se carga la vida
     * @param life 
     */
    public void setLife(double life) {
        this.life = life;
    }
    
    /**
     * se obtiene las monedas
     * @return  coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * se carga las monedas
     * @param coins 
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    /**
     * se obtiene combatientes
     * @return combatientes
     */
    public ArrayList<Combatientes> getCombatientes() {
        return combatientes;
    }

       
    /**
     * se carga nuevo combatiente
     * @param combatientes 
     */
    public void setCombatientes(Combatientes combatientes) {
        this.combatientes.add(combatientes);
    }
    
}
