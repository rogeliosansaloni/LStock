package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import controller.SharesListController;
import utils.JSONReader;
import view.SharesListView;


public class Server extends Thread {
    private String ip;
    private int port;
    private ServerSocket sSocket;
    private boolean isOn;
    private LinkedList<DedicatedServer> clients;
    private ServerConfiguration serverConfiguration;
    private SharesListController sharesListController;
    private SharesListView sharesListView;

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
        initShareView();
        isOn = true;
        this.start();
    }

    public void stopServer() {
        // Stop main server thread
        isOn = false;
        stopListening();
        //model.disconnectFromDatabase();
        this.interrupt();
    }

    public void initShareView () {
        this.sharesListView = new SharesListView();
        this.sharesListController = new SharesListController(sharesListView);
        sharesListView.registerController(sharesListController);
        this.sharesListView.registerController(this.sharesListController);
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

    public void stopListening() {
        // Paramos todos los servidores dedicados creados cuando ya no atendemos más peticiones
        for (DedicatedServer client : clients) {
            client.stopServerConnection();
        }
    }

    public ServerConfiguration getServerConfiguration() {
        return serverConfiguration;
    }
}
