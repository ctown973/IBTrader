package ib.connect;

import com.ib.client.Order;

public interface OrderListener {

	public void onCancelReplaceReject(Order order);
	
	public void onFill(Order order);
	public void onPartialFill(Order order);
	public void onNewOrder(Order order);
	public void onCancelReplace(Order order);
	public void onCancel(Order order);
	public void onNewOrderReject(Order order);
}
