# Mars Explorer Simulator (Simple Version)

## Description

- The application is a simulation of a mars explorer moving on a square tabletop of dimensions 5 units x 5 units.
- The explorer is free to roam around the surface of the table but must be prevented from falling to destruction. Any
  movement that would result in the explorer falling from the table must be prevented, however further valid movement
  commands must still be allowed.
- The explorer only can move to the sorrounding units vertically or horizontally.

Create an application that can read in commands of the following (textual) form:

```shell
PLACE X,Y
MOVE X,Y
REPORT
```

- `PLACE` will put the mars explorer on the table in position X,Y.
  - The origin (0,0) can be considered to be the bottom left most corner.
  - The first valid command to the explorer is a `PLACE` command, after that, any sequence of commands may be issued,
    in any order, including another `PLACE` command. The application should discard all commands in the sequence until
    a valid `PLACE` command has been executed.

- `REPORT` will announce the X,Y of the explorer, and the positions of blocks.
  - A explorer that is not on the table can choose to ignore the `REPORT` command.

- `MOVE` will find the proper path to move from the original position to the target position X,Y through units along
- the path. Command will print out the positions of path which the explorer moves along.
  - A explorer that is not on the table can choose to ignore the `MOVE` command.
  - A explorer can only move nearest position vertically or horizontally. e.g. A explorer with position (0,0) can only
    move to (0,1), (1,0) positions instead of (1,1).
  - A explore can choose to ignore a position which is not on the table.

- Input can be from a file or from standard input as the developer chooses.
- Provide test data to exercise the application.
- The application must be a command line application.

## Constraints

- The explorer must not fall off the table during movement. This also includes the initial placement of the toy
  explorer.
- Any move that would cause the explorer to fall must be ignored.

## Example Input and Output

### Example 1

```shell
PLACE 0,0
REPORT
```

Expected output:

```shell
P:(0,0)  
```

### Example 2

```shell
PLACE 0,0
MOVE 0,1
```

Expected output:

```shell
M: (0,0) (0,1)
```

### Example 3

```shell
PLACE 0,0
MOVE 1,1
REPORT
```

Expected output:

```shell
M: (0,0) (0,1) (1,1)
P: (1,1)
```

## Deliverables

Please provide your source code, and any test code/data you using in developing your solution.

Please engineer your solution to a standard you consider suitable for production. It is not required to provide any
graphical output showing the movement of the toy explorer.

Please do not put your name in any of the submitted code since this makes it harder for us to review your submission
anonymously.
