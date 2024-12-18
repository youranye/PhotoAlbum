package model;

import java.util.*;
import java.util.stream.Collectors;

public class PhotoAlbumModel implements IPhotoAlbumModel, IBusinessModel {
  private LinkedHashMap<String, IShape> shapes;
  private ShapeFactory shapeFactory;
  private List<ISnapshot> snapshots;
  private int currentSnapshotIndex;

  public PhotoAlbumModel() {
    this.shapes = new LinkedHashMap<>();
    this.shapeFactory = new ShapeFactory();
    this.snapshots = new ArrayList<>();
  }

  @Override
  public void addShape(String shape, String name, int x, int y, int xRadius, int yRadius, float r, float g, float b) {
    // Check if the shape already exists by name
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("A shape with the same name already exists: " + name);
    }

    // Create the shape using the factory
    IShape newShape = shapeFactory.createShape(shape, name, x, y, xRadius, yRadius, r, g, b);

    // Add the shape to the album
    shapes.put(name, newShape);
  }

  @Override
  public void removeShape(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape not found: " + name);
    }
    shapes.remove(name);
  }

  @Override
  public void move(String name, int x, int y) {
    IShape shape = findShapeByName(name);  // Find the shape by name
    shape.move(x, y);
  }

  @Override
  public void changeColor(String name, float r, float g, float b) {
    IShape shape = findShapeByName(name);  // Find the shape by name
    shape.changeColor(r, g, b);
  }

  @Override
  public void resize(String name, int x, int y) {
    IShape shape = findShapeByName(name);
    shape.resize(x, y);
  }

  @Override
  public void takeSnapshot(String description) {
    ISnapshot snapshot;
    if (shapes == null || shapes.isEmpty()) {
      snapshot = new Snapshot(new ArrayList<>(), description);
    } else {
      snapshot = new Snapshot(new ArrayList<>(shapes.values()), description);
    }
    snapshots.add(snapshot);
  }

  @Override
  public String getState() {
    if (shapes == null || shapes.isEmpty()) {
      return "No shape";
    } else {
      StringBuilder sb = new StringBuilder();
      for (Map.Entry<String, IShape> shape : shapes.entrySet()) {
        sb.append(shape.toString()).append("\n");
      }
      return sb.toString();
    }
  }

  @Override
  public String printAllSnapshots() {
    if (snapshots.isEmpty()) {
      return "No snapshot yet";
    }

    StringBuilder result = new StringBuilder("Printing Snapshots\n");
    for (ISnapshot snapshot : snapshots) {
      result.append(snapshot.toString()).append("\n");
    }
    return result.toString();
  }

  @Override
  public List<ISnapshot> getSnapshots() {
    return new ArrayList<>(snapshots); // Return a copy to protect encapsulation
  }



  @Override
  public List<String> getSnapshotIDs() {
    return snapshots.stream()
            .map(ISnapshot::getId)
            .collect(Collectors.toList());
  }

  @Override
  public int getIndex() {
    return currentSnapshotIndex;
  }

  @Override
  public void updateIndex(int index) {
    currentSnapshotIndex = index;
  }

  @Override
  public void reset() {
    shapes.clear();
    snapshots.clear();
  }

  /**
   * Finds a shape by its name.
   *
   * @param name the name of the shape
   * @return the shape associated with the given name
   * @throws IllegalArgumentException if no shape with the given name exists
   */
  private IShape findShapeByName(String name) {
    IShape shape = shapes.get(name);  // Retrieve the shape using the name key
    if (shape == null) {
      throw new IllegalArgumentException("Shape not found: " + name);  // Throw exception if not found
    }
    return shape;
  }

  @Override
  public ISnapshot getCurrentSnapshot() {
    if (currentSnapshotIndex >= 0 && currentSnapshotIndex < snapshots.size()) {
      return snapshots.get(currentSnapshotIndex);
    }
    return null;
  }

}

