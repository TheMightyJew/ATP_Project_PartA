package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    private int row;
    private int column;

    public int getRowIndex() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Position(int row, int column) {

        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "{"+row+","+column+'}';
    }
}
