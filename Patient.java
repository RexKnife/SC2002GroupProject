
/*
Requirements:
    <Patient Class>
    - Patients can update non-medical personal information such as email address and
    contact number.
    - Patients are not allowed to modify the past diagnoses, prescribed medications,
    treatments or blood type.
    - Patients can view their own medical record, which consists of:
        - Patient ID, Name, Date of Birth, Gender
        - Contact Information (e.g., phone number, email address)
        - Blood Type
        - Appointments List<AppointmentID(String)> store of appointment IDs that can then be accessed from Appointment Data
    
    <File Class>
    - All Data is stored in CSV Format
    - There are 7 data stores: User Type(Links ID to User Type and Password), Patient Data, Appointment Data, Doctor Data, Admin Data, Medicine and Pharmacist Data
    - File Class Implements this CSV storage and structures this data when being stored, retrieved and edited
    - file.get(): Returns a Map<map<string>> of the patient data, assuming the first columns are the IDs with the path and headers initialized in the constructor
    - file.update() Update the stored path with the new map to reflect the changes in CSV form

    <MedicalRecord Class>
    - Medical Record is stored in patient data
    - It contains a list of AppointmentIDs (strings)
    - Diagnoses the patient has recieved as a list of <DiagnosisID>
    - Treatments the patient has received as a list of <treatmentID> 

    <Diagnosis Class>
    Class provides details on diagnoses made by doctors along with the doctor ID and date of diagnosis
    - Doctor(String): DoctorID
    - Diagnosis(String): Name of the diagnosis
    - Notes(String): Any notes on diagnosis
    - Date(Date): Date of Diagnosis

    <Treatment Class>
    Class provides details on treatments done by doctors along with the doctor ID and date of treatment
    - Doctor(String): DoctorID
    - Treatment(String): Name of the treatment
    - Notes(Treatment): Any notes on treatment
    - Date(List<Date>): Dates of treatment
*/ 
import java.io.*;
import java.util.*;

public class Patient {
    private String patientID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String bloodType;
    private MedicalRecord medicalRecord;

    // Constructor to load patient data and medical records from CSV
    public Patient(String patientID) throws IOException {
        this.patientID = patientID;
        this.medicalRecord = new MedicalRecord(patientID);
        
        // Instantiate the File class for patient data (patients.csv)
        
        CSVFile patientFile = new CSVFile("patients.csv");
        
        // Retrieve patient data from patients.csv
        Map<String, Map<String, String>> patientData = patientFile.get(); 
        if (patientData.containsKey(patientID)) {
            Map<String, String> patientInfo = patientData.get(patientID);
            this.name = patientInfo.get("Name");
            this.dateOfBirth = patientInfo.get("DateOfBirth");
            this.gender = patientInfo.get("Gender");
            this.contactNumber = patientInfo.get("ContactNumber");
            this.emailAddress = patientInfo.get("EmailAddress");
            this.bloodType = patientInfo.get("BloodType");
        } else {
            throw new IOException("Patient with ID " + patientID + " not found.");
        }
    }

    // Update non-medical information (contact number, email)
    public void updatePersonalInformation(String newContactNumber, String newEmailAddress) {
        this.contactNumber = newContactNumber;
        this.emailAddress = newEmailAddress;
    }

    // View the medical record
    public void viewMedicalRecord() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Blood Type: " + bloodType);
        
        System.out.println("\nAppointments: ");
        for (String appointmentID : medicalRecord.getAppointmentIDs()) {
            System.out.println(appointmentID);
        }

        System.out.println("\nDiagnoses: ");
        for (Diagnosis diagnosis : medicalRecord.getDiagnoses()) {
            System.out.println(diagnosis);
        }

        System.out.println("\nTreatments: ");
        for (Treatment treatment : medicalRecord.getTreatments()) {
            System.out.println(treatment);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            // Display the patient menu
            System.out.println("\nPatient Menu:");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    patient.viewMedicalRecord();
                    break;
                case "2":
                    patient.updatePersonalInfo();
                    break;
                case "3":
                    patient.viewAvailableSlots();
                    break;
                case "4":
                    patient.scheduleAppointment();
                    break;
                case "5":
                    patient.rescheduleAppointment();
                    break;
                case "6":
                    patient.cancelAppointment();
                    break;
                case "7":
                    patient.viewScheduledAppointments();
                    break;
                case "8":
                    patient.viewPastAppointments();
                    break;
                case "9":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
    // Getters
    public String getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getBloodType() {
        return bloodType;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
}
