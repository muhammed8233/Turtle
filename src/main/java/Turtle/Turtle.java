package Turtle;

public class Turtle {
    private Pen pen = Pen.UP;
    private Direction direction = Direction.EAST;
    private int newRow = 0;
    private int newColumn = 0;

    private int currentDirection = 0;

    private final SketchPad sketchPad = new SketchPad();

    /** A turtle in the top-left corner, facing east, with its pen up. */
    public Turtle() {
    }

    /** A turtle at the given row and column, facing east, with its pen up. */
    public Turtle(int newRow, int newColumn) {
        if (newRow < 0 || newRow >= sketchPad.floor.length || newColumn < 0 || newColumn >= sketchPad.floor.length) {
            throw new IllegalArgumentException("Cell " + newRow + "," + newColumn + " is not on the floor");
        }
        this.newRow = newRow;
        this.newColumn = newColumn;
    }

    /**
     * Moves the turtle forward. While the pen is down, the starting cell and every
     * cell it walks onto are set to 1. The turtle stops at the edge of the floor.
     */
    public SketchPad moveTurtle(int steps){
        if (steps < 0) {
            throw new IllegalArgumentException("Number of steps cannot be negative: " + steps);
        }
        int rowStep = 0;
        int columnStep = 0;
        if (direction == Direction.NORTH) {
            rowStep = -1;
        } else if (direction == Direction.SOUTH) {
            rowStep = 1;
        } else if (direction == Direction.EAST) {
            columnStep = 1;
        } else if (direction == Direction.WEST) {
            columnStep = -1;
        }

        if (pen == Pen.DOWN) {
            sketchPad.floor[newRow][newColumn] = 1;
        }
        for (int i = 0; i < steps; i++) {
            int nextRow = newRow + rowStep;
            int nextColumn = newColumn + columnStep;
            if (!isOnFloor(nextRow, nextColumn)) {
                break;
            }
            newRow = nextRow;
            newColumn = nextColumn;
            if (pen == Pen.DOWN) {
                sketchPad.floor[newRow][newColumn] = 1;
            }
        }
        return sketchPad;
    }

    /** Lifts the turtle to another cell without drawing. */
    public void jumpTurtle(int row, int column) {
        if (!isOnFloor(row, column)) {
            throw new IllegalArgumentException("Cell " + row + "," + column + " is not on the floor");
        }
        newRow = row;
        newColumn = column;
    }

    public boolean isOnFloor(int row, int column) {
        return row >= 0 && row < sketchPad.floor.length
                && column >= 0 && column < sketchPad.floor[row].length;
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

    /** 3 turns right and 4 turns left, like the turtle commands. Returns the command used. */
    public int changeDirection(int value){
        if (value == 3) {
            changeDirectionToRight();
        } else if (value == 4) {
            changeDirectionToLeft();
        } else {
            throw new IllegalArgumentException("Direction must be 3 (right) or 4 (left): " + value);
        }
        currentDirection = value;
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

    /** Displays the floor: an asterisk wherever there is a 1, a blank wherever there is a 0. */
    public void turtleFloor(){
        System.out.println("+" + "--".repeat(sketchPad.floor.length) + "+");
        for (int[] row : sketchPad.floor) {
            System.out.print("|");
            for (int column : row) {
                System.out.print(column == 1 ? "* " : "  ");
            }
            System.out.println("|");
        }
        System.out.println("+" + "--".repeat(sketchPad.floor.length) + "+");
    }

}
