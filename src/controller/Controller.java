package controller;

import model.IBusinessModel;
import model.IPhotoAlbumModel;
import model.ISnapshot;
import views.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

public class Controller implements IController, ActionListener{

  private final IPhotoAlbumModel dataModel;
  private final IBusinessModel model;
  private final IView view;


  /**
   * Constructor.
   *
   * @param dataModel    data model instance
   * @param model    business model instance
   * @param view     view instance
   */
  public Controller(IPhotoAlbumModel dataModel, IBusinessModel model, IView view) {
    this.dataModel = dataModel;
    this.model = model;
    this.view = view;
  }

  /**
   * Execute this application by handling command file input and displaying view.
   *
   * @throws IOException when input parameter is invalid
   */
  public void run() throws IOException {
    view.display(dataModel);
  }

  private void updateView() {
//    ISnapshot current = model.getCurrentSnapshot();
//    if (current != null) {
//      view.setInfo("ID: " + current.getId() + ", Description: " + current.getDescription());
//    } else {
//      view.setInfo("No snapshots available.");
//    }
    view.repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    List<String> IDList = model.getSnapshotIDs();

    switch (command) {
      case "previous":
        if (model.getIndex() > 0) {
          model.updateIndex(model.getIndex() - 1);
          updateView();
        } else {
          JOptionPane.showMessageDialog(null,
                  "No snapshots to show prior to this one");
        }
        break;

      case "next":
        if (model.getIndex() < model.getSnapshotIDs().size() - 1) {
          model.updateIndex(model.getIndex() + 1);
          updateView();
        } else {
          JOptionPane.showMessageDialog(null,
                  "End of the photo album. No snapshots to show after this one");
        }
        break;

      case "quit":
        System.exit(0);
        break;

      case "select":
        Object option = JOptionPane.showInputDialog(null,
                "Choose", "Menu", JOptionPane.PLAIN_MESSAGE,
                null, IDList.toArray(), IDList.toArray()[model.getIndex()]);
        if (option != null) {  // Check if the user made a selection
          model.updateIndex(IDList.indexOf(option));
          updateView();
        }
        break;

      default:
        break;
    }
  }

}
