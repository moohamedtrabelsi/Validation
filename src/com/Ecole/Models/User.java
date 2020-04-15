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
public class User {
    private int id;
    private String username;
    private String email;
    private Classe classe;
    private String nom;
    private String prenom;
    private String nomclasse;

    public User(int id,String username, String email, Classe classe, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.classe = classe;
        this.nom = nom;
        this.prenom = prenom;
        this.nomclasse=classe.getNom();
    }

    public String getNomclasse() {
        return nomclasse;
    }

    public void setNomclasse(String nomclasse) {
        this.nomclasse = nomclasse;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

  

    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
    
}
