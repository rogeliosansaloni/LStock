package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import main.java.model.entities.TunnelObject;
import main.java.utils.JSONReader;

public class NetworkManager extends Thread {
  private Socket serverSocket;
  private ObjectInputStream obin;
  private ObjectOutputStream obout;
  private boolean running;
  private static NetworkManager instance = null;
  private NetworkConfiguration configuaration;
  // TODO: Specify the controllers as attributes
  // TODO: Specify the views to be shown

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
    configuaration = jsonReader.getClientConfiguration();

    // Set up the connection to the server
    this.running = false;
    this.serverSocket = new Socket(configuaration.getIp(), configuaration.getPort()); // pass ip and port from NetworkConfiguration
    obout = new ObjectOutputStream(this.serverSocket.getOutputStream());
    obout.flush();
    obin = new ObjectInputStream(this.serverSocket.getInputStream());

    this.start();
  }

  public void startServerConnection () {
    running = true;
    start();
  }

  public void stopServerConnection () {
    running = false;
    interrupt();
  }

  @Override
  public void run () {
    try {
      while (running) {
        System.out.println("Waiting for object to be received...");
        TunnelObject recibido = (TunnelObject) obin.readObject();
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
