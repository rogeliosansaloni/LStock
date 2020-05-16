package view;

import model.entities.User;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class ProfileView extends JPanel {
    protected StockColors color;
    private JLabel jlNickname;
    private JLabel jlUserNick;
    private JLabel jlEmail;
    private JLabel jlUserEmail;
    private JLabel jlDescription;
    private JLabel jlUserDescription;
    private JPanel jpInformation;

    public ProfileView (){
        //General window setup
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());

        //Panel Information
        JPanel jpInformation = new JPanel(new FlowLayout());
        jpInformation.setBackground(color.getBLACK());
        Font fontData = new Font("Segoe UI Semibold", Font.PLAIN, 20);

        //NICKNAME
        jlNickname = new JLabel("NICKNAME");
        jlNickname.setForeground(color.getWHITE());
        Font fontN = jlNickname.getFont();
        Map attributesN = fontN.getAttributes();
        attributesN.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlNickname.setFont(fontN.deriveFont(attributesN));
        jlUserNick = new JLabel("peter.foxie");
        jlUserNick.setForeground(color.getWHITE());
        jlUserNick.setFont(fontData);

        //EMAIL
        jlEmail = new JLabel("NICKNAME");
        jlEmail.setForeground(color.getWHITE());
        Font fontE = jlEmail.getFont();
        Map attributesE = fontE.getAttributes();
        attributesE.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlNickname.setFont(fontE.deriveFont(attributesE));
        jlUserEmail = new JLabel("peter.foxie@gmail.com");
        jlUserEmail.setForeground(color.getWHITE());
        jlUserEmail.setFont(fontData);

        //DESCRIPTION
        jlDescription = new JLabel("NICKNAME");
        jlDescription.setForeground(color.getWHITE());
        Font fontD = jlDescription.getFont();
        Map attributesD = fontD.getAttributes();
        attributesD.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlDescription.setFont(fontD.deriveFont(attributesD));
        jlUserDescription = new JLabel("peter.foxie@gmail.com");
        jlUserDescription.setForeground(color.getWHITE());
        jlUserDescription.setFont(fontData);

        //Adding in the south panel
        jpInformation.add(jlNickname);
        jpInformation.add(jlUserNick);
        jpInformation.add(jlEmail);
        jpInformation.add(jlUserEmail);
        jpInformation.add(jlDescription);
        jpInformation.add(jlUserDescription);

        //Icon Panel


    }

    public void updateProfile (User user){

    }

    public void showMessages (String type){

    }

}


