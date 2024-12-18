package model;

import java.util.List;

/**
 * It is basically the data model.
 * Controller asks View to display according to the execution result of it.
 * However, user's action (clicking buttons) has no effect on it.
 */
public interface IPhotoAlbumModel {
  /**
   * Adds a new shape to the photo album.
   * @param shape the shape to be added
   * @throws IllegalArgumentException if a shape with the same name already exists
   */
  void addShape(String shape,
                String name,
                int x, int y,
                int xRadius, int yRadius,
                float r, float g, float b);

  /**
   * Removes a shape from the photo album by its name.
   * @param name the name of the shape to remove
   * @throws IllegalArgumentException if no shape with the given name exists
   */
  void removeShape(String name);

  /**
   * Move the shape to (x,y)
   *
   * @param name  the name of the shape
   * @param x  x
   * @param y  y
   */
  void move(String name, int x, int y);

  /**
   * Change the color of the shape.
   *
   * @param name  the name of the shape
   * @param r the red component (0.0 to 1.0)
   * @param g the green component (0.0 to 1.0)
   * @param b the blue component (0.0 to 1.0)
   */
  void changeColor(String name, float r, float g, float b);

  /**
   * Resize a shape.
   *
   * @param name  the name of the shape
   * @param x  x-dimension
   * @param y  y-dimension
   */
  void resize(String name, int x, int y);

  /**
   * Get a textual description of all shapes currently ion the canvas.
   * Add the current snapshot to snapshots.
   * @param description  description of the snapshot
   */
  void takeSnapshot(String description);

  String getState();

  String printAllSnapshots();

  List<ISnapshot> getSnapshots();

  /**
   * Resets the photo album by clearing all shapes and snapshots.
   */
  void reset();

  List<String> getSnapshotIDs();

  int getIndex();

  ISnapshot getCurrentSnapshot();
}

