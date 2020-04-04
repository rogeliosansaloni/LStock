package network;

import model.entities.TunnelObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DedicatedServer extends Thread {
    private boolean isOn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Socket sClient;

    public DedicatedServer(Socket sClient) {
        this.sClient = sClient;
    }

    public void stopServerConnection() {
        this.isOn = true;
        this.start();
    }

    public void startServerConnection() {
        this.isOn = false;
        this.interrupt();
    }

    public void run() {
        try {
            // Create the communication channels
            objectIn = new ObjectInputStream(sClient.getInputStream());
            objectOut = new ObjectOutputStream(sClient.getOutputStream());

            while(isOn) {
                TunnelObject tunnelObject = (TunnelObject) objectIn.readObject();

                // When receiving tunnel object, we need to check its type
                //if (tunnelObject instanceof AuthenticationInfo) {
                //    // Handle object
                //}
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
