package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int rows, int column);
    default long measureAlgorithmTimeMillis(int rows, int columns){
        long time=System.currentTimeMillis();
        generate(rows,columns);
        long ans=time-System.currentTimeMillis();
        return ans;
    }
}
