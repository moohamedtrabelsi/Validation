/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mohamed Trabelsi
 */
public class Connexion {
    private final String URL = "jdbc:mysql://localhost/pidev";
    private final String PWD = "";
    private final String Login = "root";
    private Connection cnx;
    private static Connexion instance;
    
    private Connexion() {
        try {
            cnx = DriverManager.getConnection(URL, Login, PWD);
            System.out.println("Connectée à la Base !");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Connexion getInstance() {
        if(instance == null)
            instance = new Connexion();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
