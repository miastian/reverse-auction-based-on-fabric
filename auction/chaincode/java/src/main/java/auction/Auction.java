package auction;

import java.util.List;
import java.util.ArrayList;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType()
public final class Auction {
    private final String auctionName;

    private final String itemSold;

    private final String seller;

    private List<String> orgs;

    private String winner;

    private String status;

    private List<BidHash> bids;

    private int price;

    public Auction(final String auctionName, final String seller, final String itemSold) {
        this.auctionName = auctionName;
        this.seller = seller;
        this.orgs = null;// how to define Orgs?
        this.price = 0;
        this.itemSold = itemSold;
        this.winner = "";
        this.status = "open";
        this.bids = null;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public String getItemSold() {
        return itemSold;
    }

    public String getSeller() {
        return seller;
    }

    public List<String> getOrgs() {
        return orgs;
    }

    public String getWinner() {
        return winner;
    }

    public String getStatus() {
        return status;
    }

    public int getPrice() {
        return price;
    }

    public List<BidHash> getBids() {
        return bids;
    }

    public void setWinner(final String winner) {
        this.winner = winner;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void addBid(final BidHash bid) {
        this.bids.add(bid);
    }
    // how to add bid to the array

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [AuctionName="
                + this.auctionName + ", Seller="
                + this.seller + ", Price=" + this.price + ", Winner=" + this.winner + ", ItemSold=" + this.itemSold
                + ", Orgs="
                + this.orgs + ", Status=" + this.status + "]";
    }
}
