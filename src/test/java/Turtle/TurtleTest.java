package Turtle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTest {

//    @Test
////    void moveTurtle() {
////        Turtle turtle = new Turtle();
////        SketchPad value = turtle.moveTurtle(3, 3, 2);
////        assertEquals(3, value.);
////    }

    @Test
    void changeDirection() {
        Turtle turtle = new Turtle();
        int output = turtle.changeDirection(4);
        assertEquals(4, output);
    }

    @Test
    void changePenStatus() {
        Turtle turtle = new Turtle();
        Pen result = turtle.changePenStatus(Pen.UP);
        assertEquals(Pen.UP, result);

    }

    // Deitel exercise: with the turtle near the centre of the floor, the program
    // 2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9
    // draws and displays a 12-by-12 square and leaves the pen up.
    @Test
    void drawTwelveByTwelveSquare() {
        Turtle turtle = new Turtle(4, 4);   // near the centre of the floor

        turtle.changePenStatus(Pen.DOWN);   // 2
        turtle.moveTurtle(12);              // 5,12
        turtle.changeDirection(3);          // 3
        turtle.moveTurtle(12);              // 5,12
        turtle.changeDirection(3);          // 3
        turtle.moveTurtle(12);              // 5,12
        turtle.changeDirection(3);          // 3
        turtle.moveTurtle(12);              // 5,12
        turtle.changePenStatus(Pen.UP);     // 1

        // the elements of floor the turtle walked over are set to 1
        int[][] floor = turtle.getSketchPad().floor;
        for (int i = 4; i <= 16; i++) {
            assertEquals(1, floor[4][i]);    // top side
            assertEquals(1, floor[16][i]);   // bottom side
            assertEquals(1, floor[i][4]);    // left side
            assertEquals(1, floor[i][16]);   // right side
        }

        // everything else is still 0
        int ones = 0;
        for (int row = 0; row < floor.length; row++) {
            for (int column = 0; column < floor[row].length; column++) {
                ones = ones + floor[row][column];
            }
        }
        assertEquals(48, ones);

        // the pen is left in the up position
        assertEquals(Pen.UP, turtle.getPen());

        // 6: the display shows an asterisk for every 1 and a blank for every 0
        String display = turtle.floorDisplay();
        int asterisks = 0;
        for (int i = 0; i < display.length(); i++) {
            if (display.charAt(i) == '*') {
                asterisks++;
            }
        }
        assertEquals(48, asterisks);
        assertTrue(display.contains("|        * * * * * * * * * * * * *       |"));   // top and bottom sides
        assertTrue(display.contains("|        *                       *       |"));   // left and right sides
    }
}
