package controller;

import model.managers.UserManager;
import view.SharesListView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller for the shares list
 */
public class SharesListController implements ListSelectionListener {
    private SharesListView view;
    private UserManager userManager;
    private ListSelectionModel selectionModel;
    private static final String NO_SHARES_AVAILABLE = "This user doesn't have any shares to display!";
    private int selectedRow = 0;
    private boolean selectedUser;

    /**
     * SharesList Controller
     * @param sharesListView SharesListView
     */
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
        this.selectedUser = false;
        loadUserList(userManager.getUserList());
    }

    /**
     *  Load data into SharesList Table
     *
     * @param data String array containing data for the table
     */
    public void loadUserList(String[][] data){
        this.view.setTableRow(data);
        this.view.emptyTable();
        this.view.fillUserData();
    }

    /**
     *  Method to detect clicks on SharesList JTable
     */
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        selectionModel = this.view.getSelectionModel();
        if (!selectedUser) {
            if (!listSelectionEvent.getValueIsAdjusting()) {
                if (!selectionModel.isSelectionEmpty()) {
                    selectedRow = selectionModel.getMinSelectionIndex();
                    String[][] shares = userManager.getUserShares(this.view.getSelectedUser(selectedRow));
                    if (shares != null){
                        this.view.setTableRow(userManager.getUserShares(this.view.getSelectedUser(selectedRow)));
                        this.view.emptyTable();
                        this.view.fillShareData();
                        this.selectedUser = true;
                    }else{
                        this.view.showErrorMessage(NO_SHARES_AVAILABLE);
                    }
                }
            }
        }
    }
}
