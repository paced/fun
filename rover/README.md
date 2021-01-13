# Rover

A programming challenge where a rover navigates commands on a virtual grid.

## Specification

I've included a slightly edited version of the specification [here](SPEC.md).

The most important thing is to consider the ranges allowable by the application. Our assumptions/important notes from
the spec:

- The bottom left of the grid is (0, 0).
  - This means that the grid's dimensions are always positive.
- The maximum dimensions on (x, y) is (5, 5).
  - Assumptions must be made:
    - x direction is "right = positive"
    - y direction is "up = positive"
- Placing the rover after it is already placed is not allowed.
  - This is based on real life: rovers can only be dropped and are too heavy (with no propulsion) to simply be
    re-deployed after landing.
- On a `MOVE` command that will not end on a valid space, just ignore the whole command.

### Design

When the application starts and parses input, it must keep track of state. Other than position, it must keep track of
the table state. The commands to move the rover seem to be analogous to state transitions. This lends well to
object-oriented design.

## Build Instructions

This is a typical Gradle project but it must be built and ran with access to the public internet.

### Requirements

- Java JDK 8

### Build

Use the appropriate build script. On Windows, use:

```powershell
gradlew.bat build
```

On Unix-based OSes, use:

```shell
./gradlew build
```

This will also run unit tests. You can also use `gradlew test` if you just want to run the tests by themselves.

### Running

The `rover` application will read the `input.txt` file, one command per line, and output to STDOUT.

You can run using the Gradle scripts, as seen above:

```powershell
gradlew.bat run -x test
```

On Unix-based OSes, use:

```shell
./gradlew run -x test
```
