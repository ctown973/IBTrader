package ib.connect.marketdata;

public class PriceQuote {

	private int tickerId, size;
	private double price;
	
	public PriceQuote(int tickerId, int size, double price ) {
		this.tickerId = tickerId;
		this.size = size;
		this.price = price;
	}

	public int getTickerId() {
		return tickerId;
	}

	public int getSize() {
		return size;
	}

	public double getPrice() {
		return price;
	}
	
	
}
