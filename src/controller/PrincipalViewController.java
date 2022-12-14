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
import models.Partner;
import models.Player;
import models.SuperCombatiente;

/**
 *
 * @author Davis
 */
public class PrincipalViewController implements Initializable {
    
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
    

    
    private ArrayList<String> experienceAvailablearray = new ArrayList();
    
    private ArrayList<String> typeCArray = new ArrayList();
    
    private ArrayList<SuperCombatiente> habilitySuper = new ArrayList();

    
    private Player player1 = new Player("JUGADOR 1",100, 10);  
    private Player player2 = new Player("JUGADOR 2",100, 10);

    private int flagTurn = 1;
    
    
    private Combatientes combatiente1selected;
    private Combatientes combatiente2selected;
    @FXML
    private TableView<Partner> tablePartner1;
    @FXML
    private TableColumn<Partner, String> partnerNameColumn1;
    @FXML
    private TableColumn<Partner, Double> partnerDamageColumn1;

    @FXML
    private TableView<Partner> tablePartner2;
    @FXML
    private TableColumn<Partner, String> partnerNameColumn2;
    @FXML
    private TableColumn<Partner, Double> partnerDamageColumn2;
    @FXML
    private Button addPartner1button;
    @FXML
    private Button addPartner2Button;

    
    /**
     * inicia la view y ejecuta initizalize
     * @param url
     * @param rb 
     */
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
    
    
    /**
     * setea una super habilidad al combatiente 
     * @param combatiente 
     */
    public void setSuperHability(Combatientes combatiente){
        habilitySuper.clear();
        cmbHability1.setValue("");        
        cmbHability2.setValue("");
        if(combatiente.getExperienceCombatiente().equals("RAID BOSS")){
            if(combatiente.getTypeCombatiente().equals("ATACANTE")){
                habilitySuper.add(new SuperCombatiente("CLONAR",20, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
                habilitySuper.add(new SuperCombatiente("VARIAR",0, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
                habilitySuper.add(new SuperCombatiente("LIBERAR",0, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
            }else if(combatiente.getTypeCombatiente().equals("CURANDERO")){
                habilitySuper.add(new SuperCombatiente("CURACI??N INSTANTANEA",100, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
            }
            habilitySuper.add(new SuperCombatiente("SOLTAR MASCOTA",2, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));

        }else{
            if(combatiente.getTypeCombatiente().equals("ATACANTE")){
                habilitySuper.add(new SuperCombatiente("ATAQUE MORTAL POR ATACANTE SUPREMO",10, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
            }else if(combatiente.getTypeCombatiente().equals("CURANDERO")){
                habilitySuper.add(new SuperCombatiente("CURACI??N INSTANTANEA",100, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
            }
            habilitySuper.add(new SuperCombatiente("SOLTAR MASCOTA",1, combatiente.getName(),combatiente.getDamage(), combatiente.getTypeCombatiente(),combatiente.getExperienceCombatiente()));
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
    
    
    
    public void clonar(){
    
        if(flagTurn==1){
            if(combatiente2selected != null){
                player1.setCombatientes(combatiente2selected);
                uploadDataCombatientes1();
            }
        }else{
            if(combatiente1selected != null){
                player2.setCombatientes(combatiente1selected);
                uploadDataCombatientes1();
            }
        }
    }
    
    /**
     * selecciona el combatiente del jugador1
     * @param event 
     */
      @FXML
    public void clickCombatiente1(MouseEvent event) {
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
            uploadDataPartner1();
            
        }catch(Exception e){
        
        }
    }
    
    /**
     * selecciona el combatiente del jugador 2
     * @param event 
     */
    @FXML
    public void clickCombatiente2(MouseEvent event) {
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
            uploadDataPartner2();
            
        }catch(Exception e){
        
        }
    }
    
    /**
     * carga la data de combatientes para jugador 1 
     */
    public void uploadDataCombatientes1(){
        ObservableList<Combatientes> combatientesPlayer1;
        combatientesPlayer1 = FXCollections.observableList(player1.getCombatientes());
        //configura la table de programas en ejecuci??n
        tablePlayer1.setItems(combatientesPlayer1);
        atacker1Column.setCellValueFactory(new PropertyValueFactory("name"));
        damage1Column.setCellValueFactory(new PropertyValueFactory("damage"));
        type1Column.setCellValueFactory(new PropertyValueFactory("typeCombatiente"));
    }
    
    
   
    
    
     /**
      * carga la data de combatientes para jugador 2
      */
    public void uploadDataCombatientes2(){
        ObservableList<Combatientes> combatientesPlayer2;
        combatientesPlayer2 = FXCollections.observableList(player2.getCombatientes());
        //configura la table de programas en ejecuci??n
        tablePlayer2.setItems(combatientesPlayer2);
        atacker2Column.setCellValueFactory(new PropertyValueFactory("name"));
        damage2Column.setCellValueFactory(new PropertyValueFactory("damage"));
        type2Column.setCellValueFactory(new PropertyValueFactory("typeCombatiente"));
    }
    
    
    
    /**
     * carga la data de acompa??antes para jugador 1
     */
    public void uploadDataPartner1(){
        ObservableList<Partner> partnersPlayer1;
        partnersPlayer1 = FXCollections.observableList(combatiente1selected.getPartners());
        //configura la table de programas en ejecuci??n
        tablePartner1.setItems(partnersPlayer1);
        partnerNameColumn1.setCellValueFactory(new PropertyValueFactory("name"));
        partnerDamageColumn1.setCellValueFactory(new PropertyValueFactory("damage"));
    }
    
    /**
     * carga la data de acompa??antes para jugador 2
     */
    public void uploadDataPartner2(){
        ObservableList<Partner> partnersPlayer2;
        partnersPlayer2 = FXCollections.observableList(combatiente2selected.getPartners());
        //configura la table de programas en ejecuci??n
        tablePartner2.setItems(partnersPlayer2);
        partnerNameColumn2.setCellValueFactory(new PropertyValueFactory("name"));
        partnerDamageColumn2.setCellValueFactory(new PropertyValueFactory("damage"));
    }
    
    /**
     * guarda los valores de experiencia
     */
    public void setExperienceArray(){
        experienceAvailablearray.add("RAID BOSS");
        experienceAvailablearray.add("JEFE");
        experienceAvailablearray.add("SOLDADO");
        experienceAvailablearray.add("CAZADOR");
    }
    
    /**
     * guarda los tipos de combatientes
     */
    public void setTypeCArray(){
        typeCArray.add("CURANDERO");
        typeCArray.add("ATACANTE");
    }

    
    
    /**
     * ataca al jugador 2
     * @param event 
     */
    
    @FXML
    public void atackPlayer1(ActionEvent event) {
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
                    }else if(cmbHability1.getValue().equals("SOLTAR MASCOTA")){
                        if(combatiente1selected.getExperienceCombatiente().equals("RAID BOSS")){
                            damage = damage+5;
                        }else{
                            damage = damage+10;
                        }
                        
                        cmbHability1.getSelectionModel().clearSelection();
                        cmbHability1.setValue(null);
                    
                    }else if(cmbHability1.getValue().equals("CLONAR")){
                        clonar();
                    }
                    if(player2.getLife()-damage<=0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("EL JUGADOR 1 HA GANADO");
                        alert.setHeaderText("FELICIDADES JUGADOR 1");
                        alert.show();
                    }
                    player2.setLife(player2.getLife()-damage);
                    System.out.println(player2.getLife());
                    double lifeTotal = player2.getLife()/100;
                    barPlayer2.setProgress(lifeTotal);

                    changeTurn();
                }else{
                    double damage = combatiente1selected.getDamage();
                    if(cmbHability1.getValue().equals("CURACI??N INSTANTANEA")){
                        damage = damage+100;
                        cmbHability1.getSelectionModel().clearSelection();
                        cmbHability1.setValue(null);
                    }else if(cmbHability1.getValue().equals("CLONAR")){
                        clonar();
                    }
                    if(player1.getLife()+damage>=100){
                        player1.setLife(100);
                        double lifeTotal = player1.getLife()/100;
                        barPlayer1.setProgress(lifeTotal);
                    }else{
                        player1.setLife(player1.getLife()+damage);
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
    
    /**
     * ataca al jugador 1
     * @param event 
     */

    @FXML
    public void atackPlayer2(ActionEvent event) {
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
                    }else if(cmbHability2.getValue().equals("SOLTAR MASCOTA")){
                        if(combatiente2selected.getExperienceCombatiente().equals("RAID BOSS")){
                            damage = damage+5;
                        }else{
                            damage = damage+10;
                        }
                        
                        cmbHability2.getSelectionModel().clearSelection();
                        cmbHability2.setValue(null);
                    
                    }else if(cmbHability2.getValue().equals("CLONAR")){
                        clonar();
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
                    if(cmbHability2.getValue().equals("CURACI??N INSTANTANEA")){
                        damage = damage+100;
                        cmbHability2.getSelectionModel().clearSelection();
                        cmbHability2.setValue(null);
                    }else if(cmbHability2.getValue().equals("CLONAR")){
                        clonar();
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

    
    /**
     * se compra un nuevo combatiente para jugador 1
     * @param event 
     */
    @FXML
    public void buyPlayer1(ActionEvent event) {
       BuyPlayer();
    }
    
    
    /**
     * m??todo para evaluar que combatiente se va a comprar y a quien le corresponde
     */
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
        
        Label label = new Label("EL DA??O SE CALCULA EN AUTOM??TICO");
        
        
        dialogPane.setContent(new VBox(8,label1, textField, comboBox,comboBox2,label));
        Platform.runLater(textField::requestFocus);
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                double damage = 15;
                
                if(comboBox.getValue().equals("JEFE")){
                    damage = damage+7;
                }else if(comboBox.getValue().equals("CURANDERO")){
                    damage = damage-5;
                }else{
                    damage = damage+20;
                }
                
                return new Combatientes(textField.getText(),damage,comboBox2.getValue(), comboBox.getValue());
            }
            return null;
        });
        Optional<Combatientes> optionalResult = dialog.showAndWait();
        
        int coin = 2;
        
        if(optionalResult.get().getExperienceCombatiente().equals("JEFE")){
            coin = coin+2;
        }else if(optionalResult.get().getExperienceCombatiente().equals("RAID BOSS")){
            coin = coin+3;
        }
        
        if(flagTurn==1){
            if(player1.getCoins()-coin>=0){
                player1.setCoins(player1.getCoins()-coin);
                player1.setCombatientes(optionalResult.get());
                txtCoinPlayer1.setText(String.valueOf(player1.getCoins()));
                changeTurn();
                uploadDataCombatientes1();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ACCI??N FALTANTE");
                alert.setHeaderText("NO TIENES SUFICIENTES MONEDAS");
                alert.show();
            }
        }else{
            if(player2.getCoins()-coin>=0){
                player2.setCoins(player2.getCoins()-coin);
                txtCoinPlayer2.setText(String.valueOf(player2.getCoins()));
                player2.setCombatientes(optionalResult.get());
                changeTurn();
                uploadDataCombatientes2();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ACCI??N FALTANTE");
                alert.setHeaderText("NO TIENES SUFICIENTES MONEDAS");
                alert.show();
            }
        }
        
    }
    
    
    /**
     * cambio de turno
     */
    public void changeTurn(){
        if(flagTurn==1){
            flagTurn=2;
            jugadorActualtxt.setText(player2.getName());
            buttonAtack1.setDisable(true);
            btnBuyPlayer1.setDisable(true);
            addPartner1button.setDisable(true);
            buttonAtack2.setDisable(false);
            btnbuyPlayer2.setDisable(false);
            addPartner2Button.setDisable(false);
            
        }else{
            flagTurn=1;
            jugadorActualtxt.setText(player1.getName());
            buttonAtack1.setDisable(false);
            btnBuyPlayer1.setDisable(false);
            addPartner1button.setDisable(false);
            buttonAtack2.setDisable(true);
            btnbuyPlayer2.setDisable(true);
            addPartner2Button.setDisable(true);

        }
    }

    
    /**
     * compra un combatiente el jugador 2
     * @param event 
     */
    @FXML
    public void buyPlayer2(ActionEvent event) {
        BuyPlayer();
    }

    
    /**
     * comprar acompa??ante 1
     * @param event 
     */
    @FXML
    private void addPartner1(ActionEvent event) {
        
        buyPartner();
    }
    
    /**
     * comprar acompa??ante 2
     * @param event 
     */

    @FXML
    private void addPartner2(ActionEvent event) {
        buyPartner();
    }
    
    

    /**
     * seleccionar el acompa??ante 1
     * @param event 
     */
    @FXML
    private void clickPartner1(MouseEvent event) {
    }

    
    /**
     * seleccionar el acompa??ante 2
     * @param event 
     */
    @FXML
    private void clickPartner2(MouseEvent event) {
    }

    
    
    public void buyPartner(){
        
        boolean flag=false;
        
        if(flagTurn==1){
            if(combatiente1selected!=null){
                flag=true;
            }
        }else{
            if(combatiente2selected!=null){
                flag=true;
            }
        }
        
        
        if(flag){
             Dialog<Partner> dialog = new Dialog<>();
            dialog.setTitle("Comprar Acompa??ante");
            dialog.setHeaderText("");
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            Label label1 = new Label("NOMBRE DEL ACOMPA??ANTE:");
            TextField textField = new TextField("");
            textField.setPromptText("INGRESA EL NOMBRE");



            Label label = new Label("EL DA??O del acompa??ante es siempre 5 si es RAID BOSS es 10");


            dialogPane.setContent(new VBox(8,label1, textField,label));
            Platform.runLater(textField::requestFocus);
            dialog.setResultConverter((ButtonType button) -> {
                if (button == ButtonType.OK) {
                    double damage = 5;

                    if(flagTurn==1){
                        if(combatiente1selected.getExperienceCombatiente().equals("RAID BOSS")){
                            damage=damage+5;
                        }
                    }else{
                        if(combatiente2selected.getExperienceCombatiente().equals("RAID BOSS")){
                            damage=damage+5;
                        }
                    }
                    return new Partner(textField.getText(),damage);
                }
                return null;
            });
            Optional<Partner> optionalResult = dialog.showAndWait();

            int coin = 1;



            if(flagTurn==1){
                if(player1.getCoins()-coin>=0){
                    player1.setCoins(player1.getCoins()-coin);
                    combatiente1selected.setPartners(optionalResult.get());
                    txtCoinPlayer1.setText(String.valueOf(player1.getCoins()));
                    changeTurn();
                    uploadDataPartner1();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ACCI??N FALTANTE");
                    alert.setHeaderText("NO TIENES SUFICIENTES MONEDAS");
                    alert.show();
                }
            }else{
                if(player2.getCoins()-coin>=0){
                    player2.setCoins(player2.getCoins()-coin);
                    txtCoinPlayer2.setText(String.valueOf(player2.getCoins()));
                    combatiente2selected.setPartners(optionalResult.get());
                    changeTurn();
                    uploadDataCombatientes2();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ACCI??N FALTANTE");
                    alert.setHeaderText("NO TIENES SUFICIENTES MONEDAS");
                    alert.show();
                }
            }
        
        
        }else{
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ACCI??N FALTANTE");
            alert.setHeaderText("DEBES SELECCIONAR UN COMBATIENTE");
            alert.show();

        }
       
            
        
    }
  
}
