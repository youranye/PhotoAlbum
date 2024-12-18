package utils;

import java.io.*;
import java.util.*;

/**
 * Read the file and return a list of lists of strings.
 */

public class FileReader implements IFileReader {
  private final String filePath;

  public FileReader(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public List<String> readFile() throws IOException {
    List<String> commands = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (!line.isEmpty() && !line.startsWith("#")) { // Skip empty lines and comments
          commands.add(line);
        }
      }
    }
    return commands;
  }
}

