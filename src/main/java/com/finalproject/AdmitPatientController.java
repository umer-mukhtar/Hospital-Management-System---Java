package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Objects;

public class AdmitPatientController {
    @FXML
    private TextField bednumber;
    @FXML
    private  TextField id;
    @FXML
    private Label label;
    @FXML
    public void initialize(){
        Ward ward = HospitalSystem.getWardInfo();
        String str = "Ward Number: " + ward.getWardNumber()+"\nBed Number\n\n";
        for(WardBed bed:ward.getBeds()){
            if(Objects.equals(bed.getPatientID(), "")){
                str+=bed.getBedNo() + "\n";
            }
        }
        label.setText(str);
    }
    public void submitE(ActionEvent actionEvent) throws IOException {
        if(HospitalSystem.manageWard("Add",id.getText(),bednumber.getText()) == 0){
            Stage stage;
            Alert a;
            stage = (Stage) bednumber.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
        else{
            Stage stage;
            Alert a;
            stage = (Stage) id.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
    }
}
