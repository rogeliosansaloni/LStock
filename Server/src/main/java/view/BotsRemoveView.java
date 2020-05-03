package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotsRemoveView extends JPanel {
    private JComboBox<String> jcbBot;
    private JComboBox<String> jcbCompany;
    private JLabel jlStatus;
    private JButton jbRemove;
    private JButton jbCancel;

    public BotsRemoveView() {
        // TODO: Implement remove view
    }

    public void registerController(ActionListener controller) {
        this.jbRemove.addActionListener(controller);
        this.jbRemove.setActionCommand("removeExistingBot");
        this.jbCancel.addActionListener(controller);
        this.jbCancel.setActionCommand("cancelBotRemoval");
    }

    public String getBotName() {
        return jcbBot.getSelectedItem().toString();
    }
}
