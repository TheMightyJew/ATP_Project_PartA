package algorithms.search;

import algorithms.mazeGenerators.Maze;

public class MazeState extends AState{

    public MazeState(int x, int y) {
        super(x, y);
    }
    public boolean isGoalState(ISearchable game) {
        if(game instanceof Maze && ((Maze)game).getGoalPosition().getRowIndex()==x && ((Maze)game).getGoalPosition().getColumnIndex()==y)
            return true;
        return false;
    }
}
