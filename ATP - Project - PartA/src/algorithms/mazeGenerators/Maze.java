package algorithms.mazeGenerators;
import javafx.util.Pair;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Maze implements Serializable {
    private int[][] array;
    private int rowsNum;
    private int columnsNum;
    private Position StartPosition;
    private Position goalPosition;

    public Maze(int rows,int columns){
        rowsNum=rows;
        columnsNum=columns;
        array=new int[rows][columns];
    }
    public Maze(byte[] arr){
        int j,i,sum;
        int[] Details=new int[6];
        for(j=0,i=0;j<6;i=i+1,j=j+1){
            sum=0;
            while(arr[i]!=-128){
                if(arr[i]<0)
                    sum=sum+127-arr[i];
                else sum=sum+arr[i];
                i++;
            }
            Details[j]=sum;
        }
        StartPosition=new Position(Details[0],Details[1]);
        goalPosition=new Position(Details[2],Details[3]);
        rowsNum=Details[4];
        columnsNum=Details[5];
        array=new int[rowsNum][columnsNum];
        int row,col;
        for (row=0;row<rowsNum;row++)
            for(col=0;col<columnsNum;col++,i++)
                array[row][col]=arr[i];
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

    public byte[] toByteArray(){
        List ans=new ArrayList();
        byte[] finalAns;
        addDetail(ans,StartPosition.getRowIndex());
        addDetail(ans,StartPosition.getColumnIndex());
        addDetail(ans,goalPosition.getRowIndex());
        addDetail(ans,goalPosition.getColumnIndex());
        addDetail(ans,rowsNum);
        addDetail(ans,columnsNum);
        for(int i=0;i<array.length;i=i+1){
            for(int j=0;j<array[0].length;j=j+1){
                if(array[i][j]==0)
                    ans.add((byte)0);
                else ans.add((byte)1);
            }
        }
        finalAns=new byte[ans.size()];
        for(int i=0;i<ans.size();i=i+1)
            finalAns[i]=(byte)(ans.get(i));
        return  finalAns;
    }

    private void addDetail(List ans, int Detail) {
        int temp=Detail;
        while(temp>254){
            ans.add(((byte)(-127)));
            temp=temp-254;
        }
        if(temp>127)
            ans.add((byte)(127-temp));
        else ans.add((byte)temp);
        ans.add((byte)(-128));
    }


    public void print(){
        for(int i=0;i<array.length;i++) {
            for (int j = 0; j < array[0].length; j++){
                if(i== StartPosition.getRowIndex() && j==StartPosition.getColumnIndex())
                    System.out.print('S');
                else if(i== goalPosition.getRowIndex() && j==goalPosition.getColumnIndex())
                    System.out.print('E');
                else System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}
