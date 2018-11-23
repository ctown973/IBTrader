package ib.connect.order;

public abstract class Order {

	
	protected OrderStatusListener statusListener;
	
	public Order(OrderStatusListener statusListener) {
		this.statusListener = statusListener;
	}
}
