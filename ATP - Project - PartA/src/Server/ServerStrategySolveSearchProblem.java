package Server;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            byte[] compressedMaze = (byte[]) fromClient.readObject();
            InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
            byte[] decompressedMaze = new byte[compressedMaze.length*10];
            is.read(decompressedMaze);
            Maze maze=new Maze(decompressedMaze);
            SearchableMaze sMaze=new SearchableMaze(maze);
            BreadthFirstSearch search=new BreadthFirstSearch();
            Solution ans=search.solve(sMaze);

            toClient.writeObject(ans);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
