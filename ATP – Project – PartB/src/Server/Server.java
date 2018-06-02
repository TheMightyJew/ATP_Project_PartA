package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
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
        threadPoolExecutor.setCorePoolSize(Integer.parseInt(Configurations.getProperty(Configurations.prop.threadsNum)));
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
        threadPoolExecutor.shutdown();
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
    static class Configurations{
        enum prop {threadsNum,solveAlgo,generateAlgo}
        private static Properties pro = new Properties();
        private Configurations() {
        }
        public static void setProperty(prop key, String value) {
            OutputStream output = null;

            try {
                output = new FileOutputStream("Resources/config.properties");

                pro.setProperty(key.name(), value);
                pro.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        public static String getProperty(prop key) {
            InputStream input = null;
            String value = null;
            try {
                input = new FileInputStream("Resources/config.properties");
                pro.load(input);
                value = pro.getProperty(key.name());
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return value;
        }
    }
}
