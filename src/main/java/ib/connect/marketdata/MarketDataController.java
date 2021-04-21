package ib.connect.marketdata;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;


import ib.connect.ApiController;
import ib.connect.client.network.Types.WhatToShow;
import ib.connect.marketdata.RealTimeDepthMarketData.Depth;
import ib.connect.messages.MarketDataRequest;
import ib.connect.messages.MarketDataRequest.MDType;
import ib.connect.securities.SecurityDefinition;

public class MarketDataController implements SubscriptionInterface {

	private ApiController controller;
	
	private Map<MarketDataRequest, LinkedList<MarketDataInterface>> subscriberMap = new HashMap<MarketDataRequest, LinkedList<MarketDataInterface>>();
	private Map<Integer, MarketDataRequest> marketDataRequestMap = new HashMap<Integer, MarketDataRequest>();
	
	
	private LinkedBlockingQueue<PriceQuote> queue = new LinkedBlockingQueue<PriceQuote>();
	
	
	
	public MarketDataController(ApiController controller) {
		this.controller = controller;
		RealTimeDepthMarketData data = new RealTimeDepthMarketData(null, MDType.BAR);
		
	}

	@Override
	public void requestMarketData(SecurityDefinition contract, WhatToShow action, boolean rthOnly, MarketDataInterface listener) {
		controller.reqRealTimeBars(contract, action, rthOnly);
	}
	
	public void start() {
//		Thread t = new Thread( ()->{
//			while (true) {
//				while(queue.poll(timeout, unit));
//			}
//		}
//		);
	}

//	@Override
//	public void onMarketDataDepthUpdate(DepthMarketData data) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public void onMarketDataDepthUpdate(int tickerId, int position, int operation, int side, double price, int size) {
		
	}
	
	public void onTopLevelSizeUpdate(int ticerId, int operation, int size) {
		
	}
	
	public void onTopLevelPriceUpdate(int tickerId, int operation, double price) {
		
	}
	
	
	
	
	
	
}
