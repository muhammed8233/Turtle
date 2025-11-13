package Turtle;

public class Main {
    public static void main(String[] args) {
        Turtle turtle = new Turtle();

        turtle.changePenStatus(Pen.DOWN);
        turtle.moveTurtle(3);
        turtle.changeDirectionToRight();
        turtle.moveTurtle(3);
        turtle.changeDirectionToRight();
        turtle.moveTurtle(3);
        turtle.changeDirectionToRight();
        turtle.moveTurtle(3);
        turtle.changePenStatus(Pen.UP);
        turtle.turtleFloor();


    }
}