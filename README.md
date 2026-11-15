# Turtle Graphics

A turtle walks around a 20-by-20 floor (a `char` array). Wherever it walks with
its pen down, it puts an asterisk on the floor. Displaying the floor shows the
asterisks and a blank everywhere else.

The code only uses what Deitel's *Java How to Program* covers in chapters 1–7:
classes and objects, `if`/`else`, `for` loops, methods and arrays.

## Classes

- `PEN_POSITION` is `UP` or `DOWN`.
- `DIRECTION` is `NORTH`, `EAST`, `WEST` or `SOUTH`.
- `Pen` holds the pen position: `raiseUp()`, `putDown()` and `penPosition()`.
  A new pen is up.
- `Location` holds a `row` and a `col`, with getters and setters.
- `Room` holds the 20-by-20 `floor`: `getFloor()`, `setFloor()` and
  `displayFloor()`.
- `Turtle` has a `Pen`, a `Location` and a `DIRECTION`. A new turtle is at row 0,
  column 0, facing north, with its pen up.
  - `changePenPosition(1)` raises the pen and `changePenPosition(2)` puts it down.
  - `turnLeft()` and `turnRight()` turn a quarter turn from the way it is facing;
    `turnUp()` faces north and `turnDown()` faces south.
  - `move(numberOfSteps, room)` walks forward, marking the floor while the pen is
    down, stops at the wall, and returns the floor.
- `Main` is empty for now.

## Turtle methods for each command

| Command | Method |
|---|---|
| `1` pen up | `turtle.changePenPosition(1)` |
| `2` pen down | `turtle.changePenPosition(2)` |
| `3` turn right | `turtle.turnRight()` |
| `4` turn left | `turtle.turnLeft()` |
| `5,n` move forward n steps | `turtle.move(n, room)` |
| `6` display the floor | `room.displayFloor()` |

## Tests

`TurtleTest`, `PenTest`, `LocationTest` and `RoomTest` test each class.
`TurtleTest` also has the Deitel exercise: starting near the centre of the floor,
the program `2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9` draws a 12-by-12 square
and leaves the pen up.

Run them with `mvn test`, or from IntelliJ.
