package ib.connect.messages;

import java.util.concurrent.atomic.AtomicInteger;

import ib.connect.messages.MarketDataRequest.MDType;
import ib.connect.securities.SecurityDefinition;

public class MessageFactory {
	
	private static AtomicInteger reqId = new AtomicInteger(1);
	
	public static MarketDataRequest createMDRequest(SecurityDefinition definition, int numOfLevels, boolean realTime ) {
		MarketDataRequest request = new MarketDataRequest(reqId.getAndIncrement(), MDType.REALTIME_TOP, definition.buildContract());
		
		
		return request;
	}
	
	
}
