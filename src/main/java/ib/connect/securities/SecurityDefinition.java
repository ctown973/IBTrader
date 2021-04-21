package ib.connect.securities;

import ib.connect.client.network.Contract;
import ib.connect.securities.SecurityMaster.SecurityType;

public class SecurityDefinition {
	
	
    private String securityID;
	private String symbol;
	private String exchangeSecurityID;
	private int tickValue;
	private String description;
	private int minSize;
	
	private boolean isDecimalOrderNotation;
	
	private SecurityType type;
	
	private Contract contract;
	
	public SecurityDefinition() {
		securityID = "";
		symbol = "";
		exchangeSecurityID = "";
		description = "";
	}
	public String getSecurityID() {
		return securityID;
	}
	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}
	public String getDescription() {
		return this.description;
	}
	
//	contract.symbol("IBKR");
//	contract.secType("STK");
//	contract.currency("USD");
//	//In the API side, NASDAQ is always defined as ISLAND
//	contract.exchange("ISLAND");
	public Contract buildContract() {
		contract = new Contract();
	//	contract.secId(securityID);
		contract.symbol(securityID);
		contract.localSymbol(securityID);
		switch (type) {
		case STOCK:
			contract.secType("STK");
			break;
		case OPTION:
			contract.secType("OPT");
		}
		
		contract.currency("USD");
		contract.exchange("SMART");
//		contract.primaryExch("ISLAND");
		return contract;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public Contract getContract() {
		return this.contract;
	}
	
	public SecurityType getType() {
		return type;
	}
	public void setType(SecurityType type) {
		this.type = type;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExchangeSecurityID() {
		return exchangeSecurityID;
	}
	public void setExchangeSecurityID(String exchangeSecurityID) {
		this.exchangeSecurityID = exchangeSecurityID;
	}
	public int getTickValue() {
		return tickValue;
	}
	public void setTickValue(int tickValue) {
		this.tickValue = tickValue;
	}
	
	public int getMinSize() {
		return minSize;
	}
	
	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}
	
	public boolean isDecimalOrderNotation() {
		return isDecimalOrderNotation;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecurityID: ");
		builder.append(securityID);
		builder.append(" Symbol: ");
		builder.append(symbol);
		builder.append(" Exchange SecurityID: ");
		builder.append(exchangeSecurityID);
		builder.append(" description: ");
		builder.append(description);
		return builder.toString();
	}
}
