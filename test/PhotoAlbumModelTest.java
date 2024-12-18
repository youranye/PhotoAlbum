import org.junit.Before;
import org.junit.Test;
import model.IPhotoAlbumModel;
import model.PhotoAlbumModel;

import java.util.List;

import static org.junit.Assert.*;

public class PhotoAlbumModelTest {

  private IPhotoAlbumModel model;

  @Before
  public void setUp() {
    model = new PhotoAlbumModel();
  }

  /**
   * Tests adding shapes and validates their presence in snapshots.
   */
  @Test
  public void testAddShape() {
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.addShape("oval", "O", 500, 100, 60, 30, 0.0f, 0.0f, 1.0f);

    model.takeSnapshot("Added two shapes");
    String snapshots = model.printAllSnapshots();

    // Validate snapshot contains the two shapes
    assertTrue(snapshots.contains("R"));
    assertTrue(snapshots.contains("O"));
    assertTrue(snapshots.contains("rectangle"));
    assertTrue(snapshots.contains("500")); // x of oval
  }

  /**
   * Tests adding a shape with a duplicate name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddDuplicateShapeName() {
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.addShape("rectangle", "R", 300, 300, 60, 30, 0.0f, 0.0f, 1.0f);
  }

  /**
   * Tests adding a shape with an invalid type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidShapeType() {
    model.addShape("circle", "C", 100, 100, 50, 50, 1.0f, 1.0f, 0.0f);
  }

  /**
   * Tests removing an existing shape.
   */
  @Test
  public void testRemoveShape() {
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.removeShape("R");
    model.takeSnapshot("Removed rectangle");

    String snapshots = model.printAllSnapshots();
    assertFalse(snapshots.contains("200.0")); //x of rectangle
  }

  /**
   * Tests removing a non-existent shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNonExistentShape() {
    model.removeShape("R");
  }

  /**
   * Tests moving a shape to a new location.
   */
  @Test
  public void testMoveShape() {
    model.addShape("oval", "O", 300, 300, 60, 30, 0.0f, 0.0f, 1.0f);
    model.move("O", 500, 400);
    model.takeSnapshot("Moved oval");

    String snapshots = model.printAllSnapshots();
    assertTrue(snapshots.contains("(500,400)"));
  }

  /**
   * Tests changing the color of a shape.
   */
  @Test
  public void testChangeColor() {
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.changeColor("R", 0.0f, 1.0f, 0.0f);
    model.takeSnapshot("Changed color of rectangle");

    String snapshots = model.printAllSnapshots();
    assertTrue(snapshots.contains("(0.0,1.0,0.0)"));
  }

  /**
   * Tests resizing a shape.
   */
  @Test
  public void testApplyValidTransformation() {
    model.addShape("oval", "O", 300, 300, 60, 30, 0.0f, 0.0f, 1.0f);
    model.resize("O", 60, 90);
    model.takeSnapshot("Resized oval");

    String snapshots = model.printAllSnapshots();
    assertTrue(snapshots.contains("X radius: 60"));
  }

  /**
   * Tests taking a snapshot when no shapes are present.
   */
  @Test
  public void testTakeSnapshotWhenEmpty() {
    model.takeSnapshot("Empty canvas");
    String snapshots = model.printAllSnapshots();
    assertTrue(snapshots.contains("Empty canvas"));
  }

  /**
   * Tests retrieving all snapshots when none exist.
   */
  @Test
  public void testGetAllSnapshotsWhenEmpty() {
    String snapshots = model.printAllSnapshots();
    assertTrue(snapshots.equals("No snapshot yet"));
  }

  /**
   * Tests retrieving snapshot IDs when none exist.
   */
  @Test
  public void testGetSnapshotIDsWhenEmpty() {
    List<String> snapshotIDs = model.getSnapshotIDs();
    assertEquals(0, snapshotIDs.size());
  }

  /**
   * Tests resetting the photo album.
   */
  @Test
  public void testResetPhotoAlbum() {
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.takeSnapshot("Initial state");
    model.reset();
    String snapshots = model.printAllSnapshots();
    List<String> snapshotIDs = model.getSnapshotIDs();
    assertEquals(0, snapshotIDs.size());
  }

