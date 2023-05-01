package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Pharmacy {
    private ArrayList<Medicine> medicines;
    public Pharmacy()  {
        medicines = new ArrayList<>();
        try{
            String sql = "SELECT * FROM medicine;";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                Medicine md = new Medicine(rs.getString("name"),rs.getString("cost"));
                medicines.add(md);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("PramcayE");
        }

    }
}
