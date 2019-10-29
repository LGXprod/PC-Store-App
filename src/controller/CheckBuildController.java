package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Build;

public class CheckBuildController extends Controller<Build>{
    
    @FXML private Text messageTxt;
    
    public Build getBuild(){ return model; }
    
    @FXML private void initialize(){
        if (getBuild().isValid()){
            messageTxt.textProperty().setValue("The build is functional.");
        } else{
            String message = "";
            for (String part : getBuild().typesMissing()){
                if (part.equals("cpu")){
                    message = message + "The build is missing a " + part.toUpperCase() + ".\n";
                } else if (part.equals("memory")){
                    message = message + "The build is missing " + "RAM" + ".\n";
                } else if (part.equals("motherboard") || part.equals("case")){
                    message = message + "The build is missing a " + part + ".\n";
                }else{
                    message = message + "The build is missing " + part + ".\n";
                }
            }
            messageTxt.textProperty().setValue(message);
        }
    }

    @FXML public void handleClose(ActionEvent event){
        stage.close();
    }
    
}
