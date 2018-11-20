package ib.connect.marketdata;

import com.ib.client.Contract;
import com.ib.client.Types.WhatToShow;

public interface SubscriptionInterface {

	
	public void requestMarketData(Contract contract, WhatToShow action, boolean rthOnly);
}
