package view;

import model.entities.User;
import utils.StockColors;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class ProfileView extends JPanel {
    private static final String PATH_USER_ICON = "/Images/icon.png";
    protected StockColors color;
    private JLabel jlNickname;
    private JLabel jlUserNick;
    private JLabel jlEmail;
    private JLabel jlUserEmail;
    private JLabel jlDescription;
    private JLabel jlUserDescription;
    private JPanel jpInformation;
    private JPanel jpIcon;
    private JLabel jlUserPhoto;
    private JLabel jlUserName;
    private User user;

    public ProfileView (){

        //General window setup
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new BorderLayout());

        //Panel Information
        jpInformation = new JPanel(new FlowLayout());
        jpInformation.setBackground(color.getBLACK());
        Font fontData = new Font("Segoe UI Semibold", Font.PLAIN, 20);

        //NICKNAME
        jlNickname = new JLabel("NICKNAME");
        jlNickname.setForeground(color.getWHITE());
        Font fontN = jlNickname.getFont();
        Map attributesN = fontN.getAttributes();
        attributesN.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlNickname.setFont(fontN.deriveFont(attributesN));

        //User Nickname
        jlUserNick = new JLabel(user.getNickname());
        jlUserNick.setForeground(color.getWHITE());
        jlUserNick.setFont(fontData);

        //EMAIL
        jlEmail = new JLabel("EMAIL");
        jlEmail.setForeground(color.getWHITE());
        Font fontE = jlEmail.getFont();
        Map attributesE = fontE.getAttributes();
        attributesE.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlNickname.setFont(fontE.deriveFont(attributesE));

        //User Email
        jlUserEmail = new JLabel(user.getEmail());
        jlUserEmail.setForeground(color.getWHITE());
        jlUserEmail.setFont(fontData);

        //DESCRIPTION
        jlDescription = new JLabel("DESCRIPTION");
        jlDescription.setForeground(color.getWHITE());
        Font fontD = jlDescription.getFont();
        Map attributesD = fontD.getAttributes();
        attributesD.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlDescription.setFont(fontD.deriveFont(attributesD));

        //User Description
        jlUserDescription = new JLabel(user.getDescription());
        jlUserDescription.setForeground(color.getWHITE());
        jlUserDescription.setFont(fontData);

        //Adding in the information panel
        jpInformation.add(jlNickname);
        jpInformation.add(jlUserNick);
        jpInformation.add(jlEmail);
        jpInformation.add(jlUserEmail);
        jpInformation.add(jlDescription);
        jpInformation.add(jlUserDescription);

        //We create the JPanel for the Icon
         jpIcon = new JPanel(new FlowLayout());

        // Create User Icon
        ImageIcon imageIcon = new ImageIcon(MainView.class.getResource(
                PATH_USER_ICON));
        Image scaleImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        jlUserPhoto = new JLabel(imageIcon);
        jpIcon.add(jlUserPhoto, BorderLayout.CENTER);
        jpIcon.setBackground(color.getBLACK());

        // Add User Name
        Font fontName = new Font("Segoe UI", Font.ITALIC, 30);
        jlUserName = new JLabel(user.getNickname(), SwingConstants.CENTER);
        jlUserName.setFont(fontName);
        jlUserName.setForeground(color.getBLACK());


        //Adding in the main pannel
        this.add(jpInformation, FlowLayout.RIGHT);
        this.add(jpIcon, FlowLayout.LEFT);

    }

}


