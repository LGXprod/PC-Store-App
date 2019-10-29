package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Build;
import model.Part;

public class BuildController extends Controller<Build>{

    @FXML private TableView<Part> partsTv;
    @FXML private TableColumn<Part, String>  typeClm;
    @FXML private TableColumn<Part, String> nameClm;
    @FXML private TableColumn<Part, String> priceClm;
    @FXML private Button removeBtn;
    @FXML private Text totalTxt;
    
    public Build getBuild(){ return model; }
    
    public List<Part> getSelectedParts(){ 
        return partsTv.getSelectionModel().getSelectedItems(); 
    }

    @FXML private void initialize(){
        partsTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        typeClm.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        nameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceClm.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("$%.2f"));
        
        partsTv.getSelectionModel().selectedItemProperty().addListener(
				(newSubject) -> removeBtn.setDisable(newSubject == null)
				);
       
    }
    
    @FXML private void handleBuild(ActionEvent event) throws IOException {
         ViewLoader.showStage(getBuild(), "/view/buildcheck.fxml", "Build Validity Status", new Stage());
    }
    
    @FXML private void handleRemove(ActionEvent event){
        List parts = getSelectedParts();
        getBuild().remove(parts);
        removeBtn.setDisable(true);
    }
    
    @FXML private void handleClose(ActionEvent event){
        stage.close();
    }
    
}
