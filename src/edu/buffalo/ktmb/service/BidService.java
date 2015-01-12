package edu.buffalo.ktmb.service;

import edu.buffalo.ktmb.bean.Bid;
import edu.buffalo.ktmb.dao.QueryBidDAO;

public class BidService {

	QueryBidDAO qbDAO = new QueryBidDAO();
	
	public void addBid(int bidAmount, String adUrl, String query, int sessionId) {
		
		int queryId = qbDAO.getQueryId(query);
		if(queryId == -1) {
			queryId = qbDAO.insertQuery(query);
		}
		
		Bid bid = new Bid(bidAmount * 100, adUrl, queryId, sessionId);
		qbDAO.addBid(bid);
	}
	
	public void updateWinningBids(int sessionId) {
		
		qbDAO.updateWinningBids(sessionId);
	}
	
}
