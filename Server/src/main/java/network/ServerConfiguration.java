package network;

public class ServerConfiguration {
    private String ip;
    private int port;
    private String dbIp;
    private int dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getDbIp() {
        return dbIp;
    }

    public int getDbPort() {
        return dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
