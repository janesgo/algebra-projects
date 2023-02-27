/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.controller;

import hr.project.model.PreviousScene;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class SingleplayerController implements Initializable, PreviousScene {

    private Scene previousScene;

    @FXML
    private GridPane gpSpaceman;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnLoad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void playGame(ActionEvent event) {
    }

    @FXML
    private void playSaved(ActionEvent event) {
    }

    @FXML
    private void back() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(previousScene);
    }

    @Override
    public void loadPreviousScene(Scene scene) {
        previousScene = scene;
    }

}
