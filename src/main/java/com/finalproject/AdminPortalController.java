package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPortalController {
    @FXML
    private TextField name;
    @FXML
    private TextField dept;
    @FXML
    private TextField password;
    @FXML
    protected void submitE(ActionEvent e) throws IOException {
        String nameText = name.getText();
        String deptText = dept.getText();
        String passwordText = password.getText();
        //0 dept 1 name 2 password
        ArrayList<String> arrLst = new ArrayList<>();
        arrLst.add(deptText);
        arrLst.add(nameText);
        arrLst.add(passwordText);
        String returnCode = HospitalSystem.registerDoctor(arrLst);
        Alert a;
        Stage stage;
        switch (returnCode){
            case "-1":
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.adminPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("FAILED");
                a.show();
                break;
            default:
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.login();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS\nDoctor-ID:" + returnCode);
                a.show();
                break;
        }//        System.out.println(returnCode);
        //-1 = failed, 1 = doctor,2 = staff/receptionist, 3 = admin, 4 = pa, 5 = ward manager
    }
}
