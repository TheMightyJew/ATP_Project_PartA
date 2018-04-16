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
        int row=startingPosition.getRow(),column=startingPosition.getColumn();
        array[row][column]=0;
        startingPosition=goOneAhead(startingPosition,array.length-1,array[0].length-1);
        row=startingPosition.getRow();
        column=startingPosition.getColumn();
        Stack<Integer> staki=new Stack<Integer>();
        int count=0;
        boolean toShort=false;
        while(0<row && row<array.length-1 && 0<column && column<array[0].length-1){
            count++;
            array[row][column]=0;
            if(!toShort){
                staki.push(row);
                staki.push(column);
            }
            toShort=false;
            while(array[row+1][column]==0 && array[row-1][column]==0 &&array[row][column+1]==0 &&array[row][column-1]==0){
                column=staki.pop();
                row=staki.pop();
            }
            double random;
            while(true){
                random=Math.random();
                if(random>=0.75 && array[row+1][column]==1){
                    row++;
                    break;
                }
                else if(random>=0.5 && array[row-1][column]==1){
                    row--;
                    break;
                }
                else if(random>=0.25 && array[row][column+1]==1){
                    column++;
                    break;
                }
                else if(array[row][column-1]==1){
                    column--;
                    break;
                }
            }
            if(!(0<row && row<array.length-1 && 0<column && column<array[0].length-1) && count<array.length+array[0].length){
                column=staki.pop();
                row=staki.pop();
                count--;
                count--;
                toShort=true;
            }
        }
        array[row][column]=0;
        for (int i=1;i<array.length-1;i++)
            for (int j=1;j<array[0].length-1;j++)
                if(array[i][j]==1)
                    if(Math.random()>0.5)
                        array[i][j]=0;
        return new Position(row,column);
    }
    private Position goOneAhead(Position startingPoint,int maxRow,int maxColumn){
        Position better=new Position(0,0);
        if(startingPoint.getRow()==0)
            better=new Position(startingPoint.getRow()+1,startingPoint.getColumn());
        else if (startingPoint.getRow()==maxRow)
            better=new Position(startingPoint.getRow()-1,startingPoint.getColumn());
        else if(startingPoint.getColumn()==0)
            better=new Position(startingPoint.getRow(),startingPoint.getColumn()+1);
        else if (startingPoint.getColumn()==maxColumn)
            better=new Position(startingPoint.getRow(),startingPoint.getColumn()-1);
        return better;
    }
}


