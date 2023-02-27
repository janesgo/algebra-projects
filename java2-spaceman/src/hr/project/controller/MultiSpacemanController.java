/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.controller;

import hr.project.model.PreviousScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
public class MultiSpacemanController implements Initializable {

    @FXML
    private GridPane gpSpaceman;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSingleplayer;
    @FXML
    private Button btnMultiplayer;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void singleplayer(ActionEvent event) throws IOException {
        setScene(event, "/hr/project/view/Singleplayer.fxml");
    }

    @FXML
    private void multiplayer(ActionEvent event) throws IOException {
        setScene(event, "/hr/project/view/Multiplayer.fxml");
    }

    @FXML
    private void settings(ActionEvent event) throws IOException {
        setScene(event, "/hr/project/view/Settings.fxml");
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    private void setScene(ActionEvent event, String fxml) throws IOException {
        Stage stage = getStage(event);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        PreviousScene sc = loader.getController();
        sc.loadPreviousScene(btnExit.getScene());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (e) -> {
            Platform.exit();
        });
    }

    private Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

}
