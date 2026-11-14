package Turtle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTest {
    private Turtle turtle;

    @BeforeEach
    void setUp() {
        turtle = new Turtle();
    }

    private int countOnes(int[][] floor) {
        int count = 0;
        for (int[] row : floor) {
            for (int column : row) {
                count = count + column;
            }
        }
        return count;
    }

    private int countAsterisks(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '*') {
                count++;
            }
        }
        return count;
    }

    @Test
    void newTurtleIsTopLeftFacingEastWithPenUp() {
        assertEquals(0, turtle.getNewRow());
        assertEquals(0, turtle.getNewColumn());
        assertEquals(Direction.EAST, turtle.getDirection());
        assertEquals(Pen.UP, turtle.getPen());
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void turtleCanStartAnywhereOnTheFloor() {
        Turtle centred = new Turtle(4, 4);
        assertEquals(4, centred.getNewRow());
        assertEquals(4, centred.getNewColumn());
    }

    @Test
    void turtleStartingOffTheFloorStaysInTheCorner() {
        Turtle outside = new Turtle(20, 3);
        assertEquals(0, outside.getNewRow());
        assertEquals(0, outside.getNewColumn());
    }

    @Test
    void changeDirection() {
        Turtle turtle = new Turtle();
        int output = turtle.changeDirection(4);
        assertEquals(4, output);
        assertEquals(Direction.NORTH, turtle.getDirection());

        assertEquals(3, turtle.changeDirection(3));
        assertEquals(Direction.EAST, turtle.getDirection());
    }

    @Test
    void changeDirectionIgnoresOtherNumbers() {
        turtle.changeDirection(3);
        assertEquals(3, turtle.changeDirection(7));
        assertEquals(Direction.SOUTH, turtle.getDirection());
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
        assertEquals(0, turtle.getNewRow());
        assertEquals(3, turtle.getNewColumn());
    }

    @Test
    void moveTurtleInEveryDirection() {
        turtle = new Turtle(10, 10);

        turtle.moveTurtle(2);                 // east
        assertEquals(10, turtle.getNewRow());
        assertEquals(12, turtle.getNewColumn());

        turtle.changeDirectionToRight();      // south
        turtle.moveTurtle(3);
        assertEquals(13, turtle.getNewRow());
        assertEquals(12, turtle.getNewColumn());

        turtle.changeDirectionToRight();      // west
        turtle.moveTurtle(4);
        assertEquals(13, turtle.getNewRow());
        assertEquals(8, turtle.getNewColumn());

        turtle.changeDirectionToRight();      // north
        turtle.moveTurtle(5);
        assertEquals(8, turtle.getNewRow());
        assertEquals(8, turtle.getNewColumn());
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
            assertEquals(1, floor[0][column]);
        }
        assertEquals(0, floor[0][6]);
        assertEquals(6, countOnes(floor));
    }

    @Test
    void moveTurtleStopsAtTheWall() {
        turtle.changePenStatus(Pen.DOWN);
        turtle.moveTurtle(50);
        assertEquals(0, turtle.getNewRow());
        assertEquals(19, turtle.getNewColumn());
        assertEquals(20, countOnes(turtle.getSketchPad().floor));

        turtle.changeDirectionToLeft();       // north, facing the wall
        turtle.moveTurtle(5);
        assertEquals(0, turtle.getNewRow());
    }

    @Test
    void moveTurtleWithNegativeStepsStaysPut() {
        turtle.moveTurtle(-3);
        assertEquals(0, turtle.getNewColumn());
    }

    @Test
    void jumpTurtleMovesWithoutDrawing() {
        turtle.changePenStatus(Pen.DOWN);
        assertTrue(turtle.jumpTurtle(12, 7));
        assertEquals(12, turtle.getNewRow());
        assertEquals(7, turtle.getNewColumn());
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void jumpTurtleOffTheFloorIsRefused() {
        assertFalse(turtle.jumpTurtle(-1, 5));
        assertFalse(turtle.jumpTurtle(5, 20));
        assertEquals(0, turtle.getNewRow());
        assertEquals(0, turtle.getNewColumn());
    }

    @Test
    void clearFloorSetsEveryCellBackToZero() {
        turtle.changePenStatus(Pen.DOWN);
        turtle.moveTurtle(8);
        turtle.clearFloor();
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void floorDisplayShowsAsterisksForOnesAndBlanksForZeros() {
        turtle.getSketchPad().floor = new int[3][3];
        turtle.getSketchPad().floor[0][0] = 1;
        turtle.getSketchPad().floor[1][1] = 1;
        String expected = "+------+\n"
                + "|*     |\n"
                + "|  *   |\n"
                + "|      |\n"
                + "+------+\n";
        assertEquals(expected, turtle.floorDisplay());
    }

    // The Deitel exercise: with the turtle near the centre of the floor, the program
    // 2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9
    // draws and displays a 12-by-12 square and leaves the pen up.
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
            assertEquals(1, floor[4][i]);    // top edge
            assertEquals(1, floor[16][i]);   // bottom edge
            assertEquals(1, floor[i][4]);    // left edge
            assertEquals(1, floor[i][16]);   // right edge
        }
        for (int row = 5; row <= 15; row++) {
            for (int column = 5; column <= 15; column++) {
                assertEquals(0, floor[row][column]);   // inside stays empty
            }
        }
        assertEquals(48, countOnes(floor));
        assertEquals(Pen.UP, turtle.getPen());
        assertEquals(4, turtle.getNewRow());
        assertEquals(4, turtle.getNewColumn());

        // 6: one asterisk on the display for every 1 on the floor
        assertEquals(48, countAsterisks(turtle.floorDisplay()));
    }
}
