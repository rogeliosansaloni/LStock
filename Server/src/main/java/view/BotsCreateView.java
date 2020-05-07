package view;

import model.entities.Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BotsCreateView extends JPanel {
    private static final String PATH_ARROW_ICON = "/Images/dropdown.png";
    private static final String NAME_LABEL = "Name";
    private static final String BUY_PERCENTAGE_LABEL = "Buy Percentatge";
    private static final String ACTIVATE_TIME_LABEL = "Activate time";
    private JMenu jcbCompanyOptions;
    private JMenuBar jcbCompany;
    private JTextField[] jtField;
    private JButton jbCreate;
    private JButton jbCancel;

    public BotsCreateView() {

        Font font = new Font("Segoe UI", Font.ITALIC, 20);
        jtField = new JTextField[4];

        // Nam
        jtField[0] = new JTextField(NAME_LABEL);
        jtField[0].setBorder(null);
        jtField[0].setFont(font);
        jtField[0].setForeground(Color.GRAY);
        jtField[0].setCaretColor(Color.WHITE);
        jtField[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                jtField[0].setCaretColor(Color.BLACK);
                jtField[0].setText("");
                jtField[0].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (jtField[0].getText().equals(NAME_LABEL)) {
                            jtField[0].setText("");
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (jtField[0].getText().equals("")) {
                            jtField[0].setCaretColor(Color.WHITE);
                            jtField[0].setText(NAME_LABEL);
                        }
                    }
                });
            }
        });

        //Company
        ImageIcon imageArrow = new ImageIcon(MainView.class.getResource(PATH_ARROW_ICON));
        Image scaleImageArrow = imageArrow.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        imageArrow = new ImageIcon(scaleImageArrow);

        jcbCompany = new JMenuBar();
        jcbCompanyOptions = new JMenu("Company");
        jcbCompanyOptions.setHorizontalTextPosition(SwingConstants.LEFT);
        jcbCompanyOptions.setFont(font);
        jcbCompanyOptions.setForeground(Color.BLACK);
        jcbCompanyOptions.setIcon(imageArrow);
        jcbCompanyOptions.setIconTextGap(80);
        jcbCompany.add(jcbCompanyOptions);

        JMenuItem itemCompany[] = new JMenuItem[20];
        itemCompany[1].setHorizontalAlignment(SwingConstants.CENTER);
        itemCompany[1].setForeground(Color.GRAY);
        itemCompany[1].setFont(font);
        itemCompany[1] = new JMenuItem("Company1");
        jcbCompanyOptions.add(itemCompany[1]);


        //Buy Percentage
        jtField[1] = new JTextField(BUY_PERCENTAGE_LABEL);
        jtField[1].setBorder(null);
        jtField[1].setFont(font);
        jtField[1].setForeground(Color.GRAY);
        jtField[1].setBackground(Color.WHITE);
        jtField[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtField[1].getText().equals(BUY_PERCENTAGE_LABEL)) {
                    jtField[1].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtField[1].getText().equals("")) {
                    jtField[1].setText(BUY_PERCENTAGE_LABEL);
                }
            }
        });

        //Activation Time
        jtField[2] = new JTextField(ACTIVATE_TIME_LABEL);
        jtField[2].setBorder(null);
        jtField[2].setFont(font);
        jtField[2].setForeground(Color.GRAY);
        jtField[2].setBackground(Color.WHITE);
        jtField[2].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtField[2].getText().equals(ACTIVATE_TIME_LABEL)) {
                    jtField[2].setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtField[2].getText().equals("")) {
                    jtField[2].setText(ACTIVATE_TIME_LABEL);
                }
            }
        });


        Font fontButton = new Font("Segoe UI Semibold", Font.PLAIN, 20);

        //Create Button
        jbCreate = new JButton("CREATE");
        jbCreate.setBackground(Color.GREEN);
        jbCreate.setFont(fontButton);
        jbCreate.setForeground(Color.BLACK);
        jbCreate.setPreferredSize(new Dimension(150, 50));

        //Cancel Button
        jbCancel = new JButton("CANCEL");
        jbCancel.setBackground(Color.RED);
        jbCancel.setFont(fontButton);
        jbCancel.setForeground(Color.BLACK);
        jbCancel.setPreferredSize(new Dimension(150, 50));

        JPanel jpGeneral = new JPanel(new GridLayout(5, 1, 0, 30));
        JPanel jpButtons = new JPanel(new FlowLayout());

        jpButtons.add(jbCreate);
        jpButtons.add(jbCancel);

        jpGeneral.add(jtField[0]);
        jpGeneral.add(jcbCompany);
        jpGeneral.add(jtField[1]);
        jpGeneral.add(jtField[2]);
        jpGeneral.add(jpButtons);

        this.add(jpGeneral);
    }

    public void registerController(ActionListener controller) {
        this.jbCreate.addActionListener(controller);
        this.jbCreate.setActionCommand("createNewBot");
        this.jbCancel.addActionListener(controller);
        this.jbCancel.setActionCommand("cancelBotCreation");
    }

    public void showCompanies(ArrayList<Company> companies) {
        Font font = new Font("Segoe UI", Font.ITALIC, 20);
        int numCompanies = companies.size();
        JMenuItem[] itemCompany = new JMenuItem[numCompanies];
        for (int i = 0; i < numCompanies; i++) {
            Company company = (Company) companies.get(i);
            itemCompany[i].setHorizontalAlignment(SwingConstants.CENTER);
            itemCompany[i].setForeground(Color.GRAY);
            itemCompany[i].setFont(font);
            itemCompany[i] = new JMenuItem(company.getName());
            jcbCompanyOptions.add(itemCompany[1]);
        }
    }

    public String getBotName() {
        return jtField[0].getText();
    }

    public String getCompanyName() {
        return jcbCompanyOptions.getName();
    }

    public float getPercentage() {
        return Float.parseFloat(jtField[1].getText());
    }

    public float getActivation() {
        return Float.parseFloat(jtField[2].getText());
    }
}
