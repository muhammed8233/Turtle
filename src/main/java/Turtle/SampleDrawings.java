package Turtle;

// Turtle programs that draw shapes. Each program is an array of commands,
// written the same way you would type them.
public class SampleDrawings {
    // near the centre of the 20-by-20 floor, so the 12-by-12 square fits
    public static final int START_ROW = 4;
    public static final int START_COLUMN = 4;

    // The 12-by-12 square from the exercise
    public static final int[] SQUARE = {
            2, 5,12, 3, 5,12, 3, 5,12, 3, 5,12, 1, 6, 9
    };

    // Five squares, one inside the other
    public static final int[] NESTED_SQUARES = {
            8,0,0, 2, 5,19, 3, 5,19, 3, 5,19, 3, 5,19, 3, 1, 8,2,2, 2, 5,15, 3,
            5,15, 3, 5,15, 3, 5,15, 3, 1, 8,4,4, 2, 5,11, 3, 5,11, 3, 5,11, 3,
            5,11, 3, 1, 8,6,6, 2, 5,7, 3, 5,7, 3, 5,7, 3, 5,7, 3, 1, 8,8,8, 2,
            5,3, 3, 5,3, 3, 5,3, 3, 5,3, 3, 1, 6, 9
    };

    // A square spiral
    public static final int[] SPIRAL = {
            8,0,0, 2, 5,19, 3, 5,19, 3, 5,19, 3, 5,17, 3, 5,17, 3, 5,15, 3, 5,15,
            3, 5,13, 3, 5,13, 3, 5,11, 3, 5,11, 3, 5,9, 3, 5,9, 3, 5,7, 3, 5,7,
            3, 5,5, 3, 5,5, 3, 5,3, 3, 5,3, 3, 5,1, 1, 6, 9
    };

    // A staircase from the bottom-left corner to the top-right
    public static final int[] STAIRCASE = {
            8,19,0, 2, 5,3, 4, 5,3, 3, 5,3, 4, 5,3, 3, 5,3, 4, 5,3, 3, 5,3, 4,
            5,3, 3, 5,3, 4, 5,3, 3, 5,3, 4, 5,3, 3, 5,1, 1, 6, 9
    };

    // A 3-by-3 grid, drawn with the backward command
    public static final int[] GRID = {
            8,0,0, 2, 5,18, 10,18, 1, 3, 5,6, 4, 2, 5,18, 10,18, 1, 3, 5,6, 4, 2,
            5,18, 10,18, 1, 3, 5,6, 4, 2, 5,18, 1, 8,0,0, 3, 2, 5,18, 10,18, 1,
            4, 5,6, 3, 2, 5,18, 10,18, 1, 4, 5,6, 3, 2, 5,18, 10,18, 1, 4, 5,6,
            3, 2, 5,18, 1, 6, 9
    };

    // The word HI
    public static final int[] HI = {
            8,4,2, 3, 2, 5,10, 1, 8,9,2, 4, 2, 5,5, 1, 8,4,7, 3, 2, 5,10, 1,
            8,4,11, 4, 2, 5,6, 1, 8,14,11, 2, 5,6, 1, 8,4,14, 3, 2, 5,10, 1, 6,
            9
    };

    // prints the name of a drawing, then draws it with a new turtle
    public static void drawSample(String name, int[] commands) {
        System.out.println(name);
        TurtleCommands.runCommands(new Turtle(START_ROW, START_COLUMN), commands);
        System.out.println();
    }

    // draws every sample, one after the other
    public static void drawAll() {
        drawSample("Square", SQUARE);
        drawSample("Nested squares", NESTED_SQUARES);
        drawSample("Spiral", SPIRAL);
        drawSample("Staircase", STAIRCASE);
        drawSample("Grid", GRID);
        drawSample("HI", HI);
    }
}
