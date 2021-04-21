package ib.connect.order;

import ib.connect.client.network.Contract;

public class OrderFactory {

	public static NewOrder createNewOrder(Contract contract, OrderStatusListener listener) {
		NewOrder newOrder = new NewOrder(listener);
		
		return newOrder;
	}
}
