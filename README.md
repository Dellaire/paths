# paths

`paths` calculates for a given set of lines all existing linear paths, sorts them according to their length and provides a visualization. 

## Execution

The `main` method starting the application resides in the root of the `src` directory.

## Input

The input is expected to be the content of a file in the project directory. The relative path to this file (including its name) has to be specified when running `paths`, so it is injected into the parameter `args[0]` of the `main` method.

The input format is as follows:
```
<x11> <y11> <x12> <y12>
...
```
Every line in the input file represents a geometrical line, every geometrical line consists of a pair of edges (`<x11> <y11>`), who define start and end.

Example:
```
282 580 44 362
970 362 742 580
```
A more complex example is placed in the directory `input` of this repository.

## Output

The console output looks like this:
```
Path with length <legnthOfLongestPath>: <edge1> -> <edge2> -> ...
Path with length <legnthOf2ndLongestPath>: ...
...
```
Additionally, UI window will pop up when the console output is finished, visualizing the results in a 2D coordinate system.

> <b>Hint:</b> A line can be part of multiple paths, a path can consist of one or multiple smaller paths and every line is a (smallest possible) path as well. Given the path `edge1 -> edge2 -> edge3`, the result list will also contain the paths `edge1 -> edge2` and `edge2 -> edge3`.