  /**
   * Tests taking a snapshot when no shapes are present in the photo album.
   * This test ensures that the snapshot functionality works even when no shapes exist,
   * and that the snapshot includes a message indicating no shapes were present.
   */
  @Test
  public void testSnapshotWithoutShapes() {
    // Take a snapshot with no shapes present
    model.takeSnapshot("Snapshot with no shapes");

    // Get all snapshots and verify the snapshot description and the "No shapes present" message
    String allSnapshots = model.printAllSnapshots();
    assertTrue(allSnapshots.contains("Snapshot with no shapes"));
    assertTrue(allSnapshots.contains("No shape"));
  }

  /**
   * Tests the consistency of snapshot information by verifying that the snapshot correctly
   * records changes to the photo album over time. This test checks whether the position of
   * the shape is updated after a transformation (moving the shape) and captured in the snapshots.
   */
  @Test
  public void testSnapshotConsistency() {
    // Add a shape and take an initial snapshot
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.takeSnapshot("Initial Snapshot");

    // Move the shape and take a snapshot after the move
    model.move("R", 300, 300);
    model.takeSnapshot("After Move");

    // Get all snapshots and verify the snapshots contain the expected data
    String allSnapshots = model.printAllSnapshots();
    assertTrue(allSnapshots.contains("Initial Snapshot"));
    assertTrue(allSnapshots.contains("After Move"));
    assertTrue(allSnapshots.contains("(200,200)")); // Initial position
    assertTrue(allSnapshots.contains("(300,300)")); // After move
  }

  @Test
  public void testInitialSnapshot() {
    // Add a shape and take an initial snapshot
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.takeSnapshot("Initial Snapshot");

    // Get all snapshots and verify the snapshot contains the expected data
    String allSnapshots = model.printAllSnapshots();

    // Check that the snapshot contains the expected name
    assertTrue(allSnapshots.contains("Initial Snapshot"));

    // Check the position consistency (initial position)
    assertTrue(allSnapshots.contains("(200,200)")); // Initial position
  }

  @Test
  public void testSnapshotAfterMove() {
    // Add a shape and take an initial snapshot
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.takeSnapshot("Initial Snapshot");

    // Move the shape and take a snapshot after the move
    model.move("R", 300, 300);
    model.takeSnapshot("After Move");

    // Get all snapshots and verify the snapshot contains the expected data
    String allSnapshots = model.printAllSnapshots();

    // Check that the snapshot contains the expected name
    assertTrue(allSnapshots.contains("After Move"));

    // Check position consistency (initial and after move)
    assertTrue(allSnapshots.contains("(200,200)")); // Initial position
    assertTrue(allSnapshots.contains("(300,300)")); // After move
  }

  @Test
  public void testSnapshotAfterWidthAndXRadiusChange() {
    // Add a shape and take an initial snapshot
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.takeSnapshot("Initial Snapshot");

    // Change the width and x-radius and take another snapshot
    model.resize("R", 50, 80);
    model.resize("R", 50, 60);
    model.takeSnapshot("After Width and height Change");

    // Get all snapshots and verify the snapshot contains the expected data
    String allSnapshots = model.printAllSnapshots();

    // Check that the snapshot contains the expected name
    assertTrue(allSnapshots.contains("After Width and height Change"));

    // Check consistency for width and x-radius change
    assertTrue(allSnapshots.contains("Width: 50"));
    assertTrue(allSnapshots.contains("Height: 60"));
  }

  @Test
  public void testSnapshotAfterColorChange() {
    // Add a shape and take an initial snapshot
    model.addShape("rectangle", "R", 200, 200, 50, 100, 1.0f, 0.0f, 0.0f);
    model.takeSnapshot("Initial Snapshot");

    // Change the color and take another snapshot
    model.changeColor("R", 0.0f, 1.0f, 0.0f);
    model.takeSnapshot("After Color Change");

    // Get all snapshots and verify the snapshot contains the expected data
    String allSnapshots = model.printAllSnapshots();

    // Check that the snapshot contains the expected name
    assertTrue(allSnapshots.contains("After Color Change"));

    // Check consistency for color change
    assertTrue(allSnapshots.contains("(0.0,1.0,0.0)")); // After color change
  }

}
