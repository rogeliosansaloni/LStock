package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotsCreateView extends JPanel {
    private JTextField jtfName;
    private JComboBox<String> jcbCompany;
    private JTextField jtfPercentage;
    private JTextField jtfActivation;
    private JButton jbCreate;
    private JButton jbCancel;

    public BotsCreateView() {

    }

    public void registerController(ActionListener controller) {
        this.jbCreate.addActionListener(controller);
        this.jbCreate.setActionCommand("createNewBot");
        this.jbCancel.addActionListener(controller);
        this.jbCancel.setActionCommand("cancelBotCreation");
    }

    public String getBotName() {
        return jtfName.getText();
    }

    public String getCompanyMame() {
        return jtfName.getText();
    }

    public float getPercentage() {
        return Float.parseFloat(jtfPercentage.getText());
    }

    public float getActivation() {
        return Float.parseFloat(jtfActivation.getText());
    }
}
