package algorithms.mazeGenerators;


import javafx.util.Pair;

import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze newone=new Maze(rows,columns);
        int[][] array=newone.getArray();
        initArray(array);
        Position startingPosition=GetStartPoint(rows,columns);
        newone.setStartPosition(startingPosition);
        newone.setGoalPosition( generatePath(array,startingPosition));
        newone.setArray(array);
        return newone;
    }

    private static Position  GetStartPoint(int rows, int columns){
        int scolumn,srow;
        if(Math.random()>=0.5){
            srow = MaxORZero(rows);
            scolumn = getRandomStart(columns);
        }
        else{
            srow = getRandomStart(rows);
            scolumn = MaxORZero(columns);
        }
        return  new Position(srow,scolumn);
    }

    private static int MaxORZero(int max){
        if(Math.random()>=0.5)
            return 0;
        else
            return max-1;
    }
    private Position generatePath(int[][] array,Position startingPosition){
        int row=startingPosition.getRow(),column=startingPosition.getColumnIndex();
        array[row][column]=0;
        startingPosition=goOneAhead(startingPosition,array.length-1,array[0].length-1);
        row=startingPosition.getRow();
        column=startingPosition.getColumnIndex();
        Stack<Integer> staki=new Stack<Integer>();
        int count=0;
        int minCount;
        minCount=Math.min(array.length,array[0].length);
        while(0<row && row<array.length-1 && 0<column && column<array[0].length-1){
            array[row][column]=0;
            while((array[row+1][column]==0 || !touchOnlyOne(array,row+1,column))  && (array[row-1][column]==0 || !touchOnlyOne(array,row-1,column))
                    && (array[row][column-1]==0 || !touchOnlyOne(array,row,column-1))  && (array[row][column+1]==0 || !touchOnlyOne(array,row,column+1))){
                column=staki.pop();
                row=staki.pop();
            }
            staki.push(row);
            staki.push(column);
            double random;
            while(true){
                random=Math.random();
                if(random>=0.75 && array[row+1][column]==1 && touchOnlyOne(array,row+1,column)){
                        row++;
                        break;
                    }
                else if(random>=0.5 && array[row-1][column]==1 && touchOnlyOne(array,row-1,column)){
                        row--;
                        break;
                }
                else if(random>=0.25 && array[row][column+1]==1 && touchOnlyOne(array,row,column+1)){
                    column++;
                    break;
                }
                else if(array[row][column-1]==1 && touchOnlyOne(array,row,column-1)){
                    column--;
                    break;
                }
            }
            if(!(0<row && row<array.length-1 && 0<column && column<array[0].length-1) && count<=minCount){
                column=staki.pop();
                row=staki.pop();
            }
            count++;
        }
        array[row][column]=0;
        fillWithBlanks(array);
        return new Position(row,column);
    }
    private Position goOneAhead(Position startingPoint,int maxRow,int maxColumn){
        Position better=new Position(0,0);
        if(startingPoint.getRow()==0)
            better=new Position(startingPoint.getRow()+1,startingPoint.getColumnIndex());
        else if (startingPoint.getRow()==maxRow)
            better=new Position(startingPoint.getRow()-1,startingPoint.getColumnIndex());
        else if(startingPoint.getColumnIndex()==0)
            better=new Position(startingPoint.getRow(),startingPoint.getColumnIndex()+1);
        else if (startingPoint.getColumnIndex()==maxColumn)
            better=new Position(startingPoint.getRow(),startingPoint.getColumnIndex()-1);
        return better;
    }
    private boolean touchOnlyOne(int[][] array,int row,int column){
        int count=0;
        if(row+1<array.length-1 && array[row+1][column]==0)
            count++;
        if(row-1>0 && array[row-1][column]==0)
            count++;
        if(column+1<array[0].length-1 && array[row][column+1]==0)
            count++;
        if(column-1>0 && array[row][column-1]==0)
            count++;
        return (count==1);
    }
    private void fillWithBlanks(int[][] array){
        for (int i=1;i<array.length-1;i++)
            for (int j=1;j<array[0].length-1;j++)
                if(array[i][j]==1 && touchOnlyOne(array,i,j))
                    array[i][j]=0;
        for (int i=1;i<array.length-1;i++)
            for (int j=array[0].length-2;j>0;j--)
                if(array[i][j]==1 && touchOnlyOne(array,i,j))
                    array[i][j]=0;
        for (int i=array.length-2;i>0;i--)
            for (int j=1;j<array[0].length-1;j++)
                if(array[i][j]==1 && touchOnlyOne(array,i,j))
                    array[i][j]=0;
        for (int i=array.length-2;i>0;i--)
            for (int j=array[0].length-2;j>0;j--)
                if(array[i][j]==1 && touchOnlyOne(array,i,j))
                    array[i][j]=0;
    }
}


