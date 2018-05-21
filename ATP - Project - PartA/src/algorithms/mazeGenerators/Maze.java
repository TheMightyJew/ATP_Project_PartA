package algorithms.mazeGenerators;
import javafx.util.Pair;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
