package Turtle;

public class Room {
    char[][] floor = new char[20][20];

    public char[][] getFloor() {
        return floor;
    }

    // shows an asterisk wherever the turtle has drawn and a blank everywhere else
    public void displayFloor(){
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[i].length; j++) {
                if (floor[i][j] == '*') {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    public void setFloor(char[][]floor){
        this.floor = floor;
    }
}
