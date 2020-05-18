package controller;

import model.managers.UserManager;
import view.SharesListView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SharesListController implements ListSelectionListener {
    private SharesListView view;
    private UserManager userManager;
    private ListSelectionModel selectionModel;
    private int selectedRow = 0;

    public SharesListController(SharesListView sharesListView){
        this.view = sharesListView;
        this.userManager = new UserManager();
        loadUsers();
        this.view.setVisible(true);
    }

    /**
     *  Get User data from User Manager and Database
     */
    public void loadUsers(){
        loadUserList(userManager.getUserList());
    }

    /**
     *  Load data into SharesList Table
     *
     * @param data String array containing data for the table
     */
    public void loadUserList(String[][] data){
        this.view.setUserList(data);
        this.view.emptyTable();
        this.view.fillData();
    }

    /**
     *  Method to detect clicks on SharesList JTable
     */
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

        selectionModel = this.view.getSelectionModel();
        if (!listSelectionEvent.getValueIsAdjusting()) {
            if (!selectionModel.isSelectionEmpty()){
                selectedRow = selectionModel.getMinSelectionIndex();
            }
        }
        //TODO get user data company shares
    }
}
