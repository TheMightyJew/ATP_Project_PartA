package algorithms.mazeGenerators;


import javafx.util.Pair;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze newone=new Maze(rows,columns);
        int[][] array=newone.getArray();
        initArray(array);
        int startColumn=getRandomStart(columns);
        newone.setStartPoint(new Pair<>(0,startColumn));
        newone.setFinishPoint( generatePath(array,startColumn));
        newone.setArray(array);
        return newone;
    }
    private Pair<Integer,Integer> generatePath(int[][] array, int column){
        int row=0;
        array[row][column]=0;
        row++;
        while(row<array.length-1){
            array[row][column]=0;
            if(column==array[0].length-2){
                row++;
                continue;
            }
            if(Math.random()>=0.5)
                row++;
            else
                column++;
        }
        array[row][column]=0;
        for (int i=1;i<array.length-1;i++)
            for (int j=1;j<array[0].length-1;j++)
                if(array[i][j]==1)
                    if(Math.random()>0.5)
                        array[i][j]=0;
        return new Pair<>(row,column);
    }
}


