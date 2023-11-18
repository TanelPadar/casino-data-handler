package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public InputStream getFileAsIOStream(final String fileName) {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public List<String> getFileContent(String fileName) throws IOException {
        InputStream inputStream = getFileAsIOStream(fileName);
        ArrayList<String> fileData = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(isr);) {
            String line;
            while ((line = br.readLine()) != null) {
                fileData.add(line);
            }
            inputStream.close();
        }
        return fileData;
    }

    public void writeListToFile(String filePath, List<String> content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
