import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Read patients from CSV
        List<Patient> patients = CSVUtility.readPatientsFromCSV();

        // Display patient information
        for (Patient patient : patients) {
            patient.displayMenu();
            patient.viewMedicalRecord();
        }

        // Update patient information
        if (!patients.isEmpty()) {
            Patient patient = patients.get(0);
            patient.updatePersonalInformation("0987654321", "john.new@example.com");
        }

        // Write updated patients to CSV
        CSVUtility.writePatientsToCSV(patients);
    }
}

public class FileUtility<T> {

    private String filePath;
    private Function<String, T> fromCSV;
    private Function<T, String> toCSV;

    public FileUtility(String filePath, Function<String, T> fromCSV, Function<T, String> toCSV) {
        this.filePath = filePath;
        this.fromCSV = fromCSV;
        this.toCSV = toCSV;
    }

    public List<T> readFromCSV() {
        List<T> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                items.add(fromCSV.apply(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void writeToCSV(List<T> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (T item : items) {
                bw.write(toCSV.apply(item));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Patient extends User {
    private String name;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String bloodType;
    private List<String> pastDiagnoses;
    private List<String> pastTreatments;

    public Patient(String hospitalID, String password, String name, String dateOfBirth, String gender, String contactNumber, String emailAddress, String bloodType, List<String> pastDiagnoses, List<String> pastTreatments) {
        super(hospitalID, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.bloodType = bloodType;
        this.pastDiagnoses = pastDiagnoses;
        this.pastTreatments = pastTreatments;
    }

    public static Patient fromCSV(String csv) {
        String[] values = csv.split(",");
        return new Patient(
            values[0], // hospitalID
            values[1], // password
            values[2], // name
            values[3], // dateOfBirth
            values[4], // gender
            values[5], // contactNumber
            values[6], // emailAddress
            values[7], // bloodType
            Arrays.asList(values[8].split(";")), // pastDiagnoses
            Arrays.asList(values[9].split(";"))  // pastTreatments
        );
    }

    public String toCSV() {
        return String.join(",",
            getHospitalID(),
            getPassword(),
            name,
            dateOfBirth,
            gender,
            contactNumber,
            emailAddress,
            bloodType,
            String.join(";", pastDiagnoses),
            String.join(";", pastTreatments)
        );
    }

    // Other methods remain unchanged
}



public class CSVUtility {

    private static final String CSV_FILE_PATH = "patients.csv";

    public static List<Patient> readPatientsFromCSV() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Patient patient = new Patient(
                    values[0], // hospitalID
                    values[1], // password
                    values[2], // name
                    values[3], // dateOfBirth
                    values[4], // gender
                    values[5], // contactNumber
                    values[6], // emailAddress
                    values[7], // bloodType
                    Arrays.asList(values[8].split(";")), // pastDiagnoses
                    Arrays.asList(values[9].split(";"))  // pastTreatments
                );
                patients.add(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public static void writePatientsToCSV(List<Patient> patients) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Patient patient : patients) {
                String line = String.join(",",
                    patient.getHospitalID(),
                    patient.getPassword(),
                    patient.getName(),
                    patient.getDateOfBirth(),
                    patient.getGender(),
                    patient.getContactNumber(),
                    patient.getEmailAddress(),
                    patient.getBloodType(),
                    String.join(";", patient.getPastDiagnoses()),
                    String.join(";", patient.getPastTreatments())
                );
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class MedicalRecord {
    private String patientID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String bloodType;
    private List<String> pastDiagnoses;
    private List<String> pastTreatments;

    public MedicalRecord(String patientID, String name, String dateOfBirth, String gender, String contactNumber, String emailAddress, String bloodType, List<String> pastDiagnoses, List<String> pastTreatments) {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.bloodType = bloodType;
        this.pastDiagnoses = pastDiagnoses;
        this.pastTreatments = pastTreatments;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBloodType() {
        return bloodType;
    }

    public List<String> getPastDiagnoses() {
        return pastDiagnoses;
    }

    public void addDiagnosis(String diagnosis) {
        this.pastDiagnoses.add(diagnosis);
    }

    public List<String> getPastTreatments() {
        return pastTreatments;
    }

    public void addTreatment(String treatment) {
        this.pastTreatments.add(treatment);
    }
}
