package Server;
import java.io.*;
import java.util.ArrayList;

import IO.*;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
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
            IMazeGenerator geni = (IMazeGenerator) Class.forName(Server.Configurations.getProperty(Server.Configurations.prop.generateAlgo)).getConstructor().newInstance();
            Maze maze=geni.generate(al[0],al[1]);
            byte[] toStr=maze.toByteArray();
            ByteArrayOutputStream tmp=new ByteArrayOutputStream();
            MyCompressorOutputStream comp=new MyCompressorOutputStream(tmp);
            comp.write(toStr);
            toClient.writeObject(tmp.toByteArray());
            comp.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
