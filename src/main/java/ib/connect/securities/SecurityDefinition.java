package ib.connect.securities;

public class SecurityDefinition {
	
	
    private String securityID;
	private String symbol;
	private String exchangeSecurityID;
	private int tickValue;
	private String description;
	
	public SecurityDefinition() {
		
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
	
	public String getSymbol() {
		return symbol;
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

	
}
