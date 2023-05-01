package com.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StaffPortalController {
    @FXML
    private TextField id;
    @FXML
    private TextField updateid;
    @FXML
    private TextField updatename;
    @FXML
    private TextField updatepassword;
    @FXML
    private TextField removeid;

    @FXML
    private Label label;

    public void removeE(ActionEvent actionEvent) throws IOException {
        ArrayList<String> arrLst = new ArrayList<>();
        arrLst.add(id.getText());
        int retCode = HospitalSystem.applyOperation(id.getText(),"Remove",arrLst);
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
            Main.staffPortal();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("FAILED\nInvalid Input");
            a.show();
        }
    }

    public void updateE(ActionEvent actionEvent) throws IOException {
        ArrayList<String> arrLst = new ArrayList<>();
        arrLst.add(id.getText());
        arrLst.add(updatename.getText());
        arrLst.add(updatepassword.getText());
        int retCode = HospitalSystem.applyOperation(id.getText(),"Update",arrLst);
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
            Main.staffPortal();
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("FAILED\nInvalid Input");
            a.show();
        }

    }

    public void submitE(ActionEvent actionEvent) {
        Doctor doc = HospitalSystem.getDoctor(id.getText());
        assert doc != null;
        String str = "Doctor ID\t\tDoctor Name\t\t DoctorPassword\n" +doc.getDoctorID() + "\t\t" + doc.getName() + "\t\t"  +doc.getPassword();
        label.setText(str);
    }
}
