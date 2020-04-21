package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.entities.AuthenticationInfo;
import model.entities.TunnelObject;
import model.entities.User;
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
                    AuthenticationInfo info = ((AuthenticationInfo) tunnelObject);
                    if (info.getAction().equals("register")) {
                        StockManager model = new StockManager();
                        UserMapperImpl mapper = new UserMapperImpl();
                        AuthenticationInfo info = model.registerUser(mapper.authenticationInfoToUser((AuthenticationInfo) tunnelObject));
                        oos.writeObject(info);
                    }

                }
                // When receiving tunnel object, we need to check its type
                if (tunnelObject instanceof AuthenticationInfo) {
                    StockManager model = new StockManager();
                    UserMapperImpl mapper = new UserMapperImpl();
                    AuthenticationInfo info = model.validateUser(mapper.authenticationInfoToUser((AuthenticationInfo) tunnelObject));
                    oos.writeObject(info);

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
