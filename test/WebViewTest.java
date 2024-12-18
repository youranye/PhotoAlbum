import static org.junit.Assert.assertEquals;

import model.IPhotoAlbumModel;
import model.PhotoAlbumModel;
import org.junit.Before;
import org.junit.Test;
import views.WebView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A JUnit test for WebView class.
 */
public class WebViewTest {
  private IPhotoAlbumModel model;
  private WebView webView;

  /**
   * Create model and WebView for testing.
   *
   * @throws IOException when an I/O error occurs
   */
  @Before
  public void setUp() throws IOException {
    // Initialize the PhotoAlbumModel instance
    model = new PhotoAlbumModel();

    // Populate the model with sample data
    model.addShape("Rectangle", "rect", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f); // RGB (255,0,0)
    model.addShape("Oval", "ellipse", 500, 100, 60, 30, 0.0f, 1.0f, 0.004f);  // RGB (0,255,1)
    model.takeSnapshot("After first selfie");

    model.move("rect", 100, 300);
    model.takeSnapshot("2nd selfie");

    model.changeColor("rect", 0.0f, 0.0f, 1.0f); // RGB (0,0,255)
    model.move("ellipse", 500, 400);
    model.takeSnapshot("");

    model.removeShape("rect");
    model.takeSnapshot("Selfie after removing the rectangle from the picture");

    // Initialize WebView with test HTML file path
    webView = new WebView("test_output.html", 1000, 1200);
  }

  /**
   * Test generateHtmlCode and display method.
   *
   * @throws IOException when an I/O error occurs
   */
  @Test
  public void testGenerateHtmlCodeAndDisplay() throws IOException {
    // Generate HTML using WebView
    webView.display(model);

    // Expected HTML output
    StringBuilder expected = new StringBuilder();
    expected.append("<!DOCTYPE html>\n<html>\n<head>\n<title>CS5004 Shapes Photo Album</title>\n"
            + "<style>\n"
            + ".photo {\n"
            + "  background-color: cyan;\n"
            + "  color: black;\n"
            + "  border: 10px solid black;\n"
            + "  margin: 30px;\n"
            + "  padding: 30px;\n"
            + "  width: 1000px;\n"
            + "  height: 1200px;\n"
            + "}\n"
            + "</style>\n"
            + "</head>\n<body>\n");

    expected.append("<div class=\"photo\">\n<h2>Snapshot ID: ").append(model.getSnapshotIDs().get(0)).append("</h2>\n");
    expected.append("<p>Description: After first selfie</p>\n");
    expected.append("<svg width=\"1000\" height=\"1200\">\n");
    expected.append("<rect id=\"rect\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\" />\n");
    expected.append("<ellipse id=\"ellipse\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n");
    expected.append("</svg>\n</div>\n");

    expected.append("<div class=\"photo\">\n<h2>Snapshot ID: ").append(model.getSnapshotIDs().get(1)).append("</h2>\n");
    expected.append("<p>Description: 2nd selfie</p>\n");
    expected.append("<svg width=\"1000\" height=\"1200\">\n");
    expected.append("<rect id=\"rect\" x=\"100\" y=\"300\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\" />\n");
    expected.append("<ellipse id=\"ellipse\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n");
    expected.append("</svg>\n</div>\n");

    expected.append("<div class=\"photo\">\n<h2>Snapshot ID: ").append(model.getSnapshotIDs().get(2)).append("</h2>\n");
    expected.append("<svg width=\"1000\" height=\"1200\">\n");
    expected.append("<rect id=\"rect\" x=\"100\" y=\"300\" width=\"50\" height=\"100\" fill=\"rgb(0,0,255)\" />\n");
    expected.append("<ellipse id=\"ellipse\" cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n");
    expected.append("</svg>\n</div>\n");

    expected.append("<div class=\"photo\">\n<h2>Snapshot ID: ").append(model.getSnapshotIDs().get(3)).append("</h2>\n");
    expected.append("<p>Description: Selfie after removing the rectangle from the picture</p>\n");
    expected.append("<svg width=\"1000\" height=\"1200\">\n");
    expected.append("<ellipse id=\"ellipse\" cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n");
    expected.append("</svg>\n</div>\n");

    expected.append("</body>\n</html>\n");

    // Read generated HTML content
    String result = Files.readString(Path.of("test_output.html"));

    // Assert that the generated HTML matches the expected output
    assertEquals(expected.toString(), result);
  }
}








