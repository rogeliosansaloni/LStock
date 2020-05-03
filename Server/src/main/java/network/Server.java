package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import utils.JSONReader;


public class Server extends Thread {
    private String ip;
    private int port;
    private ServerSocket sSocket;
    private boolean isOn;
    private LinkedList<DedicatedServer> clients;
    private ServerConfiguration serverConfiguration;

    public Server() throws IOException {
        this.serverConfiguration = new ServerConfiguration();
        initServerConfiguration();
        this.isOn = false;
        this.sSocket = new ServerSocket(port);
        this.clients = new LinkedList<DedicatedServer>();
    }

    private void initServerConfiguration() {
        JSONReader jsonReader = new JSONReader();
        this.serverConfiguration = jsonReader.getServerConfiguration();
        this.ip = serverConfiguration.getIp();
        this.port = serverConfiguration.getPort();
    }

    public void startServer() {
        // Start main server thread
        isOn = true;
        this.start();
    }

    public void stopServer() {
        // Stop main server thread
        isOn = false;
        stopListening();
        this.interrupt();
    }

    public void run() {
        while (isOn) {
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

    /**
     * Stop listening and stop server connection for each dedicated server
     */
    public void stopListening() {
        // Stop all dedicated servers when we are not listening to petitions
        for (DedicatedServer client : clients) {
            client.stopServerConnection();
        }
    }
}
