package Turtle;

public class Turtle {
    private Pen pen;
    private Direction direction;
    private int newRow = 0;
    private int newColumn = 0;

    private int currentDirection = 0;


    public void moveTurtle(SketchPad moves){
        SketchPad sketchPad = new SketchPad();
        if()
        for (int i = 0; i < sketchPad.floor.length; i++) {

        }
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

    public Pen changePenStatus(Pen value){
        if (value == Pen.UP){
           pen = Pen.UP;

        }else if(value == Pen.DOWN){
            pen = Pen.DOWN;

        }
        return pen;
    }

    public void turtleFloor(){
        SketchPad sketchPad = new SketchPad();
        for (int[] row : sketchPad.floor) {
            for (int column : row) {
                System.out.print(column == 1 ? 1 : 0);
            }
            System.out.println();
        }
    }

}
