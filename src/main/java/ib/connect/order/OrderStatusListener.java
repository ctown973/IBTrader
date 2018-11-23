package ib.connect.order;

public interface OrderStatusListener {

	public void orderStatusChanged(String newID, String originalID) ;
}
