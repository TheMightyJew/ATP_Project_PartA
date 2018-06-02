package Server;
import java.io.*;
import java.util.ArrayList;

import IO.*;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;

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
            ByteArrayOutputStream tmp=new ByteArrayOutputStream();
            MyCompressorOutputStream comp=new MyCompressorOutputStream(tmp);
            comp.write(toStr);
            toClient.writeObject(tmp.toByteArray());
            comp.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
