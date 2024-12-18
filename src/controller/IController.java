package controller;

import java.io.IOException;

/**
 * This class is the controller of the Photo Album application.
 * Its main function is to interpret events from the view and update instances of
 * PhotoAlbumModel and GUIView as appropriate.
 */
public interface IController {
  void run() throws IOException;
}