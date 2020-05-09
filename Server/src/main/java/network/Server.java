package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import controller.MainController;
import utils.JSONReader;
import view.MainView;

public class Server extends Thread {
    private String ip;
    private int port;
    private ServerSocket sSocket;
    private boolean isOn;
    private LinkedList<DedicatedServer> clients;
    private ServerConfiguration serverConfiguration;
    private MainView mainView;
    private MainController mainController;

    public Server() throws IOException {
        initServerConfiguration();
        this.isOn = false;
        this.sSocket = new ServerSocket(port);
        this.clients = new LinkedList<DedicatedServer>();
    }

    /**
     * Initializes servers configuration
     */
    private void initServerConfiguration() {
        JSONReader jsonReader = new JSONReader();
        this.serverConfiguration = jsonReader.getServerConfiguration();
        this.ip = serverConfiguration.getIp();
        this.port = serverConfiguration.getPort();
    }

    /**
     * Starts the connection and initializes the servers main view
     */
    public void startServer() {
        initMainView();
        // Start main server thread
        isOn = true;
        this.start();
    }

    /**
     * Stops connection
     */
    public void stopServer() {
        // Stop main server thread
        isOn = false;
        stopListening();
        this.interrupt();
    }

    /**
     * Initializes main view
     */
    public void initMainView () {
        this.mainView = new MainView();
        this.mainController = new MainController(mainView);
        mainView.registerController(mainController);
        this.mainView.registerHomeController(this.mainController.getHomeController());
        this.mainView.registerBotController(this.mainController.getBotMenuController());
        this.mainView.setVisible(true);
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
