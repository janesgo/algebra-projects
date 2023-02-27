/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.controller;

import hr.project.model.PreviousScene;
import hr.project.networking.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class MultiGameController implements Initializable, PreviousScene {

    @FXML
    private Pane pnGame;
    @FXML
    private Button btnInfo;
    @FXML
    private Label lblScoreFirst;
    @FXML
    private Label lblScoreSecond;
    @FXML
    private Label lblWaiting;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void loadPreviousScene(Scene scene) {
    }

    @FXML
    private void start(ActionEvent event) {
        btnInfo.setVisible(false);
        lblWaiting.setText("Connecting to server...");
        lblWaiting.setVisible(true);
        Client client = new Client();
        client.init(pnGame, pnGame.getScene());
        lblWaiting.setText("Connected. Waiting for another player...");
        client.start();
    }

}
