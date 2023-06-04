package auction;

import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import com.owlike.genson.Genson;

public class AuctionContract {

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public Auction createAuction(final Contex ctx,final String auctionName,final String seller,final String itemSold) {
        ChaincodeStub stub=ctx.getStub();

        return new Auction(auctionName, seller, itemSold);
    }

    //here, we shall use function:queryAuction to get one auction,but now instead,we simply input it
    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void submitBid(final Contex ctx,final Auction auction,final int bid){
        ChaincodeStub stub = ctx.getStub();

        if(!"open".equals(auction.getStatus())){
            //System.out.println("Submitting failed!The auction "+auction.getStatus()+"!");
            //using logger instead of println
            final Logger logger = Logger.getLogger("Mylogger");
            logger.info("Submitting failed!The auction is "+auction.getStatus()+"!");
        }

        auction.addBid(bid);
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void closeAuction(final Auction auction){
        if(!"open".equals(auction.getStatus())){
            //System.out.println("Submitting failed!The auction "+auction.getStatus()+"!");
            //using logger instead of println
            final Logger logger = Logger.getLogger("Mylogger");
            logger.info("Submitting failed!The auction is already"+auction.getStatus()+"!");
        }

        auction.setStatus("close");
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void endAuction(final Auction auction){
        if(!"open".equals(auction.getStatus())){
            //System.out.println("Submitting failed!The auction "+auction.getStatus()+"!");
            //using logger instead of println
            final Logger logger = Logger.getLogger("Mylogger");
            logger.info("Submitting failed!The auction is already"+auction.getStatus()+"!");
        }

        auction.setStatus(("ended"));
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public String queryAuction(final Contex ctx,Auction auction){
        ChaincodeStub stub = ctx.getStub();

        final String status = ctx.getStatus();

        if ("open".equals(status))
        {
            return ctx.toString();
        }

        else 
        {
            final Logger logger = Logger.getLogger("Mylogger");
            logger.info("Querying failed!The auction is already"+auction.getStatus()+"!");
            throw new ChaincodeException();
        }
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public boolean checkForHigherBid(final Contex ctx,Auction auction,int auctionPrice,List<BidHash> bidders,List<FullBid> revealedBidders){
        //get MSP ID of peer org
        String peerMSPID=ctx.MSP();

        for(int i =0;i<bidders.size();i++){
            if(bidders.get(i).isRevealed()){
                //bid is already revealed, no action to take
            }
            else{
                //might be wrong
                if(peerMSPID==bidders.get(i).getOrgs()){
                //bid is not revealed yet, need to be compared with auctionPrice, if this bid is higher, then return false;
                //here we need to reveal the price and compare
                if(bidders.get(i).getPrice() > auctionPrice){
                  
            final Logger logger = Logger.getLogger("Mylogger");
            logger.info("Cannot close auction, bidder has a higher price"+auction.getStatus()+"!");
                  return false;
                }
                }
            }
        }
    }

}
