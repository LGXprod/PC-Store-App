package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Catalogue;

public class ErrorController extends Controller<Exception>{

    public final Exception getException(){
        return model;
    }
    
    public void handleClose(ActionEvent event){
        stage.close();
    }
    
}
