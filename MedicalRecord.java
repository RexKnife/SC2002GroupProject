import java.util.*;

public class MedicalRecord {
    private String patientID;          // Replaced Appointment ID with Patient ID
    private List<Diagnosis> diagnoses;
    private List<Treatment> treatments;

    // Constructor to initialize with the patient ID
    public MedicalRecord(String patientID) {
        this.patientID = patientID;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        // Initialize the medical record by loading data from records.csv
        List<String> recordHeaders = Arrays.asList("PatientID", "Diagnosis", "Doctor", "Date", "Treatment");
        File recordFile = new File("records.csv", recordHeaders);
        
        // Retrieve medical record data from records.csv and associate it with the patient
        Map<String, Map<String, String>> recordData = recordFile.get();
        if (recordData.containsKey(patientID)) {
            Map<String, String> recordInfo = recordData.get(patientID);
            
         }
    }

    // Add a new diagnosis to the medical record
    public void addDiagnosis(Diagnosis diagnosis) {
        diagnoses.add(diagnosis);
    }

    // Add a new treatment to the medical record
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    // Get the patient's ID (patientID)
    public String getPatientID() {
        return patientID;
    }

    // Get the list of diagnoses for this patient
    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    // Get the list of treatments for this patient
    public List<Treatment> getTreatments() {
        return treatments;
    }
}
