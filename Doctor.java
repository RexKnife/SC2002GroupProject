import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

public class Doctor extends User {
    // Attributes
    private String doctorID;
    private String name;
    private String contactNumber;
    private String emailAddress;
    private Schedule availabilitySchedule; 

    // Constructor
    public Doctor(String doctorID) {
        this.doctorID = doctorID;
        
        CSVFile doctorFile = new CSVFile("doctors.csv");
        
        // Retrieve doctor data from doctors.csv
        Map<String, Map<String, String>> doctorData = doctorFile.get(); 
        if (doctorData.containsKey(doctorID)) {
            Map<String, String> doctorInfo = doctorData.get(doctorID);
            this.name = doctorInfo.get("Name");
            this.contactNumber = doctorInfo.get("ContactNumber");
            this.emailAddress = doctorInfo.get("EmailAddress");
            this.availabilitySchedule = new Schedule("C:\\Users\\sreek\\OneDrive\\Documents\\SC2002\\Project 2\\SC2002GroupProject-main\\doctor_schedule.csv", doctorID);
        } else {
            throw new IOException("doctor with ID " + doctorID + " not found.");
        }
    }

    // Methods
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nDoctor Menu:");
            System.out.println("1. View doctor Medical Records");
            System.out.println("2. Update doctor Medical Records");
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
                    viewdoctorMedicalRecord();
                    break;
                case 2:
                    updatedoctorMedicalRecord();
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
    public void viewdoctorMedicalRecord() {

    }
    public void updatedoctorMedicalRecord() {
        
    }
    public void viewSchedule() {
        availabilitySchedule.getAvailability();
    }
    public void setAvailability() { //can update schedule
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to add or remove availability?");
        String choice = scanner.nextLine();
        do{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
            if(choice == "add" || choice == "Add"){
                System.out.println("What time would you like to add? Please input in yyyy-MM-dd-HH-mm format: ");
                String timeadd = scanner.nextLine();
                LocalDateTime dateTime1 = LocalDateTime.parse(timeadd, formatter);
                availabilitySchedule.removeAvailability(dateTime1);
                System.out.println(timeadd + " added!");
            } else if (choice == "remove" || choice == "Remove"){
                System.out.println("What time would you like to remove? Please input in yyyy-MM-dd-HH-mm format: ");
                String timeremove = scanner.nextLine();
                LocalDateTime dateTime2 = LocalDateTime.parse(timeremove, formatter);
                availabilitySchedule.addAvailability(dateTime2);
                System.out.println(timeremove + " removed!");
            } else {
                System.out.println("Invalid choice");
            }
        } while (choice != "add" || choice == "Add" || choice == "remove" || choice == "Remove");


    }
    public void acceptDeclineAppointment(Appointment appointment) { //can update schedule

    }
    public void viewUpcomingAppointments() {

    }
    public void recordAppointmentOutcome() {
        
    }
}
