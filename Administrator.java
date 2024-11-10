
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrator extends User {
    private String adminID;
    private String name;
    private ContactInfo contactInfo;
    private List<User> staffList = new ArrayList<>();
    private List<Medication> inventory = new ArrayList<>();

    // Constructor
    public Administrator(String userID, String password, String adminID, String name, ContactInfo contactInfo) {
        super(userID, password, UserRole.Administrator);
        this.adminID = adminID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Menu method to display administrator options using a switch case
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Manage Staff");
            System.out.println("2. Manage Inventory");
            System.out.println("3. Approve Replenishment Request");
            System.out.println("4. Initialize System");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageStaff();
                    break;
                case 2:
                    manageInventory();
                    break;
                case 3:
                    approveReplenishmentRequest();
                    break;
                case 4:
                    initializeSystem();
                    break;
                case 5:
                    System.out.println("Exiting menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to manage staff options
    public void manageStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Staff Management:");
        System.out.println("1. Add Staff");
        System.out.println("2. Update Staff");
        System.out.println("3. Remove Staff");
        System.out.println("4. Display Staff List");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addStaff();
                break;
            case 2:
                updateStaff();
                break;
            case 3:
                removeStaff();
                break;
            case 4:
                displayStaffList();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // Method to add a new staff member
    public void addStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding new staff member.");

        System.out.print("Enter userID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Patient, Doctor, Pharmacist, Administrator): ");
        UserRole role = UserRole.valueOf(scanner.nextLine());
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        User newStaff = new User(userID, password, role, name);  // Assuming User class has this constructor
        staffList.add(newStaff);

        System.out.println("Staff member added successfully.");
    }

    // Method to update staff details
    public void updateStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter userID of staff to update: ");
        String userID = scanner.nextLine();

        User staffToUpdate = findStaffByID(userID);
        if (staffToUpdate != null) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            staffToUpdate.setName(newName);

            System.out.println("Staff details updated successfully.");
        } else {
            System.out.println("Staff member not found.");
        }
    }

    // Method to remove a staff member
    public void removeStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter userID of staff to remove: ");
        String userID = scanner.nextLine();

        User staffToRemove = findStaffByID(userID);
        if (staffToRemove != null) {
            staffList.remove(staffToRemove);
            System.out.println("Staff member removed successfully.");
        } else {
            System.out.println("Staff member not found.");
        }
    }

    // Helper method to find a staff by userID
    private User findStaffByID(String userID) {
        for (User staff : staffList) {
            if (staff.getUserID().equals(userID)) {
                return staff;
            }
        }
        return null;
    }

    // Method to display staff list
    public void displayStaffList() {
        System.out.println("Displaying staff list:");
        for (User staff : staffList) {
            System.out.println("Staff ID: " + staff.getUserID() + ", Name: " + staff.getName() + ", Role: " + staff.getRole());
        }
    }

    // Inventory management menu
    public void manageInventory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inventory Management:");
        System.out.println("1. Add Medication");
        System.out.println("2. Update Medication Stock");
        System.out.println("3. Remove Medication");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addMedication();
                break;
            case 2:
                updateMedicationStock();
                break;
            case 3:
                removeMedication();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // Method to add medication to the inventory
    public void addMedication() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding new medication to inventory.");

        System.out.print("Enter medication name: ");
        String medicationName = scanner.nextLine();
        System.out.print("Enter stock level: ");
        int stockLevel = scanner.nextInt();
        System.out.print("Enter low stock alert level: ");
        int lowStockAlertLevel = scanner.nextInt();

        Medication newMedication = new Medication(medicationName, stockLevel, lowStockAlertLevel);
        inventory.add(newMedication);

        System.out.println("Medication added to inventory.");
    }

    // Method to update medication stock level
    public void updateMedicationStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter medication name to update stock: ");
        String medicationName = scanner.nextLine();

        Medication medication = findMedicationByName(medicationName);
        if (medication != null) {
            System.out.print("Enter new stock level: ");
            int newStockLevel = scanner.nextInt();
            medication.updateStockLevel(newStockLevel);
            System.out.println("Medication stock updated.");
        } else {
            System.out.println("Medication not found.");
        }
    }

    // Method to remove medication from the inventory
    public void removeMedication() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter medication name to remove: ");
        String medicationName = scanner.nextLine();

        Medication medication = findMedicationByName(medicationName);
        if (medication != null) {
            inventory.remove(medication);
            System.out.println("Medication removed from inventory.");
        } else {
            System.out.println("Medication not found.");
        }
    }

    // Helper method to find medication by name
    private Medication findMedicationByName(String name) {
        for (Medication medication : inventory) {
            if (medication.getMedicationName().equalsIgnoreCase(name)) {
                return medication;
            }
        }
        return null;
    }

    // Method to approve a replenishment request
    public void approveReplenishmentRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pending Replenishment Requests:");
        
        // Display pending requests
        for (int i = 0; i < replenishmentRequests.size(); i++) {
            ReplenishmentRequest request = replenishmentRequests.get(i);
            System.out.println((i + 1) + ". Request ID: " + request.getRequestID() +
                               ", Medication: " + request.getMedication().getMedicationName() +
                               ", Quantity: " + request.getQuantity() +
                               ", Status: " + request.getStatus());
        }

        System.out.print("Enter the number of the request to approve: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= replenishmentRequests.size()) {
            ReplenishmentRequest request = replenishmentRequests.get(choice - 1);
            request.approveRequest();  // Set status to "Approved"
            request.getMedication().updateStockLevel(request.getQuantity());  // Update medication stock
            System.out.println("Replenishment request approved and stock updated.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Method to initialize system data from files
    public void initializeSystem(File staffFile, File patientFile, File inventoryFile) {
        loadStaffData(staffFile);
        loadInventoryData(inventoryFile);
        // For patients, you can create a similar method as `loadPatientData()` if necessary.
        System.out.println("System initialized with data from files.");
    }

    // Helper method to load staff data from file
    private void loadStaffData(File staffFile) {
        try (Scanner scanner = new Scanner(staffFile)) {
            while (scanner.hasNextLine()) {
                String[] staffData = scanner.nextLine().split(",");
                String userID = staffData[0];
                String password = staffData[1];
                UserRole role = UserRole.valueOf(staffData[2]);
                String name = staffData[3];
                
                User staff = new User(userID, password, role, name);  // Assuming User class has a suitable constructor
                staffList.add(staff);
            }
            System.out.println("Staff data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Staff file not found.");
        }
    }

    // Helper method to load inventory data from file
    private void loadInventoryData(File inventoryFile) {
        try (Scanner scanner = new Scanner(inventoryFile)) {
            while (scanner.hasNextLine()) {
                String[] inventoryData = scanner.nextLine().split(",");
                String medicationName = inventoryData[0];
                int stockLevel = Integer.parseInt(inventoryData[1]);
                int lowStockAlertLevel = Integer.parseInt(inventoryData[2]);
                
                Medication medication = new Medication(medicationName, stockLevel, lowStockAlertLevel);
                inventory.add(medication);
            }
            System.out.println("Inventory data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        }
    }

    // Method to submit a replenishment request
    public void submitReplenishmentRequest(ReplenishmentRequest request) {
        replenishmentRequests.add(request);
        System.out.println("Replenishment request submitted.");
    }
}





