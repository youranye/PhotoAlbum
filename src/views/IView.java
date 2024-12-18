package views;

import controller.Controller;
import model.IPhotoAlbumModel;

import java.awt.event.ActionListener;

public interface IView {
  void registerActionListener(ActionListener listener);

  /**
   * Display snapShots, their ID and descriptions.
   * @param model photo album model instance
   */
  void display(IPhotoAlbumModel model);

  void repaint();

}
