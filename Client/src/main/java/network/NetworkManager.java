package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.LoginController;
import controller.MainController;
import controller.RegisterController;
import model.entities.AuthenticationInfo;
import model.entities.TunnelObject;
import utils.JSONReader;
import view.LoginView;
import view.MainView;
import view.RegisterView;

public class NetworkManager extends Thread {
  private Socket serverSocket;
  private ObjectInputStream ois;
  private ObjectOutputStream oos;
  private boolean running;
  private static NetworkManager instance = null;
  private NetworkConfiguration configuration;
  private MainController mainController;
  private LoginController loginController;
  private RegisterController registerController;
  private MainView mainView;
  private RegisterView registerView;
  private LoginView loginView;

  /**
   * Represents a Singleton
   * @return single and shared global instance
   */
  public synchronized static NetworkManager getInstance () {
    try {
      if (instance == null) {
        instance = new NetworkManager();
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private NetworkManager() throws IOException {
    // Get Network configuration from JSON
    JSONReader jsonReader = new JSONReader();
    configuration = jsonReader.getClientConfiguration();
    // Set up the connection to the server
    this.serverSocket = new Socket(configuration.getIp(), configuration.getPort()); // pass ip and port from NetworkConfiguration
    oos = new ObjectOutputStream(this.serverSocket.getOutputStream());
    oos.flush();
    ois = new ObjectInputStream(this.serverSocket.getInputStream());
  }

  public void startServerConnection (RegisterController registerController) {
    //Set up controllers
    this.registerController = registerController;
    running = true;
    start();
  }

  public void stopServerConnection () {
    running = false;
    interrupt();
  }

  public void sendTunnelObject(TunnelObject object) throws IOException {
    oos.writeObject(object);
  }

  public void sendAuthentificationInformation (TunnelObject object) throws IOException {
    oos.writeObject(object);
  }

  @Override
  public void run () {
    try {
      while (running) {
        System.out.println("Waiting for object to be received...");
        TunnelObject received = (TunnelObject) ois.readObject();

        if (received instanceof AuthenticationInfo) {
          AuthenticationInfo info = ((AuthenticationInfo)received);
          if (info.getAction().equals("register")) {
            if(info.isValidated()){
              registerController.closeRegisterView();
              loginView = new LoginView();
              loginController = new LoginController(loginView);
              loginView.loginController(loginController);
              loginView.setVisible(true);
            }
            else {
              registerController.sendErrorMessage(info.getResponseType());
            }
          }
          if (info.getAction().equals("login")) {

          }
        }
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
