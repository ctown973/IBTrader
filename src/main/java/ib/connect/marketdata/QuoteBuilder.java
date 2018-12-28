package ib.connect.marketdata;

import ib.connect.messages.MarketDataRequest.MDType;

public interface QuoteBuilder {


	public PriceQuote buildQuote();
	
	
	public boolean isComplete();
	
	public int getRefID();
}
