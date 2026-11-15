package Turtle;

import java.util.Scanner;

// Starts the turtle graphics program: lets you type commands
// or shows the sample drawings.
public class Main {
    // near the centre of the 20-by-20 floor, so the 12-by-12 square fits
    public static final int START_ROW = 4;
    public static final int START_COLUMN = 4;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Turtle graphics");
        System.out.println("  1 = type your own commands");
        System.out.println("  2 = see the sample drawings");
        System.out.print("Choose 1 or 2: ");
        int choice = input.nextInt();

        if (choice == 2) {
            SampleDrawings.drawAll();
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

                TurtleCommands.runCommand(turtle, command, first, second);
            }
        }
    }
}
