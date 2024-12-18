# README

`PhotoAlbumMain.java` is the entry point of the program. The program accepts command-line arguments in the following format. Arguments delimited by `[]` are optional, depending on the command-line options:

`-in "name-of-command-file" -view "type-of-view" [-out "where-output-should-go"] [xmax] [ymax]`


## Details of Arguments
- **`-in`**: Specifies the name of the input file (mandatory).
- **`-view` or `-v`**: Specifies the type of view (mandatory). Both `-view` and `-v` are synonymous.
  - Possible values for the type of view:
    - `"web"` (HTML-based view)
    - `"graphical"` (Swing-based view)
- **`-out`**: Specifies the output file. This argument is only relevant for the HTML (`web`) view and is optional. Ignored for the graphical view.
- **`xmax` and `ymax`**: Optional integers specifying the bounds of the "view window." Default values are `1000` for both width (`xmax`) and height (`ymax`) if these arguments are not provided.

## Examples
### Example 1
`MyProgram -in buildings.txt -out myWeb.html -v web`

- Input file: `buildings.txt`
- View type: `web`
- Output file: `myWeb.html`

### Example 2
`MyProgram -in buildings.txt -v graphical 800 800`

- Input file: `buildings.txt`
- View type: `graphical`
- View window size: `800 x 800`

Input file samples can be found in `resources` directory. 

