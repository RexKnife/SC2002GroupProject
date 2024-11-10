import java.io.*;
import java.util.*;

public class CSVFile {
    private String filePath;
    private List<String> headers;
    private Map<String, Map<String, String>> data;

    // Constructor
    public CSVFile(String filePath) {
        this.filePath = filePath;
        this.headers = new ArrayList<>();
        this.data = new HashMap<>();
        loadData();
    }

    // Load data from CSV file
    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                headers = Arrays.asList(line.split(",")); // Load headers
            }
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

    // Get data from the file (returns a Map of the data, assuming first column is the ID)
    public Map<String, Map<String, String>> get() {
        return data;
    }

    // Update the CSV file with new data (Map format)
    public void update(Map<String, Map<String, String>> newData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write headers only once
            bw.write(String.join(",", headers));
            bw.newLine();

            // Write each row of data
            for (Map.Entry<String, Map<String, String>> entry : newData.entrySet()) {
                StringBuilder line = new StringBuilder(entry.getKey());
                Map<String, String> row = entry.getValue();

                for (String header : headers.subList(1, headers.size())) {
                    line.append(",").append(row.getOrDefault(header, ""));
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

    public List<String> getHeaders() {
        return headers;
    }
}
