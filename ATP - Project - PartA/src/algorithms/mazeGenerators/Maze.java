package algorithms.mazeGenerators;
import javafx.util.Pair;

public class Maze {
    private int[][] array;
    private int rowsNum;
    private int columnsNum;
    private Position StartPosition;
    private Position goalPosition;
    public Maze(int rows,int columns){
        rowsNum=rows;
        columnsNum=columns;
        array=new int[rows][columns];
        //add generateMaze
    }
    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    public Position getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }
    public void print(){
        for(int i=0;i<array.length;i++) {
            for (int j = 0; j < array[0].length; j++)
                System.out.print(array[i][j]);
            System.out.println();
        }
    }
}
