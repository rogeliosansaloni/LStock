package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

public interface ShareMapper {
    User shareTradeToUser(ShareTrade shareTrade);

    ShareTrade userToShareTrade(User user);

    Company shareTradeToCompany(ShareTrade shareTrade);

    ShareTrade companyToShareTrade(Company company);

    ShareTrade userCompanyToShareTrade(User user, Company company);

    ArrayList<ShareChange> convertToSharesChange(ShareChangeList shareChangeList);
}
