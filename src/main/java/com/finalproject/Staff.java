package com.finalproject;
public class Staff {
    private String staffID;
    private String password;

    public Staff(String staffID, String password) {
        this.staffID = staffID;
        this.password = password;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getPassword() {
        return password;
    }
}
