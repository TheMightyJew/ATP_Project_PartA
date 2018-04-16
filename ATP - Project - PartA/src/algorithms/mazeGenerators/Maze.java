package algorithms.mazeGenerators;
import javafx.util.Pair;

public class Maze {
    private int[][] array;
    private int rowsNum;
    private int columnsNum;
    private Pair<Integer,Integer> startPoint;
    private Pair<Integer,Integer> finishPoint;
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

    public Pair<Integer, Integer> getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Pair<Integer, Integer> startPoint) {
        this.startPoint = startPoint;
    }

    public Pair<Integer, Integer> getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(Pair<Integer, Integer> finishPoint) {
        this.finishPoint = finishPoint;
    }
}
