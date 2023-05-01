package com.finalproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reception { //department reception
    private String receptionistID;
    private String receptionistPassword;
    private Department department;
    public Reception(Department department)  {
        this.department = department;
        try{
            String sql = "SELECT * FROM receptionist WHERE department like '" + department.getName() + "';";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                receptionistID = (rs.getString("id"));
                receptionistPassword = (rs.getString("password"));
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("ReceptionE");
        }

    }

    public String getReceptionistID() {
        return receptionistID;
    }

    public String getReceptionistPassword() {
        return receptionistPassword;
    }

    public Department getDepartment() {
        return department;
    }
}
