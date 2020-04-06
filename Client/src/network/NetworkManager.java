package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkManager extends Thread {
  private Socket serverSocket;
  private ObjectInputStream obin;
  private ObjectOutputStream obout;
  private boolean running;
  private static NetworkManager instance = null;
  // TODO: Specify the controllers as attributes

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
    // TODO: Implement JsonReader to read from config json
    // Set up the connection to the server
    this.running = false;
    this.serverSocket = new Socket(); // pass ip and port from NetworkConfiguration
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
}
