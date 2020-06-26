package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import network.ServerConfiguration;

/**
 * JSONReader class
 */
public class JSONReader {
    private static final String PATH = "server/src/main/resources/config.json";
    private ServerConfiguration serverConfiguration;
    private Gson gson;

    /**
     * Constructor for the JSONReader
     */
    public JSONReader() {
        gson = new Gson();
    }

    /**
     * Reads the server configuration from the file
     * @return the server configuration information
     */
    public ServerConfiguration getServerConfiguration() {
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(PATH));
            serverConfiguration = gson.fromJson(reader, ServerConfiguration.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return serverConfiguration;
    }
}
