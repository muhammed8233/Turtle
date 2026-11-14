package Turtle;

public class Turtle {
    private Pen pen = Pen.UP;
    private Direction direction = Direction.EAST;
    private int newRow = 0;
    private int newColumn = 0;

    private int currentDirection = 0;

    private SketchPad sketchPad = new SketchPad();

    // a turtle in the top-left corner, facing east, with its pen up
    public Turtle() {
    }

    // a turtle at the given row and column, facing east, with its pen up
    public Turtle(int row, int column) {
        if (row >= 0 && row < sketchPad.floor.length && column >= 0 && column < sketchPad.floor.length) {
            newRow = row;
            newColumn = column;
        }
    }

    // moves the turtle forward; while the pen is down, the starting cell and
    // every cell the turtle walks onto are set to 1; the turtle stops at the wall
    public SketchPad moveTurtle(int steps){
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

        int count = 0;
        while (count < steps && isOnFloor(newRow + rowStep, newColumn + columnStep)) {
            newRow = newRow + rowStep;
            newColumn = newColumn + columnStep;
            if (pen == Pen.DOWN) {
                sketchPad.floor[newRow][newColumn] = 1;
            }
            count++;
        }
        return sketchPad;
    }

    // lifts the turtle to another cell without drawing;
    // returns false and stays put if the cell is not on the floor
    public boolean jumpTurtle(int row, int column) {
        if (isOnFloor(row, column)) {
            newRow = row;
            newColumn = column;
            return true;
        }
        return false;
    }

    public boolean isOnFloor(int row, int column) {
        return row >= 0 && row < sketchPad.floor.length
                && column >= 0 && column < sketchPad.floor.length;
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

    // sets every cell of the floor back to 0
    public void clearFloor() {
        sketchPad = new SketchPad();
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

    // builds the picture of the floor: an asterisk wherever there is a 1
    // and a blank wherever there is a 0, inside a border
    public String floorDisplay() {
        String border = "+";
        for (int i = 0; i < sketchPad.floor.length; i++) {
            border = border + "--";
        }
        border = border + "+\n";

        String display = border;
        for (int[] row : sketchPad.floor) {
            display = display + "|";
            for (int column : row) {
                if (column == 1) {
                    display = display + "* ";
                } else {
                    display = display + "  ";
                }
            }
            display = display + "|\n";
        }
        return display + border;
    }

    public void turtleFloor(){
        System.out.print(floorDisplay());
    }

}
