import java.io.IOException;
import model.entities.TunnelObject;
import network.NetworkManager;

public class ClientMain {
    public static void main (String[] args) {
        try {
            // Test for server-client connection
            TunnelObject testOjbect = new TunnelObject();
            NetworkManager.getInstance().sendTunnelObject(testOjbect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
