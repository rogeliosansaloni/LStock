package controller;

import model.entities.Bot;
import model.managers.BotManager;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotsRemoveController implements ActionListener {
    private static final String REMOVE = "REMOVE";
    private static final String CANCEL = "CANCEL";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView view;
    private BotManager model;

    public BotsRemoveController(MainView view, BotManager model) {
        this.view = view;
        this.model = model;
        initView();
    }

    private void initView() {
        view.showCompanies(model.getCompanies());
        String companyName = view.getBotsRemoveView().getCompanyName();
        int companyId = model.getCompanyId(companyName);
        ArrayList<Bot> bots = model.getAllBotsByCompany(companyId);
        view.showBots(bots);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case REMOVE:
                int botId = view.getBotsRemoveView().getBotId();
                model.deleteBot(botId);
                break;
            case CANCEL:
                view.updateView(CARD_BOTS);
                break;
        }
    }
}
