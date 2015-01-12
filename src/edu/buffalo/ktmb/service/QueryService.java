package edu.buffalo.ktmb.service;

import java.util.List;

import edu.buffalo.ktmb.bean.Bid;
import edu.buffalo.ktmb.bean.Query;
import edu.buffalo.ktmb.dao.QueryBidDAO;

public class QueryService {

	QueryBidDAO qbDao = new QueryBidDAO();
	private static final Float MIN_BID = 5.00F;
	private static final Float QUERY_HIT_QUOTIENT = 0.5F;
	private static final Float AD_HIT_QUOTIENT = 0.5F;
	private static final Float MAX_INCREMENT_QUOTIENT = 0.1F;
	
	public List<String> getAdsForQuery(String query) {
		return qbDao.getAdsForQuery(query);
	}
	
	public void incrementAdHitsForQuery(String query) {
		qbDao.incrementAdHitsForQuery(query);
	}
	
	public Query getQueryInfo(String query) {
		return qbDao.getInfoForQuery(query);
	}
	
	public List<Bid> getPreviousBidsForQuery(String query) {
		return qbDao.getPreviousBidsForQuery(query);
	}
	
	public Bid getWinningBidForQuery(String query) {
		return qbDao.getWinningBidForQuery(query);
	}
	
	public float getMinBidPrice(String query) {
		Query q = qbDao.getInfoForQuery(query);
		float minBid;
		if(q != null) {
			minBid = ((float) q.getMinBidPrice()) / 100;
		} else {
			minBid = MIN_BID;
		}
		return minBid;
	}
	
	public void updateMinBidPriceForQueries() {
		List<Query> queryList = qbDao.getQueriesToUpdateMinBidPrice();
		int maxHits = qbDao.getMaxQueryHits();
		
		for(Query query: queryList) {
			int currentMin = query.getMinBidPrice();
			int suggestedBidPrice = query.getSuggestedBidPrice();
			int newMin = currentMin;
			int newSuggestedPrice = suggestedBidPrice;
			int queryHits = query.getQueryHits();
			int adHits = query.getAdHits();
			
			float queryPopularity = (float) queryHits / maxHits;
			float adCTR = (float) adHits / queryHits;
			
			float popularityIndex = queryPopularity * QUERY_HIT_QUOTIENT + adCTR * AD_HIT_QUOTIENT;
			
			// calculate new min for query
			int maxIncrease = (int)(MAX_INCREMENT_QUOTIENT * currentMin);
			int actualIncrease = (int) (maxIncrease * popularityIndex);
			newMin = currentMin + actualIncrease;
			
			// calculate new suggested bid price
			List<Bid> previousWinningBids = getPreviousBidsForQuery(query.getQuery());
			int sum = 0;
			int count = 0;
			for(Bid bid: previousWinningBids) {
				sum += bid.getBidAmount();
				count++;
			}
			int avgWinningBids = sum/count;
			if(avgWinningBids > suggestedBidPrice) {
				int diff = avgWinningBids - suggestedBidPrice;
				int inc = (int) (diff * popularityIndex);
				newSuggestedPrice = suggestedBidPrice + inc;
			}
			
			if(newSuggestedPrice < newMin) {
				newSuggestedPrice = newMin;
			}
			
			if(newSuggestedPrice != suggestedBidPrice || newMin != currentMin) {
				qbDao.updateQueryMinBid(query.getQueryId(), newMin, newSuggestedPrice);
			}
		}
		
	}
}
