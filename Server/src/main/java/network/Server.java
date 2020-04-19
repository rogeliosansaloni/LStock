package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import utils.JSONReader;


public class Server extends Thread {
    private String ip;
    private int port;
    private Server server;
    private ServerConfiguration config;
    private ServerSocket sSocket;
    private boolean isOn;
    private LinkedList<DedicatedServer> clients;
    private DBConnector dbConnector;


    public Server() throws IOException {
        initServerConfiguration();
        this.isOn = false;
        this.sSocket = new ServerSocket(port);
        this.clients = new LinkedList<DedicatedServer>();
    }

    public void connectDBconnector(){
        this.dbConnector = new DBConnector(config.getDbIp(), config.getDbName(), config.getDbPassword(), config.getDbUser(), config.getPort());
        this.dbConnector.connect();
    }

    private void initServerConfiguration() {
        JSONReader jsonReader = new JSONReader();
        config = jsonReader.getServerConfiguration();
        this.ip = config.getIp();
        this.port = config.getPort();
    }

    public void startServer() {
        // Start main server thread
        isOn = true;
        this.start();
        connectDBconnector();
    }

    public void stopServer() {
        // Stop main server thread
        isOn = false;
        //model.disconnectFromDatabase();
        this.interrupt();
    }

    public void run() {
        while(isOn) {
            try {
                // Wait for petitions to accept them
                // Block the execution
                System.out.println("Waiting for clients to connect...");
                Socket clientSocket = sSocket.accept();

                // Create dedicated server to attend to the client
                DedicatedServer client = new DedicatedServer(clientSocket);
                clients.add(client);

                System.out.println("Client has connected correctly!");

                // Start dedicated server for the client
                client.startServerConnection();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void stopListening() {
        // Paramos todos los servidores dedicados creados cuando ya no atendemos más peticiones
        for (DedicatedServer client : clients) {
            client.stopServerConnection();
        }
    }


}
