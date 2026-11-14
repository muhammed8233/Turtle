package Turtle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTest {
    private Turtle turtle;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        turtle = new Turtle();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private int countOnes(int[][] floor) {
        int count = 0;
        for (int[] row : floor) {
            for (int column : row) {
                count += column;
            }
        }
        return count;
    }

    private void assertPosition(int row, int column) {
        assertEquals(row, turtle.getNewRow(), "row");
        assertEquals(column, turtle.getNewColumn(), "column");
    }

    @Test
    void newTurtleIsTopLeftFacingEastWithPenUp() {
        assertPosition(0, 0);
        assertEquals(Direction.EAST, turtle.getDirection());
        assertEquals(Pen.UP, turtle.getPen());
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void turtleCanStartAnywhereOnTheFloor() {
        turtle = new Turtle(4, 4);
        assertPosition(4, 4);
        assertThrows(IllegalArgumentException.class, () -> new Turtle(20, 0));
    }

    @Test
    void changeDirection() {
        Turtle turtle = new Turtle();
        int output = turtle.changeDirection(4);
        assertEquals(4, output);
        assertEquals(Direction.NORTH, turtle.getDirection());
        assertEquals(3, turtle.changeDirection(3));
        assertEquals(Direction.EAST, turtle.getDirection());
        assertThrows(IllegalArgumentException.class, () -> turtle.changeDirection(5));
    }

    @Test
    void changeDirectionToRight() {
        assertEquals(Direction.SOUTH, turtle.changeDirectionToRight());
        assertEquals(Direction.WEST, turtle.changeDirectionToRight());
        assertEquals(Direction.NORTH, turtle.changeDirectionToRight());
        assertEquals(Direction.EAST, turtle.changeDirectionToRight());
    }

    @Test
    void changeDirectionToLeft() {
        assertEquals(Direction.NORTH, turtle.changeDirectionToLeft());
        assertEquals(Direction.WEST, turtle.changeDirectionToLeft());
        assertEquals(Direction.SOUTH, turtle.changeDirectionToLeft());
        assertEquals(Direction.EAST, turtle.changeDirectionToLeft());
    }

    @Test
    void changePenStatus() {
        Turtle turtle = new Turtle();
        Pen result = turtle.changePenStatus(Pen.UP);
        assertEquals(Pen.UP, result);
        assertEquals(Pen.DOWN, turtle.changePenStatus(Pen.DOWN));
        assertEquals(Pen.DOWN, turtle.getPen());
    }

    @Test
    void moveTurtle() {
        SketchPad value = turtle.moveTurtle(3);
        assertSame(turtle.getSketchPad(), value);
        assertPosition(0, 3);
    }

    @Test
    void moveTurtleInEveryDirection() {
        turtle = new Turtle(10, 10);
        turtle.moveTurtle(2);
        assertPosition(10, 12);
        turtle.changeDirectionToRight();
        turtle.moveTurtle(3);
        assertPosition(13, 12);
        turtle.changeDirectionToRight();
        turtle.moveTurtle(4);
        assertPosition(13, 8);
        turtle.changeDirectionToRight();
        turtle.moveTurtle(5);
        assertPosition(8, 8);
    }

    @Test
    void moveTurtleDoesNotDrawWithPenUp() {
        turtle.moveTurtle(10);
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void moveTurtleDrawsOnesWithPenDown() {
        turtle.changePenStatus(Pen.DOWN);
        int[][] floor = turtle.moveTurtle(5).floor;
        for (int column = 0; column <= 5; column++) {
            assertEquals(1, floor[0][column], "column " + column);
        }
        assertEquals(0, floor[0][6]);
        assertEquals(6, countOnes(floor));
    }

    @Test
    void moveTurtleStopsAtTheWall() {
        turtle.changePenStatus(Pen.DOWN);
        turtle.moveTurtle(50);
        assertPosition(0, 19);
        assertEquals(20, countOnes(turtle.getSketchPad().floor));

        turtle.changeDirectionToLeft();
        turtle.moveTurtle(5);
        assertPosition(0, 19);
    }

    @Test
    void moveTurtleRejectsNegativeSteps() {
        assertThrows(IllegalArgumentException.class, () -> turtle.moveTurtle(-1));
    }

    @Test
    void jumpTurtleMovesWithoutDrawing() {
        turtle.changePenStatus(Pen.DOWN);
        turtle.jumpTurtle(12, 7);
        assertPosition(12, 7);
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
        assertThrows(IllegalArgumentException.class, () -> turtle.jumpTurtle(-1, 0));
    }

    @Test
    void turtleFloorShowsAsterisksForOnesAndBlanksForZeros() {
        turtle.getSketchPad().floor = new int[3][3];
        turtle.getSketchPad().floor[0][0] = 1;
        turtle.getSketchPad().floor[1][1] = 1;
        turtle.turtleFloor();
        String nl = System.lineSeparator();
        String expected = "+------+" + nl
                + "|*     |" + nl
                + "|  *   |" + nl
                + "|      |" + nl
                + "+------+" + nl;
        assertEquals(expected, output.toString());
    }

    /**
     * The Deitel exercise: with the turtle near the centre of the floor, the program
     * 2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9
     * draws and displays a 12-by-12 square and leaves the pen up.
     */
    @Test
    void turtleDrawsTheDeitelTwelveByTwelveSquareAndLeavesPenUp() {
        turtle = new Turtle(4, 4);

        turtle.changePenStatus(Pen.DOWN);   // 2
        turtle.moveTurtle(12);              // 5,12
        turtle.changeDirectionToRight();    // 3
        turtle.moveTurtle(12);              // 5,12
        turtle.changeDirectionToRight();    // 3
        turtle.moveTurtle(12);              // 5,12
        turtle.changeDirectionToRight();    // 3
        turtle.moveTurtle(12);              // 5,12
        turtle.changePenStatus(Pen.UP);     // 1

        int[][] floor = turtle.getSketchPad().floor;
        for (int i = 4; i <= 16; i++) {
            assertEquals(1, floor[4][i], "top edge at column " + i);
            assertEquals(1, floor[16][i], "bottom edge at column " + i);
            assertEquals(1, floor[i][4], "left edge at row " + i);
            assertEquals(1, floor[i][16], "right edge at row " + i);
        }
        assertEquals(12 * 4, countOnes(floor));
        for (int row = 5; row <= 15; row++) {
            for (int column = 5; column <= 15; column++) {
                assertEquals(0, floor[row][column], "inside the square at " + row + "," + column);
            }
        }
        assertEquals(Pen.UP, turtle.getPen());
        assertPosition(4, 4);
        assertEquals(Direction.NORTH, turtle.getDirection());

        turtle.turtleFloor();               // 6
        String[] lines = output.toString().split("\\R");
        assertEquals(22, lines.length);
        assertEquals("|" + "  ".repeat(20) + "|", lines[1]);
        assertEquals("|" + "  ".repeat(4) + "* ".repeat(13) + "  ".repeat(3) + "|", lines[5]);
        assertEquals("|" + "  ".repeat(4) + "* " + "  ".repeat(11) + "* " + "  ".repeat(3) + "|", lines[10]);
        assertEquals("|" + "  ".repeat(4) + "* ".repeat(13) + "  ".repeat(3) + "|", lines[17]);
    }
}
