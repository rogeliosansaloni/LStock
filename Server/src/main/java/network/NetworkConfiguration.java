package network;

public class NetworkConfiguration {
    private String ip;
    private int port;
    private String db_ip;
    private int db_port;
    private String db_name;
    private String db_user;
    private String db_password;

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getDb_ip() {
        return db_ip;
    }

    public int getDb_port() {
        return db_port;
    }

    public String getDb_name() {
        return db_name;
    }

    public String getDb_user() {
        return db_user;
    }

    public String getDb_password() {
        return db_password;
    }
}
