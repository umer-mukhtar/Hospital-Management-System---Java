package com.finalproject;

import javafx.fxml.FXML;
import javafx.scene.LightBase;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController
{

    @FXML
    private TextField userID;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> userType;
    @FXML
    private Label loginMessage;


    @FXML
    public void initialize()
    {
        userType.setItems(FXCollections.observableArrayList("Doctor", "Reception", "Admin","PA","Ward Manager", "Front Desk","Staff"));
    }

    @FXML
    protected void onLoginButtonClick() throws  IOException {
        String typeOfLogin = userType.getValue();
//        HospitalSystem.enterDoctorID("12345");
        System.out.println(typeOfLogin);
        int returnCode = HospitalSystem.login(typeOfLogin,userID.getText(),password.getText());
        System.out.println(returnCode);
        //-1 = failed, 1 = doctor,2 = receptionist, 3 = admin, 4 = pa, 5 = ward manager, 6 = front desk 7 = staff
        Stage stage;
        Alert a;
        switch (returnCode){
            case -1:
                loginMessage.setText("Invalid Credentials. Please try again");
                break;
            case 1:
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.doctorPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
                break;
            case 2:
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.receptionPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
                break;
            case 3:
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.adminPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
                break;
            case 4:
                break;
            case 5:
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.wardManagerPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
                break;
            case 6:
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.frontDeskPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
                break;
            case 7:
                stage = (Stage) password.getScene().getWindow();
                stage.close();
                Main.staffPortal();
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("SUCCESS");
                a.show();
        }
    }
}
