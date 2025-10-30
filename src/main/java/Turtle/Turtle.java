package Turtle;

public class Turtle {
    private Pen pen;
    private Direction direction;

    public Turtle moveTurtle(SketchPad move){
        SketchPad sketchPad = new SketchPad();
        if(){

        }

    }

    public Turtle changeDirection(int number){
        if(number == 1){
            direction = Direction.NORTH;
        } else if (number == 2) {
            direction = Direction.SOUTH;
        } else if (number == 3 ) {
            direction = Direction.WEST;
        } else if (number == 4) {
            direction = Direction.EAST;
        }
        throw new IllegalArgumentException("input number is out of range");
    }

    public Turtle changePenStatus(int value){
        if (value == 1){
           pen = Pen.UP;
        }else if(value == 2){
            pen = Pen.DOWN;
        }
        throw new IllegalArgumentException("input value out of range");
    }
}
