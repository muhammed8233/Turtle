package Turtle;

public class Turtle {
    private Pen pen = Pen.UP;
    private Direction direction = Direction.EAST;
    private int newRow = 0;
    private int newColumn = 0;

    private int currentDirection = 0;

    private final SketchPad sketchPad = new SketchPad();

    // a turtle in the top-left corner, facing east, with its pen up
    public Turtle() {
    }

    // a turtle at the given row and column, facing east, with its pen up
    public Turtle(int row, int column) {
        if (sketchPad.isOnFloor(row, column)) {
            newRow = row;
            newColumn = column;
        }
    }

    // moves the turtle forward one cell at a time; while the pen is down,
    // the starting cell and every cell the turtle walks onto are set to 1;
    // the turtle stops at the edge of the floor
    public SketchPad moveTurtle(int steps){
        int lastCell = sketchPad.lastCell();

        if (pen == Pen.DOWN) {
            sketchPad.markCell(newRow, newColumn);
        }

        for (int i = 0; i < steps; i++) {
            if (direction == Direction.EAST && newColumn < lastCell) {
                newColumn++;
            } else if (direction == Direction.WEST && newColumn > 0) {
                newColumn--;
            } else if (direction == Direction.SOUTH && newRow < lastCell) {
                newRow++;
            } else if (direction == Direction.NORTH && newRow > 0) {
                newRow--;
            }

            if (pen == Pen.DOWN) {
                sketchPad.markCell(newRow, newColumn);
            }
        }
        return sketchPad;
    }

    // lifts the turtle to another cell without drawing;
    // returns false and stays put if the cell is not on the floor
    public boolean jumpTurtle(int row, int column) {
        if (sketchPad.isOnFloor(row, column)) {
            newRow = row;
            newColumn = column;
            return true;
        }
        return false;
    }

    public Direction changeDirectionToRight(){
        if(direction == Direction.NORTH){
            direction = Direction.EAST;
        }else if (direction == Direction.SOUTH) {
            direction = Direction.WEST;

        }else if (direction == Direction.EAST) {
            direction = Direction.SOUTH;

        }else if (direction == Direction.WEST) {
            direction = Direction.NORTH;
        }
        return direction;
    }

    public Direction changeDirectionToLeft(){
        if(direction == Direction.EAST){
            direction = Direction.NORTH;
        }else if (direction == Direction.WEST) {
            direction = Direction.SOUTH;

        }else if (direction == Direction.SOUTH) {
            direction = Direction.EAST;

        }else if (direction == Direction.NORTH ) {
            direction = Direction.WEST;
        }
        return direction;
    }

    // 3 turns right and 4 turns left, like the turtle commands;
    // returns the last direction command that was used
    public int changeDirection(int value){
        if (value == 3) {
            changeDirectionToRight();
            currentDirection = value;
        } else if (value == 4) {
            changeDirectionToLeft();
            currentDirection = value;
        }
        return currentDirection;
    }

    public Pen changePenStatus(Pen value){
        if (value == Pen.UP){
           pen = Pen.UP;

        }else if(value == Pen.DOWN){
            pen = Pen.DOWN;

        }
        return pen;
    }

    // asks the sketch pad to set every cell back to 0
    public void clearFloor() {
        sketchPad.clearFloor();
    }

    public Pen getPen() {
        return pen;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getNewRow() {
        return newRow;
    }

    public int getNewColumn() {
        return newColumn;
    }

    public SketchPad getSketchPad() {
        return sketchPad;
    }

    // the picture of the floor, built by the sketch pad
    public String floorDisplay() {
        return sketchPad.floorDisplay();
    }

    // displays the floor (command 6)
    public void turtleFloor(){
        sketchPad.printFloor();
    }

}
