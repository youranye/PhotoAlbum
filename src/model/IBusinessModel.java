package model;

import java.util.List;

/**
 * There are only a few methods that the user need,
 * so I separate them to this interface IBusinessModel.
 */
public interface IBusinessModel {
  /**
   * Retrieves a list of snapshot IDs for all snapshots taken so far.
   *
   * @return a list of snapshot IDs
   */
  List<String> getSnapshotIDs();

  /**
   * Retrieves the current snapshot index.
   *
   * @return the index of the current snapshot
   */
  int getIndex();

  /**
   * Updates the current snapshot index.
   *
   * @param index the new index to set for the current snapshot
   */
  void updateIndex(int index);

  /**
   * Retrieves the current snapshot.
   *
   * @return the current snapshot
   */
  ISnapshot getCurrentSnapshot();

  List<ISnapshot> getSnapshots();
}

