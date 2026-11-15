package Turtle;

// Carries out turtle commands.
//
//   1        pen up
//   2        pen down
//   3        turn right
//   4        turn left
//   5,n      move forward n steps
//   6        display the floor
//   9        end of program
//
// Extra commands
//   7        clear the floor
//   8,r,c    jump to row r, column c without drawing
//   10,n     move backward n steps
//   14       show where the turtle is
public class TurtleCommands {

    // how many numbers come after a command: 5 and 10 need steps, 8 needs a row and a column
    public static int numbersNeeded(int command) {
        if (command == 5 || command == 10) {
            return 1;
        } else if (command == 8) {
            return 2;
        } else {
            return 0;
        }
    }

    // runs the commands in the array, one after the other, until command 9
    public static void runCommands(Turtle turtle, int[] commands) {
        int index = 0;
        int command = 0;

        while (command != 9 && index < commands.length) {
            command = commands[index];
            int needed = numbersNeeded(command);

            int first = 0;
            int second = 0;
            if (needed >= 1) {
                first = commands[index + 1];
            }
            if (needed == 2) {
                second = commands[index + 2];
            }

            runCommand(turtle, command, first, second);
            index = index + 1 + needed;
        }
    }

    // carries out one command; first and second are the numbers that go with it
    public static void runCommand(Turtle turtle, int command, int first, int second) {
        if (command == 1) {
            turtle.changePenStatus(Pen.UP);
        } else if (command == 2) {
            turtle.changePenStatus(Pen.DOWN);
        } else if (command == 3 || command == 4) {
            turtle.changeDirection(command);
        } else if (command == 5) {
            turtle.moveTurtle(first);
        } else if (command == 6) {
            turtle.turtleFloor();
        } else if (command == 7) {
            turtle.clearFloor();
        } else if (command == 8) {
            boolean jumped = turtle.jumpTurtle(first, second);
            if (!jumped) {
                System.out.println("Row " + first + ", column " + second + " is not on the floor.");
            }
        } else if (command == 10) {
            // turn around, walk forward, then turn back
            turtle.changeDirectionToRight();
            turtle.changeDirectionToRight();
            turtle.moveTurtle(first);
            turtle.changeDirectionToRight();
            turtle.changeDirectionToRight();
        } else if (command == 14) {
            System.out.println("Turtle at row " + turtle.getNewRow() + ", column " + turtle.getNewColumn()
                    + ", facing " + turtle.getDirection() + ", pen " + turtle.getPen());
        } else if (command != 9) {
            System.out.println("Unknown command: " + command);
        }
    }
}
