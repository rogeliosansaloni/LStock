import java.io.IOException;

import network.DBConnector;
import network.Server;
import network.ServerConfiguration;

public class ServerMain {
    public static void main (String[] args) {
        try {
            Server server = new Server();
            server.startServer();

            ServerConfiguration config = new ServerConfiguration();
            DBConnector connector = new DBConnector("ec2-15-236-105-133.eu-west-3.compute.amazonaws.com","root","admin","LStock",3306);
            //Intente conectarlo con ServerConfiguration para no hardcodearlo como decis pero no me dejaba conectarme asi que lo puse a mano
            //DBConnector connector = new DBConnector("",config.getDbUser(),config.getDbPassword(),"LStock",3306);
            connector.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
