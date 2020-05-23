package controller;

import model.entities.Top10;
import model.managers.StockManager;
import view.TopTenCompaniesView;

import java.util.ArrayList;


public class TopTenController {
    private TopTenCompaniesView view;
    private StockManager stockManager;

    public TopTenController(){
//        this.view = view;
        this.stockManager = new StockManager();
    }

    public ArrayList<Top10> getTopTenCompanies(){
//        for (Top10 top : stockManager.getTopTenlist()){
//            System.out.println("CONTROLLER TOP TEN---- Company: "+top.getName()+" ("+top.getPrice()+"€/share)");
//        }
       return stockManager.getTopTenlist();
    }
}
