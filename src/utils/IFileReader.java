package utils;

import java.io.IOException;
import java.util.List;

public interface IFileReader {
  List<String> readFile() throws IOException;
}
