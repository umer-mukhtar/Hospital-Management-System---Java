package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DischargePatientController {
    @FXML
    private TextField id;
    @FXML
    private TextField cc;
    @FXML
    private Label label;
    @FXML
    private Boolean ds;
    public void dischargeE(ActionEvent actionEvent) throws IOException {
        if(ds){
            int retCode = HospitalSystem.enterPaidPayment(id.getText(),HospitalSystem.getDischargeAmount(id.getText()));
            Stage stage;
            Alert a;
            if(retCode == 0){
                stage = (Stage) id.getScene().getWindow();
                stage.close();
                Main.login();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
            }
            else{
                stage = (Stage) id.getScene().getWindow();
                stage.close();
                Main.login();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("FAILED\nInvalid Input");
                a.show();
            }
        }
        else{
            Stage stage;
            Alert a;
            stage = (Stage) id.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("FAILED\nNot Ready to Discharge");
            a.show();
        }

    }

    public void submitE(ActionEvent actionEvent) throws IOException {
        String dischargeStatus = HospitalSystem.checkDischargeStatus(id.getText());
        if(dischargeStatus!="-1"){
            String str = "Discharge Status: " + dischargeStatus;// +doc.getDoctorID() + "\t\t" + doc.getName() + "\t\t"  +doc.getPassword();
            if(Objects.equals(dischargeStatus, "True")){
                ds = true;
                float dischargeAmount = HospitalSystem.getDischargeAmount(id.getText());
                str +="\nDischarge Amount: " + dischargeAmount;

            }
            else{
                ds = false;
            }
            label.setText(str);
        }
        else{
            Stage stage;
            Alert a;
            stage = (Stage) id.getScene().getWindow();
            stage.close();
            Main.dischargePatient();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("FAILED\nInvalid Input");
            a.show();
        }
    }
}
