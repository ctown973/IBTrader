package ib.connect.messages;

import ib.connect.client.network.Contract;

public class MarketDataRequest {

	private int numOfLevels, reqId;
	private final Contract contract;
	private final MDType type;
	
	public MarketDataRequest(int reqId, MDType type, Contract contract) {
		this.reqId = reqId;
		this.type = type;
		this.contract = contract;
	
	}
	
	public int getReqId( ) {
		return reqId;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public MDType getMDType() {
		return type;
	}
	
	public String getSecurityID() {
		return contract.symbol();
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) 
			return true;
		
		if (obj != null && obj instanceof MarketDataRequest) {
			MarketDataRequest mr = (MarketDataRequest)obj;
			return (mr.getSecurityID().equals(this.getSecurityID()) && mr.getMDType() == this.getMDType());
				
		}
	  return false;
	}
	
	



	@Override
	public int hashCode() {
		
		return this.getSecurityID().hashCode() + this.getMDType().hashCode();
	}





	public enum MDType { BAR, REALTIME_TOP, REALTIME_LEVEL_II  }
}
