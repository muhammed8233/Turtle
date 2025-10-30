package Turtle;

public class Turtle {
    private Pen pen;
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Pen getPen() {
        return pen;
    }

    public void setPen(Pen pen) {
        this.pen = pen;
    }

    public Turtle moveTurtle(Pen value, Direction number){
        Turtle turtle = new Turtle();
        setPen(value);
        setDirection(number);

        return turtle;
    }

    public int changeDirection(int number){
        if(number == 1){
            direction = Direction.NORTH;
            System.out.println(direction);
        }else if (number == 2) {
            direction = Direction.SOUTH;
            System.out.println(direction);
        }else if (number == 3 ) {
            direction = Direction.WEST;
            System.out.println(direction);
        }else if (number == 4) {
            direction = Direction.EAST;
            System.out.println(direction);
        }else {
            throw new IllegalArgumentException("input number out of range");
        }
        return number;
    }

    public int changePenStatus(int value){
        if (value == 1){
           pen = Pen.UP;
            System.out.println(pen);
        }else if(value == 2){
            pen = Pen.DOWN;
            System.out.println(pen);
        }else {
            throw new IllegalArgumentException("input value out of range");
        }
        return value;
    }
}
