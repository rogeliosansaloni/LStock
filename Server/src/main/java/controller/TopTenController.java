package controller;

import model.entities.Top10;
import model.managers.StockManager;
import view.TopTenCompaniesView;

import java.util.ArrayList;

/**
 * Controller for the TopTenCompanies View
 */
public class TopTenController {
    private TopTenCompaniesView view;
    private StockManager stockManager;

    /**
     * Creates and initializes the controller
     */
    public TopTenController(TopTenCompaniesView topTenCompaniesView){
        this.view = topTenCompaniesView;
        this.stockManager = new StockManager();
    }

    /**
     * Return the Top 10 Company List
     *
     * @return TopTenList
     */
    public ArrayList<Top10> getTopTenCompanies(){
       return stockManager.getTopTenlist();
    }

    /**
     * Updates de Top 10 Company list
     */
    public void updateTopTenView(){
        view.showTopTen(stockManager.getTopTenlist());
    }
}
