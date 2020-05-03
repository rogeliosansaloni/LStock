package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotsListView extends JPanel {
    private JTable jtBotsList;
    private JButton jbReturn;

    public BotsListView() {

    }

    public void registerController(ActionListener controller) {
        this.jbReturn.addActionListener(controller);
        this.jbReturn.setActionCommand("return");
    }
}
