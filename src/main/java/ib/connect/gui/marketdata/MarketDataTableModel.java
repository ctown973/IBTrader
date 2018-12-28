package ib.connect.gui.marketdata;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;


import ib.connect.marketdata.DepthMarketData;
import ib.connect.marketdata.PriceQuote;
import ib.connect.order.Order;
import ib.connect.order.OrderBook;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.DefaultEventTableModel;

public class MarketDataTableModel extends AbstractTableModel {
	
	private static Map<Integer, DepthDataHeader> columnToHeader = new HashMap<Integer, DepthDataHeader>();
	private static DecimalFormat decimalFormat = new DecimalFormat("#.#######");
	
	static enum DepthDataHeader {
		BID_PRICE(0, "MyBids"),
		BID_SIZE(1, "MySize"),
		MARKET_BID_SIZE(2, "Size"),
		MARKET_BID(3, "Bids"),
		MARKET_OFFER(4, "Offers"),
		MARKET_OFFER_SIZE(5, "Size"),
		OFFER_SIZE(6, "MySize"),
		OFFER_PRICE(7, "MyOffers");
		private final int index;
		private String name;
		
	   DepthDataHeader(int index, String name) {
			this.index = index;
			this.name = name;
			columnToHeader.put(index, this);
		}
		
		public int getIndex()  {
			return index;
		}
		
		public String getName() {
			return name;
		}
		
		public static DepthDataHeader getMarketDataHeader(int column) {
			return columnToHeader.get(column);
		}
	}
	private static final int headerCount;
	public static final DepthDataHeader[] headers;
	
	static {
		headerCount = DepthDataHeader.values().length;
		headers = DepthDataHeader.values();
	}
	
	private DepthMarketData depthMarketData;
	private OrderBook orderBook;
	
	public MarketDataTableModel(DepthMarketData data, OrderBook book) {
		this.depthMarketData = data;
		this.orderBook = book;
	}
	

	@Override
	public int getRowCount() {
	
		int finalBidListSize =0;
		int finalOfferListSize = 0;
		int orderBookBidListSize = 0;
		int orderBookOfferListSize = 0;
		if (depthMarketData != null) {
			finalBidListSize = depthMarketData.bidListSize();
			finalOfferListSize = depthMarketData.offerListSize();

		}
		
		if (orderBook != null) {
			orderBookBidListSize = orderBook.getBidListSize();
			orderBookOfferListSize = orderBook.getOfferListSize();
		}
	
		return Math.max(Math.max(finalBidListSize, finalOfferListSize), Math.max(orderBookBidListSize, orderBookOfferListSize))  ;
	}

	@Override
	public int getColumnCount() {
	
		return headerCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DepthDataHeader header = headers[columnIndex];
		Order order;
		PriceQuote quote;
		switch (header)  {
			case BID_PRICE: 
				   order = orderBook.getBidOrder(rowIndex);
				   if (order != null)
					   return decimalFormat.format(order.getLimit());
			
				break;
			case BID_SIZE:
				 order = orderBook.getBidOrder(rowIndex);
				 if (order != null)
					return String.valueOf(order.getSize());
				
				break;
			case MARKET_BID_SIZE:
				if (depthMarketData != null) {
				    quote = depthMarketData.getBid(rowIndex);
					if (quote != null) {
						return String.valueOf(quote.getSize());
					}
				}
				break;
			case MARKET_BID:
				if (depthMarketData != null) {
					quote = depthMarketData.getBid(rowIndex);
					if (quote != null) {
						return decimalFormat.format(quote.getPrice());
					}
				}
				break;
			case MARKET_OFFER:
				if (depthMarketData != null) {
					quote = depthMarketData.getOffer(rowIndex);
					if (quote != null) {
						return decimalFormat.format(quote.getPrice());
					}		
				}
				break;
			case MARKET_OFFER_SIZE:
				if (depthMarketData != null) {
					quote = depthMarketData.getOffer(rowIndex);
					if (quote != null) {
						return String.valueOf(quote.getSize());
					}
				}
				break;
			case OFFER_SIZE:
				 order = orderBook.getOffOrder(rowIndex);
				if (order != null) {
					return String.valueOf(order.getSize());
				}
				break;
			case OFFER_PRICE:
				order = orderBook.getOffOrder(rowIndex);
				if (order != null) {
					return decimalFormat.format(order.getLimit());
				}
				break;
			
		}
		
		return "";
	}
	
	@Override
	public String getColumnName(int column) {
		DepthDataHeader header = headers[column];
		switch (header) {
		case BID_PRICE:
			return "MyBids";
		case BID_SIZE:
			return "MySize";
		case MARKET_BID_SIZE:
			return "Size";
		case MARKET_BID:
			return "Bids";
		case MARKET_OFFER:
			return "Offers";
		case MARKET_OFFER_SIZE:
			return "Size";
		case OFFER_SIZE:
			return "MySize";
		case OFFER_PRICE:
			return "MyOffers";
		default:
			return null;
		}
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}
}
