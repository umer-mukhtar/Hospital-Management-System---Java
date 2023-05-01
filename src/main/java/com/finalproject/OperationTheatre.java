package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OperationTheatre {
    private ArrayList<Operation> OTList;

    public OperationTheatre()  {
        OTList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM operation;";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                Operation op = new Operation(rs.getString("patientID"),rs.getString("doctorID"),rs.getDate("date"),rs.getTime("time"));
                OTList.add(op);
            }
            st.close();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("OTE");
        }

    }
}
