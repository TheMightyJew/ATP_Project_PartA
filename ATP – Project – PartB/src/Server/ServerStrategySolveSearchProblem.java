package Server;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Maze maze=((Maze)fromClient.readObject());
            byte[] byteMaze = (byte[]) maze.toByteArray();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            File dir=new File(tempDirectoryPath);
            boolean done=false;
            for(File fileEntry : dir.listFiles()){
                if(fileEntry.isDirectory() || !fileEntry.getName().contains("Sol"))
                    continue;
                FileInputStream fis=new FileInputStream(fileEntry);
                ObjectInputStream ois = new ObjectInputStream(fis);
                byte[] key=(byte[])ois.readObject();
                Solution value=(Solution)ois.readObject();
                if(CheckIfByteArrEquals(key,byteMaze)){
                    toClient.writeObject(value);
                    done=true;
                    ois.close();
                    break;
                }
            }
            if(!done){
                SearchableMaze sMaze=new SearchableMaze(maze);
                ISearchingAlgorithm search = (ISearchingAlgorithm) Class.forName(Server.Configurations.getProperty(Server.Configurations.prop.solveAlgo)).getConstructor().newInstance();
                Solution ans=(Solution)search.solve(sMaze);
                toClient.writeObject(ans);
                String newFile=tempDirectoryPath+"/Sol"+Server.getSolvedNum();
                FileOutputStream fos=new FileOutputStream(newFile);
                ObjectOutputStream oos=new ObjectOutputStream(fos);
                oos.writeObject(maze.toByteArray());
                oos.flush();
                oos.writeObject(ans);
                toClient.writeObject(ans);
                oos.flush();
                oos.close();
            }
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean CheckIfByteArrEquals(byte[] key, byte[] byteMaze) {
        if(key.length!=byteMaze.length)
            return false;
        for(int i=0 ; i<key.length; i++){
            if(key[i] != byteMaze[i])
                return false;
        }
        return true;
    }
}
