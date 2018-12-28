package ib.connect.marketdata;

import com.ib.client.Contract;
import com.ib.client.Types.WhatToShow;

import ib.connect.securities.SecurityDefinition;

public interface SubscriptionInterface {

	
	public void requestMarketData(SecurityDefinition contract, WhatToShow action, boolean rthOnly, MarketDataInterface listener);
}
