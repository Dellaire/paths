# paths

`paths` calculates for a given set of lines all existing linear paths, sorts them according to their length and provides a visualization. 

## Execution

The `main` method starting the application resides in the root of the `src` directory.

## Input

The input is expected to be the path of a file in the project directory. The relative path to this file (including its name) has to be specified when running `paths`, so it is injected into the parameter `args[0]` of the `main` method.

The input format is as follows:
```
<x11> <y11> <x12> <y12>
...
```
Every line in the input file represents a geometrical line, every geometrical line consists of a pair of vertices (`<x11> <y11>`), who define start and end.

Example:
```
282 580 44 362
970 362 742 580
```
A more complex example is placed in the directory `input` of this repository.

## Output

The console output looks like this:
```
Path with length <legnthOfLongestPath>: <vertex1> -> <vertex2> -> ...
Path with length <legnthOf2ndLongestPath>: ...
...
```

Additionally, a UI window will pop up when the console output is finished, visualizing the results in a 2D coordinate system.

> <b>Note:</b> Although, a path is an ordered list of vertices, the reversed list, meaning reversing the order of vertices visited when traveling along the path, is considered equal to it, since it covers the same distance. Therefore, `vertex1 -> vertex2 -> vertex3` is equal to `vertex3 -> vertex2 -> vertex1`.