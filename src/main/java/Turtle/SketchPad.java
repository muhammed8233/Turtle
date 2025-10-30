package Turtle;

public class SketchPad {
    int[][] floor = new int[20][20];
    public  void turtleFloor(){
        for(int row = 0; row < floor.length; row++){
            for(int column = 0; column < floor[row].length; column++){
                System.out.printf("%d ", floor[row][column]);
            }
            System.out.println();
        }
    }
}