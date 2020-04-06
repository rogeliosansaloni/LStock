package network;

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
    if (instance == null) {
      instance = new NetworkManager();
    }
    return instance;
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
