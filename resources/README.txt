HW7

IPhotoAlbumModel: Interface of the model.

PhotoAlbumModel: Implements the model logic for the photo album. Manages shapes and snapshots and provides methods to manipulate them. It has a map consists of <String, Shape> pairs, a shapeFactory and a list of snapshots. 

IShape: Interface representing a shape with basic operations like move, resize, and change color.
A copy method is added here so that change in the shape won’t affect snapshots. 

AbstractShape: Implements IShape. 

Color: Represents the color. Has 3 fields (r, g, b).

Point2D: Represents the reference point of an IShape.

Rectangle: Subclass of AbstractShape.
Oval: Subclass of AbstractShape.
For the implementation of resize method `public void resize(String property, double value)
`in both class, I use a “switch” so that users can choose the property they want to change (e.g. width or height in Rectangle). 

ShapeFactory: A factory class to create specific shapes (e.g., rectangle, oval) based on user input.

Snapshot: Represents a "selfie" of the photo album's state at a given time. Stores a timestamp, description, and the state of all shapes. It has a list of IShape.


HW8

Since hw8 has to deal with user interactions, I created a new model interface IBusinessModel that only contains functions controller might call according to user’s actions. 

My Controller is the interlinking between the Model and the View. On the graphical interface, my controller acts as the point of contact for executing the Snapshots that are sent to the View. If a user selects a snapshot, returns to the previous snapshot, or goes to the next snapshot- my Model will receive this request and then the View will display it accordingly. My Controller acts as the main point of contact where the command line arguments are processed. When users pass arguments into the CLI, the controller processes these requests, and determines if they are valid, and if so it will send the request to retrieve the data from the Model and then activate the correct view to display.