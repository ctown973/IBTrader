package ib.connect.order;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {

	private static Map<String, OrderBook> orderMap;
	private static final OrderManager orderManager;
	
	
	static {
		orderManager = new OrderManager();
	}
	
	private OrderManager() {
		orderMap = new HashMap<String, OrderBook>();
	}
	
	public static OrderManager getOrderManager() {
		return orderManager;
	}
	
	public OrderBook get(String securityId) {
		return orderMap.get(securityId);
		
	}
	
	public static OrderBook getOrderBook(String securityId) {
		if (securityId == null) return null;
		return getOrderManager().get(securityId);
	}
}
