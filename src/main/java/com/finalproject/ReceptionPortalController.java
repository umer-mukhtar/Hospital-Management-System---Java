package com.finalproject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ReceptionPortalController {
    @FXML
    private ChoiceBox<String> actionTypee;

    @FXML
    public void initialize()
    {
        actionTypee.setItems(FXCollections.observableArrayList("Assign Doctor" ));

    }
    @FXML
    protected void submitE(ActionEvent e) throws  IOException {
        String acType = actionTypee.getValue();
        Stage stage;
        Alert a;
        switch (acType){
            case "Assign Doctor":
                stage = (Stage) actionTypee.getScene().getWindow();
                stage.close();
                Main.assignDoctorPortal();
//                a = new Alert(Alert.AlertType.CONFIRMATION);
//                a.setHeaderText("SUCCESS");
//                a.show();
                break;
        }
    }
}
