# README

The program accepts command-line arguments in the following format. Arguments delimited by `[]` are optional, depending on the command-line options:
`-in "name-of-command-file" -view "type-of-view" [-out "where-output-should-go"] [xmax] [ymax]`


## Details of Arguments
- **`-in`**: Specifies the name of the input file (mandatory).
- **`-view` or `-v`**: Specifies the type of view (mandatory). Both `-view` and `-v` are synonymous.
  - Possible values for the type of view:
    - `"web"` (HTML-based view)
    - `"graphical"` (Swing-based view)
- **`-out`**: Specifies the output file. This argument is only relevant for the HTML (`web`) view and is optional. Ignored for the graphical view.
- **`xmax` and `ymax`**: Optional integers specifying the bounds of the "view window." Default values are `1000` for both width (`xmax`) and height (`ymax`) if these arguments are not provided.

## Characteristics of a Valid Input
1. Each pair of arguments (e.g., `-in "input-file"`, `-out "output-file"`) can appear in any order. For example, `-view` can come before `-in`, or vice versa.
2. Each pair of arguments must be ordered. For instance:
   - `-in` must be followed by the name of the input file.
   - `-view` (or `-v`) must be followed by the type of view.
   - If `-out` is specified, it must be followed by the name of the output file.
3. Providing an input file (`-in`) and a view (`-view` or `-v`) is mandatory.
4. If the output file (`-out`) is not specified but required for the selected view (e.g., `web`), the program should gracefully exit with an error message explaining the missing argument.

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

### Default Behavior
If `xmax` and `ymax` are not specified, the program uses the default values of `1000` for both.

## Error Handling
- If either the `-in` or `-view` pair is missing, the program should exit with an error message.
- If the `-out` argument is not provided for the `web` view, the program should also exit with an error message explaining the issue.



