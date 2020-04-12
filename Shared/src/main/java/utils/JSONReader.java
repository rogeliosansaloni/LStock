package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import network.NetworkConfiguration;
import network.ServerConfiguration;

public class JSONReader {
    private static final String PATH = "client/src/main/resources/config.json";
    private NetworkConfiguration clientConfiguration;
    private ServerConfiguration serverConfiguration;
    private Gson gson;

    public JSONReader () {
        gson = new Gson();
    }

    public NetworkConfiguration getClientConfiguration () {
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(PATH));
            clientConfiguration = gson.fromJson(reader, ServerConfiguration.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return clientConfiguration;
    }

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
