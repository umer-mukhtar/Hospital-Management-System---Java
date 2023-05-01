package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class FrontDesk {
    private ArrayList<String> receptionistID;
    private ArrayList<String> receptionistPassword;
    private ArrayList<String> password;

    public FrontDesk()  {
        receptionistPassword = new ArrayList<>();
        receptionistID = new ArrayList<>();
        try{
            String sql = "SELECT * FROM receptionist WHERE department like 'null';";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                receptionistID.add(rs.getString("id"));
                receptionistPassword.add(rs.getString("password"));
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("FrontDeskE");
        }

    }

    public int login(String userID, String password) {
//        System.out.println(userID);
//        System.out.println(password);
//        System.out.println("aa");
        for(String recID:receptionistID){
            if(Objects.equals(userID, recID)){
                for(String recPass:receptionistPassword){
                    System.out.println(recID);
                    System.out.println(recPass);
                    if(Objects.equals(password, recPass)){
                        return 0;
                    }
                }
            }
        }
        return -1;
    }

}
