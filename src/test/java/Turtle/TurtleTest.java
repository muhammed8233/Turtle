package Turtle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTest {

    @Test
    void moveTurtle() {
        Turtle turtle = new Turtle();
        SketchPad value = turtle.moveTurtle();
        assertEquals(3, value.);
    }

    @Test
    void changeDirection() {
        Turtle turtle = new Turtle();
        int output = turtle.changeDirection(4);
        assertEquals(4, output);
    }

    @Test
    void changePenStatus() {
        Turtle turtle = new Turtle();
        int result = turtle.changePenStatus(1);
        assertEquals(1, result);

    }
}