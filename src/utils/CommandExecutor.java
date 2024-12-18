package utils;

import model.IPhotoAlbumModel;
import views.IView;

import java.io.IOException;
import java.util.List;

/**
 * This class represents a CommandExecutor. It receives a List of String
 * and execute the commands in it.
 */
public class CommandExecutor {
  private final List<String> commands;

  public CommandExecutor(List<String> commands) {
    this.commands = commands;
  }

  public void executeCommands(IPhotoAlbumModel model) throws IOException {
    for (String command : commands) {
      String[] parts = command.split("\\s+");
      try {
        switch (parts[0].toLowerCase()) {
          case "shape":
            if (parts.length != 10) {
              throw new IllegalArgumentException("Invalid arguments for 'shape' command: " + command);
            }
            model.addShape(parts[2], parts[1],
                    Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
                    Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
                    Float.parseFloat(parts[7]) / 255,
                    Float.parseFloat(parts[8]) / 255,
                    Float.parseFloat(parts[9]) / 255);
            break;
          case "move":
            if (parts.length != 4) {
              throw new IllegalArgumentException("Invalid arguments for 'move' command: " + command);
            }
            model.move(parts[1],
                    Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            break;
          case "color":
            if (parts.length != 5) {
              throw new IllegalArgumentException("Invalid arguments for 'color' command: " + command);
            }
            model.changeColor(parts[1],
                    Float.parseFloat(parts[2]) / 255,
                    Float.parseFloat(parts[3]) / 255,
                    Float.parseFloat(parts[4]) / 255);
            break;
          case "resize":
            if (parts.length != 4) {
              throw new IllegalArgumentException("Invalid arguments for 'resize' command: " + command);
            }
            model.resize(parts[1],
                    Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            break;
          case "remove":
            if (parts.length != 2) {
              throw new IllegalArgumentException("Invalid arguments for 'remove' command: " + command);
            }
            model.removeShape(parts[1]);
            break;
          case "snapshot":
            model.takeSnapshot(command.length() > 8 ? command.substring(command.indexOf(' ', 8)).trim() : "");
            break;
          default:
            throw new IllegalArgumentException("Unknown command: " + parts[0]);
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid number format in command: " + command, e);
      } catch (IndexOutOfBoundsException e) {
        throw new IllegalArgumentException("Insufficient arguments in command: " + command, e);
      }
    }
  }

}
