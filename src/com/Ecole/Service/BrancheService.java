/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Service;

import com.Ecole.Models.Branche;
import com.Ecole.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arbi
 */
public class BrancheService {
    private Connection con;
    private Statement ste;
    
      public BrancheService() {
        con = Connexion.getInstance().getCnx();

    }
      
      public void Ajouter(Branche b) throws SQLException
    {
         PreparedStatement pre=con.prepareStatement("INSERT INTO `branche` ( `id`, `nom`) VALUES ( null, ?);");
    pre.setString(1, b.getNom());
    
    pre.executeUpdate();
    }
      
      public List<Branche> readAll() throws SQLException {
    List<Branche> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from branche");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
              
               Branche b=new Branche(id, nom);
     arr.add(b);
     }
    return arr;
    }
      
       public boolean Delete(int id) throws SQLException
    {
    /*PreparedStatement pre=con.prepareStatement("DELETE FROM `branche` WHERE id=? ;");
    pre.setInt(1,id);
    pre.executeUpdate();*/
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from classe where branche_id="+id);
    int x=0;
     while (rs.next()) {                
               x++;
     }
     if(x==0)
     {
         PreparedStatement pre=con.prepareStatement("DELETE FROM `branche` WHERE id=? ;");
    pre.setInt(1,id);
    pre.executeUpdate();
    return true;
     }
     
         
         return false;
     
    }
       
       public void Update(int id, String nom) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("UPDATE  `branche` SET nom=? WHERE id=? ;");
    pre.setString(1,nom);
     pre.setInt(2,id);
    pre.executeUpdate();   
    }
      
      
      
      
      
      
}
