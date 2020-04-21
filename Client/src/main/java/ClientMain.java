import java.io.IOException;

import controller.LoginController;
import controller.RegisterController;
import model.entities.TunnelObject;
import network.NetworkConfiguration;
import network.NetworkManager;
import view.LoginView;
import view.MainView;
import view.RegisterView;

import javax.swing.*;

public class ClientMain {
    public static void main (String[] args) {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.loginController(loginController);
        loginView.setVisible(true);
        RegisterView registerView = new RegisterView();
        RegisterController registerController = new RegisterController(registerView);
        registerView.registerController(registerController);
        registerView.setVisible(true);

        // Start client's connection to the server
        NetworkManager.getInstance().startServerConnection(registerController);

        /*try {
            // Test for server-client connection
            TunnelObject testOjbect = new TunnelObject();
            NetworkManager.getInstance().sendTunnelObject(testOjbect);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //test

    }
}
