To run the program: 
The command-line arguments will be of the form below (arguments delimited by [] are optional, depending on the command line options);

 

-in "name-of-command-file" -view "type-of-view" [-out "where-output-should-go"] [xmax] [ymax] 
-view and -v are synonymous. Your program should support both command line "switches".

The xmax and ymax are optional integers that specify the bounds of the "view window". If these attributes are not specified, a default value of 1000 is used for both x (width) and y (height). Also note that the "where output should go" is only relevant for the HTML view, so it is optional (and ignored) for the Graphical (Swing) view.

Two examples:

MyProgram -in buildings.txt -out myWeb.html  -v web

MyProgram -in buildings.txt -v graphical 800 800

Characteristics of a valid input are:

Each pair of arguments (-in "input-file", -out "output-file", etc.) may appear in any order (e.g. the -view pair can appear first, followed by -in and so on)

Each pair of arguments are ordered. That is, if the user types -in then the next input must be the name of an input file, and so on.

Providing an input file (the -in pair) and a view (the -view pair) are mandatory. If the output set is not specified and the view needs it, gracefully exit the program with an error message indicating why the program did not run.
