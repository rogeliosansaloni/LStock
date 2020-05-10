package view;

import model.entities.Company;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BotsCreateView extends JPanel {
    private static final String PATH_ARROW_ICON = "/Images/dropdown.png";
    private static final String FONT = "Segoe UI";
    private static final String FONT_BUTTON = "Segoe UI Semibold";
    private static final String TITLE = "Create Bot";
    private static final String NAME_LABEL = "Name";
    private static final String BUY_PERCENTAGE_LABEL = "Buy Percentatge";
    private static final String ACTIVATE_TIME_LABEL = "Activate time";
    private static final String CREATE = "CREATE";
    private static final String CANCEL = "CANCEL";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 20;
    private JPanel jpButtons;
    private JMenu jcbCompanyOptions;
    private JMenuBar jcbCompany;
    private JTextField[] jtField;
    private JButton jbCreate;
    private JButton jbCancel;
    private StockColors color;

    public BotsCreateView() {
        color = new StockColors();
        Font font = new Font(FONT, Font.ITALIC, 20);

        this.setLayout(new GridLayout(6,1,0,15));
        this.setBackground(color.getWHITE());

        JLabel jlTitle = new JLabel(TITLE);
        jlTitle.setPreferredSize(new Dimension(200, 1));
        jlTitle.setHorizontalAlignment(JLabel.CENTER);
        jlTitle.setFont(font);
        this.add(jlTitle);

        jtField = new JTextField[4];

        jtField[0] = new JTextField(NAME_LABEL);
        jtField[0].setBorder(null);
        jtField[0].setFont(font);
        jtField[0].setForeground(Color.GRAY);
        jtField[0].setBackground(color.getTEXTFIELD());
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
                    jtField[0].setText(NAME_LABEL);
                }
            }
        });
        this.add(jtField[0]);

        //Company
        ImageIcon imageArrow = new ImageIcon(MainView.class.getResource(PATH_ARROW_ICON));
        Image scaleImageArrow = imageArrow.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        imageArrow = new ImageIcon(scaleImageArrow);

        jcbCompany = new JMenuBar();
        jcbCompany.setBorder(null);
        jcbCompany.setForeground(Color.GRAY);
        jcbCompany.setBackground(color.getTEXTFIELD());
        jcbCompanyOptions = new JMenu();
        jcbCompanyOptions.setHorizontalTextPosition(SwingConstants.LEFT);
        jcbCompanyOptions.setFont(font);
        jcbCompanyOptions.setForeground(Color.BLACK);
        jcbCompanyOptions.setIcon(imageArrow);
        //TODO: Fix position of ImageIcon
        //jcbCompanyOptions.setIconTextGap(160);
        jcbCompany.add(jcbCompanyOptions);
        this.add(jcbCompany);

        //Buy Percentage
        jtField[1] = new JTextField(BUY_PERCENTAGE_LABEL);
        jtField[1].setBorder(null);
        jtField[1].setFont(font);
        jtField[1].setForeground(Color.GRAY);
        jtField[1].setBackground(color.getTEXTFIELD());
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
        this.add(jtField[1]);

        //Activation Time
        jtField[2] = new JTextField(ACTIVATE_TIME_LABEL);
        jtField[2].setBorder(null);
        jtField[2].setFont(font);
        jtField[2].setForeground(Color.GRAY);
        jtField[2].setBackground(color.getTEXTFIELD());
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
        this.add(jtField[2]);

        jpButtons = new JPanel(new GridLayout(1, 2, 30, 0));
        jpButtons.setBackground(color.getWHITE());

        Font buttonFont = new Font(FONT_BUTTON, Font.PLAIN, 20);
        jbCreate = new JButton(CREATE);
        jbCreate.setFont(buttonFont);
        jbCreate.setForeground(color.getBLACK());
        jbCreate.setBorder(null);
        jbCreate.setBackground(color.getGREEN());
        jbCreate.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jpButtons.add(jbCreate);

        jbCancel = new JButton(CANCEL);
        jbCancel.setFont(buttonFont);
        jbCancel.setForeground(color.getBLACK());
        jbCancel.setBorder(null);
        jbCancel.setBackground(color.getRED());
        jbCancel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        jpButtons.add(jbCancel);

        this.add(jpButtons);
        this.setBorder(BorderFactory.createEmptyBorder(0, 150, 50, 150));
    }

    public void registerController(ActionListener controller) {
        /*for (int i = 0; i < jcbCompanyOptions.getItemCount(); i++) {
            jcbCompanyOptions.getItem(i).addActionListener(controller);
        }*/
        this.jcbCompanyOptions.addActionListener(controller);
        this.jbCreate.addActionListener(controller);
        this.jbCreate.setActionCommand(CREATE);
        this.jbCancel.addActionListener(controller);
        this.jbCancel.setActionCommand(CANCEL);
    }

    public void showCompanies(ArrayList<Company> companies) {
        Font font = new Font(FONT, Font.ITALIC, 20);
        int numCompanies = companies.size();
        JMenuItem[] itemCompany = new JMenuItem[numCompanies];
        for (int i = 0; i < numCompanies; i++) {
            Company company = (Company) companies.get(i);
            itemCompany[i] = new JMenuItem(company.getName());
            itemCompany[i].setHorizontalAlignment(SwingConstants.CENTER);
            itemCompany[i].setForeground(Color.GRAY);
            itemCompany[i].setFont(font);
            jcbCompanyOptions.add(itemCompany[i]);
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
