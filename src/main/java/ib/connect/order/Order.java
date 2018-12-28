package ib.connect.order;

import ib.connect.ConnectionVenue;
import ib.connect.securities.SecurityDefinition;

public abstract class Order {

	
	protected OrderStatusListener statusListener;
	
	
	public static final char SIDE_BID = 'B';
	public static final char SIDE_OFFER= 'S';
	public static final char SIDE_NA = 'N';
	
	public static final char TYPE_MARKET = 'M';
	public static final char TYPE_LIMIT = 'L';
	public static final char TYPE_STOP = 'S';
	public static final char TYPE_STOPLIMIT = 'C';
	
	public static final char TIF_DAY = 'D';
	public static final char TIF_GTC = 'X';
	
	public static final char STATUS_NEW = 'N';
	public static final char STATUS_FILL = 'F';
	public static final char STATUS_PART_FILL = 'P';
	public static final char STATUS_REPLACED = 'R';
	public static final char STATUS_CANCELLED = 'O';
	public static final char STATUS_REJECTED = 'Z';
	public static final char STATUS_BUSTED = 'B';
	public static final char STATUS_UNDER_INVESTIGATION = 'U';
	public static final char STATUS_TRADE_ADJUSTED= 'A';
	
	private double limit, fillPrice;
	
	private int size, fillSize;
	
	private char side, orderType, timeInForce, orderStatus;
	
	protected SecurityDefinition securityDefinition;
	
	protected String clordID, origClordID, sendTime, correlatedId;
	
	private String orderID;
	
	private int leavesQty, cumQty;
	
	private ConnectionVenue connectionVenue;
	private String execId;
	private boolean isLeg = false;
	private String text, strategyName, transactTime, account, traderId, clientId, strategyState;
	;
	
	public Order(OrderStatusListener statusListener) {
		this.statusListener = statusListener;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(strategyName);
		buffer.append(",");
		if (securityDefinition != null) {
			buffer.append(securityDefinition.getSecurityID());
			buffer.append(",");
		}
		buffer.append(account);
		buffer.append(",");
		buffer.append(execId);
		buffer.append(",");
		buffer.append(clordID);
		buffer.append(",");
		buffer.append(origClordID);
		buffer.append(",");
		buffer.append(size);
		buffer.append(",");
		buffer.append(limit);
		buffer.append(",");
		buffer.append(fillSize);
		buffer.append(",");
		buffer.append(fillPrice);
		buffer.append(",");
		buffer.append(orderID);
		buffer.append(",");
		buffer.append(text);
		buffer.append(",");
		buffer.append(leavesQty);
		buffer.append(",");
		return buffer.toString();
	}
	

	public String getSecurityID() {
		return this.securityDefinition.getSecurityID();
	}
	
	public String getSymbol() {
		return this.securityDefinition.getSymbol();
	}
	
	public boolean isDecimalOrderNotation() {
		return this.securityDefinition.isDecimalOrderNotation();
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public double getFillPrice() {
		return fillPrice;
	}

	public void setFillPrice(double fillPrice) {
		this.fillPrice = fillPrice;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFillSize() {
		return fillSize;
	}

	public void setFillSize(int fillSize) {
		this.fillSize = fillSize;
	}

	public char getSide() {
		return side;
	}

	public void setSide(char side) {
		this.side = side;
	}

	public char getOrderType() {
		return orderType;
	}

	public void setOrderType(char orderType) {
		this.orderType = orderType;
	}

	public char getTimeInForce() {
		return timeInForce;
	}

	public void setTimeInForce(char timeInForce) {
		this.timeInForce = timeInForce;
	}

	public SecurityDefinition getSecurityDefinition() {
		return securityDefinition;
	}

	public void setSecurityDefinition(SecurityDefinition securityDefinition) {
		this.securityDefinition = securityDefinition;
	}
	
	public void setStrategyState(String strategyState) {
		this.strategyState = strategyState;
	}
	
	public String getStrategyState() {
		return this.strategyState;
	}

}
