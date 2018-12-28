package ib.connect.order;

import com.ib.client.Contract;

public class OrderFactory {

	public static NewOrder createNewOrder(Contract contract, OrderStatusListener listener) {
		NewOrder newOrder = new NewOrder(listener);
		
		return newOrder;
	}
}
