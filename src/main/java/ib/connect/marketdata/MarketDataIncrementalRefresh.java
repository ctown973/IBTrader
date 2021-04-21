package ib.connect.marketdata;

public class MarketDataIncrementalRefresh {
  
	
	 enum MDAction {ADD, CHANGE, DELETE};
	
	 enum MDUpdate {BID, OFFER, TRADE};
	
	private MDAction action;
	private MDUpdate update;
	private PriceQuote quote;
	
	private int level;
	
	public MarketDataIncrementalRefresh(MDAction action, MDUpdate update, PriceQuote quote) {
		this.action = action;
		this.update = update;
		this.quote = quote;
	}
	
	public void setLevel(int level ) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	public MDAction getAction() {
		return action;
	}

	public MDUpdate getUpdate() {
		return update;
	}

	public PriceQuote getQuote() {
		return quote;
	}
	
	
}
