package test;
import algorithms.mazeGenerators.*;
public class RunMazeGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s",
                mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(100/*rows*/, 100/*columns*/);
        // prints the maze
        maze.print();
// get the maze entrance
        Position startPosition = maze.getStartPosition();
// print the position
        System.out.println(String.format("Start Position: %s",
                startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s",
                maze.getGoalPosition()));
    }
}
/*package test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import javafx.util.Pair;

public class RunMazeGenerator {
    public static void main(String[] args) {
        SimpleMazeGenerator newG=new SimpleMazeGenerator();
        MyMazeGenerator newM=new MyMazeGenerator();
        System.out.println(newG.measureAlgorithmTimeMillis(1000,1000));
        System.out.println(newM.measureAlgorithmTimeMillis(1000,1000));
        Maze newone=newM.generate(10,10);
        int[][] array=newone.getArray();
        printArray(array);
        Pair<Integer,Integer> peri=new Pair<>(2,5);
        System.out.println(peri.toString());
        peri=check(peri);
        System.out.println(peri.toString());
    }
    private static void printArray(int[][] array){
        for(int i=0;i<array.length;i++) {
            for (int j = 0; j < array[0].length; j++){
                if(array[i][j]==0)
                    System.out.print((char)27 + "[31m" +array[i][j]);
                else
                    System.out.print((char)27 + "[34m" +array[i][j]);
            }
            System.out.println();
        }
    }
    private static Pair<Integer,Integer> check(Pair<Integer,Integer> peri){
        Pair<Integer,Integer> newone=new Pair<>(0,0);
        newone=new Pair<>(peri.getKey()*2,peri.getValue()*2);
        return newone;
    }
}*/
