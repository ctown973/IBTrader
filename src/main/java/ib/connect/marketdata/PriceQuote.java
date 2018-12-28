package ib.connect.marketdata;

public class PriceQuote {

	private int refId, size;
	private double price;
	
	public PriceQuote(int refId, int size, double price ) {
		this.refId = refId;
		this.size = size;
		this.price = price;
	}

	public int getRefId() {
		return refId;
	}

	public int getSize() {
		return size;
	}

	public double getPrice() {
		return price;
	}
	
	
}
