/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Service;

import com.Ecole.Models.Branche;
import com.Ecole.Models.Classe;
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
public class ClasseService {
     private Connection con;
    private Statement ste;
    
      public ClasseService() {
        con = Connexion.getInstance().getCnx();

    }
      
       public void Ajouter(Classe c) throws SQLException
    {
         PreparedStatement pre=con.prepareStatement("INSERT INTO `classe` ( `id`, `branche_id`, `nom`, `nbrEtudiants`) VALUES ( null, ?,?,0);");
    pre.setInt(1, c.getBranche().getId());
    pre.setString(2, c.getNom());
    
    pre.executeUpdate();
    }
       
       
       
       public List<Classe> readAll() throws SQLException {
  List<Classe> arr=new ArrayList<>();
    List<Branche>branches= new ArrayList<Branche>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from classe");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
               int nbr=rs.getInt("nbrEtudiants");
               int branche_id=rs.getInt("branche_id");
               //recuperer client
                   PreparedStatement pre_branche=con.prepareStatement("select * from branche where id=? ");
               pre_branche.setInt(1,branche_id);
               ResultSet rsBranche=pre_branche.executeQuery();
               while(rsBranche.next())
               {
                   int idb=rsBranche.getInt("id");
                   String nomb=rsBranche.getString("nom");
                   
                   Branche b= new Branche(idb,nomb);
                   branches.add(b);
               }
              
                
               Classe c=new Classe(id,branches.get(branches.size()-1),nom,nbr);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(c);
     }
    return arr;
    }

    public boolean Delete(int id) throws SQLException {
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user where classe_id="+id);
    int x=0;
     while (rs.next()) {                
               x++;
     }
     if(x==0)
     {
    PreparedStatement pre=con.prepareStatement("DELETE FROM `classe` WHERE id=? ;");
    pre.setInt(1,id);
    pre.executeUpdate();
     return true;

    }
    
    
    return false;
    }

    public void Update(int id, String nom) throws SQLException {
 PreparedStatement pre=con.prepareStatement("UPDATE  `classe` SET nom=? WHERE id=? ;");
    pre.setString(1,nom);
     pre.setInt(2,id);
    pre.executeUpdate();      }
    
}
