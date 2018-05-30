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
            byte[] compressedMaze = (byte[]) ((Maze)fromClient.readObject()).toByteArray();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            File dir=new File(tempDirectoryPath);
            boolean done=false;
            for(File fileEntry : dir.listFiles()){
                FileInputStream fis=new FileInputStream(fileEntry);
                ObjectInputStream ois = new ObjectInputStream(fis);
                byte[] key=(byte[])ois.readObject();
                Solution value=(Solution)ois.readObject();
                if(key.equals(compressedMaze)){
                    toClient.writeObject(value);
                    done=true;
                    ois.close();
                    break;
                }
            }
            if(!done){
                InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                byte[] decompressedMaze = new byte[compressedMaze.length*10];
                is.read(decompressedMaze);
                Maze maze=new Maze(decompressedMaze);
                SearchableMaze sMaze=new SearchableMaze(maze);
                BreadthFirstSearch search=new BreadthFirstSearch();
                Solution ans=search.solve(sMaze);
                toClient.writeObject(ans);
                String newFile="Sol"+Server.getSolvedNum();
                FileOutputStream fos=new FileOutputStream(newFile);
                ObjectOutputStream oos=new ObjectOutputStream(fos);
                oos.writeObject(compressedMaze);
                oos.flush();
                oos.writeObject(ans);
                oos.flush();
                oos.close();
            }
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
