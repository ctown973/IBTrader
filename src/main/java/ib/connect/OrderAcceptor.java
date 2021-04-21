package ib.connect;

import ib.connect.client.network.Order;

public interface OrderAcceptor {
	
	public void sendOrder(Order order);

}
