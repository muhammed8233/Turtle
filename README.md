# Turtle Graphics

A turtle walks around a 20-by-20 floor (an `int` array of 0s and 1s). Wherever it
walks with its pen down, the floor is set to 1. Command 6 displays the floor:
an asterisk for every 1 and a blank for every 0.

The turtle starts near the centre of the floor (row 4, column 4), facing east,
with its pen up, so the 12-by-12 square from the exercise fits and is centred.

## Commands

| Command | Meaning |
|---|---|
| `1` | pen up |
| `2` | pen down |
| `3` | turn right |
| `4` | turn left |
| `5,n` | move forward n steps |
| `6` | display the floor |
| `9` | end of program |
| `7` | clear the floor *(extra)* |
| `8,r,c` | jump to row r, column c without drawing *(extra)* |
| `10,n` | move backward n steps without turning *(extra)* |
| `12,n` … `13` | repeat the commands in between n times; repeats can be nested *(extra)* |
| `14` | show where the turtle is and which way it faces *(extra)* |

Blank lines and anything after `#` are ignored, so programs can have comments.
The turtle stops at the wall instead of walking off the floor.

## Example programs

The `programs` folder has turtle programs that draw:

- `square.txt`: the 12-by-12 square from the exercise
- `square-repeat.txt`: the same square, using repeat
- `nested-squares.txt`: five squares inside each other
- `spiral.txt`: a square spiral
- `staircase.txt`: a staircase
- `grid.txt`: a 3-by-3 grid
- `checkerboard.txt`: a 5-by-5 checkerboard
- `hi.txt`: the word HI

## Classes

- `Pen` is `UP` or `DOWN`, and `Direction` is `NORTH`, `SOUTH`, `EAST` or `WEST`.
- `SketchPad` holds the 20-by-20 `floor`.
- `Turtle` keeps its pen, direction, position (`newRow`, `newColumn`) and its
  sketch pad. It turns (`changeDirectionToRight`, `changeDirectionToLeft`,
  `changeDirection`), changes its pen (`changePenStatus`), moves (`moveTurtle`)
  and displays the floor (`turtleFloor`).
- `Main` reads the commands and tells the turtle what to do.

## Running

Open the project in IntelliJ and run `Turtle.Main`. Pass program files as
program arguments (for example `programs/square.txt`) to run them, or run it
with no arguments to type commands.

Run the tests with `mvn test`, or from IntelliJ. They use JUnit 5; run them from
the project folder so `MainTest` can find the `programs` folder.
