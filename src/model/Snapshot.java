package model;

import java.util.*;

public class Snapshot implements ISnapshot {

  private final String id;  // Unique identifier for the snapshot
  private final String timestamp;  // Timestamp of when the snapshot was taken
  private final String description;  // Description of the snapshot
  private final List<IShape> shapes;  // List of shapes in the snapshot

  /**
   * Constructor to create a new Snapshot.
   *
   * @param shapes List of shapes at the time of the snapshot
   * @param description Description of the snapshot
   */
  public Snapshot(List<IShape> shapes, String description) {
    this.timestamp = java.time.LocalDateTime.now().toString();  // Get the current timestamp
    this.id = generateSnapshotId(timestamp);  // Generate the snapshot ID based on timestamp
    this.shapes = createShapeCopies(shapes);   // Save the current state of shapes
    this.description = description;  // Set the description
  }

  /**
   * Creates a list of shape copies from the list of shapes.
   *
   * @param shapes The shapes at the time of the snapshot
   * @return A list of copied shapes
   */
  private List<IShape> createShapeCopies(List<IShape> shapes) {
    List<IShape> shapeCopies = new ArrayList<>();
    for (IShape shape : shapes) {
      shapeCopies.add(shape.copy());  // Create and add a copy of the shape
    }
    return shapeCopies;
  }

  /**
   * Generates a snapshot ID based on the timestamp and a unique suffix.
   *
   * @param timestamp The timestamp to use in the ID
   * @return A unique snapshot ID in the format "timestamp-UUID"
   */
  private String generateSnapshotId(String timestamp) {
    // Create a unique suffix (e.g., random part) and append to the timestamp
    String uniqueSuffix = UUID.randomUUID().toString().substring(0, 6); // Take the first 6 characters of UUID
    return timestamp + "-" + uniqueSuffix;
  }

  /**
   * Gets the unique ID of the snapshot.
   *
   * @return The unique ID of the snapshot
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the timestamp when the snapshot was taken.
   *
   * @return The timestamp of the snapshot
   */
  public String getTimestamp() {
    return timestamp;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public List<IShape> getShapes() {
    return createShapeCopies(shapes);
  }

  /**
   * Returns a string representation of the snapshot, including the ID, timestamp, and shape information.
   *
   * @return A string representation of the snapshot
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Snapshot ID: ").append(id).append("\n");
    sb.append("Timestamp: ").append(timestamp).append("\n");
    sb.append("Description: ").append(description).append("\n");
    sb.append("Shape Information:\n");

    if (shapes == null || shapes.isEmpty()) {
      sb.append("No shape");
    } else {
      for (IShape shape : shapes) {
        sb.append(shape.toString()).append("\n");  // Assuming each shape has a meaningful toString method
      }
    }

    return sb.toString();
  }
}

