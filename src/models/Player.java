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

    public Player(String name, double life, int coins) {
        this.name = name;
        this.life = life;
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    
       public ArrayList<Combatientes> getCombatientes() {
        return combatientes;
    }

    public void setCombatientes(Combatientes combatientes) {
        this.combatientes.add(combatientes);
    }
    
}
