package edu.buffalo.ktmb.bean;

public class Bid {
	
	private int bidId;
	private int bidAmount;
	private String adUrl;
	private int queryId;
	private int sessionId;
	
	public Bid() {
		
	}
	
	public Bid(int bidAmount, String adUrl, int queryId, int sessionId) {
		this.bidAmount = bidAmount;
		this.adUrl = adUrl;
		this.queryId = queryId;
		this.sessionId = sessionId;
	}
	
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public int getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

}
