/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Combatientes;
import models.Player;
import models.SuperCombatiente;

/**
 *
 * @author Davis
 */
public class PrincipalViewController implements Initializable {
    
    private Label label;
    @FXML
    private ProgressBar barPlayer1;
    @FXML
    private ProgressBar barPlayer2;
    @FXML
    private TableView<Combatientes> tablePlayer1;
    @FXML
    private TableColumn<Combatientes, String> atacker1Column;
    @FXML
    private TableColumn<Combatientes, Double> damage1Column;
    @FXML
    private TableColumn<Combatientes, String> type1Column;
    @FXML
    private TableView<Combatientes> tablePlayer2;
    @FXML
    private TableColumn<Combatientes, String> atacker2Column;
    @FXML
    private TableColumn<Combatientes, Double> damage2Column;
    @FXML
    private TableColumn<Combatientes, String> type2Column;
    @FXML
    private Label jugadorActualtxt;
    @FXML
    private ComboBox<String> cmbHability1;
    @FXML
    private Button buttonAtack1;
    @FXML
    private ComboBox<String> cmbHability2;
    @FXML
    private Button buttonAtack2;
    @FXML
    private Label txtCoinPlayer1;
    @FXML
    private Label txtCoinPlayer2;
    @FXML
    private Button btnBuyPlayer1;
    @FXML
    private Button btnbuyPlayer2;
    

    
    ArrayList<String> experienceAvailablearray = new ArrayList();
    
    ArrayList<String> typeCArray = new ArrayList();
    
    ArrayList<SuperCombatiente> habilitySuper = new ArrayList();

    
    Player player1 = new Player("JUGADOR 1",100, 10);  
    Player player2 = new Player("JUGADOR 2",100, 10);

    int flagTurn = 1;
    
    
    Combatientes combatiente1selected;
    Combatientes combatiente2selected;
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        barPlayer1.setProgress(player1.getLife());
        barPlayer2.setProgress(player2.getLife());
        txtCoinPlayer1.setText(String.valueOf(player1.getCoins()));
        txtCoinPlayer2.setText(String.valueOf(player2.getCoins()));
        buttonAtack2.setDisable(true);
        btnbuyPlayer2.setDisable(true);
        setExperienceArray();
        setTypeCArray();
        
