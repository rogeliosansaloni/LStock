import java.io.IOException;

import model.entities.Share;
import network.Server;
import view.SharesListView;

public class ServerMain {
    public static void main(String[] args) {
        /*try {
            Server server = new Server();
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        SharesListView sharesListView = new SharesListView();
        sharesListView.setVisible(true);
    }
}
