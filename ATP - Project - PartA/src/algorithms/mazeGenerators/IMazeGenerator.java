package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int rows, int column);
    long measureAlgorithmTimeMillis(int rows, int column);
}