        cmbHability1.setValue("");        
        cmbHability2.setValue("");

        
    }    
    
    public void setSuperHability(Combatientes combatiente){
        habilitySuper.clear();
        cmbHability1.setValue("");        
        cmbHability2.setValue("");
        if(combatiente.getTypeCombatiente().equals("ATACANTE")){
            habilitySuper.add(new SuperCombatiente("ATAQUE MORTAL POR ATACANTE SUPREMO",10, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
        }else{
            habilitySuper.add(new SuperCombatiente("CURACIÓN INSTANTANEA",100, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
        }
        ArrayList<String>comboHability = new ArrayList();
        for(SuperCombatiente combat:habilitySuper){
            comboHability.add(combat.getHabilityExtra());
        }
        ObservableList<String>habilitiesObservable;
        habilitiesObservable = FXCollections.observableList(comboHability);
        cmbHability1.setItems(habilitiesObservable);
        cmbHability2.setItems(habilitiesObservable);
    }
    
    
    
      @FXML
    private void clickCombatiente1(MouseEvent event) {
        try{
            combatiente1selected = tablePlayer1.getSelectionModel().getSelectedItem();
            Random random = new Random();
            int x =random.nextInt(2 - 1 + 1) + 1;
            if(x==1){
                cmbHability1.setDisable(false);
                setSuperHability(combatiente1selected);
                cmbHability1.setValue("");        
                cmbHability2.setValue("");
            }else{
                cmbHability1.setDisable(true);
                cmbHability1.setValue("");        
                cmbHability2.setValue("");
            }
            
            
        }catch(Exception e){
        
        }
    }

    @FXML
    private void clickCombatiente2(MouseEvent event) {
         try{
            combatiente2selected = tablePlayer2.getSelectionModel().getSelectedItem();
            Random random = new Random();
            int x =random.nextInt(2 - 1 + 1) + 1;
            if(x==1){
                cmbHability2.setDisable(false);
                setSuperHability(combatiente2selected);
            }else{
                cmbHability2.setDisable(true);
                cmbHability1.setValue("");        
                cmbHability2.setValue("");
            }
            
            
        }catch(Exception e){
        
        }
    }
    
    
    public void uploadDataCombatientes1(){
        ObservableList<Combatientes> combatientesPlayer1;
        combatientesPlayer1 = FXCollections.observableList(player1.getCombatientes());
        //configura la table de programas en ejecución
        tablePlayer1.setItems(combatientesPlayer1);
        atacker1Column.setCellValueFactory(new PropertyValueFactory("name"));
        damage1Column.setCellValueFactory(new PropertyValueFactory("damage"));
        type1Column.setCellValueFactory(new PropertyValueFactory("typeCombatiente"));
    }
    
     
    public void uploadDataCombatientes2(){
        ObservableList<Combatientes> combatientesPlayer2;
        combatientesPlayer2 = FXCollections.observableList(player2.getCombatientes());
        //configura la table de programas en ejecución
        tablePlayer2.setItems(combatientesPlayer2);
        atacker2Column.setCellValueFactory(new PropertyValueFactory("name"));
        damage2Column.setCellValueFactory(new PropertyValueFactory("damage"));
        type2Column.setCellValueFactory(new PropertyValueFactory("typeCombatiente"));
    }
    
    
    private void setExperienceArray(){
        experienceAvailablearray.add("JEFE");
        experienceAvailablearray.add("SOLDADO");
    }
    
    private void setTypeCArray(){
        typeCArray.add("CURANDERO");
        typeCArray.add("ATACANTE");
    }

    
    
    
    
    @FXML
    private void atackPlayer1(ActionEvent event) {
        try{
            if(combatiente1selected==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR AL ATACAR");
                alert.setHeaderText("DEBES SELECCIONAR UN COMBATIENTE DE TU LISTA");
                alert.show();
            }else{
                if(combatiente1selected.getTypeCombatiente().equals("ATACANTE")){
                    double damage = combatiente1selected.getDamage();
                    
                    if(cmbHability1.getValue().equals("ATAQUE MORTAL POR ATACANTE SUPREMO")){
                        damage = damage+10;
                        cmbHability1.getSelectionModel().clearSelection();
                        cmbHability1.setValue(null);
                    }
                    if(player2.getLife()-damage<=0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("EL JUGADOR 1 HA GANADO");
                        alert.setHeaderText("FELICIDADES JUGADOR 1");
                        alert.show();
                    }
                    player2.setLife(player2.getLife()-combatiente1selected.getDamage());
                    System.out.println(player2.getLife());
                    double lifeTotal = player2.getLife()/100;
                    barPlayer2.setProgress(lifeTotal);

                    changeTurn();
                }else{
                    double damage = combatiente1selected.getDamage();
                    if(cmbHability1.getValue().equals("CURACIÓN INSTANTANEA")){
                        damage = damage+100;
                        cmbHability1.getSelectionModel().clearSelection();
                        cmbHability1.setValue(null);
                    }
                    if(player1.getLife()+damage>=100){
                        player1.setLife(100);
                        double lifeTotal = player1.getLife()/100;
                        barPlayer1.setProgress(lifeTotal);
                    }else{
                        player1.setLife(player1.getLife()+combatiente1selected.getDamage());
                        double lifeTotal = player1.getLife()/100;
                        barPlayer1.setProgress(lifeTotal);
                    }
                    changeTurn();
                }
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR AL ATACAR");
            alert.setHeaderText("DEBES SELECCIONAR UN JUGADOR");
            alert.show();
        }
        
        
        
    }

    @FXML
    private void atackPlayer2(ActionEvent event) {
          try{
            
            
            if(combatiente2selected==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR AL ATACAR");
                alert.setHeaderText("DEBES SELECCIONAR UN COMBATIENTE DE TU LISTA");
                alert.show();
            }else{
                
                if(combatiente2selected.getTypeCombatiente().equals("ATACANTE")){
                    double damage = combatiente2selected.getDamage();
                    
                    if(cmbHability2.getValue().equals("ATAQUE MORTAL POR ATACANTE SUPREMO")){
                        damage = damage+10;
                        cmbHability2.getSelectionModel().clearSelection();
                        cmbHability2.setValue(null);
                    }
                    if(player1.getLife()-damage<=0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("EL JUGADOR 2 HA GANADO");
                        alert.setHeaderText("FELICIDADES JUGADOR 2");
                        alert.show();
                    }
                    player1.setLife(player1.getLife()-combatiente2selected.getDamage());
                    System.out.println(player1.getLife());
                    double lifeTotal = player1.getLife()/100;
                    barPlayer1.setProgress(lifeTotal);

                    changeTurn();
                }else{
                    double damage = combatiente2selected.getDamage();
                    if(cmbHability2.getValue().equals("CURACIÓN INSTANTANEA")){
                        damage = damage+100;
                        cmbHability2.getSelectionModel().clearSelection();
                        cmbHability2.setValue(null);
                    }
                    if(player2.getLife()+damage>=100){
                        player2.setLife(100);
                        double lifeTotal = player2.getLife()/100;
                        barPlayer2.setProgress(lifeTotal);
                    }else{
                        player2.setLife(player2.getLife()+combatiente2selected.getDamage());
                        double lifeTotal = player2.getLife()/100;
                        barPlayer2.setProgress(lifeTotal);
                    }
                    changeTurn();
                }
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR AL ATACAR");
            alert.setHeaderText("DEBES SELECCIONAR UN JUGADOR");
            alert.show();
        }

    }

    @FXML
    private void buyPlayer1(ActionEvent event) {
       BuyPlayer();
    }
    
    
    public void BuyPlayer(){
         Dialog<Combatientes> dialog = new Dialog<>();
        dialog.setTitle("Comprar Jugador");
        dialog.setHeaderText("");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Label label1 = new Label("NOMBRE DEL COMBATIENTE:");
        TextField textField = new TextField("");
        textField.setPromptText("INGRESA EL NOMBRE");
        
        ObservableList<String> options = FXCollections.observableArrayList(experienceAvailablearray);
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();
        
        ObservableList<String> options2 = FXCollections.observableArrayList(typeCArray);
        ComboBox<String> comboBox2 = new ComboBox<>(options2);
        
        Label label = new Label("EL DAÑO SE CALCULA EN AUTOMÁTICO");
        
        
        dialogPane.setContent(new VBox(8,label1, textField, comboBox,comboBox2,label));
        Platform.runLater(textField::requestFocus);
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                double damage = 15;
                
                if(comboBox.getValue().equals("JEFE")){
                    damage = damage+7;
                }else{
                    damage = damage-5;
                }
                
                return new Combatientes(textField.getText(),damage,comboBox2.getValue(), comboBox.getValue());
            }
            return null;
        });
        Optional<Combatientes> optionalResult = dialog.showAndWait();
        
        int coin = 2;
        
        if(optionalResult.get().getExperienceCombatiente().equals("JEFE")){
            coin = coin+2;
        }
        if(flagTurn==1){
            if(player1.getCoins()-coin>0){
                player1.setCoins(player1.getCoins()-coin);
                player1.setCombatientes(optionalResult.get());
                txtCoinPlayer1.setText(String.valueOf(player1.getCoins()));
                changeTurn();
                uploadDataCombatientes1();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ACCIÓN FALTANTE");
                alert.setHeaderText("NO TIENES SUFICIENTES MONEDAS");
                alert.show();
            }
        }else{
            if(player2.getCoins()-coin>0){
                player2.setCoins(player2.getCoins()-coin);
                txtCoinPlayer2.setText(String.valueOf(player2.getCoins()));
                player2.setCombatientes(optionalResult.get());
                changeTurn();
                uploadDataCombatientes2();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ACCIÓN FALTANTE");
                alert.setHeaderText("NO TIENES SUFICIENTES MONEDAS");
                alert.show();
            }
        }
        
    }
    
    public void changeTurn(){
        if(flagTurn==1){
            flagTurn=2;
            jugadorActualtxt.setText(player2.getName());
            buttonAtack1.setDisable(true);
            btnBuyPlayer1.setDisable(true);
            buttonAtack2.setDisable(false);
            btnbuyPlayer2.setDisable(false);
        }else{
            flagTurn=1;
            jugadorActualtxt.setText(player1.getName());
            buttonAtack1.setDisable(false);
            btnBuyPlayer1.setDisable(false);
            buttonAtack2.setDisable(true);
            btnbuyPlayer2.setDisable(true);
        }
    }

    @FXML
    private void buyPlayer2(ActionEvent event) {
        BuyPlayer();
    }

  
}
