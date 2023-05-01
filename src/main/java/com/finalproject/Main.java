package com.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


import java.io.IOException;

public class Main extends Application
{
    public static Scanner sc;
    public static Connection conn;
    public static HospitalSystem hospitalSystem;



    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args)
    {
        try{
            String jdbcURL = "jdbc:mysql://localhost:3306/hospital";
            String username = "root";
            String password = "toor";
            conn = DriverManager.getConnection(jdbcURL,username,password);
            HospitalSystem.initialize();
        } catch (SQLException e) {
            System.out.println("DBE");
        }
        launch();
    }

    public static void adminPortal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminPortal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }
    public static void login() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void frontDeskPortal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FrontDesk.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Front Desk");
        stage.setScene(scene);
        stage.show();
    }
    public static void receptionPortal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("receptionPortal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Reception");
        stage.setScene(scene);
        stage.show();
    }
    public static void assignDoctorPortal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("assignDoctorPortal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 1000);
        Stage stage = new Stage();
        stage.setTitle("Reception");
        stage.setScene(scene);
        stage.show();
    }

    public static void editSchedule() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editSchedule.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Doctor");
        stage.setScene(scene);
        stage.show();
    }

    public static void doctorPortal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("doctorPortal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Doctor");
        stage.setScene(scene);
        stage.show();
    }

    public static void staffPortal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("staffPortal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Staff");
        stage.setScene(scene);
        stage.show();
    }
    public static void wardManagerPortal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("wardManagerPortal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Ward Manager");
        stage.setScene(scene);
        stage.show();
    }
    public static void dischargePatient() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dischargePatient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Ward Manager");
        stage.setScene(scene);
        stage.show();
    }
    public static void manageWard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ManageWardPatients.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        Stage stage = new Stage();
        stage.setTitle("Ward Manager");
        stage.setScene(scene);
        stage.show();
    }
    public static void admitPatient() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdmitPatient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        Stage stage = new Stage();
        stage.setTitle("Ward Manager");
        stage.setScene(scene);
        stage.show();
    }


}