package com.finalproject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

abstract public class HospitalSystem
{
    static private String name;
    static private ArrayList<Admin> admins;
    static private ArrayList<Department> depts;
    static private FrontDesk frontdesk;
    static private Pharmacy pharmacy;
    static private OperationTheatre ot;
    static private PatientManager ptMgr;
    static private ArrayList<WardManager> wardManagers;
    static private ArrayList<Staff> staff;

    public HospitalSystem(String name) {

        admins = new ArrayList<>();
        this.name = name;
        try{
            String sql = "SELECT * FROM admin;";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                Admin ad = new Admin(rs.getString("username"),rs.getString("password"),rs.getString("name"));
                admins.add(ad);
            }
            st.close();
//        admins.forEach(admin->System.out.println(admin.username));

            depts = new ArrayList<>();
            sql = "SELECT * FROM department;";
            st = Main.conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                Department dept = new Department(rs.getString("name"));
                depts.add(dept);
            }
            st.close();

            pharmacy = new Pharmacy();
            ot = new OperationTheatre();
            ptMgr = new PatientManager();

            depts = new ArrayList<>();
            sql = "SELECT * FROM department;";
            st = Main.conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                Department dept = new Department(rs.getString("name"));
                depts.add(dept);
            }
            st.close();

            wardManagers = new ArrayList<>();
            sql = "SELECT * FROM Ward;";
            st = Main.conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                WardManager wm = new WardManager(rs.getString("managerName"),rs.getString("number"),rs.getString("password"));
                wardManagers.add(wm);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("HospitalSysE");
        }


    }

    //Controller Functions ----NOTE: DB NOT APPLIED TO ANY

    //USE CASE----------Register Patient DONE
    static public String addNewPatient(String name, String address)
    {
        Patient newPat = ptMgr.addPatient(name,address);
        String sql = "INSERT INTO patient VALUES ('" + newPat.getPatientID() + "','" + newPat.getName() + "','" + newPat.getAddress() + "');";
        try{
            Statement st = Main.conn.createStatement();
            st.executeUpdate(sql);
        }
        catch (Exception e){
            System.err.print("DB Exception: ");
            System.err.println(e);
            System.err.println("Stack Trace: ");
            e.printStackTrace();
            return "-1";
        }
        return newPat.getPatientID();
    }
    static public Patient assignDepartment(Patient patient, String Dept)
    {
        return ptMgr.assignDept(patient.getPatientID(), Dept);
    }

    //USE CASE----------Edit Schedule DONE
    static public ArrayList<Meeting> getDoctorSchedule(String doctorID)
    {
        for (Department dept: depts)
        {
            ArrayList<Meeting> res = dept.getDoctorSchedule(doctorID);
            if(res != null)
                return res;
        }

        return null; //Doctor with specified ID was not found
    }
    static public Meeting selectSchedule(String doctorID,String meetingID)
    {
        for(Department dept: depts)
        {
            Meeting meeting = dept.selectDoctorMeeting(doctorID, meetingID);
            if(meeting != null)
                return meeting;
        }

        return null; //Indicates some sort of error, either meeting not found or doctor not found
    }
    static public int applyUpdate(String doctorID,String meetID, String operation, String time)
    {
        if(Objects.equals(operation, "Cancel"))
        {
            for(Department dept: depts)
            {
                if(dept.removeDoctorMeeting(doctorID,meetID) == 0){
                    return 0;
                }
            }
            return -1;
        }
        else if (Objects.equals(operation, "Update"))
        {
            try {
                for(Department dept: depts){
                    ArrayList<Doctor> docLst = dept.getDoctors();
                    for(Doctor doc: docLst){
                        ArrayList<Meeting> meets= doc.getMeetings();
                        for(Meeting meet: meets){
                            if(Objects.equals(meet.getMeetingID(), meetID)){
                                meet.setTime(Time.valueOf(time));
                                return 0;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("time exception");
                return -1;
            }
//            return getDoctorSchedule(doctorID);
        }
        return -1;
//        return null; //Any errors here
    }

    //USE CASE----------Prescribe Medicine
    static public ArrayList<Prescription> prescribeMedicine(String patientID, String dept)
    {
        return ptMgr.getPatientPrescription(patientID);
    }
    static public Prescription addMedicine(String patientID, ArrayList<String> Medicines)
    {
        return ptMgr.addMedicines(patientID, Medicines);
    }
    //UPDATE THE DB FOR THIS USE CASE in endPrecription()
    static public void endPrescription()
    {
        //Update the DB here, can also the return Prescription/Patient details obtained from above funcs
    }


    //USE CASE----------Manage Doctors DONE
    static public Doctor enterDoctorID(String DoctorID)
    {
        for(Department dept : depts)
        {
            Doctor doc = dept.searchForDoctor(DoctorID);
            if(doc != null)
                return doc;
        }

        return null;
    }
    static public int applyOperation(String docID, String operation, ArrayList<String> args)
    {
        Doctor doctor = null;
        for(Department dept: depts){
            doctor = dept.getDoctor(docID);
            if(doctor != null){
                break;
            }
        }
        if(doctor == null){
            return -1;
        }
        //First value of arg is the meeting ID
        //Second value of arg is the new time
        if(Objects.equals(operation, "Update Time"))
        {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            try
            {
                Time newTime = new Time(formatter.parse(args.get(1)).getTime());
                doctor.updateMeetingTime(args.get(0), newTime);
                return 0;
            }
            catch (Exception p)
            {
                return -1;
            }
        }
        //First value of arg has doctor id
        //Second value of arg has doctor name
        //Third value of arg has doctor password
        else if(Objects.equals(operation, "Update"))
        {
            return doctor.updateDoctorData(args.get(0), args.get(1), args.get(2));
        }
        //arg is empty
        else if(Objects.equals(operation, "Remove"))
        {
            for(Department dept: depts)
            {
                Doctor removed = dept.removeDoctor(doctor.getDoctorID());
                if(removed != null)
                    return 0;
            }

            return -1;
        }

        return -1;
    }

    //USE CASE----------Assign Doctor DONE
    static public Patient getPatientDetails(String patientID)
    {
        return ptMgr.getPatient(patientID);
    }
    static public ArrayList<Doctor> checkDoctorAvailability(String time, String dept)
    {
        for(Department currDept: depts)
        {
            if(Objects.equals(currDept.getName(), dept))
            {
                return currDept.checkAvailability(time);
            }

        }

        return null;
    }
    //In case we decided to implement waiting list functionality
    static public int addToWaitingList(String patientID, String doctorID, String deptName)
    {
        Patient patient = ptMgr.getPatient(patientID);
        if(patient == null){
            return -1;
        }
        for(Department dept: depts)
        {
            Doctor doc = dept.searchForDoctor(doctorID);
            if(doc != null && Objects.equals(dept.getName(), deptName))
            {
                doc.addPatientToWaitingList(patient);
                return 0;
            }
        }
        return -2;
    }
    //Only used after the doctor availability is checked, pass the array list obtained from that func in this func
    static public Meeting addNewMeting(Patient patient, String time, String doctorID, ArrayList<Doctor> docs)
    {
        for(Doctor doc: docs)
        {
            if(Objects.equals(doc.getDoctorID(), doctorID))
            {
                return doc.addNewMeeting(time, patient.getPatientID());
            }
        }

        return null;
    }
    static public void createMeetings(ArrayList<Doctor> docs){
        for(Doctor doc:docs){
            addNewMeting(ptMgr.getPatients().get((int)(Math.random() * ptMgr.getPatients().size())), String.valueOf(new Time(System.currentTimeMillis())),doc.getDoctorID(),docs);
        }
    }

    //USE CASE----------Register New Staff/Doctors DONE
    //Implement the staff version when we have sorted out the receptionist/staff thing
    static public Staff registerStaff()
    {
        return null;
    }
    //ArrayList has the department, name and password
    static public String registerDoctor(ArrayList<String> doctorData)
    {
        for(Department dept :depts)
        {
            if(Objects.equals(dept.getName(), doctorData.get(0)))
            {
                Doctor newDoc = dept.addDoctor(doctorData.get(1),doctorData.get(2));
                String sql = "INSERT INTO doctor VALUES ('" + newDoc.getDoctorID() + "','OPD','" + newDoc.getName() + "','" + dept.getName() + "','" + newDoc.getPassword() + "');";
                try{
                    Statement st = Main.conn.createStatement();
                    st.executeUpdate(sql);
                    return newDoc.getDoctorID();
                }
                catch (Exception e){
                    System.err.print("DB Exception: ");
                    System.err.println(e);
                    System.err.println("Stack Trace: ");
                    e.printStackTrace();
                    return "-1";
                }
            }
        }
        return "-1";
    }

    //USE CASE----------Discharge Patient DONE
    static public String checkDischargeStatus(String patientID)
    {
        for(WardManager wm: wardManagers)
        {
            if(wm.checkPatientDischarge(patientID) != null)
            {
                return wm.checkPatientDischarge(patientID);
            }
        }

        return "-1"; //Indicates error

    }
    static public float getDischargeAmount(String patientID) //DISPLAY AMOUNT
    {
        for(WardManager wm: wardManagers)
        {
            if(wm.checkPatientDischarge(patientID) != null)
            {
                return wm.getDischargeAmount(patientID);
            }
        }

        return -1; //Indicates some form of error
    }
    static public int enterPaidPayment(String patientID, float Amount)
    {
        //Display the amount after the last function call
        for(WardManager wm: wardManagers)
        {
            if(wm.checkPatientDischarge(patientID) != null)
            {
                return 0;
            }
        }

        return -1;
    }



    public static int login(String typeOfLogin, String userID, String password)  {
        switch(typeOfLogin){
            case "Doctor":
                for(Department dept:depts){
                    if(dept.login(userID,password) == 0){
                        return 1;
                    }
                }
                return -1;
            case "Reception":
                for(Department dept:depts){
//                    System.out.println(dept.getRecp().getReceptionistID());
                    if(Objects.equals(dept.getRecp().getReceptionistID(), userID) && Objects.equals(dept.getRecp().getReceptionistPassword(), password)){
                        return 2;
                    }
                }
                break;
            case "Admin":
                for (Admin ad: admins){
                    if(Objects.equals(ad.getUsername(), userID) && Objects.equals(ad.getPassword(), password)){
                        return 3;
                    }
                }
                return -1;
            case "PA":
                break;
            case "Ward Manager":
                for(WardManager wm:wardManagers){
                    if(Objects.equals(wm.getName(), userID) && Objects.equals(wm.getPassword(), password)){
                        return 5;
                    }
                }
                return -1;
            case "Front Desk":
                if(frontdesk.login(userID,password) == 0){
                    return 6;
                }
                else{
                    return -1;
                }
            case "Staff":
                for(Staff stf:staff){
                    if(Objects.equals(stf.getStaffID(), userID) && Objects.equals(stf.getPassword(), password)){
                        return 7;
                    }
                }
                return -1;
            default:
                return -1;
        }
        return -1;
    }

    public static void initialize()  {
        name = "SHIFA";
        admins = new ArrayList<>();
        try{
            String sql = "SELECT * FROM admin;";
            Statement st = Main.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                Admin ad = new Admin(rs.getString("username"),rs.getString("password"),rs.getString("name"));
                admins.add(ad);
            }
            st.close();
//        admins.forEach(admin->System.out.println(admin.username));

            depts = new ArrayList<>();
            sql = "SELECT * FROM department;";
            st = Main.conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                Department dept = new Department(rs.getString("name"));
                depts.add(dept);
            }
            st.close();

            pharmacy = new Pharmacy();
            ot = new OperationTheatre();
            ptMgr = new PatientManager();

            depts = new ArrayList<>();
            sql = "SELECT * FROM department;";
            st = Main.conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                Department dept = new Department(rs.getString("name"));
                depts.add(dept);
            }
            st.close();

            wardManagers = new ArrayList<>();
            sql = "SELECT * FROM Ward;";
            st = Main.conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                WardManager wm = new WardManager(rs.getString("managerName"),rs.getString("number"),rs.getString("loginPassword"));
                wardManagers.add(wm);
            }
            st.close();

            frontdesk = new FrontDesk();

            for(Department dept: depts){
                createMeetings(dept.getDoctors());
            }

            staff = new ArrayList<>();
            sql = "SELECT * FROM Staff;";
            st = Main.conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                Staff stf = new Staff(rs.getString("id"),rs.getString("password"));
                staff.add(stf);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("InitializeE");
        }


    }

    public static String getReception(String id) {
        for(Department dept:depts){
            if(Objects.equals(dept.getRecp().getReceptionistID(), id)){
                return dept.getName();
            }
        }
        return "";
    }

    public static ArrayList<Doctor> getAvailableDoctors(String deptName) {
        ArrayList<Doctor> docLst = new ArrayList<>();
        for(Department dept:depts){
            if(Objects.equals(dept.getName(), deptName)){
                docLst = (dept.getDoctors());
            }
        }
        return docLst;
    }

    public static Doctor getDoctor(String docID) {
        for(Department dep:depts){
            if(dep.getDoctor(docID) != null){
                return dep.getDoctor(docID);
            }
        }
        return null;
    }

    public static int manageWard(String operation, String arg1, String arg2) {
        return wardManagers.get(0).manageWardPatients(operation,arg1,arg2);
    }

    public static Ward getWardInfo() {
        return wardManagers.get(0).getWardInfo();
    }
}
