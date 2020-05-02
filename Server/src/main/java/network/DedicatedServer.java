package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.entities.AuthenticationInfo;
import model.entities.TunnelObject;
import model.entities.UserProfileInfo;
import model.managers.StockManager;
import utils.UserMapperImpl;

public class DedicatedServer extends Thread {
    private boolean isOn;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sClient;
    private StockManager stockModel;
    private UserMapperImpl mapper;

    /**
     * DedicatedServer constructor
     * @param sClient client socket
     */
    public DedicatedServer(Socket sClient) {
        this.sClient = sClient;
        this.stockModel = new StockManager();
        this.mapper = new UserMapperImpl();
    }

    /**
     * Stops the connection to the server
     */
    public void stopServerConnection() {
        this.isOn = false;
        this.interrupt();
    }

    /**
     * Starts the connection to the server
     */
    public void startServerConnection() {
        this.isOn = true;
        this.start();
    }

    /**
     * Main dedicated server thread that sends and receives information to and from the client side. It handles
     * each object received.
     */
    public void run() {
        try {
            // Create the communication channels
            ois = new ObjectInputStream(sClient.getInputStream());
            oos = new ObjectOutputStream(sClient.getOutputStream());

            while (isOn) {
                TunnelObject tunnelObject = (TunnelObject) ois.readObject();

                if (tunnelObject instanceof AuthenticationInfo) {
                    AuthenticationInfo info = ((AuthenticationInfo) tunnelObject);
                    // Check if the object is for registering users
                    if (info.getAction().equals("register")) {
                        AuthenticationInfo authInfoRegister =
                                stockModel.registerUser(mapper.authenticationInfoToUser((AuthenticationInfo) tunnelObject));
                        oos.writeObject(authInfoRegister);
                    } else {
                        // Check if we need user validation for login
                        if (info.getAction().equals("login")) {
                            AuthenticationInfo authInfoLogin =
                                    stockModel.validateUser(mapper.authenticationInfoToUser((AuthenticationInfo) tunnelObject));
                            oos.writeObject(authInfoLogin);
                        }
                    }
                }
                // Check if the object is for updating the users balance or description
                if (tunnelObject instanceof UserProfileInfo) {
                    UserProfileInfo userInfo = ((UserProfileInfo) tunnelObject);
                    if (userInfo.getAction().equals("balance")) {
                        UserProfileInfo userProfileInfo = stockModel.updateUserBalance(mapper.userProfileInfoToUser((UserProfileInfo) tunnelObject));
                        oos.writeObject(userProfileInfo);
                    }
                    else {
                        if (userInfo.getAction().equals("information")) {
                            UserProfileInfo userProfileInfo = stockModel.updateUserInformation(mapper.userProfileInfoToUser((UserProfileInfo) tunnelObject));
                            oos.writeObject(userProfileInfo);
                        }
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
