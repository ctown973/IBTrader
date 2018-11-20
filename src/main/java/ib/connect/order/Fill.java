package ib.connect.order;

import ib.connect.securities.SecurityDefinition;

public class Fill {

	
	private double fillPrice;
	private SecurityDefinition definition;
	private int size;
	private String datetime;
	private String clOrderID, execId;
	private char side;
	
	public Fill() {
		
	}


	public double getFillPrice() {
		return fillPrice;
	}


	public void setFillPrice(double fillPrice) {
		this.fillPrice = fillPrice;
	}

	public String getSymbol() {
		return this.definition.getSymbol();
	}
	
	public String getSecurityID() {
		return this.definition.getSecurityID();
	}
	
	public String getDescription() {
		return definition.getDescription();
	}
	
	public void setDefinition(SecurityDefinition definition) {
		this.definition = definition;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getDatetime() {
		return datetime;
	}


	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	public String getClOrderID() {
		return clOrderID;
	}


	public void setClOrderID(String clOrderID) {
		this.clOrderID = clOrderID;
	}


	public String getExecId() {
		return execId;
	}


	public void setExecId(String execId) {
		this.execId = execId;
	}


	public char getSide() {
		return side;
	}


	public void setSide(char side) {
		this.side = side;
	}
	
	
}
