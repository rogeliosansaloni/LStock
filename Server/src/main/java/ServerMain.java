import java.io.IOException;

import network.DBConnector;
import network.Server;
import network.ServerConfiguration;

public class ServerMain {
    public static void main (String[] args) {
        try {
            Server server = new Server();
            server.startServer();

            DBConnector connector = new DBConnector(server.getServerConfiguration().getDbIp(), server.getServerConfiguration().getDbUser(), server.getServerConfiguration().getDbPassword(),
                                                    server.getServerConfiguration().getDbName(),server.getServerConfiguration().getDbPort());
            connector.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
