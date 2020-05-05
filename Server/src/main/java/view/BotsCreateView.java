package view;

//import sun.tools.jps.Jps;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BotsCreateView extends JPanel {
   //private JTextField jtfName;
   //private JComboBox<String> jcbCompany;
    private JTextField[] jtField;
    private JTextField jtfPercentage;
    private JTextField jtfActivation;
    private JButton jbCreate;
    private JButton jbCancel;

    public BotsCreateView() {
        // TODO: Implement create view

        Font font = new Font("Segoe UI", Font.ITALIC, 20);
        jtField = new JTextField[4];

        //Name
        jtField[0] = new JTextField("Name");
        jtField[0].setBorder(null);
        jtField[0].setFont(font);
        jtField[0].setForeground(Color.GRAY);
        jtField[0].setBackground(Color.WHITE);
        jtField[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtField[0].getText().equals("Name")) {
                    jtField[0].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtField[0].getText().equals("")) {
                    jtField[0].setText("Name");
                }
            }
        });

        //Company
        jtField[1] = new JTextField("Company");
        jtField[1].setBorder(null);
        jtField[1].setFont(font);
        jtField[1].setForeground(Color.GRAY);
        jtField[1].setBackground(Color.WHITE);
        jtField[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtField[0].getText().equals("Company")) {
                    jtField[0].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtField[0].getText().equals("")) {
                    jtField[0].setText("Company");
                }
            }
        });

        //Buy Percentage
        jtField[2] = new JTextField("Buy Percentage");
        jtField[2].setBorder(null);
        jtField[2].setFont(font);
        jtField[2].setForeground(Color.GRAY);
        jtField[2].setBackground(Color.WHITE);
        jtField[2].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtField[0].getText().equals("Buy Percentage")) {
                    jtField[0].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtField[0].getText().equals("")) {
                    jtField[0].setText("Buy Percentage");
                }
            }
        });

        //Activation Time
        jtField[3] = new JTextField("Activation Time");
        jtField[3].setBorder(null);
        jtField[3].setFont(font);
        jtField[3].setForeground(Color.GRAY);
        jtField[3].setBackground(Color.WHITE);
        jtField[3].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtField[0].getText().equals("Activation Time")) {
                    jtField[0].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtField[0].getText().equals("")) {
                    jtField[0].setText("Activation Time");
                }
            }
        });


        Font fontButton = new Font("Segoe UI Semibold", Font.PLAIN, 20);

        //Create Button
        jbCreate = new JButton("CREATE");
        jbCreate.setBackground(Color.GREEN);
        jbCreate.setFont(fontButton);
        jbCreate.setForeground(Color.BLACK);
        jbCreate.setPreferredSize(new Dimension(150,50));

        //Cancel Button
        jbCancel = new JButton("CANCEL");
        jbCancel.setBackground(Color.RED);
        jbCancel.setFont(fontButton);
        jbCancel.setForeground(Color.BLACK);
        jbCancel.setPreferredSize(new Dimension(150,50));

        JPanel jpGeneral = new JPanel(new BorderLayout());
        JPanel  jpCenter = new JPanel(new GridLayout(5, 1, 0, 30));
        JPanel jpSouth = new JPanel(new FlowLayout());

        jpSouth.add(jbCreate);
        jpSouth.add(jbCancel);

        jpCenter.add(jtField[0]);
        jpCenter.add(jtField[1]);
        jpCenter.add(jtField[2]);
        jpCenter.add(jtField[3]);
        jpCenter.add(jpSouth);





        //jpGeneral.add(jpCenter,BorderLayout.CENTER);
        //jpGeneral.add(jpSouth, BorderLayout.SOUTH);

        this.add(jpCenter);



    }

    public void registerController(ActionListener controller) {
        this.jbCreate.addActionListener(controller);
        this.jbCreate.setActionCommand("createNewBot");
        this.jbCancel.addActionListener(controller);
        this.jbCancel.setActionCommand("cancelBotCreation");
    }

    //public String getBotName() {
      //  return jtfName.getText();
   // }

    //public String getCompanyMame() {
      //  return jtfName.getText();
    //}

    public float getPercentage() {
        return Float.parseFloat(jtfPercentage.getText());
    }

    public float getActivation() {
        return Float.parseFloat(jtfActivation.getText());
    }
}
