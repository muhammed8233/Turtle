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
}