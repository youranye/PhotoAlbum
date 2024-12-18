package model;

import java.util.List;

public interface ISnapshot {
  /**
   * Gets the unique ID of the snapshot.
   *
   * @return The unique ID of the snapshot
   */
  String getId();

  /**
   * Gets the list of shapes in the snapshot.
   *
   * @return The list of shapes in the snapshot
   */
  List<IShape> getShapes();

  /**
   * Gets the description of the snapshot.
   *
   * @return The description of the snapshot
   */
  String getDescription();
}
