/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Service;

import com.Ecole.Models.Classe;
import com.Ecole.Models.User;
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
public class UserService {
     private Connection con;
    private Statement ste;
    
      public UserService() {
        con = Connexion.getInstance().getCnx();

    }
      
      public void AffecterEtudiant(int id,int idc) throws SQLException
      {
                   ste=con.createStatement();

          int idancien=0;
          int nbrAncien=0;
          int nbrNouv=0;
          ResultSet r=ste.executeQuery("SELECT classe_id from `user` where id="+id);
          while(r.next())
          {
              idancien=r.getInt("classe_id");
          }
          ResultSet r2=ste.executeQuery("SELECT nbrEtudiants from `classe` where id="+idancien);
          while(r2.next())
          {
              nbrAncien=r2.getInt("nbrEtudiants");
          }
          
          ResultSet r3=ste.executeQuery("SELECT nbrEtudiants from `classe` where id="+idc);
          while(r3.next())
          {
              nbrNouv=r3.getInt("nbrEtudiants");
          }
          System.out.println(nbrNouv);
          nbrAncien--;
          nbrNouv++;
          ste.executeUpdate("update classe set nbrEtudiants="+nbrNouv+" where id="+idc);
          ste.executeUpdate("update classe set nbrEtudiants="+nbrAncien+" where id="+idancien);
          ste.executeUpdate("update user set classe_id="+idc+" where id="+id);
      }
      
       public List<User> findEtudiants() throws SQLException
      {
          List<User> arr=new ArrayList<>();
          List<Classe> classes= new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select id,username,email,nom,prenom,classe_id from user where roles like '%ROLE_ETUDIANT%' ");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String email=rs.getString("email");
               String username=rs.getString("username");
               int classe_id=rs.getInt("classe_id");
               PreparedStatement pre_classe=con.prepareStatement("select * from classe where id=? ");
               pre_classe.setInt(1,classe_id);
               ResultSet rsClasse=pre_classe.executeQuery();
               while(rsClasse.next())
               {
                   int idc=rsClasse.getInt("id");
                   String nomClasse=rsClasse.getString("nom");
                   int nbrE=rsClasse.getInt("nbrEtudiants");
                   
                   Classe c= new Classe(idc,nomClasse,nbrE);
                   classes.add(c);
               }
               
               User u=new User(id,username,email,classes.get(classes.size()-1),nom,prenom);
     arr.add(u);
     }
    return arr;
          
      }
      
}
