package controller;

import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotsCreateController implements ActionListener {
    private static final String CREATE = "CREATE";
    private static final String CANCEL = "CANCEL";
    private static final String CARD_BOTS = "Manage Bots";
    private MainView view;

    public BotsCreateController (MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CREATE:
                /*Bot bot = new Bot();
                Company company = new Company(view.getBotsCreateView().getCompanyName());
                bot.setCompany(company);
                bot.setCompany(company);
                bot.setActiveTime(view.getBotsCreateView().getActivation());
                bot.setProbability(view.getBotsCreateView().getPercentage());
                model.createBot(bot);*/
                break;
            case CANCEL:
                view.updateView(CARD_BOTS);
                break;
        }
    }
    public void validatePercentage(float percentage) {
        // TODO: Implement validatePercentage
    }
}
