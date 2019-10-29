package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Catalogue;
import model.Part;

public class AddToCatalogueController extends Controller<Catalogue>{
    
    @FXML private TextField typeTf;
    @FXML private TextField nameTf;
    @FXML private TextField priceTf;
    
    public Catalogue getCatalogue(){
        return model;
    }
    
    private String getType(){ return typeTf.getText(); }
    private String getName(){ return nameTf.getText(); }
    private double getPrice() throws Exception { 
        
        String[] input = priceTf.getText().split("");
        String[] accept = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
        
        for (String character : input){
            
            boolean willAccept = false;
            
            for (String acceptable: accept){
                if (character.equals(acceptable)){
                    willAccept = true;
                }
            }
            
            if (!willAccept){ throw new Exception("Not a number."); }
            
        }
        
        return Double.parseDouble(priceTf.getText());
        
    }
    
    @FXML public void handleAddNewPart(ActionEvent event) throws Exception {
        try {
            double price = getPrice();
            getCatalogue().addPart(getName(), getType(), price);
            stage.close();
        } catch (Exception e) {
            ViewLoader.showStage(e, "/view/error.fxml", "Incorrect Input", new Stage());
        }
    }
    
}
