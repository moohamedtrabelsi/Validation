/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Controller;

import com.Ecole.Models.Branche;
import com.Ecole.Models.Classe;
import com.Ecole.Models.User;
import com.Ecole.Service.BrancheService;
import com.Ecole.Service.ClasseService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Arbi
 */
public class ClasseController implements Initializable {

    @FXML
    private ComboBox<Branche> select_branche;
    @FXML
    private TextField nom_classe;
    @FXML
    private Button ajout_classe;
    @FXML
    private TableView<Classe> afficher_classe;
    @FXML
    private TableColumn<Classe, Integer> afficher_classe_id;
    @FXML
    private TableColumn<Classe, String> afficher_classe_nom;
    @FXML
    private TableColumn<Classe, Integer> afficher_classe_nbr;
    @FXML
    private TableColumn<Classe, String> afficher_classe_branche;
    @FXML
    private Button deleteClasse;
    @FXML
    private Button gestionEtudiant;
    @FXML
    private Button gestionEtudiant1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Creation d'une cellule personalisée pour le combobox
         Callback<ListView<Branche>, ListCell<Branche>> cellFactory = new Callback<ListView<Branche>, ListCell<Branche>>() {

    @Override
    public ListCell<Branche> call(ListView<Branche> l) {
        return new ListCell<Branche>() {

            @Override
            protected void updateItem(Branche item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setText(item.getId() + "    " + item.getNom());
                }
            }
        } ;
    }
};

// Just set the button cell here:
select_branche.setButtonCell(cellFactory.call(null));
select_branche.setCellFactory(cellFactory);
        //Recuperation des branches de la bd
          BrancheService bs=new BrancheService();
          List<Branche> arr=new ArrayList<>();
       
        try {
            arr=bs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(BrancheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Remplissage du combobox
       for(Branche b: arr)
        {
            select_branche.getItems().add(b);
        }
       
       //affichage
         afficher_classe_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         afficher_classe_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         afficher_classe_nbr.setCellValueFactory(new PropertyValueFactory<>("nb"));
         afficher_classe_branche.setCellValueFactory(new PropertyValueFactory<>("nombranche"));
         
         ClasseService cs= new ClasseService();
         List<Classe> arrc=new ArrayList<>();
        try {
            arrc=cs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
         ObservableList<Classe> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_classe.setItems(ovbservableList);
       //Updatable 
        afficher_classe.setEditable(true);
        afficher_classe_nom.setCellFactory(TextFieldTableCell.forTableColumn());
       
        
    }   

    @FXML
    private void AjouterClasse(ActionEvent event) throws SQLException {
        Branche b=select_branche.getValue();
        String nom=nom_classe.getText();
        Classe c= new Classe(1,b,nom,0);
        ClasseService cs= new ClasseService();
        cs.Ajouter(c);
        nom_classe.setText("");
        //Update affichage
         List<Classe> arrc=new ArrayList<>();
        try {
            arrc=cs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Classe> ovbservableList= FXCollections.observableArrayList(arrc);
        afficher_classe.setItems(ovbservableList);
        
        
        
        
    }

    @FXML
    private void DeleteClasse(ActionEvent event) throws SQLException {
         Classe b=afficher_classe.getSelectionModel().getSelectedItem();
        ClasseService cs= new ClasseService();
        if(cs.Delete(b.getId()))
        {
            afficher_classe.getItems().removeAll(afficher_classe.getSelectionModel().getSelectedItem());
        }
         else
        {
            Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("Veuillez supprimer les etudiants de cette classe avant");

alert.showAndWait();
        }
        
    }

    @FXML
    private void UpdateNom(TableColumn.CellEditEvent<Classe, String> event) throws SQLException {
        Classe c=afficher_classe.getSelectionModel().getSelectedItem();
        c.setNom(event.getNewValue());
        ClasseService cs= new ClasseService();
        cs.Update(c.getId(), c.getNom());
    }

   

    @FXML
    private void GererEtudiants(ActionEvent event) throws IOException {
         Parent page2= FXMLLoader.load(getClass().getResource("/validation/Etudiants.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    

    @FXML
    private void GererBranches(ActionEvent event) throws IOException {
         Parent page2= FXMLLoader.load(getClass().getResource("/validation/Branche.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

   

   

   
    
    
    
    
}
