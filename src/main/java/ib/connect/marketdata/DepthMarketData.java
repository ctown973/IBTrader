package ib.connect.marketdata;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ib.connect.securities.SecurityDefinition;

public interface DepthMarketData {


	
	public static PriorityQueue<String> queue;
	public  PriceQuote getBid(int level);
	
	public  PriceQuote getOffer(int level);
	
	public  int bidListSize();
	
	public  int offerListSize();
	
	public void notifyListeners();
}
