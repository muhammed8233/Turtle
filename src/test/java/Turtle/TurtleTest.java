package Turtle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TurtleTest {
    private Turtle turtle;

    @BeforeEach
    void setUp() {
        turtle = new Turtle();
    }
    @Test
    void testThatTurtleExist(){
        assertNotNull(turtle);
    }
    @Test
    void testThatTurtleHasAPen(){
        Pen pen = new Pen();
        turtle.setPen(pen);
        assertNotNull(turtle.getPen());
    }
    @Test
    void testThatWhenOneIsPassedTurtleRaisesPenUp(){
        Pen pen = new Pen();
        turtle.setPen(pen);
        turtle.changePenPosition(1);
        assertEquals(turtle.getPen().penPosition(), PEN_POSITION.UP);
    }
    @Test
    void testThenWhenPenIsDown(){
        Pen pen = new Pen();
        turtle.setPen(pen);
        turtle.changePenPosition(2);
        assertEquals(turtle.getPen().penPosition(), PEN_POSITION.DOWN);
    }
    @Test
    void testThatTurtleCanTurnLeft(){
        turtle.turnLeft();
        assertEquals(turtle.getDirection(), DIRECTION.WEST);
        turtle.turnRight();
        assertEquals(turtle.getDirection(), DIRECTION.NORTH);
    }
    @Test
    void testThatTurtleCanMove(){
        Location location = new Location();
        turtle.setLocation(location);
        turtle.turnRight();
        turtle.move(10, new Room());
        assertEquals(turtle.getLocation().getCol() - 1, 9);
        assertEquals(turtle.getLocation().getRow(), 0);
    }

    @Test
    void testThatTurtlePrintsShapeOnBoardWhenPenIsDownAndTurtleDirectionIsRight(){
        Room room = new Room();
        Location location = new Location();
        turtle.setLocation(location);
        turtle.turnRight();
        char[][] floor = turtle.move(10, room);
        assertEquals(turtle.getLocation().getCol() - 1, 9);
        assertEquals(turtle.getLocation().getRow(), 0);
        room.setFloor(floor);
        room.displayFloor();

    }

    // Deitel exercise: with the turtle near the centre of the floor, the program
    // 2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9
    // draws and displays a 12-by-12 square and leaves the pen up.
    @Test
    void testThatTurtleDrawsTwelveByTwelveSquare(){
        Room room = new Room();
        Location location = new Location();
        location.setRow(16);
        location.setCol(4);
        turtle.setLocation(location);        // near the centre, facing north

        turtle.changePenPosition(2);         // 2
        turtle.move(12, room);               // 5,12
        turtle.turnRight();                  // 3
        turtle.move(12, room);               // 5,12
        turtle.turnRight();                  // 3
        turtle.move(12, room);               // 5,12
        turtle.turnRight();                  // 3
        char[][] floor = turtle.move(12, room);   // 5,12
        turtle.changePenPosition(1);         // 1
        room.displayFloor();                 // 6

        for (int i = 4; i <= 16; i++) {
            assertEquals('*', floor[4][i]);    // top side
            assertEquals('*', floor[16][i]);   // bottom side
            assertEquals('*', floor[i][4]);    // left side
            assertEquals('*', floor[i][16]);   // right side
        }
        for (int row = 5; row <= 15; row++) {
            for (int col = 5; col <= 15; col++) {
                assertNotEquals('*', floor[row][col]);   // inside stays empty
            }
        }
        assertEquals(PEN_POSITION.UP, turtle.getPen().penPosition());
        assertEquals(16, turtle.getLocation().getRow());
        assertEquals(4, turtle.getLocation().getCol());
    }
}
