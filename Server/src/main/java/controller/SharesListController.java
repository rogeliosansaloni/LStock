package controller;

import model.entities.User;
import model.managers.UserManager;
import view.SharesListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SharesListController extends MouseAdapter implements ActionListener {
    private SharesListView view;
    private int currentView;
    private UserManager userManager;

    public SharesListController(SharesListView sharesListView){
        this.view = sharesListView;
        this.userManager = new UserManager();
        this.currentView = 0;
        loadUserList(currentView, userManager.getUserList());
        this.view.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.currentView = 1;
        User user = view.getSelectedUser();
        //get user data company shares
        String [][] s = {{ view.getSelectedUser().getNickname(), "111", "222", "333"}};
        loadUserList(currentView, s);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("return")){
            System.out.println("RETURN PRESSED");
            if (currentView == 0){
                System.out.println("back to server menu");
            }else{
                System.out.println("back to user list");
                currentView = 0;
            }
        }
    }

    public void loadUserList(int currentView, String [][] data){
        if (currentView == 0) {
            this.view.setUserList(data);
        }
        if (currentView == 1) {
            String [][] s = {{ view.getSelectedUser().getNickname(), "111", "222", "333"}};
            this.view.setUserList(data);
        }
    }
}
