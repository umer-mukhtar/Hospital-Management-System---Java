package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class EditScheduleController {
    @FXML
    private Label label;
    @FXML
    private TextField cancel1;
    @FXML
    private TextField update1;
    @FXML
    private TextField update2;

    @FXML
    public void initialize(){
        String str = "Meeting ID\t\tPatient ID\t\tMeeting Time\n\n";
        ArrayList<Meeting> meetings = HospitalSystem.getDoctorSchedule("1200");
        for(Meeting meet: meetings){
            str+=meet.getMeetingID() + "\t\t" + meet.getPatientID() + "\t\t" + meet.getTime() +"\n";
        }
        label.setText(str);
    }

    public void cancelE(ActionEvent actionEvent) throws IOException {
        int retCode = HospitalSystem.applyUpdate("1200",cancel1.getText(), "Cancel", "");
        Stage stage;
        Alert a;
        if(retCode == 0){
            stage = (Stage) label.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
        else{
            stage = (Stage) label.getScene().getWindow();
            stage.close();
            Main.editSchedule();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("ERROR\nInvalid Meeting ID");
            a.show();
        }
    }
    public void updateE(ActionEvent actionEvent) throws IOException {
        int retCode = HospitalSystem.applyUpdate("1200",update1.getText(), "Update", update2.getText());
        Stage stage;
        Alert a;
        if(retCode == 0){
            stage = (Stage) label.getScene().getWindow();
            stage.close();
            Main.login();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("SUCCESS");
            a.show();
        }
        else{
            stage = (Stage) label.getScene().getWindow();
            stage.close();
            Main.editSchedule();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("ERROR\nInvalid Meeting ID/ Time");
            a.show();
        }
    }
}
