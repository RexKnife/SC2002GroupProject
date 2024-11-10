import java.io.*;
import java.util.*;

public class File {
    private String filePath;
    private List<String> headers;
    private Map<String, Map<String, String>> data;

    // Constructor
    public File(String filePath, List<String> headers) {
        this.filePath = filePath;
        this.headers = headers;
        this.data = new HashMap<>();
        loadData();
    }

    // Load data from CSV file
    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String key = values[0];
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i < values.length; i++) {
                    row.put(headers.get(i), values[i]);
                }
                data.put(key, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get data from the file (returns a Map of the patient data, assuming first column is the ID)
    public Map<String, Map<String, String>> get() {
        return data;
    }

    // Update the CSV file with new data (Map format)
    public void update(Map<String, Map<String, String>> newData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write the headers
            bw.write(String.join(",", headers));
            bw.newLine();

            // Write the data
            for (Map.Entry<String, Map<String, String>> entry : newData.entrySet()) {
                StringBuilder line = new StringBuilder(entry.getKey());
                for (String header : headers.subList(1, headers.size())) {
                    line.append(",").append(entry.getValue().get(header));
                }
                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter for the file path
    public String getFilePath() {
        return filePath;
    }
}
