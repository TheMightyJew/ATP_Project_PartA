package Server;
import java.io.*;
import java.util.ArrayList;

import IO.*;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            int[] al = (int[])fromClient.readObject();
            AMazeGenerator geni=new MyMazeGenerator();
            Maze maze=geni.generate(al[0],al[1]);
            byte[] toStr=maze.toByteArray();
            MyCompressorOutputStream comp=new MyCompressorOutputStream(toClient);
            comp.write(toStr);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
