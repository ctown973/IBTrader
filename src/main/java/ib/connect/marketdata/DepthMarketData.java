package ib.connect.marketdata;



public interface DepthMarketData {


	public  PriceQuote getBid(int level);
	
	public  PriceQuote getOffer(int level);
	
	public  int bidListSize();
	
	public  int offerListSize();
	
	public void notifyListeners();
}
