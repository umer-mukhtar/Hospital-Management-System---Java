package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FrontDeskController {
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    protected void submitE(ActionEvent e) throws IOException {
        String nameText = name.getText();
        String addressText = address.getText();
        //0 dept 1 name 2 password
        String returnCode = HospitalSystem.addNewPatient(nameText,addressText);
        Alert a;
        Stage stage;
        switch (returnCode){
            case "-1":
                stage = (Stage) name.getScene().getWindow();
                stage.close();
                Main.adminPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("FAILED");
                a.show();
                break;
            default:
                stage = (Stage) name.getScene().getWindow();
                stage.close();
                Main.login();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS\nPatient-ID:" + returnCode);
                a.show();
                break;
        }//        System.out.println(returnCode);
        //-1 = failed, 1 = doctor,2 = staff/receptionist, 3 = admin, 4 = pa, 5 = ward manager
    }
}
