/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.controller;

import hr.project.model.PreviousScene;
import hr.project.utils.MessageUtils;
import hr.project.utils.ReflectionUtils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SettingsController implements Initializable, PreviousScene {

    private Scene previousScene;

    private static final String DOCUMENTATION_FILENAME = "documentation.html";

    private static final String HTML_START = new StringBuilder()
            .append("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n")
            .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
            .append("<title>Documentation</title>\n")
            .append("<style>\n.tab {\ndisplay: inline-block;\nmargin-left: 20px;\n}\n</style>\n</head>\n<body>\n").toString();
    private static final String HTML_END = "</body>\n</html>";
    private static final String HTML_SEPARATOR = "<br>";
    private static final String CLASSES_PATH = "src/hr/project/model";
    private static final String CLASSES_PACKAGE
            = CLASSES_PATH.substring(CLASSES_PATH.indexOf("/") + 1)
                    .replace("/", ".").concat(".");

    @FXML
    private GridPane gpSpaceman;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnGenerateDocs;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void generateDocs() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DOCUMENTATION_FILENAME))) {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(CLASSES_PATH));

            StringBuilder classAndMembersInfo = new StringBuilder(HTML_START);
            classAndMembersInfo.append("<h1>Documentation for Spaceman project</h1>");

            stream.forEach(file -> {
                String filename = file.getFileName().toString();
                String className = filename.substring(0, filename.indexOf("."));

                classAndMembersInfo
                        .append("<h2>")
                        .append(className)
                        .append("</h2>\n");

                try {
                    Class<?> clazz = Class.forName(CLASSES_PACKAGE.concat(className));
                    ReflectionUtils.readClassAndMembersInfo(clazz, classAndMembersInfo);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                classAndMembersInfo
                        .append(System.lineSeparator())
                        .append(System.lineSeparator());

            });

            classAndMembersInfo.append(HTML_END);
            writer.write(classAndMembersInfo.toString());

            MessageUtils.showInfoMessage("Generate docs info", "Documentation created", "Check '" + DOCUMENTATION_FILENAME + "'");
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
