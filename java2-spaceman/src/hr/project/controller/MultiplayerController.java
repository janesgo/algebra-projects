/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.controller;

import hr.project.model.PreviousScene;
import hr.project.networking.Server;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class MultiplayerController implements Initializable, PreviousScene {

    private Scene previousScene;

    @FXML
    private GridPane gpSpaceman;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnStartMulti;
    @FXML
    private Button btnStartServer;

    private boolean serverStarted;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void back() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(previousScene);
    }

    @FXML
    private void startMulti(ActionEvent event) throws IOException {
        setScene(event, "/hr/project/view/MultiGame.fxml");
    }

    private void setScene(ActionEvent event, String fxml) throws IOException {
        Stage stage = getStage(event);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        PreviousScene sc = loader.getController();
        sc.loadPreviousScene(btnBack.getScene());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (e) -> {
        });
    }

    private Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    @Override
    public void loadPreviousScene(Scene scene) {
        previousScene = scene;
    }

    @FXML
    private void startServer(ActionEvent event) {
        
        // FIX SERVER IN USE EXCEPTION
        
        if (!serverStarted) {
            Thread serverThread = new Thread(new Server());
            serverThread.setDaemon(true);
            serverThread.start();
            serverStarted = true;
        }
    }

}
