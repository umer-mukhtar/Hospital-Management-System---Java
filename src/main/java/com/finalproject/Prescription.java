package com.finalproject;
import java.util.ArrayList;

public class Prescription {
    private ArrayList<String> medicines;
    public Prescription(){
        medicines = new ArrayList<>();
    }
    public Prescription(String medicineName) {
        medicines = new ArrayList<>();
        medicines.add(medicineName);
    }

    public void addMedicine(String medicine)
    {
        medicines.add(medicine);
    }
}
