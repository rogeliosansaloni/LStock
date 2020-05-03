package view;

import model.entities.Bot;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotsListView extends JPanel {
    private JTable jtBotsList;
    private JButton jbReturn;

    public BotsListView() {
        // TODO: Implement list view
    }

    public void registerController(ActionListener controller) {
        this.jbReturn.addActionListener(controller);
        this.jbReturn.setActionCommand("cancelBotList");
    }

    public void showBotsInTable(ArrayList<Bot> bots) {
        // TODO: Show bots in table
    }
}
