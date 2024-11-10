import java.util.List;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

public class Doctor extends User {
    // Attributes
    private String doctorID;
    private String name;
    private String specialization;
    private ContactInfo contactInfo;
    private Schedule availabilitySchedule;
    private List<Appointment> appointments;
    private List<Patient> patientsUnderCare;

    // Constructor
    public Doctor(String username, String password) {
        //code to create doctor object from excel file using username and password passed in 
    }

    // Methods
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nDoctor Menu:");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome ");
            System.out.println("8. Logout");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewPatientMedicalRecord();
                    break;
                case 2:
                    updatePatientMedicalRecord();
                    break;
                case 3:
                    viewSchedule();
                    break;
                case 4:
                    setAvailability();
                    break;
                case 5:
                    acceptDeclineAppointment();
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    recordAppointmentOutcome();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
        // code to logout and return to main
    }
    public void viewPatientMedicalRecord() {
        for (int i = 0; i < patientsUnderCare.size(); i++){
            System.out.println("Patient " + patientsUnderCare.get(i).patientID);
            (patientsUnderCare.get(i)).viewMedicalRecord();
        }
    }
    public void updatePatientMedicalRecord() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Which patient's medical record would you like to update? Here are the patients under your care:");
        for (int i=0; i < patientsUnderCare.size(); i++){
            System.out.println((i + 1) + ". " + patientsUnderCare.get(i).patientID);
        }
        int patientChoice = scanner.nextInt();

        System.out.println("Here is the chosen patient's medical record:");
        (patientsUnderCare.get(patientChoice)).viewMedicalRecord();

        System.out.println("Please add a new diagnosis:");
        ((patientsUnderCare.get(patientChoice)).medicalRecord).addDiagnosis();
        System.out.println("Please add a new treatment plan:");
        ((patientsUnderCare.get(patientChoice)).medicalRecord).addTreatment();
        
        System.out.println("Patient's medical record updated successfully! Here is the updated medical record:");
        (patientsUnderCare.get(patientChoice)).viewMedicalRecord();
        
    }
    public void viewSchedule() {
        availabilitySchedule.getAvailability();
    }
    public void setAvailability() {
        availabilitySchedule.setAvailability();
    }
    public void acceptDeclineAppointment(Appointment appointment) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < patientsUnderCare.size(); i++){
            for (int j = 0; i < ((patientsUnderCare.get(i)).appointments.get(j)).size(); j++){
                if (((patientsUnderCare.get(i)).appointments.get(j)).doctor == doctorID){
                    if (((patientsUnderCare.get(i)).appointments.get(j)).status == "pending"){
                        System.out.println("Here are pending appointments from your patients, choose one to accept or decline: ");
                        System.out.println((1+j) +". " + ((patientsUnderCare.get(i)).appointments.get(j)).appointmentID + "(" + ((patientsUnderCare.get(i)).appointments.get(j)).patient.patientID + " on ... at " + ((patientsUnderCare.get(i)).appointments.get(j)).timeSlot.startTime + " to " + ((patientsUnderCare.get(i)).appointments.get(j)).timeSlot.endTime); //add date
                    }
                    int acceptDeclineChoice = scanner.nextInt();
                    String choiceAD;
                    do{
                        System.out.println("Would you like to accept or decline this appointment?");
                        choiceAD = scanner.nextLine();
                        if (choiceAD == "accept"){
                            ((patientsUnderCare.get(i)).appointments.get(acceptDeclineChoice)).status = "confirmed";
                            availabilitySchedule.removeAvailablity(((patientsUnderCare.get(i)).appointments.get(acceptDeclineChoice)).timeSlot);
                        } else if(choiceAD == "decline"){
                            ((patientsUnderCare.get(i)).appointments.get(acceptDeclineChoice)).status = "declined";
                        }
                    } while (choiceAD != "accept" || choiceAD != "decline");
                    
                }   
            }
        }
    }
    public void viewUpcomingAppointments() {
        for (int i = 0; i < patientsUnderCare.size(); i++){
            for (int j = 0; i < ((patientsUnderCare.get(i)).appointments.get(j)).size(); j++){
                if (((patientsUnderCare.get(i)).appointments.get(j)).doctor == doctorID){
                    if (((patientsUnderCare.get(i)).appointments.get(j)).status == "confirmed"){
                        System.out.println("Here is the list of confirmed appointments from your patients");
                        System.out.println((1+j) +". " + ((patientsUnderCare.get(i)).appointments.get(j)).appointmentID + "(" + ((patientsUnderCare.get(i)).appointments.get(j)).patient.patientID + " on ... at " + ((patientsUnderCare.get(i)).appointments.get(j)).timeSlot.startTime + " to " + ((patientsUnderCare.get(i)).appointments.get(j)).timeSlot.endTime); //add date
                    }
                }
            }
        }
    }
    public void recordAppointmentOutcome() {
        //have to update timeslot class to hold actual date, time objects, so we can determine whichconfirmed appointments have completed already
    }
    public void addPatient (Patient patient){//use method in patient class for when a patient books/rescehdules an appointment with this doctor
        patientsUnderCare.add(patient); 
    }
    public void removePatient (Patient patient){ //use method in patient class for when a patient cancels an appointment with this doctor
        for (int i=0; i < patientsUnderCare.size(); i++){
            if ((patientsUnderCare.get(i)).patientID == patient.patientID){
                patientsUnderCare.remove(i);
            }
        }
    }
    // Getters and setters can be added as needed
}
