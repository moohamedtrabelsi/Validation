/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Models;

/**
 *
 * @author Mohamed Trabelsi
 */
public class Classe {
    private int id;
    private Branche branche;
    private String nom;
    private int nb;
    private String nombranche;
 
    public Classe(int id, String nom, int nb) {
        this.id = id;
        this.nom = nom;
        this.nb = nb;
        
    }
    
    public Classe(int id, Branche branche, String nom, int nb) {
        this.id = id;
        this.branche = branche;
        this.nom = nom;
        this.nb = nb;
        this.nombranche=branche.getNom();
    }

    public String getNombranche() {
        return nombranche;
    }

    public void setNombranche(String nombranche) {
        this.nombranche = nombranche;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    
    
    
}
