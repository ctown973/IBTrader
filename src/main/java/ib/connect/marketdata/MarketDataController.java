package ib.connect.marketdata;

import com.ib.client.Contract;
import com.ib.client.Types.WhatToShow;

import ib.connect.ApiController;

public class MarketDataController implements MarketDataInterface, SubscriptionInterface {

	private ApiController controller;
	
	public MarketDataController(ApiController controller) {
		this.controller = controller;
	}

	@Override
	public void requestMarketData(Contract contract, WhatToShow action, boolean rthOnly) {
		controller.reqRealTimeBars(contract, action, rthOnly);
	}
	
	
	
	
	
	
}
