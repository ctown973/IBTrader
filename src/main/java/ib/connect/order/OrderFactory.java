package ib.connect.order;

import com.ib.client.Contract;

public class OrderFactory {

	public static NewOrder createNewOrder(Contract contract) {
		NewOrder newOrder = new NewOrder();
		
		return newOrder;
	}
}
