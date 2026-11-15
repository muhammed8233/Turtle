package Turtle;

// The floor the turtle draws on: a 20-by-20 array of 0s and 1s.
public class SketchPad{
    int[][] floor = new int[20][20];

    // true if the row and column are inside the floor
    public boolean isOnFloor(int row, int column) {
        return row >= 0 && row < floor.length
                && column >= 0 && column < floor.length;
    }

    // sets the cell at row, column to 1
    public void markCell(int row, int column) {
        if (isOnFloor(row, column)) {
            floor[row][column] = 1;
        }
    }

    // true if the cell at row, column is 1
    public boolean isMarked(int row, int column) {
        return isOnFloor(row, column) && floor[row][column] == 1;
    }

    // sets every cell back to 0
    public void clearFloor() {
        for (int row = 0; row < floor.length; row++) {
            for (int column = 0; column < floor[row].length; column++) {
                floor[row][column] = 0;
            }
        }
    }

    // the number of the last row (and the last column) on the floor
    public int lastCell() {
        return floor.length - 1;
    }

    // builds the picture of the floor: an asterisk wherever there is a 1
    // and a blank wherever there is a 0, inside a border
    public String floorDisplay() {
        String border = "+";
        for (int i = 0; i < floor.length; i++) {
            border = border + "--";
        }
        border = border + "+\n";

        String display = border;
        for (int[] row : floor) {
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

    // prints the picture of the floor
    public void printFloor() {
        System.out.print(floorDisplay());
    }
}
