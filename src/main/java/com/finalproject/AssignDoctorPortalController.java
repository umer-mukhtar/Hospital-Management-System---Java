package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AssignDoctorPortalController {
    @FXML
    private Label label;
    @FXML
    private TextField docid;
    @FXML
    private TextField patid;

    @FXML
    public void initialize(){
        ArrayList<Doctor> docLst =  HospitalSystem.getAvailableDoctors("Urology");
        String str="Doctor-ID\t\tDoctor Name\n\n";
        for(Doctor doc:docLst){
            str += (doc.getDoctorID() + "\t\t" + doc.getName()) + "\n";
        }
        label.setText(str);
    }

    public void submitE(ActionEvent e) throws IOException {
        int returnCode = HospitalSystem.addToWaitingList(patid.getText(),docid.getText(),"Urology");
        Stage stage;
        Alert a;
        switch(returnCode){
            case 0:
                stage = (Stage) patid.getScene().getWindow();
                stage.close();
                Main.login();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
                break;
            case -1:
                stage = (Stage) patid.getScene().getWindow();
                stage.close();
                Main.assignDoctorPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("FAILED\nInvalid Patient ID");
                a.show();
                break;
            case -2:
                stage = (Stage) patid.getScene().getWindow();
                stage.close();
                Main.assignDoctorPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("FAILED\nInvalid Doctor ID");
                a.show();
                break;
        }
    }
}
