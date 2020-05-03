package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotsEditView extends JPanel {
    private JComboBox<String> jcbBot;
    private JComboBox<String> jcbCompany;
    private JButton jbActivate;
    private JButton jbDeactivate;

    public BotsEditView() {
        // TODO: Implement edit view
    }

    public String getBotName() {
        return jcbBot.getSelectedItem().toString();
    }

    public String getCompany() {
        return jcbCompany.getSelectedItem().toString();
    }

    public void registerController(ActionListener controller) {
        this.jbActivate.addActionListener(controller);
        this.jbActivate.setActionCommand("activateBot");
        this.jbDeactivate.addActionListener(controller);
        this.jbDeactivate.setActionCommand("deactivateBot");
    }
}
