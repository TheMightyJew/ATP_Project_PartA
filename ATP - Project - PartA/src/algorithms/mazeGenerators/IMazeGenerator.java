package algorithms.mazeGenerators;

public interface IMazeGenerator {
    public Maze generate(int rows, int column);
    public long measureAlgorithmTimeMillis(int rows, int column);
}
