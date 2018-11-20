package ib.connect;

import com.ib.client.Order;

public interface OrderAcceptor {
	
	public void sendOrder(Order order);

}
