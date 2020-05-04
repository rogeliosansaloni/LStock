package utils.mappers;

import model.entities.Company;
import model.entities.ShareTrade;
import model.entities.User;

public interface ShareMapper {
    User shareTradeToUser (ShareTrade shareTrade);

    ShareTrade userToShareTrade (User user);

    Company shareTradeToCompany (ShareTrade shareTrade);

    ShareTrade companyToShareTrade (Company company);
}
