package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Catalogue;
import model.Part;

public class CatalogueController extends Controller<Catalogue>{
    
    @FXML private TableView<Part> partsTv;
    @FXML private TableColumn<Part, String> typeClm;
    @FXML private TableColumn<Part, String> nameClm;
    @FXML private TableColumn<Part, String> priceClm;
    @FXML private TextField typeTf;
    @FXML private TextField minTf;
    @FXML private TextField maxTf;
    @FXML private Button addToBuildBtn;
    @FXML private Button removeBtn;
    
    public Catalogue getCatalogue() { return model; }
    
    public List<Part> getSelectedParts(){ 
        return partsTv.getSelectionModel().getSelectedItems(); 
    }
    
    @FXML private void initialize(){
        partsTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        typeClm.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        nameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceClm.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("$%.2f"));
        
        partsTv.getSelectionModel().selectedItemProperty().addListener(
				(newSubject) -> addToBuildBtn.setDisable(newSubject == null)
				);
        partsTv.getSelectionModel().selectedItemProperty().addListener(
				(newSubject) -> removeBtn.setDisable(newSubject == null)
				);

        typeTf.textProperty().addListener((o, oldText, newText) -> getCatalogue().filterList(newText, minTf.getText(), maxTf.getText()));
        minTf.textProperty().addListener((o, oldText, newText) -> getCatalogue().filterList(typeTf.getText(), newText, maxTf.getText()));
        maxTf.textProperty().addListener((o, oldText, newText) -> getCatalogue().filterList(typeTf.getText(), minTf.getText(), newText));
    }
    
    @FXML private void handleAddSelected(ActionEvent event){
        List<Part> parts = getSelectedParts();
        if (parts.size() > 0){
            getCatalogue().addToBuild(parts);
        }   
    }  
    
    @FXML private void handleRemovePart(ActionEvent event){
        List<Part> parts = getSelectedParts();
        if (parts.size() > 0){
            getCatalogue().remove(parts);
        }
        addToBuildBtn.setDisable(true);
        removeBtn.setDisable(true);
    }
    
    @FXML public void handleQuit(ActionEvent event){
        stage.close();
    }
    
    @FXML public void handleViewNewPart(ActionEvent event) throws IOException {
        ViewLoader.showStage(getCatalogue(), "/view/addtocatalogue.fxml", "Add New Part to Catalogue", new Stage());
    }
}
