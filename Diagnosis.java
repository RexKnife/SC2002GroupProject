import java.util.Date;

public class Diagnosis {
    private String diagnosisName;
    private String doctorName; // Added doctor name
    private String notes;
    private Date diagnosisDate;

    // Constructor
    public Diagnosis(String diagnosisName, String doctorName, String notes, Date diagnosisDate) {
        this.diagnosisName = diagnosisName;
        this.doctorName = doctorName;
        this.notes = notes;
        this.diagnosisDate = diagnosisDate;
    }

    // Getters and Setters
    public String getDiagnosisName() {
        return diagnosisName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    @Override
    public String toString() {
        return diagnosisDate + ", " + doctorName + ", " + diagnosisName;
    }
}
