package ib.connect.marketdata;

import ib.connect.messages.MarketDataRequest.MDType;

public class PriceQuoteBuilder implements QuoteBuilder {

	private int refId;
	private final MDType type;
	private int size = 0;
	private double price = Double.NaN;
	
	public PriceQuoteBuilder(int refId, String data) {
		this.refId = refId;
		this.type = MDType.REALTIME_TOP;
		
	}
	
	@Override
	public PriceQuote buildQuote() {
		
		return null;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRefID() {
		// TODO Auto-generated method stub
		return refId;
	}

}
