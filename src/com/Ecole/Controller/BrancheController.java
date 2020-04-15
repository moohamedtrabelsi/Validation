/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Controller;

import com.Ecole.Models.Branche;
import com.Ecole.Service.BrancheService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arbi
 */
public class BrancheController implements Initializable {

    @FXML
    private AnchorPane tabBranche;
    @FXML
    private TextField nombranche;
    @FXML
    private Button ajoutBranche;
    @FXML
    private TableView<Branche> afficher_branche;
    @FXML
    private TableColumn<Branche, Integer> afficher_branche_id;
    @FXML
    private TableColumn<Branche, String> afficher_branche_nom;
    @FXML
    private Button delete_branche;
    @FXML
    private Button gestion_classes;
    @FXML
    private Button gestion_etudiants;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficher_branche_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         afficher_branche_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         BrancheService bs= new BrancheService();
         List<Branche> arr=new ArrayList<>();
       
        try {
            arr=bs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(BrancheController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         ObservableList<Branche> ovbservableList= FXCollections.observableArrayList(arr);
         afficher_branche.setItems(ovbservableList);
         
         
         afficher_branche.setEditable(true);
         
        afficher_branche_nom.setCellFactory(TextFieldTableCell.forTableColumn());
    }    

    @FXML
    private void ajoutBranche(ActionEvent event) throws SQLException {
        Branche b= new Branche(1,nombranche.getText());
        BrancheService bs= new BrancheService();
        bs.Ajouter(b);
        //Vider le champ
        nombranche.setText("");
        //Actualiser l'affichage (refaire l'affichage pour avoir le nouvel element)
         List<Branche> arr=new ArrayList<>();
       
        try {
            arr=bs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(BrancheController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         ObservableList<Branche> ovbservableList= FXCollections.observableArrayList(arr);
         afficher_branche.setItems(ovbservableList);
        
        
    }

    @FXML
    private void DeleteBranche(ActionEvent event) throws SQLException {
        Branche b=afficher_branche.getSelectionModel().getSelectedItem();
        BrancheService bs= new BrancheService();
        if(bs.Delete(b.getId()))
        {
                    afficher_branche.getItems().removeAll(afficher_branche.getSelectionModel().getSelectedItem());

        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("Veuillez supprimer les classes de cette branche avant");

alert.showAndWait();
        }
        
    }

    @FXML
    private void editNom(TableColumn.CellEditEvent<Branche, String> event) throws SQLException {
         Branche b=afficher_branche.getSelectionModel().getSelectedItem();
        b.setNom(event.getNewValue());
        BrancheService bs= new BrancheService();
        bs.Update(b.getId(), b.getNom());
    }

    

    @FXML
    private void GestionClasses(ActionEvent event) throws IOException {
          Parent page2= FXMLLoader.load(getClass().getResource("/validation/Classe.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void GestionEtudiants(ActionEvent event) throws IOException {
         Parent page2= FXMLLoader.load(getClass().getResource("/validation/Etudiants.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    
    
    
}
