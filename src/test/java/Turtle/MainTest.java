package Turtle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private int countOnes(int[][] floor) {
        int count = 0;
        for (int[] row : floor) {
            for (int column : row) {
                count = count + column;
            }
        }
        return count;
    }

    @Test
    void numbersNeeded() {
        assertEquals(0, Main.numbersNeeded(1));
        assertEquals(0, Main.numbersNeeded(6));
        assertEquals(1, Main.numbersNeeded(5));
        assertEquals(1, Main.numbersNeeded(10));
        assertEquals(2, Main.numbersNeeded(8));
    }

    @Test
    void exerciseProgramDrawsTheSquareAndLeavesPenUp() {
        Turtle turtle = new Turtle(Main.START_ROW, Main.START_COLUMN);
        int[] program = {2, 5, 12, 3, 5, 12, 3, 5, 12, 3, 5, 12, 1, 6, 9};

        assertTrue(Main.runCommands(turtle, program));

        int[][] floor = turtle.getSketchPad().floor;
        assertEquals(48, countOnes(floor));
        assertEquals(1, floor[4][4]);
        assertEquals(1, floor[16][16]);
        assertEquals(0, floor[10][10]);
        assertEquals(Pen.UP, turtle.getPen());
    }

    @Test
    void penCommands() {
        Turtle turtle = new Turtle();
        Main.runCommand(turtle, 2, 0, 0);
        assertEquals(Pen.DOWN, turtle.getPen());
        Main.runCommand(turtle, 1, 0, 0);
        assertEquals(Pen.UP, turtle.getPen());
    }

    @Test
    void turnCommands() {
        Turtle turtle = new Turtle();
        Main.runCommand(turtle, 3, 0, 0);
        assertEquals(Direction.SOUTH, turtle.getDirection());
        Main.runCommand(turtle, 4, 0, 0);
        Main.runCommand(turtle, 4, 0, 0);
        assertEquals(Direction.NORTH, turtle.getDirection());
    }

    @Test
    void moveCommand() {
        Turtle turtle = new Turtle(4, 4);
        Main.runCommands(turtle, new int[] {2, 5, 3});
        assertEquals(7, turtle.getNewColumn());
        assertEquals(1, turtle.getSketchPad().floor[4][7]);
    }

    @Test
    void programStopsAtCommandNine() {
        Turtle turtle = new Turtle(4, 4);
        assertTrue(Main.runCommands(turtle, new int[] {5, 2, 9, 5, 2}));
        assertEquals(6, turtle.getNewColumn());
    }

    @Test
    void programWithoutNineReturnsFalse() {
        Turtle turtle = new Turtle(4, 4);
        assertFalse(Main.runCommands(turtle, new int[] {5, 2}));
        assertEquals(6, turtle.getNewColumn());
    }

    @Test
    void commandMissingItsNumberStopsTheProgram() {
        Turtle turtle = new Turtle(4, 4);
        assertFalse(Main.runCommands(turtle, new int[] {5, 1, 5}));
        assertEquals(5, turtle.getNewColumn());
    }

    @Test
    void clearCommand() {
        Turtle turtle = new Turtle(4, 4);
        Main.runCommands(turtle, new int[] {2, 5, 5, 7});
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void jumpCommandMovesWithoutDrawing() {
        Turtle turtle = new Turtle(4, 4);
        Main.runCommands(turtle, new int[] {2, 8, 15, 2});
        assertEquals(15, turtle.getNewRow());
        assertEquals(2, turtle.getNewColumn());
        assertEquals(0, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void jumpOffTheFloorIsIgnored() {
        Turtle turtle = new Turtle(4, 4);
        Main.runCommands(turtle, new int[] {8, 20, 3});
        assertEquals(4, turtle.getNewRow());
        assertEquals(4, turtle.getNewColumn());
    }

    @Test
    void backwardCommandMovesBackAndKeepsDirection() {
        Turtle turtle = new Turtle(4, 4);
        Main.runCommands(turtle, new int[] {2, 10, 3});
        assertEquals(1, turtle.getNewColumn());
        assertEquals(Direction.EAST, turtle.getDirection());
        assertEquals(4, countOnes(turtle.getSketchPad().floor));
    }

    @Test
    void unknownCommandIsIgnored() {
        Turtle turtle = new Turtle(4, 4);
        Main.runCommands(turtle, new int[] {15, 5, 1});
        assertEquals(5, turtle.getNewColumn());
    }

    // runs a sample program and checks that it draws something and ends with 9
    private void assertSampleDraws(int[] program) {
        Turtle turtle = new Turtle(Main.START_ROW, Main.START_COLUMN);
        assertTrue(Main.runCommands(turtle, program));
        assertTrue(countOnes(turtle.getSketchPad().floor) > 0);
    }

    @Test
    void everySampleProgramDrawsSomethingAndEndsWithNine() {
        assertSampleDraws(Main.SQUARE);
        assertSampleDraws(Main.NESTED_SQUARES);
        assertSampleDraws(Main.SPIRAL);
        assertSampleDraws(Main.STAIRCASE);
        assertSampleDraws(Main.GRID);
        assertSampleDraws(Main.HI);
    }

    @Test
    void squareSampleIsTheExerciseProgram() {
        int[] exercise = {2, 5, 12, 3, 5, 12, 3, 5, 12, 3, 5, 12, 1, 6, 9};
        assertArrayEquals(exercise, Main.SQUARE);
    }
}
