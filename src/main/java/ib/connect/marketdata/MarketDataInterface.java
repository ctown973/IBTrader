package ib.connect.marketdata;

import com.ib.client.Contract;
import com.ib.client.Types.WhatToShow;

public interface MarketDataInterface {

	
	public void onMarketDataDepthUpdate(DepthMarketData data);
}
