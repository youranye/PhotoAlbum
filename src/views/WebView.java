package views;

import model.IPhotoAlbumModel;
import model.IShape;
import model.ISnapshot;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The WebView. It generates html code according to data model.
 */
public class WebView implements IView {
  private FileWriter out;
  private int xMax;
  private int yMax;

  /**
   * Constructor.
   * @param outputFilePath path name of the output html file
   * @throws IOException when path name is not right
   */
  public WebView(String outputFilePath, int xMax, int yMax) throws IOException {
    if (outputFilePath == null) {
      throw new IllegalArgumentException("The output is not specified");
    }
    this.xMax = xMax;
    this.yMax = yMax;
    this.out = new FileWriter(outputFilePath);
  }

  /**
   * Display web view of the photo album.
   * @param dataModel photo album model instance
   */
  public void display(IPhotoAlbumModel dataModel) {
    generateHtmlCode(dataModel);
    try {
      out.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void registerActionListener(ActionListener listener) {

  }

  @Override
  public void repaint() {

  }

  /**
   * Generate HTML code for the entire photo album.
   *
   * @param dataModel photo album model instance
   */
  private void generateHtmlCode(IPhotoAlbumModel dataModel) {
    try {
      out.write("<!DOCTYPE html>\n<html>\n<head>\n<title>CS5004 Shapes Photo Album</title>\n"
              + "<style>\n"
              + ".photo {\n"
              + "  background-color: cyan;\n"
              + "  color: black;\n"
              + "  border: 10px solid black;\n"
              + "  margin: 30px;\n"
              + "  padding: 30px;\n"
              + "  width: " + xMax + "px;\n"
              + "  height: " + yMax + "px;\n"
              + "}\n"
              + "</style>\n"
              + "</head>\n<body>\n");

      // Loop through all snapshots
      for (ISnapshot snapshot : dataModel.getSnapshots()) {
        out.write("<div class=\"photo\">\n");
        out.write(String.format("<h2>Snapshot ID: %s</h2>\n", snapshot.getId()));
        if (!snapshot.getDescription().isEmpty()) {
          out.write(String.format("<p>Description: %s</p>\n", snapshot.getDescription()));
        }
        out.write("<svg width=\"" + xMax + "\" height=\"" + yMax + "\">\n");

        // Add shapes to SVG
        for (IShape shape : snapshot.getShapes()) {
          out.write(shape.toSvg() + "\n");
        }

        out.write("</svg>\n");
        out.write("</div>\n");
      }

      out.write("</body>\n</html>\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}

