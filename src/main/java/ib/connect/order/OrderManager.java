package ib.connect.order;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {

	private Map<String, OrderBook> orderMap;
	
	public OrderManager() {
		orderMap = new HashMap<String, OrderBook>();
	}
}
