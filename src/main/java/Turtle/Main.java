package Turtle;

import java.util.Scanner;

// Turtle graphics: reads commands and tells the turtle what to do.
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
public class Main {
    // near the centre of the 20-by-20 floor, so the 12-by-12 square fits
    public static final int START_ROW = 4;
    public static final int START_COLUMN = 4;

    // The 12-by-12 square from the exercise
    public static final int[] SQUARE = {
            2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9
    };

    // Five squares, one inside the other
    public static final int[] NESTED_SQUARES = {
            8,0,0, 2, 5,19, 3, 5,19, 3, 5,19, 3, 5,19, 3, 1, 8,2,2, 2, 5,15, 3,
            5,15, 3, 5,15, 3, 5,15, 3, 1, 8,4,4, 2, 5,11, 3, 5,11, 3, 5,11, 3,
            5,11, 3, 1, 8,6,6, 2, 5,7, 3, 5,7, 3, 5,7, 3, 5,7, 3, 1, 8,8,8, 2,
            5,3, 3, 5,3, 3, 5,3, 3, 5,3, 3, 1, 6, 9
    };

    // A square spiral
    public static final int[] SPIRAL = {
            8,0,0, 2, 5,19, 3, 5,19, 3, 5,19, 3, 5,17, 3, 5,17, 3, 5,15, 3, 5,15,
            3, 5,13, 3, 5,13, 3, 5,11, 3, 5,11, 3, 5,9, 3, 5,9, 3, 5,7, 3, 5,7,
            3, 5,5, 3, 5,5, 3, 5,3, 3, 5,3, 3, 5,1, 1, 6, 9
    };

    // A staircase from the bottom-left corner to the top-right
    public static final int[] STAIRCASE = {
            8,19,0, 2, 5,3, 4, 5,3, 3, 5,3, 4, 5,3, 3, 5,3, 4, 5,3, 3, 5,3, 4,
            5,3, 3, 5,3, 4, 5,3, 3, 5,3, 4, 5,3, 3, 5,1, 1, 6, 9
    };

    // A 3-by-3 grid, drawn with the backward command
    public static final int[] GRID = {
            8,0,0, 2, 5,18, 10,18, 1, 3, 5,6, 4, 2, 5,18, 10,18, 1, 3, 5,6, 4, 2,
            5,18, 10,18, 1, 3, 5,6, 4, 2, 5,18, 1, 8,0,0, 3, 2, 5,18, 10,18, 1,
            4, 5,6, 3, 2, 5,18, 10,18, 1, 4, 5,6, 3, 2, 5,18, 10,18, 1, 4, 5,6,
            3, 2, 5,18, 1, 6, 9
    };

    // The word HI
    public static final int[] HI = {
            8,4,2, 3, 2, 5,10, 1, 8,9,2, 4, 2, 5,5, 1, 8,4,7, 3, 2, 5,10, 1,
            8,4,11, 4, 2, 5,6, 1, 8,14,11, 2, 5,6, 1, 8,4,14, 3, 2, 5,10, 1, 6,
            9
    };

    // prints the name of a sample drawing, then draws it with a new turtle
    public static void drawSample(String name, int[] commands) {
        System.out.println(name);
        runCommands(new Turtle(START_ROW, START_COLUMN), commands);
        System.out.println();
    }

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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Turtle graphics");
        System.out.println("  1 = type your own commands");
        System.out.println("  2 = see the sample drawings");
        System.out.print("Choose 1 or 2: ");
        int choice = input.nextInt();

        if (choice == 2) {
            drawSample("Square", SQUARE);
            drawSample("Nested squares", NESTED_SQUARES);
            drawSample("Spiral", SPIRAL);
            drawSample("Staircase", STAIRCASE);
            drawSample("Grid", GRID);
            drawSample("HI", HI);
        } else {
            Turtle turtle = new Turtle(START_ROW, START_COLUMN);
            System.out.println("The turtle starts at row " + START_ROW + ", column " + START_COLUMN
                    + ", facing east, pen up.");
            System.out.println("  1 = pen up, 2 = pen down, 3 = turn right, 4 = turn left,");
            System.out.println("  5 = move forward, 6 = display, 9 = end,");
            System.out.println("  7 = clear, 8 = jump, 10 = move backward, 14 = where am I");

            int command = 0;
            while (command != 9) {
                System.out.print("Enter a command: ");
                command = input.nextInt();

                int first = 0;
                int second = 0;
                if (command == 5 || command == 10) {
                    System.out.print("Enter the number of steps: ");
                    first = input.nextInt();
                } else if (command == 8) {
                    System.out.print("Enter the row: ");
                    first = input.nextInt();
                    System.out.print("Enter the column: ");
                    second = input.nextInt();
                }

                runCommand(turtle, command, first, second);
            }
        }
    }
}
