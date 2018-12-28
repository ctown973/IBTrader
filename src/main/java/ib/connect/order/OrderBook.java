package ib.connect.order;

import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ib.connect.securities.SecurityDefinition;

public class OrderBook implements OrderStatusListener {
	
	private EventList<Order> bidMap = new BasicEventList<Order>(20);
	private EventList<Order> offerMap = new BasicEventList<Order>(20);

	private SecurityDefinition definition;
	
	public OrderBook(SecurityDefinition definition) {
			this.definition = definition;
	}

	@Override
	public void orderStatusChanged(String newID, String originalID) {
		
	}	
	
	public Order getBidOrder(int index) {
		return (bidMap.size() > index) ? bidMap.get(index) : null ;
	}
	
	public Order getOffOrder(int index) {
		return (offerMap.size() > index) ? offerMap.get(index) : null;
	}
	
	public int getBidListSize() {
		return bidMap.size();
	}
	
	public int getOfferListSize() {
		return offerMap.size();
	}
	
}
