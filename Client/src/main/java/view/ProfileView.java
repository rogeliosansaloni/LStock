package view;

import model.entities.User;
import utils.StockColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 * Profile view
 */
public class ProfileView extends JPanel {
    private static final String PATH_USER_PHOTO = "/Images/logoUser.png";
    private StockColors color;
    private JLabel jlNicknameTitle, jlEmailTitle, jlDescriptionTitle;
    private JLabel jlNickname, jlEmail, jlDescription;
    private JPanel jpInformation;
    private JPanel jpPhoto;
    private JPanel jpNickname, jpEmail, jpDescription;
    private JLabel jlUserPhoto;

    /**
     * Creates the Profile view
     */
    public ProfileView() {

        //General window setup
        color = new StockColors();
        this.setBackground(color.getBLACK());
        this.setLayout(new FlowLayout());

        //We create the JPanel for the Icon
        jpPhoto = new JPanel(new BorderLayout());
        // Create User Icon
        ImageIcon imageIcon = new ImageIcon(ProfileView.class.getResource(
                PATH_USER_PHOTO));
        Image scaleImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(scaleImage);
        jlUserPhoto = new JLabel(imageIcon);
        jlUserPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        jlUserPhoto.setVerticalAlignment(SwingConstants.CENTER);
        jpPhoto.add(jlUserPhoto, BorderLayout.CENTER);
        jpPhoto.setBackground(color.getBLACK());
        jpPhoto.setPreferredSize(new Dimension(350, 500));
        jpPhoto.setAlignmentX(SwingConstants.CENTER);
        jpPhoto.setAlignmentY(SwingConstants.CENTER);

        //Panel Information
        jpInformation = new JPanel(new BorderLayout());
        jpInformation.setAlignmentX(SwingConstants.LEFT);
        jpInformation.setBackground(color.getBLACK());
        jpInformation.setAlignmentX(SwingConstants.CENTER);
        jpInformation.setAlignmentY(SwingConstants.CENTER);
        jpInformation.setBorder(new EmptyBorder(0, 60, 0, 0));

        Font fontTitle = new Font("Segoe UI Semibold", Font.BOLD, 32);
        Font fontText = new Font("Segoe UI Semibold", Font.PLAIN, 25);

        //NICKNAME
        jpNickname = new JPanel(new BorderLayout());
        jpNickname.setBackground(color.getBLACK());
        jlNicknameTitle = new JLabel("NICKNAME");
        jlNicknameTitle.setBackground(color.getBLACK());
        jlNicknameTitle.setForeground(color.getWHITE());
        Map attributesN = fontTitle.getAttributes();
        attributesN.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlNicknameTitle.setFont(fontTitle.deriveFont(attributesN));
        jlNicknameTitle.setBorder(new EmptyBorder(0, 0, 10, 0));

        jlNickname = new JLabel("This is my nickname.");
        jlNickname.setBackground(color.getBLACK());
        jlNickname.setForeground(color.getWHITE());
        jlNickname.setFont(fontText);
        jpNickname.add(jlNicknameTitle, BorderLayout.NORTH);
        jpNickname.add(jlNickname, BorderLayout.CENTER);
        jpNickname.setBorder(new EmptyBorder(0, 0, 35, 0));

        //EMAIL
        jpEmail = new JPanel(new BorderLayout());
        jpEmail.setBackground(color.getBLACK());
        jlEmailTitle = new JLabel("EMAIL");
        jlEmailTitle.setForeground(color.getWHITE());
        attributesN.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlEmailTitle.setFont(fontTitle.deriveFont(attributesN));
        jlEmailTitle.setBorder(new EmptyBorder(0, 0, 10, 0));

        jlEmail = new JLabel("This is my email.");
        jlEmail.setBackground(color.getBLACK());
        jlEmail.setForeground(color.getWHITE());
        jlEmail.setFont(fontText);
        jpEmail.add(jlEmailTitle, BorderLayout.NORTH);
        jpEmail.add(jlEmail, BorderLayout.CENTER);
        jpEmail.setBorder(new EmptyBorder(0, 0, 35, 0));

        //DESCRIPTION
        jpDescription = new JPanel(new BorderLayout());
        jpDescription.setBackground(color.getBLACK());
        jlDescriptionTitle = new JLabel("DESCRIPTION");
        jlDescriptionTitle.setForeground(color.getWHITE());
        attributesN.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlDescriptionTitle.setFont(fontTitle.deriveFont(attributesN));
        jlDescriptionTitle.setBorder(new EmptyBorder(0, 0, 10, 0));


        jlDescription = new JLabel("This is my description.");
        jlDescription.setBackground(color.getBLACK());
        jlDescription.setForeground(color.getWHITE());
        jlDescription.setFont(fontText);
        jpDescription.add(jlDescriptionTitle, BorderLayout.NORTH);
        jpDescription.add(jlDescription, BorderLayout.CENTER);

        //Adding in the information panel
        jpInformation.add(jpNickname, BorderLayout.NORTH);
        jpInformation.add(jpEmail, BorderLayout.CENTER);
        jpInformation.add(jpDescription, BorderLayout.SOUTH);


        //Adding in the main panel
        this.add(jpPhoto);
        this.add(jpInformation);
    }

    /**
     * Updates the user information: nickname, email and description.
     */
    public void updateProfileView(User user) {
        jlNickname.setText(user.getNickname());
        jlEmail.setText(user.getEmail());
        String description = user.getDescription();
        if (description == null || description.equals("")) {
            jpDescription.setVisible(false);
        } else {
            jpDescription.setVisible(true);
            jlDescription.setText(user.getDescription());
        }
    }

}


