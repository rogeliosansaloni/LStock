import java.io.IOException;

import network.Server;

public class ServerMain {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
