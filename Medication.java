
public class Medication {

    private String medicationName;
    private int stockLevel;
    private int lowStockAlertLevel;

    public Medication(String medicationName, int stockLevel, int lowStockAlertLevel) {
        this.medicationName = medicationName;
        this.stockLevel = stockLevel;
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

    // Method to update the stock level
    public void updateStockLevel(int quantity) {
        this.stockLevel += quantity;
        System.out.println("Stock level updated. New stock level for " + medicationName + ": " + stockLevel);
    }

    // Method to check if the stock level is low
    public boolean checkLowStock() {
        return stockLevel <= lowStockAlertLevel;
    }

    // Getters and setters
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getLowStockAlertLevel() {
        return lowStockAlertLevel;
    }

    public void setLowStockAlertLevel(int lowStockAlertLevel) {
        this.lowStockAlertLevel = lowStockAlertLevel;
    }
}
