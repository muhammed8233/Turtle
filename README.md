# Turtle Graphics

A turtle walks around a 20-by-20 floor (an `int` array of 0s and 1s). Wherever it
walks with its pen down, the floor is set to 1. Command 6 displays the floor:
an asterisk for every 1 and a blank for every 0.

The turtle starts near the centre of the floor (row 4, column 4), facing east,
with its pen up, so the 12-by-12 square from the exercise fits and is centred.

The code only uses what Deitel's *Java How to Program* covers in chapters 1–7:
classes and objects, `Scanner` input, `if`/`else`, `while` and `for` loops,
`static` methods and arrays.

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
| `14` | show where the turtle is and which way it faces *(extra)* |

The turtle stops at the wall instead of walking off the floor.

## Sample drawings

`SampleDrawings` stores each turtle program as an array of commands, for example the
square from the exercise:

```java
public static final int[] SQUARE = {
        2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9
};
```

The samples are: the 12-by-12 square, nested squares, a spiral, a staircase,
a 3-by-3 grid and the word HI.

## Classes

- `Pen` is `UP` or `DOWN`, and `Direction` is `NORTH`, `SOUTH`, `EAST` or `WEST`.
- `SketchPad` is the floor. It holds the 20-by-20 `floor` array and does the
  floor's jobs: checks whether a cell is on the floor (`isOnFloor`), sets a cell
  to 1 (`markCell`), clears it (`clearFloor`) and builds and prints the picture
  of it (`floorDisplay`, `printFloor`).
- `Turtle` is the turtle. It keeps its pen, direction, position (`newRow`,
  `newColumn`) and its sketch pad. It turns (`changeDirectionToRight`,
  `changeDirectionToLeft`, `changeDirection`), changes its pen (`changePenStatus`)
  and moves (`moveTurtle`, `jumpTurtle`), asking its sketch pad to mark the cells
  it walks over.
- `TurtleCommands` carries out the commands: `runCommand` does one command and
  `runCommands` runs a whole program stored in an array.
- `SampleDrawings` holds the sample programs as arrays and draws them.
- `Main` starts the program: it reads what you type with `Scanner` and passes
  each command to `TurtleCommands`, or shows the sample drawings.

## Running

Run `Turtle.Main` in IntelliJ. Choose 1 to type your own commands or 2 to see
the sample drawings. When you type command 5 or 10, the program asks for the
number of steps; for command 8 it asks for the row and the column.

Run the tests with `mvn test`, or from IntelliJ.
