package ib.connect.marketdata;

import ib.connect.client.network.Contract;
import ib.connect.client.network.Types.WhatToShow;

public interface MarketDataInterface {

	
	public void onMarketDataDepthUpdate(DepthMarketData data);
}
