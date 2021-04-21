package ib.connect.marketdata;

import ib.connect.client.network.Types.WhatToShow;
import ib.connect.securities.SecurityDefinition;

public interface SubscriptionInterface {

	
	public void requestMarketData(SecurityDefinition contract, WhatToShow action, boolean rthOnly, MarketDataInterface listener);
}
