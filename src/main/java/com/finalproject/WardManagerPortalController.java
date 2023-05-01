package com.finalproject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class WardManagerPortalController {
    @FXML
    private ChoiceBox<String> actionType;

    @FXML
    public void initialize()
    {
        actionType.setItems(FXCollections.observableArrayList("Discharge Patient","Manage Patients/Beds","Admit Patient" ));

    }
    @FXML
    protected void submitE(ActionEvent e) throws IOException {
        String acType = actionType.getValue();
        Stage stage;
        Alert a;
        switch (acType){
            case "Discharge Patient":
                stage = (Stage) actionType.getScene().getWindow();
                stage.close();
                Main.dischargePatient();
//                a = new Alert(Alert.AlertType.CONFIRMATION);
//                a.setHeaderText("SUCCESS");
//                a.show();
                break;
            case "Manage Patients/Beds":
                stage = (Stage) actionType.getScene().getWindow();
                stage.close();
                Main.manageWard();
                break;
            case "Admit Patient":
                stage = (Stage) actionType.getScene().getWindow();
                stage.close();
                Main.admitPatient();
                break;
        }
    }
}
