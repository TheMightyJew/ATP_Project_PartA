package algorithms.mazeGenerators;


import javafx.util.Pair;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze newone=new Maze(rows,columns);
        int[][] array=newone.getArray();
        initArray(array);
        int startColumn=getRandomStart(columns);
        newone.setStartPoint(new Pair<>(0,startColumn));
        //newone.setFinishPoint( generatePath(array,startColumn));
        newone.setArray(array);
        return newone;
    }

    private static Pair<Integer,Integer>  GetStartPoint(int rows, int columns){
        int scolumn,srow;
        if(Math.random()>=0.5){
            srow = MaxORZero(rows);
            scolumn = getRandomStart(columns);
        }
        else{
            srow = getRandomStart(rows);
            scolumn = MaxORZero(columns);
        }
        return  new Pair<>(srow,scolumn);
    }

    private static int MaxORZero(int max){
        if(Math.random()>=0.5)
            return 0;
        else
            return max-1;
    }
}


