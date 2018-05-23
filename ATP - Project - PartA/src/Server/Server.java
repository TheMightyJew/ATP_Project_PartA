package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.*;

public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;

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

            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void stop() {
        stop = true;
    }
}
