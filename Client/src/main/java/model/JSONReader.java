package model;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import network.NetworkConfiguration;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONReader {
    private static final String PATH = "client/src/main/resources/config.json";
    private NetworkConfiguration configuration;
    private Gson gson;

    public JSONReader () {
        gson = new Gson();
    }

    public NetworkConfiguration getConfiguration () {
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(PATH));
            configuration = gson.fromJson(reader, NetworkConfiguration.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return configuration;
    }
}
