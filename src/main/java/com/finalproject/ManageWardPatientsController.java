package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageWardPatientsController {
    @FXML
    private TextField  addid;
    @FXML
    private TextField addbed;
    @FXML
    private TextField updatebed;
    @FXML
    private TextField updatestatus;
    @FXML
    private TextField removeid;
    @FXML
    private Label label;
    @FXML
    public void initialize(){
        Ward ward = HospitalSystem.getWardInfo();
        String str = "Ward Number: " + ward.getWardNumber()+"\nBed Number \t\tPatient Info\n\n";
        for(WardBed bed:ward.getBeds()){
            str+=bed.getBedNo() + "\t\t" + bed.getPatientID() + "\n";
        }
        label.setText(str);

    }

    public void addE(ActionEvent actionEvent) throws IOException {
        if(HospitalSystem.manageWard("Add",addid.getText(),addbed.getText()) == 0){
            Stage stage;
            Alert a;
            stage = (Stage) addbed.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
        else{
            Stage stage;
            Alert a;
            stage = (Stage) addbed.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
    }
    public void updateE(ActionEvent actionEvent) throws IOException {
        if(HospitalSystem.manageWard("Update",updatebed.getText(),updatestatus.getText()) == 0){
            Stage stage;
            Alert a;
            stage = (Stage) addbed.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
        else{
            Stage stage;
            Alert a;
            stage = (Stage) addbed.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("FAILED");
            a.show();
        }
    }
    public void removeE(ActionEvent actionEvent) throws IOException {
        if(HospitalSystem.manageWard("Remove",removeid.getText(),"") == 0){
            Stage stage;
            Alert a;
            stage = (Stage) addbed.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
        else{
            Stage stage;
            Alert a;
            stage = (Stage) addbed.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("FAILED");
            a.show();
        }
    }
}
