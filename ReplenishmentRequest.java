import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReplenishmentRequest {
    private String requestID;
    private Medication medication;
    private int quantity;
    private String status; // Possible values: "Pending", "Approved"

    // Static list to keep track of all submitted replenishment requests
    private static List<ReplenishmentRequest> requestList = new ArrayList<>();

    // Constructor to initialize a new replenishment request with default status "Pending"
    public ReplenishmentRequest(String requestID, Medication medication, int quantity) {
        this.requestID = requestID;
        this.medication = medication;
        this.quantity = quantity;
        this.status = "Pending"; // Default status when request is created
    }

    // Method to submit a replenishment request and add it to the list
    public void submitRequest() {
        if (!requestList.contains(this)) {
            requestList.add(this);
            System.out.println("Replenishment request submitted for medication: " + medication.getMedicationName() +
                               ", Quantity: " + quantity);
        } else {
            System.out.println("This request is already submitted.");
        }
    }

    // Method to approve a replenishment request
    public void approveRequest() {
        if (status.equals("Pending")) {
            status = "Approved";
            System.out.println("Replenishment request " + requestID + " approved.");
            medication.updateStockLevel(quantity); // Increase medication stock
            requestList.remove(this); // Remove from the list of pending requests
        } else {
            System.out.println("Request " + requestID + " has already been approved or cannot be approved.");
        }
    }

    // Static method to get all pending requests
    public static List<ReplenishmentRequest> getPendingRequests() {
        List<ReplenishmentRequest> pendingRequests = new ArrayList<>();
        for (ReplenishmentRequest request : requestList) {
            if (request.status.equals("Pending")) {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }

    // Getters for attributes
    public String getRequestID() {
        return requestID;
    }

    public Medication getMedication() {
        return medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    // Setters if needed
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}









