package ib.connect.order;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {

	private static Map<String, OrderBook> orderMap;
	private static OrderManager orderManager;
	
	private OrderManager() {
		orderMap = new HashMap<String, OrderBook>();
	}
	
	public static OrderManager initializeOrderManager() {
		if (orderManager == null) {
			synchronized(OrderManager.class) {
				if (orderManager == null)
					orderManager = new OrderManager();
		
			}
		}
		return orderManager;
	}
	
	public OrderBook get(String securityId) {
		return orderMap.get(securityId);
		
	}
	
	public static OrderBook getOrderBook(String securityId) {
		if (securityId == null) return null;
		return initializeOrderManager().get(securityId);
	}
}
