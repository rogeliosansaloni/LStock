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
}
