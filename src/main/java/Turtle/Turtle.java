package Turtle;

public class Turtle {
    private Pen pen = new Pen();
    private Location location = new Location();
    private DIRECTION direction = DIRECTION.NORTH;

    public Pen getPen() {
        return this.pen;
    }

    // 1 raises the pen, 2 puts it down
    public void changePenPosition(int positionValue) {
        if (positionValue == 1){
            pen.raiseUp();
        } else if (positionValue == 2) {
            pen.putDown();
        }
    }
    public void setPen(Pen pen){
        this.pen = pen;
    }

    // turns a quarter turn to the left of the way the turtle is facing
    public void turnLeft() {
        if (direction == DIRECTION.NORTH) {
            direction = DIRECTION.WEST;
        } else if (direction == DIRECTION.WEST) {
            direction = DIRECTION.SOUTH;
        } else if (direction == DIRECTION.SOUTH) {
            direction = DIRECTION.EAST;
        } else if (direction == DIRECTION.EAST) {
            direction = DIRECTION.NORTH;
        }
    }
    // turns a quarter turn to the right of the way the turtle is facing
    public void turnRight() {
        if (direction == DIRECTION.NORTH) {
            direction = DIRECTION.EAST;
        } else if (direction == DIRECTION.EAST) {
            direction = DIRECTION.SOUTH;
        } else if (direction == DIRECTION.SOUTH) {
            direction = DIRECTION.WEST;
        } else if (direction == DIRECTION.WEST) {
            direction = DIRECTION.NORTH;
        }
    }
    public void turnUp() {
        this.direction = DIRECTION.NORTH;
    }
    public void turnDown() {
        this.direction = DIRECTION.SOUTH;
    }

    public DIRECTION getDirection() {
        return direction;
    }
    public void setLocation(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }

    // moves the turtle forward one cell at a time; while the pen is down,
    // the starting cell and every cell the turtle walks onto get an asterisk;
    // the turtle stops at the edge of the floor
    public char[][] move(int numberOfSteps, Room room) {
        char[][]floor = room.getFloor();
        int lastCell = floor.length - 1;
        int row = location.getRow();
        int col = location.getCol();

        if (pen.penPosition() == PEN_POSITION.DOWN) {
            floor[row][col] = '*';
        }

        for (int i = 0; i < numberOfSteps; i++) {
            if (direction == DIRECTION.EAST && col < lastCell) {
                col++;
            } else if (direction == DIRECTION.WEST && col > 0) {
                col--;
            } else if (direction == DIRECTION.SOUTH && row < lastCell) {
                row++;
            } else if (direction == DIRECTION.NORTH && row > 0) {
                row--;
            }

            if (pen.penPosition() == PEN_POSITION.DOWN) {
                floor[row][col] = '*';
            }
        }

        location.setRow(row);
        location.setCol(col);
        return floor;
    }
}
