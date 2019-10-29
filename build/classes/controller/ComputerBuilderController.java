package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Build;
import model.Catalogue;
import model.ComputerBuilder;

public class ComputerBuilderController extends Controller<ComputerBuilder>{
    
    public ComputerBuilder getComputerBuilder () { return model; }
    
    @FXML public void handleViewCatalogue(ActionEvent event) throws IOException {
        Catalogue catalogue = getComputerBuilder().getCatalogue();
        ViewLoader.showStage(catalogue, "/view/catalogue.fxml", "Catalogue", new Stage());
    }
    
    @FXML public void handleViewBuild(ActionEvent event) throws IOException {
        Build build = getComputerBuilder().getCatalogue().getBuild();
        ViewLoader.showStage(build, "/view/build.fxml", "Current Build", new Stage());
    }
    
    @FXML public void handleQuit(ActionEvent event) throws IOException {
        //stage.close();
        Platform.exit();
    }
    
}