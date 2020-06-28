package network;

import com.google.gson.annotations.SerializedName;

/**
 * Server configuration class
 */
public class ServerConfiguration {
    private String ip;
    private int port;

    @SerializedName("db_ip")
    private String dbIp;

    @SerializedName("db_port")
    private int dbPort;

    @SerializedName("db_name")
    private String dbName;

    @SerializedName("db_user")
    private String dbUser;

    @SerializedName("db_password")
    private String dbPassword;

    // Getters
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
