# Turtle Graphics

A turtle walks around a 20-by-20 floor (an `int` array of 0s and 1s). Wherever it
walks with its pen down, the floor is set to 1. Displaying the floor shows an
asterisk for every 1 and a blank for every 0.

The code only uses what Deitel's *Java How to Program* covers in chapters 1–7:
classes and objects, `if`/`else`, `while` and `for` loops, methods and arrays.

## Turtle methods for each command

| Command | Method |
|---|---|
| `1` pen up | `turtle.changePenStatus(Pen.UP)` |
| `2` pen down | `turtle.changePenStatus(Pen.DOWN)` |
| `3` turn right | `turtle.changeDirection(3)` or `turtle.changeDirectionToRight()` |
| `4` turn left | `turtle.changeDirection(4)` or `turtle.changeDirectionToLeft()` |
| `5,n` move forward n steps | `turtle.moveTurtle(n)` |
| `6` display the floor | `turtle.turtleFloor()` |
| clear the floor *(extra)* | `turtle.clearFloor()` |
| jump to row r, column c without drawing *(extra)* | `turtle.jumpTurtle(r, c)` |

The turtle stops at the wall instead of walking off the floor.

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
- `Main` is empty for now.

## Tests

`TurtleTest` checks `changeDirection`, `changePenStatus` and the Deitel exercise:
starting near the centre of the floor (row 4, column 4), the program
`2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9` draws a 12-by-12 square and leaves
the pen up.

Run them with `mvn test`, or from IntelliJ.
