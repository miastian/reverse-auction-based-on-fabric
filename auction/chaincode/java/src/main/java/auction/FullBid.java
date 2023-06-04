package auction;

import java.util.List;
import java.util.ArrayList;

import org.hyperledger.fabric.contract.annotation.DataType;

@DataType()
public class FullBid {
    private String type;
    private String orgs;
    private String bidder;
    private int price;

    public FullBid(String type, String bidder) {
        this.type = type;
        this.bidder = bidder;
        this.orgs = null;
        this.price = 0;
    }

    public String getType() {
        return type;
    }

    public String getOrgs() {
        return orgs;
    }

    public String getBidder() {
        return bidder;
    }

    public int getPrice() {
        return price;
    }
}
