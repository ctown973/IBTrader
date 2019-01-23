package ib.connect.marketdata;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ib.connect.messages.MarketDataRequest.MDType;
import ib.connect.securities.SecurityDefinition;

public class RealTimeDepthMarketData implements DepthMarketData {

	
	private MDType type;
	
	private PriceQuote topBid, topOffer;
	
	private Depth bidDepth, offerDepth;
	
	private SecurityDefinition definition;
	
	private List<MarketDataInterface> list = new LinkedList<>();
	
	public RealTimeDepthMarketData(SecurityDefinition definition, MDType type) {
		this.definition = definition;
		this.type = type;
		if (this.type == MDType.REALTIME_LEVEL_II) {
			this.bidDepth = new Depth();
			this.offerDepth  = new Depth();
		}
	}
	
	public PriceQuote getBid(int level) {
		switch (level) {
		case 0:
			return topBid;
		default:
			if (bidDepth != null ) {
				return bidDepth.getQuote(level);
			}
		}
		return null;
		
	}
	
	@Override
	public void notifyListeners() {
		// TODO Auto-generated method stub
		
	}

	public PriceQuote getOffer(int level) {
		switch (level) {
		case 0:
			return topOffer;
		default:
			if (offerDepth != null ) {
				return offerDepth.getQuote(level);
			}
		}
		return null;
		
	}
	
	public int bidListSize() {
		return getSize(topBid, bidDepth);
	}
	
	public int offerListSize() {
		return getSize(topOffer, offerDepth);
	}
	
	private int getSize(PriceQuote topQuote, Depth depth) {
		int size = 0;
		if (topQuote != null)
			size++;
		if (depth != null) {
			size += depth.getQuoteListSize();
		}
		
		return size;
	}
	
	
	class RealTimeQuoteBuilder implements QuoteBuilder {
		
		private int refId;
		private int size = 0;
		private double price = Double.NaN;
		@Override
		public PriceQuote buildQuote() {
			if (size != 0 && !Double.isNaN(price)) {
				return new PriceQuote(refId, size, price);
			}
			return null;
		}

		@Override
		public boolean isComplete() {
			return size != 0 && !Double.isNaN(price);
		}

		@Override
		public int getRefID() {
			return refId;
		}
		
	}
	
   class Depth  {
		private List<PriceQuote> quoteList = new ArrayList<PriceQuote>();

		
		public Depth() {
			
		}

		public PriceQuote getQuote(int level) {
			return (level) >= quoteList.size() ? quoteList.get(level-1) : null;
			
		}


		public int getQuoteListSize() {
			return quoteList.size();
		}


		
		
	}
}
