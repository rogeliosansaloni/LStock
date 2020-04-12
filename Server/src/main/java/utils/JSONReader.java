package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import network.ServerConfiguration;

public class JSONReader {
    private static final String PATH = "server/src/main/resources/config.json";
    private ServerConfiguration serverConfiguration;
    private Gson gson;

    public JSONReader() {
        gson = new Gson();
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
