package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.entities.AuthenticationInfo;
import model.entities.TunnelObject;
import model.managers.StockManager;
import utils.UserMapperImpl;

public class DedicatedServer extends Thread {
    private boolean isOn;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sClient;

    public DedicatedServer(Socket sClient) {
        this.sClient = sClient;
    }

    public void stopServerConnection() {
        this.isOn = false;
        this.interrupt();
    }

    public void startServerConnection() {
        this.isOn = true;
        this.start();
    }

    public void run() {
        try {
            // Create the communication channels
            ois = new ObjectInputStream(sClient.getInputStream());
            oos = new ObjectOutputStream(sClient.getOutputStream());

            while(isOn) {
                TunnelObject tunnelObject = (TunnelObject) ois.readObject();

                if (tunnelObject instanceof AuthenticationInfo) {
                    if (((AuthenticationInfo) tunnelObject).getAction().equals("register")) {
                        StockManager model = new StockManager();
                        UserMapperImpl mapper = new UserMapperImpl();
                        AuthenticationInfo info = model.registerUser(mapper.authenticationInfoToUser((AuthenticationInfo) tunnelObject));
                        oos.writeObject(info);
                    }
                    if (((AuthenticationInfo) tunnelObject).getAction().equals("login")) {

                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            stopServerConnection();
            System.out.println("Stopped client connection to the server...");
            e.printStackTrace();

        }

    }
}
