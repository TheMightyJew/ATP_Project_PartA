package algorithms.search;

import algorithms.mazeGenerators.Maze;

public class MazeState extends AState{
    private int x;
    private int y;
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public MazeState(int X, int Y,AState cameFrom) {
        super();
        x=X;
        y=Y;
    }

    public boolean isGoalState(ISearchable game) {
        if(game instanceof Maze && ((Maze)game).getGoalPosition().getRowIndex()==getX() && ((Maze)game).getGoalPosition().getColumnIndex()==getY())
            return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }

    @Override
    public String toString() {
        return (x+","+y);
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
