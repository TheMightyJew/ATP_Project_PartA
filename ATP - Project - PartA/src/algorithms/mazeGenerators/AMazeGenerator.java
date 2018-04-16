package algorithms.mazeGenerators;

import javafx.util.Pair;

abstract class AMazeGenerator implements IMazeGenerator{
    abstract public Maze generate(int rows, int columns);
    public long measureAlgorithmTimeMillis(int rows, int columns){
        long time=System.currentTimeMillis();
        generate(rows,columns);
        long ans=System.currentTimeMillis()-time;
        return ans;
    }
    protected void initArray(int [][] array){
        for(int i=0;i<array.length;i++)
            for(int j=0;j<array[0].length;j++)
                array[i][j]=1;
    }
    protected static int getRandomStart(int column){
        int range=column-2;
        return (int)(Math.random()*range)+1;
    }
}
