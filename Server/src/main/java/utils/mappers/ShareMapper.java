package utils.mappers;

import model.entities.Company;
import model.entities.Purchase;
import model.entities.ShareTrade;
import model.entities.User;

public interface ShareMapper {
    Purchase shareTradeToPurchase(ShareTrade shareTrade);

    ShareTrade purchaseToShareTrade(Purchase purchase);

    User shareTradeToUser (ShareTrade shareTrade);

    ShareTrade userToShareTrade (User user);

    Company shareTradeToCompany (ShareTrade shareTrade);

    ShareTrade companyToShareTrade (Company company);

    ShareTrade userCompanyToShareTrade (User user, Company company);
}
