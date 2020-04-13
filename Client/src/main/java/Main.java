import main.java.utils.JSONReader;
import network.NetworkConfiguration;
import view.*;
import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        //test
        JSONReader jsonReader = new JSONReader();
        NetworkConfiguration test = jsonReader.getClientConfiguration();
        JFrame frame = new JFrame();
        MainView mainView = new MainView();
        frame.add(mainView);
        frame.setVisible(true);
    }
}
