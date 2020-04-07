import model.JSONReader;
import network.NetworkConfiguration;

public class Main {
    public static void main (String[] args) {
        //test
        JSONReader jsonReader = new JSONReader();
        NetworkConfiguration test = jsonReader.getConfiguration();

    }
}
