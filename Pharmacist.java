import java.util.List;
import java.util.Scanner;

public class Pharmacist extends User {

    private String pharmacistID;
    private String name;
    private ContactInfo contactInfo;

    public Pharmacist(String pharmacistID, String name, ContactInfo contactInfo) {
        this.pharmacistID = pharmacistID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Updated menu method with switch functionality
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. Monitor Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Assuming an appointment object is retrieved somehow
                    Appointment appointment = getAppointment(); // Placeholder method
                    viewAppointmentOutcomeRecord(appointment);
                    break;
                case 2:
                    // Assuming prescription and status input from the user
                    Prescription prescription = getPrescription(); // Placeholder method
                    System.out.print("Enter new status for prescription: ");
                    String status = scanner.nextLine();
                    updatePrescriptionStatus(prescription, status);
                    break;
                case 3:
                    monitorInventory();
                    break;
                case 4:
                    // Assuming medication and quantity input from the user
                    Medication medication = getMedication(); // Placeholder method
                    System.out.print("Enter quantity for replenishment request: ");
                    int quantity = scanner.nextInt();
                    submitReplenishmentRequest(medication, quantity);
                    break;
                case 5:
                    System.out.println("Exiting the Pharmacist Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    public void viewAppointmentOutcomeRecord(Appointment appointment) {
        System.out.println("Viewing Appointment Outcome Record for Appointment ID: " + appointment.getAppointmentID());
        System.out.println(appointment.getOutcomeDetails());
    }

    public void updatePrescriptionStatus(Prescription prescription, String status) {
        System.out.println("Updating Prescription Status for Prescription ID: " + prescription.getPrescriptionID());
        prescription.setStatus(status);
        System.out.println("Prescription status updated to: " + status);
    }

    public void monitorInventory() {
        System.out.println("Monitoring Inventory:");
        List<Medication> medications = Inventory.getMedications();
        for (Medication medication : medications) {
            System.out.println("Medication: " + medication.getName() + " | Stock: " + medication.getStockLevel());
        }
    }

    
public void submitReplenishmentRequest(Medication medication, int quantity) {
    if (medication.checkLowStock()) {
        System.out.println("Submitting replenishment request for " + medication.getMedicationName());
        ReplenishmentRequest request = new ReplenishmentRequest(this, medication, quantity);
        request.submit();
        System.out.println("Replenishment request submitted for " + quantity + " units of " + medication.getMedicationName());
    } else {
        System.out.println("Stock level is sufficient; no need for replenishment.");
    }
}


    // Placeholder methods to simulate data fetching
    private Appointment getAppointment() {
        // Logic to retrieve an appointment
        return new Appointment();
    }

    private Prescription getPrescription() {
        // Logic to retrieve a prescription
        return new Prescription();
    }

    private Medication getMedication() {
        // Logic to retrieve a medication
        return new Medication();
    }

    // Getters and setters
    public String getPharmacistID() {
        return pharmacistID;
    }

    public void setPharmacistID(String pharmacistID) {
        this.pharmacistID = pharmacistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
