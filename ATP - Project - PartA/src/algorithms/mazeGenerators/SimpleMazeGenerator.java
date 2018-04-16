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
    private void initArray(int [][] array){
        for(int i=0;i<array.length;i++)
            for(int j=0;j<array[0].length;j++)
                array[i][j]=1;
    }
    private int getRandomStart(int column){
        int range=column;
        return (int)(Math.random()*range);
    }
    private Pair<Integer,Integer> generatePath(int[][] array,int column){
        int row=0;
        while(row<array.length){
            array[row][column]=0;
            if(column==array[0].length-1){
                row++;
                continue;
            }
            if(Math.random()>=0.5)
                row++;
            else
                column++;
        }
        return new Pair<>(row-1,column);
    }
}


