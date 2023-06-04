package auction;

import java.util.List;
import java.util.ArrayList;

import org.hyperledger.fabric.contract.annotation.DataType;

@DataType()
public class BidHash {
    private String hash;
    private String orgs;
    private boolean revealed;

    public BidHash(String hash)
    {
        this.hash=hash;
        this.orgs = null;
        this.revealed=false;
    }

    public String getHash() {
        return hash;
    }

    public String getOrgs() {
        return orgs;
    }

    public void setRevealed(){
        this.revealed=true;
    }

    public boolean isRevealed(){
        return revealed;
    }
}
