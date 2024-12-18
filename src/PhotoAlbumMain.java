import controller.Controller;
import controller.IController;
import model.IBusinessModel;
import model.IPhotoAlbumModel;
import model.PhotoAlbumModel;
import model.Snapshot;
import utils.CommandExecutor;
import utils.FileReader;
import utils.IFileReader;
import views.IView;
import views.SwingView;
import views.WebView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoAlbumMain {
  public static void main(String[] args) throws IOException {
    String inputFile = null;
    String viewType = null;
    String outputFile = null;
    int xMax = 1000; // Default width
    int yMax = 1000; // Default height

    // Parse command-line arguments
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          if (i + 1 < args.length) {
            inputFile = args[++i];
          } else {
            System.err.println("Error: Missing input file name after -in.");
            return;
          }
          break;
        case "-view":
        case "-v":
          if (i + 1 < args.length) {
            viewType = args[++i];
          } else {
            System.err.println("Error: Missing view type after -view or -v.");
            return;
          }
          break;
        case "-out":
          if (i + 1 < args.length) {
            outputFile = args[++i];
          } else {
            System.err.println("Error: Missing output file name after -out.");
            return;
          }
          break;
        default:
          try {
            int dimension = Integer.parseInt(args[i]);
            if (xMax == 1000) {
              xMax = dimension;
            } else {
              yMax = dimension;
            }
          } catch (NumberFormatException e) {
            System.err.println("Error: Invalid argument " + args[i]);
            return;
          }
          break;
      }
    }

    // Validate mandatory arguments
    if (inputFile == null || viewType == null) {
      System.err.println("Error: Both -in and -view arguments are required.");
      return;
    }



    // Read commands from the input file
    IFileReader fileReader = new FileReader(inputFile);
    List<String> commands = fileReader.readFile();

    //Initialize the model and Execute the commands
    CommandExecutor commandExecutor = new CommandExecutor(commands);
    IPhotoAlbumModel dataModel = new PhotoAlbumModel();
    commandExecutor.executeCommands(dataModel);
    IBusinessModel model = (IBusinessModel) dataModel;

    // Initialize the view
    switch (viewType.toLowerCase()) {
      case "graphical":
        IView view = new SwingView("CS5004 Photo Album Viewer", xMax, yMax);
        IController controller = new Controller(dataModel, model, view);
        view.registerActionListener((ActionListener) controller);
        controller.run();
        break;
      case "web":
        if (outputFile == null) {
          System.err.println("Error: Output file is required for the web view.");
          System.exit(1);
        }
        IView webView = new WebView(outputFile, xMax, yMax);
        IController webController = new Controller(dataModel, model, webView);
        webController.run();
        System.out.println("HTML output generated at: " + outputFile);
        break;
      default:
        System.err.println("Error: Unknown view type: " + viewType);
    }
  }
}

