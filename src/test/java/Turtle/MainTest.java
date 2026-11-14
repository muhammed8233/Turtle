package Turtle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MainTest {
    private Main program;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        program = new Main();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private void assertLocation(int row, int col) {
        assertEquals(row, program.getTurtle().getNewRow(), "row");
        assertEquals(col, program.getTurtle().getNewColumn(), "column");
    }

    private static int countOnes(int[][] floor) {
        int count = 0;
        for (int[] row : floor) {
            for (int cell : row) {
                count += cell;
            }
        }
        return count;
    }

    private String floorDisplay(Turtle turtle) {
        ByteArrayOutputStream display = new ByteArrayOutputStream();
        PrintStream previous = System.out;
        System.setOut(new PrintStream(display));
        turtle.turtleFloor();
        System.setOut(previous);
        return display.toString();
    }

    @Test
    void testThatTurtleStartsNearTheCentreFacingEastWithPenUp() {
        assertLocation(4, 4);
        assertEquals(Direction.EAST, program.getTurtle().getDirection());
        assertEquals(Pen.UP, program.getTurtle().getPen());
    }

    @Test
    void testThatTheExerciseProgramDrawsATwelveByTwelveSquareAndLeavesPenUp() {
        program.runProgram(List.of("2", "5,12", "3", "5,12", "3", "5,12", "3", "5,12", "1", "6", "9"));

        int[][] floor = program.getTurtle().getSketchPad().floor;
        for (int i = 4; i <= 16; i++) {
            assertEquals(1, floor[4][i], "top edge at column " + i);
            assertEquals(1, floor[16][i], "bottom edge at column " + i);
            assertEquals(1, floor[i][4], "left edge at row " + i);
            assertEquals(1, floor[i][16], "right edge at row " + i);
        }
        assertEquals(12 * 4, countOnes(floor));
        assertEquals(0, floor[10][10]);
        assertEquals(Pen.UP, program.getTurtle().getPen());
        assertLocation(4, 4);
        assertTrue(program.isFinished());
        assertEquals(floorDisplay(program.getTurtle()), output.toString());
    }

    @Test
    void testThatPenCommandsChangePen() {
        program.runCommand("2");
        assertEquals(Pen.DOWN, program.getTurtle().getPen());
        program.runCommand("1");
        assertEquals(Pen.UP, program.getTurtle().getPen());
    }

    @Test
    void testThatTurnCommandsTurnTurtle() {
        program.runCommand("3");
        assertEquals(Direction.SOUTH, program.getTurtle().getDirection());
        program.runCommand("4");
        program.runCommand("4");
        assertEquals(Direction.NORTH, program.getTurtle().getDirection());
    }

    @Test
    void testThatMoveCommandMovesAndDraws() {
        program.runCommand("2");
        program.runCommand("5,3");
        assertLocation(4, 7);
        assertEquals(1, program.getTurtle().getSketchPad().floor[4][7]);
    }

    @Test
    void testThatCommandsAllowSpaces() {
        program.runCommand(" 5 , 4 ");
        assertLocation(4, 8);
    }

    @Test
    void testThatEndCommandStopsTheProgram() {
        assertFalse(program.runCommand("9"));
        assertFalse(program.runCommand("5,3"));
        assertLocation(4, 4);
    }

    @Test
    void testThatClearCommandEmptiesTheFloor() {
        program.runProgram(List.of("2", "5,5", "7"));
        assertEquals(0, countOnes(program.getTurtle().getSketchPad().floor));
    }

    @Test
    void testThatJumpCommandMovesWithoutDrawing() {
        program.runProgram(List.of("2", "8,15,2"));
        assertLocation(15, 2);
        assertEquals(0, countOnes(program.getTurtle().getSketchPad().floor));
    }

    @Test
    void testThatJumpOutsideTheFloorIsRejected() {
        program.runCommand("8,20,3");
        assertLocation(4, 4);
        assertTrue(output.toString().contains("Cannot jump outside the floor"));
    }

    @Test
    void testThatBackwardCommandMovesBackAndKeepsDirection() {
        program.runProgram(List.of("2", "10,3"));
        assertLocation(4, 1);
        assertEquals(Direction.EAST, program.getTurtle().getDirection());
        assertEquals(4, countOnes(program.getTurtle().getSketchPad().floor));
    }

    @Test
    void testThatStatusCommandShowsTheTurtle() {
        program.runProgram(List.of("2", "3", "14"));
        assertTrue(output.toString().contains("Turtle at row 4, column 4, facing SOUTH, pen DOWN"));
    }

    @Test
    void testThatRepeatDrawsTheSameSquareAsWritingItOut() {
        program.runProgram(List.of("2", "12,4", "5,12", "3", "13", "1"));
        Main longHand = new Main();
        longHand.runProgram(List.of("2", "5,12", "3", "5,12", "3", "5,12", "3", "5,12", "1"));
        assertArrayEquals(longHand.getTurtle().getSketchPad().floor, program.getTurtle().getSketchPad().floor);
    }

    @Test
    void testThatRepeatsCanBeNested() {
        program.runProgram(List.of("12,3", "12,2", "5,1", "13", "13"));
        assertLocation(4, 10);
    }

    @Test
    void testThatRepeatZeroTimesSkipsTheCommands() {
        program.runProgram(List.of("12,0", "5,5", "13", "5,1"));
        assertLocation(4, 5);
    }

    @Test
    void testThatEndInsideARepeatStopsEverything() {
        program.runProgram(List.of("12,5", "5,1", "9", "13", "5,10"));
        assertLocation(4, 5);
        assertTrue(program.isFinished());
    }

    @Test
    void testThatRepeatWaitsForItsEndWhenTypedInteractively() {
        program.runCommand("12,2");
        program.runCommand("5,3");
        assertLocation(4, 4);
        program.runCommand("13");
        assertLocation(4, 10);
    }

    @Test
    void testThatUnclosedAndUnopenedRepeatsAreReported() {
        program.runProgram(List.of("13", "12,2", "5,1"));
        assertTrue(output.toString().contains("13 without a matching 12,n"));
        assertTrue(output.toString().contains("Missing 13"));
    }

    @Test
    void testThatCommentsAndBlankLinesAreIgnored() {
        program.runProgram(List.of("# draw a line", "", "2   # pen down", "5,2"));
        assertLocation(4, 6);
        assertEquals("", output.toString());
    }

    @Test
    void testThatHittingTheWallIsReported() {
        program.runCommand("5,30");
        assertTrue(output.toString().contains("Hit the wall after 15 of 30 steps."));
    }

    @Test
    void testThatInvalidCommandsAreReportedAndIgnored() {
        assertTrue(program.runCommand("15"));
        assertTrue(program.runCommand("hello"));
        assertTrue(program.runCommand("5"));
        assertTrue(program.runCommand("5,-2"));
        assertTrue(program.runCommand("8,1"));
        assertTrue(program.runCommand("12"));
        assertLocation(4, 4);
        String text = output.toString();
        assertTrue(text.contains("Unknown command: 15"));
        assertTrue(text.contains("Unknown command: hello"));
        assertTrue(text.contains("Move needs a number of steps"));
        assertTrue(text.contains("cannot be negative"));
        assertTrue(text.contains("Jump needs a row and a column"));
        assertTrue(text.contains("Repeat needs a count"));
    }

    @TestFactory
    Stream<DynamicTest> testThatEveryExampleProgramRunsCleanly() throws IOException {
        Path folder = Path.of("programs");
        assumeTrue(Files.isDirectory(folder), "run the tests from the project folder");
        List<Path> files;
        try (Stream<Path> list = Files.list(folder)) {
            files = list.filter(p -> p.toString().endsWith(".txt")).sorted().toList();
        }
        assertFalse(files.isEmpty());
        return files.stream().map(file -> DynamicTest.dynamicTest(file.getFileName().toString(), () -> {
            output.reset();
            Main example = new Main();
            example.runProgram(Files.readAllLines(file));
            assertTrue(example.isFinished(), "program should end with 9");
            assertTrue(countOnes(example.getTurtle().getSketchPad().floor) > 0, "program should draw something");
            assertEquals(floorDisplay(example.getTurtle()), output.toString(), "program should only print the floor");
        }));
    }
}
