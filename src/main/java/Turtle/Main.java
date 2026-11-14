package Turtle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads turtle graphics commands and tells the turtle what to do.
 *
 * <pre>
 *   1        pen up
 *   2        pen down
 *   3        turn right
 *   4        turn left
 *   5,n      move forward n steps
 *   6        display the floor
 *   9        end of program
 *
 *   Extra commands
 *   7        clear the floor
 *   8,r,c    jump to row r, column c without drawing
 *   10,n     move backward n steps
 *   12,n     repeat the commands up to the matching 13, n times
 *   13       end of repeat
 *   14       show where the turtle is
 *   #        everything after it is a comment
 * </pre>
 *
 * Run with no arguments to type commands, or pass program files to run them.
 */
public class Main {
    /** Near the centre of the 20-by-20 floor, so the 12-by-12 square fits and is centred. */
    public static final int START_ROW = 4;
    public static final int START_COLUMN = 4;

    private final Turtle turtle = new Turtle(START_ROW, START_COLUMN);
    private boolean finished;
    private int repeatDepth;
    private int repeatCount;
    private final List<String> repeatCommands = new ArrayList<>();

    public Turtle getTurtle() {
        return turtle;
    }

    public boolean isFinished() {
        return finished;
    }

    public void runProgram(List<String> commands) {
        for (String command : commands) {
            if (!runCommand(command)) {
                return;
            }
        }
        if (repeatDepth > 0) {
            System.out.println("Missing 13: a repeat was never closed.");
            repeatDepth = 0;
            repeatCommands.clear();
        }
    }

    /** Runs one command. Returns false once the program has ended. */
    public boolean runCommand(String command) {
        if (finished) {
            return false;
        }
        int comment = command.indexOf('#');
        if (comment >= 0) {
            command = command.substring(0, comment);
        }
        command = command.trim();
        if (command.isEmpty()) {
            return true;
        }

        int[] values;
        try {
            String[] parts = command.split("\\s*,\\s*", -1);
            values = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                values[i] = Integer.parseInt(parts[i].trim());
            }
        } catch (NumberFormatException e) {
            System.out.println("Unknown command: " + command);
            return true;
        }

        if (repeatDepth > 0) {
            collectRepeat(values[0], command);
            return !finished;
        }

        switch (values[0]) {
            case 1 -> turtle.changePenStatus(Pen.UP);
            case 2 -> turtle.changePenStatus(Pen.DOWN);
            case 3, 4 -> turtle.changeDirection(values[0]);
            case 5 -> move(values, command, false);
            case 6 -> turtle.turtleFloor();
            case 7 -> turtle.getSketchPad().floor = new int[20][20];
            case 8 -> jump(values, command);
            case 9 -> finished = true;
            case 10 -> move(values, command, true);
            case 12 -> startRepeat(values, command);
            case 13 -> System.out.println("13 without a matching 12,n");
            case 14 -> System.out.println("Turtle at row " + turtle.getNewRow()
                    + ", column " + turtle.getNewColumn()
                    + ", facing " + turtle.getDirection()
                    + ", pen " + turtle.getPen());
            default -> System.out.println("Unknown command: " + command);
        }
        return !finished;
    }

    private void move(int[] values, String command, boolean backward) {
        if (values.length != 2) {
            System.out.println("Move needs a number of steps, e.g. " + values[0] + ",10: " + command);
            return;
        }
        int steps = values[1];
        if (steps < 0) {
            System.out.println("Number of steps cannot be negative: " + command);
            return;
        }
        if (backward) {
            turtle.changeDirectionToRight();
            turtle.changeDirectionToRight();
        }
        int startRow = turtle.getNewRow();
        int startColumn = turtle.getNewColumn();
        turtle.moveTurtle(steps);
        int stepsTaken = Math.abs(turtle.getNewRow() - startRow) + Math.abs(turtle.getNewColumn() - startColumn);
        if (backward) {
            turtle.changeDirectionToRight();
            turtle.changeDirectionToRight();
        }
        if (stepsTaken < steps) {
            System.out.println("Hit the wall after " + stepsTaken + " of " + steps + " steps.");
        }
    }

    private void jump(int[] values, String command) {
        if (values.length != 3) {
            System.out.println("Jump needs a row and a column, e.g. 8,10,10: " + command);
        } else if (!turtle.isOnFloor(values[1], values[2])) {
            System.out.println("Cannot jump outside the floor: " + command);
        } else {
            turtle.jumpTurtle(values[1], values[2]);
        }
    }

    private void startRepeat(int[] values, String command) {
        if (values.length != 2 || values[1] < 0) {
            System.out.println("Repeat needs a count of zero or more, e.g. 12,4: " + command);
            return;
        }
        repeatDepth = 1;
        repeatCount = values[1];
        repeatCommands.clear();
    }

    private void collectRepeat(int code, String command) {
        if (code == 12) {
            repeatDepth++;
        } else if (code == 13) {
            repeatDepth--;
        }
        if (repeatDepth > 0) {
            repeatCommands.add(command);
            return;
        }
        List<String> commands = new ArrayList<>(repeatCommands);
        int times = repeatCount;
        repeatCommands.clear();
        for (int i = 0; i < times && !finished; i++) {
            for (String repeated : commands) {
                if (!runCommand(repeated)) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            for (String file : args) {
                System.out.println("=== " + file);
                new Main().runProgram(Files.readAllLines(Path.of(file)));
            }
            return;
        }
        Main program = new Main();
        System.out.println("Turtle graphics. The turtle starts at row 4, column 4, facing east, pen up.");
        System.out.println("  1 = pen up, 2 = pen down, 3 = turn right, 4 = turn left,");
        System.out.println("  5,n = forward n, 6 = display, 9 = end, 7 = clear floor,");
        System.out.println("  8,r,c = jump to row r column c, 10,n = back n,");
        System.out.println("  12,n ... 13 = repeat n times, 14 = show turtle, # = comment");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine() || !program.runCommand(scanner.nextLine())) {
                break;
            }
        }
    }
}
