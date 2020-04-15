/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Controller;

import com.Ecole.Models.Branche;
import com.Ecole.Models.Classe;
import com.Ecole.Models.User;
import com.Ecole.Service.ClasseService;
import com.Ecole.Service.UserService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Arbi
 */
public class EtudiantsController implements Initializable {

    @FXML
    private ComboBox<Classe> classes;
    @FXML
    private TableView<User> afficher_etudiant;
    @FXML
    private TableColumn<User, Integer> afficher_etudiant_id;
    @FXML
    private TableColumn<User, String> afficher_etudiant_prenom;
    @FXML
    private TableColumn<User, String> afficher_etudiant_email;
    @FXML
    private TableColumn<User, String> afficher_etudiant_classe;
    @FXML
    private TableColumn<?, ?> afficher_etudiant_nom;
    @FXML
    private Button affecter;
    @FXML
    private Button gestionclasses;
    @FXML
    private Button gestionbranches;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClasseService cs=new ClasseService();
        List<Classe> arr=new ArrayList<>();
        try {
            arr=cs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Callback<ListView<Classe>, ListCell<Classe>> cellFactory = new Callback<ListView<Classe>, ListCell<Classe>>() {

    @Override
    public ListCell<Classe> call(ListView<Classe> l) {
        return new ListCell<Classe>() {

            @Override
            protected void updateItem(Classe item, boolean empty) {
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
classes.setButtonCell(cellFactory.call(null));
classes.setCellFactory(cellFactory);
for(Classe c: arr)
        {
            classes.getItems().add(c);
        }


            UserService us= new UserService();
            List<User> arr2=new ArrayList<>();
        try {
            arr2=us.findEtudiants();
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         afficher_etudiant_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         afficher_etudiant_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         afficher_etudiant_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         afficher_etudiant_classe.setCellValueFactory(new PropertyValueFactory<>("nomclasse"));
         afficher_etudiant_email.setCellValueFactory(new PropertyValueFactory<>("email"));
         ObservableList<User> ovbservableList= FXCollections.observableArrayList(arr2);
       afficher_etudiant.setItems(ovbservableList);
            

        
        
        
        
    }    

    @FXML
    private void AffecterEtudiant(ActionEvent event) throws SQLException {
        User u=afficher_etudiant.getSelectionModel().getSelectedItem();
        Classe c=classes.getValue();
        
        UserService us=new UserService();
        us.AffecterEtudiant(u.getId(), c.getId());
        List<User>arr=new ArrayList<>();
        arr=us.findEtudiants();
        ObservableList<User> ovbservableList= FXCollections.observableArrayList(arr);
       afficher_etudiant.setItems(ovbservableList);
    }

    @FXML
    private void GererClasses(ActionEvent event) throws IOException {
         Parent page2= FXMLLoader.load(getClass().getResource("/validation/Classe.fxml"));
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
