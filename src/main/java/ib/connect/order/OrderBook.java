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
	
	private EventList<Order> bidMap = new BasicEventList<Order>();
	private List<Order> offerMap = new LinkedList<Order>();

	private SecurityDefinition definition;
	
	public OrderBook(SecurityDefinition definition) {
			this.definition = definition;
	}	
}
