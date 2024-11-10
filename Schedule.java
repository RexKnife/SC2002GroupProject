import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Schedule {
    // Inner class to represent a time interval with an availability indicator
    public static class TimeInterval {
        private LocalDateTime start;
        private LocalDateTime end;
        private boolean isAvailable;

        public TimeInterval(LocalDateTime start, LocalDateTime end, boolean isAvailable) {
            this.start = start;
            this.end = end;
            this.isAvailable = isAvailable;
        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalDateTime getEnd() {
            return end;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
        }

        @Override
        public String toString() {
            return "From " + start + " to " + end + " - Available: " + isAvailable;
        }
    }

    // Attributes
    private String doctorId;
    private List<TimeInterval> intervals;
    private CSVFile csvFile;

    // Constructor that loads intervals for a specific doctor from a CSV file
    public Schedule(String filePath, String doctorId) {
        this.intervals = new ArrayList<>();
        this.csvFile = new CSVFile(filePath);
        this.doctorId = doctorId;
        loadIntervalsFromCSV();
    }

    private void loadIntervalsFromCSV() {
        Map<String, Map<String, String>> data = csvFile.get();
        List<String> headers = csvFile.getHeaders();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        // Load only the schedule for the specified doctor ID
        if (data.containsKey(doctorId)) {
            Map<String, String> slots = data.get(doctorId);

            // Parse each time slot and availability for the current doctor
            for (String slotKey : headers.subList(1, headers.size())) { // Skip Doctor_ID header
                String slotValue = slots.get(slotKey);
                if (slotValue != null) {
                    LocalDateTime start = LocalDateTime.parse(slotKey, formatter);
                    LocalDateTime end = start.plusMinutes(15); // Assuming each slot is 15 minutes
                    boolean isAvailable = slotValue.equals("1");
                    intervals.add(new TimeInterval(start, end, isAvailable));
                }
            }
        } else {
            System.out.println("Doctor ID " + doctorId + " not found in the data.");
        }
    }

    // Method to remove availability for a specific time slot
    public void removeAvailability(LocalDateTime start) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        String slotKey = start.format(formatter);

        // Update in-memory interval
        for (TimeInterval interval : intervals) {
            if (interval.getStart().equals(start)) {
                interval.setAvailable(false);
                break;
            }
        }

        // Update in the CSV file data
        Map<String, Map<String, String>> data = csvFile.get();
        if (data.containsKey(doctorId) && data.get(doctorId).containsKey(slotKey)) {
            data.get(doctorId).put(slotKey, "0"); // Set availability to 0
            csvFile.update(data); // Save updated data to CSV file
        }
    }

    // Method to add availability for a specific time slot
    public void addAvailability(LocalDateTime start) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        String slotKey = start.format(formatter);

        // Update in-memory interval
        for (TimeInterval interval : intervals) {
            if (interval.getStart().equals(start)) {
                interval.setAvailable(true);
                break;
            }
        }

        // Update in the CSV file data
        Map<String, Map<String, String>> data = csvFile.get();
        if (data.containsKey(doctorId) && data.get(doctorId).containsKey(slotKey)) {
            data.get(doctorId).put(slotKey, "1"); // Set availability to 1
            csvFile.update(data); // Save updated data to CSV file
        }
    }

    // Method to print availability for the doctor
    public void getAvailability() {
        for (TimeInterval interval : intervals) {
            System.out.println(interval);
        }
    }

    public static void main(String[] args) {
        // Example CSV file path
        String filePath = "C:\\Users\\sreek\\OneDrive\\Documents\\SC2002\\Project 2\\SC2002GroupProject-main\\doctor_schedule.csv";
        String doctorId = "D001"; // Example doctor ID

        Schedule schedule = new Schedule(filePath, doctorId);

        LocalDateTime timeToRemove = LocalDateTime.of(2024, 11, 11, 8, 45);
        schedule.removeAvailability(timeToRemove);

        LocalDateTime timeToAdd = LocalDateTime.of(2024, 11, 11, 8, 15);
        schedule.addAvailability(timeToAdd);

        schedule.getAvailability();
    }
}
