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
}
