package com.finalproject;
import java.util.ArrayList;

public class WaitingList
{
    ArrayList<Patient> patients;
    public WaitingList()
    {
        patients = new ArrayList<Patient>();
    }

    public void addPatient(Patient patient)
    {
        patients.add(patient);
    }
}
