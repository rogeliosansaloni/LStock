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
    private JLabel jlUserPhoto;
    private JLabel jlUserName;

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

        //Adding in the information panel
        jpInformation.add(jlNickname);
        jpInformation.add(jlUserNick);
        jpInformation.add(jlEmail);
        jpInformation.add(jlUserEmail);
        jpInformation.add(jlDescription);
        jpInformation.add(jlUserDescription);

        //We create the JPanel for the Icon
        JPanel jpIcon = new JPanel(new FlowLayout());

        // Create User Icon
        ImageIcon imageIcon = new ImageIcon(MainView.class.getResource(
                PATH_USER_ICON));
        Image scaleImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        jlUserPhoto = new JLabel(imageIcon);
        jpIcon.add(jlUserPhoto, BorderLayout.CENTER);
        jpIcon.setBackground(color.getBLACK());

        // Add User Name
        Font fontLogo = new Font("Segoe UI", Font.ITALIC, 30);
        jlUserName = new JLabel("Peter Foxie", SwingConstants.CENTER);
        jlUserName.setFont(fontLogo);
        jlUserName.setForeground(color.getBLACK());


    }

    public void updateProfile (User user){

    }

    public void showMessages (String type){

    }

}


