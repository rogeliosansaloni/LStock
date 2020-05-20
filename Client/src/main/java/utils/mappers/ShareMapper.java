package utils.mappers;

import model.entities.*;

import java.util.ArrayList;

public interface ShareMapper {

    ArrayList<ShareSell> converToSharesSell(ShareSellList shareSellList);

    ShareSellList convertToShareSellList(ArrayList<ShareSell> shareSells);
}
