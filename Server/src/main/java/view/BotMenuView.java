package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotMenuView extends JFrame {
    private JButton jbCreate;
    private JButton jbRemove;
    private JButton jbShowList;
    private JButton jbEnable;
    private JButton jbDisable;

    public BotMenuView() {

    }

    public void registerControllers(ActionListener controller) {
        jbCreate.addActionListener(controller);
        jbRemove.addActionListener(controller);
        jbShowList.addActionListener(controller);
        jbEnable.addActionListener(controller);
        jbDisable.addActionListener(controller);
    }
}
