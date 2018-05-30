package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.*;

public class Server  implements Serializable{
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private static int solvedNum=0;

    public static int getSolvedNum() {
        return solvedNum;
    }

    public static void upSolvedNum(int solvedNum) {
        Server.solvedNum++;
    }

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        new Thread(() -> { runServer(); }).start();
    }

    private void runServer() {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        threadPoolExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            while (!stop) {
                try {
                    Socket clientSocket = server.accept(); // blocking call
                    threadPoolExecutor.submit(() -> { handleClient(clientSocket); });
                }
                catch (SocketTimeoutException e) {
                    System.out.println("SocketTimeout - No clients pending!");
                }
            }
            server.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            InputStream input=clientSocket.getInputStream();
            OutputStream output=clientSocket.getOutputStream();
            serverStrategy.serverStrategy(input, output);
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void stop() {
        stop = true;
    }
}
