package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable {
    private Maze mazi;
    public SearchableMaze(Maze maze){
        mazi=maze;
    }

    public Maze getMazi() {
        return mazi;
    }

    public void setMazi(Maze mazi) {
        this.mazi = mazi;
    }

    @Override
    public AState getStartState() {
        return new MazeState(mazi.getStartPosition().getRowIndex(),mazi.getStartPosition().getColumnIndex());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(mazi.getGoalPosition().getRowIndex(),mazi.getGoalPosition().getColumnIndex());
    }

    @Override
    public List<AState> getAllSuccessors(AState stati) {
        List<AState> ans=new ArrayList<>();
        int[][] array=mazi.getArray();
        int x=stati.getX();
        int y=stati.getY();
        if(x+1>=0 && x+1<array.length && y>=0 && y<array.length && array[x+1][y]==0)
            ans.add(new MazeState(x+1,y));
        if(x-1>=0 && x-1<array.length && y>=0 && y<array.length && array[x-1][y]==0)
            ans.add(new MazeState(x-1,y));
        if(x>=0 && x<array.length && y+1>=0 && y+1<array.length && array[x][y+1]==0)
            ans.add(new MazeState(x,y+1));
        if(x>=0 && x<array.length && y-1>=0 && y-1<array.length && array[x][y-1]==0)
            ans.add(new MazeState(x,y-1));
        return ans;
    }
}
