package controller;

import model.entities.Bot;
import model.managers.BotManager;
import view.BotsRemoveView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotsRemoveController implements ActionListener {
    private static final String REMOVE = "REMOVE";
    private static final String CANCEL = "CANCEL";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView mainView;
    private BotsRemoveView view;
    private BotManager model;

    public BotsRemoveController(BotsRemoveView view, MainView mainView, BotManager model) {
        this.view = view;
        this.mainView = mainView;
        this.model = model;
        initView();
    }

    private void initView() {
        view.showCompanies(model.getCompanies());
        ArrayList<Bot> bots = model.getAllBotsByCompany(getSelectedCompanyId());
        view.showBots(bots);
    }

    private int getSelectedCompanyId() {
        String companyName = mainView.getBotsRemoveView().getCompanyName();
        return model.getCompanyId(companyName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case REMOVE:
                int botId = mainView.getBotsRemoveView().getBotId();
                model.deleteBot(botId);
                view.showBots(model.getAllBotsByCompany(getSelectedCompanyId()));
                break;
            case CANCEL:
                mainView.updateView(CARD_BOTS);
                break;
        }
    }
}
