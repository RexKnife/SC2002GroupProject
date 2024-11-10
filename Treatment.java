import java.util.Date;
import java.util.List;

public class Treatment {
    private String treatmentName;
    private String doctorName; // Added doctor name
    private String notes;
    private List<Date> treatmentDates;

    // Constructor
    public Treatment(String treatmentName, String doctorName, String notes, List<Date> treatmentDates) {
        this.treatmentName = treatmentName;
        this.doctorName = doctorName;
        this.notes = notes;
        this.treatmentDates = treatmentDates;
    }

    // Getters and Setters
    public String getTreatmentName() {
        return treatmentName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getNotes() {
        return notes;
    }

    public List<Date> getTreatmentDates() {
        return treatmentDates;
    }

    @Override
    public String toString() {
        return treatmentDates.get(0) + ", " + doctorName + ", " + treatmentName; // Displaying first treatment date for simplicity
    }
}
