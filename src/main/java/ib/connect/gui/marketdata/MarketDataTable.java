package ib.connect.gui.marketdata;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.ib.client.Contract;
import com.ib.client.Types.WhatToShow;

import ib.connect.ConnectionVenue;
import ib.connect.OrderAcceptor;
import ib.connect.gui.MySpinner;
import ib.connect.gui.marketdata.MarketDataTableModel.DepthDataHeader;
import ib.connect.marketdata.DepthMarketData;
import ib.connect.marketdata.MarketDataInterface;
import ib.connect.marketdata.PriceQuote;
import ib.connect.marketdata.RealTimeDepthMarketData;
import ib.connect.marketdata.SubscriptionInterface;
import ib.connect.messages.MarketDataRequest.MDType;
import ib.connect.order.OrderBook;
import ib.connect.order.OrderManager;
import ib.connect.securities.SecurityDefinition;
import ib.connect.securities.SecurityMaster;

public class MarketDataTable implements MarketDataInterface, MouseListener, MouseWheelListener/*, Subscriber<DepthMarketData>*/{

	
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private OrderBook book;
	private OrderAcceptor orderAcceptor; 
	private SubscriptionInterface subscriptionInterface;
	private MarketDataControlPanel mdPanel;
	private DepthMarketData depthMarketData;
	
	private static Map<String, MarketDataTable> marketDataTableMap = new HashMap<String, MarketDataTable>();
	
	public MarketDataTable( OrderAcceptor acceptListener, SubscriptionInterface subscriptionInterface) {
		this.orderAcceptor = acceptListener;
		this.subscriptionInterface = subscriptionInterface;
	}

	
	
//	@Override
//	public void onSubscribe(Subscription subscription) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void onNext(DepthMarketData item) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void onError(Throwable throwable) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void onComplete() {
//		// TODO Auto-generated method stub
//		
//	}



	public synchronized void initialize(SecurityDefinition definition) {
		if (panel == null ) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			
			mdPanel = new MarketDataControlPanel(definition);
			mdPanel.initialize();
			table = new JTable(new MarketDataTableModel(new RealTimeDepthMarketData(definition, MDType.REALTIME_TOP), OrderManager.getOrderBook(definition.getSecurityID())));
			table.getTableHeader().setDefaultRenderer(new MyCellRenderer());
		 	scrollPane = new JScrollPane(table);
			panel.add(scrollPane, BorderLayout.CENTER);
			
			panel.add(mdPanel.getPanel(), BorderLayout.SOUTH);
			panel.setVisible(true);
		}
		
		if (definition != null && !marketDataTableMap.containsKey( definition.getExchangeSecurityID())) {
			marketDataTableMap.put(definition.getExchangeSecurityID(), this);
		}
	}
	
//	public synchronized void setSecurityDefinition(SecurityDefinition definition) {
//		if (mdPanel == null) {
//		
//		}
//	}
//	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



//	@Override
	public void onMarketDataDepthUpdate(DepthMarketData data) {
		this.depthMarketData = data;
		((AbstractTableModel)table.getModel()).fireTableDataChanged();
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JPanel getPanel() {
		return panel;
	}

	class MyCellRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				MarketDataTableModel.DepthDataHeader data = MarketDataTableModel.DepthDataHeader.getMarketDataHeader(column);
				
				switch (data) {
				case BID_PRICE:
				case MARKET_BID:
					if (depthMarketData != null && depthMarketData.getBid(row) != null)
						cell.setBackground(Color.GREEN);
					break;
				case OFFER_PRICE:
				case MARKET_OFFER:
					if (depthMarketData != null && depthMarketData.getOffer(row) != null)
						cell.setBackground(Color.RED);
					break;
				default:
					cell.setBackground(Color.LIGHT_GRAY);
					break;
				}
				return cell;
		}
		
	}

	class MarketDataControlPanel {
		private JPanel panel;
		
		private SecurityDefinition definition;
		private JComboBox<ConnectionVenue> destinationBox = new JComboBox<ConnectionVenue>(ConnectionVenue.values());
		private JComboBox<String> securityBox = new JComboBox<String>();
		
	    private JSpinner maxSizeSpinner;
	    
	    private JLabel orderQtyLabel;
	    private JSpinner orderQtySpinner;
	    
	    private JLabel maxSizeLabel;
	    private JLabel lastPriceLabel;
	    private JLabel settlementLabel;
	    private JLabel totalVolumeTradedLabel;
	    
	    private JComboBox<String> accountMenu;
	    
		
		public MarketDataControlPanel(SecurityDefinition definition	 ) {
		
	
			this.securityBox.setPreferredSize(new Dimension(65, 25));
			this.securityBox.setEditable(true);
		}
		
		public JPanel getPanel() {
			return this.panel;
		}
		
		public void initialize() {
			panel = new JPanel();
			GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.BASELINE_LEADING, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 2, 2);
			panel.setLayout(new GridBagLayout());
			orderQtyLabel = new JLabel("Order Qty");
			maxSizeLabel = new JLabel("Max Size");
			settlementLabel = new JLabel("Settl" );
			totalVolumeTradedLabel = new JLabel("TtlVol");
			orderQtySpinner = new MySpinner(100);
			maxSizeSpinner = new MySpinner(100);
			panel.add(orderQtyLabel,constraints);
			constraints.gridx+=1;
			panel.add(maxSizeLabel, 	constraints);
			constraints.gridx+=1;
			panel.add(settlementLabel, constraints);
			constraints.gridx+=1;
			panel.add(totalVolumeTradedLabel, constraints);
			constraints.gridx = 0;
			constraints.gridy += 2;
			panel.add(orderQtySpinner, constraints);
			constraints.gridx += 1;
			panel.add(maxSizeSpinner, constraints);
			constraints.gridx +=1;
			panel.add(destinationBox, constraints);
			constraints.gridx += 1;
			panel.add(securityBox, constraints);
			securityBox.addActionListener((ActionEvent e)->{
					String security = securityBox.getSelectedItem().toString();
					MarketDataTable mdTable = marketDataTableMap.get(security);
					if (mdTable == null) {
						mdTable = new MarketDataTable(orderAcceptor, subscriptionInterface);
						SecurityDefinition definition = SecurityMaster.getSecurityMaster().getSecurityDefinition(security);
						mdTable.initialize(definition);
						MarketDataTable.this.subscriptionInterface.requestMarketData(definition, WhatToShow.TRADES, true, MarketDataTable.this);
						marketDataTableMap.put(security, mdTable);
						
					}
						MarketDataTable.this.scrollPane.setViewportView(mdTable.getTable());
						MarketDataTable.this.panel.remove(mdPanel.getPanel());
						MarketDataTable.this.panel.add(mdPanel.getPanel(), BorderLayout.SOUTH);
								
			//	subscriptionInterface.requestMarketData(SecurityMaster., action, rthOnly);
			});
		
		}
		
		public String getAccount() {
			return (String) accountMenu.getSelectedItem();
		}
		
		public int getOrderSize() {
			int size = (Integer)orderQtySpinner.getValue();
			int maxQty = (Integer)maxSizeSpinner.getValue();
			return (int)Math.min(size, maxQty);
		}
		
		public void setLast(PriceQuote last) {
			if (last != null) {
				lastPriceLabel.setText("Last: "+last.getPrice()+ " "+last.getSize());
			}
		}
		
		   
	    public void setSettlment(double settle) {
	    	settlementLabel.setText("Sttl "+settle);
	    }
	    
	    public void setTotalVolumeTraded(long totalVol) {
	    	totalVolumeTradedLabel.setText("TtlVol "+totalVol);
	    }
	    
	    public ConnectionVenue getConnectionVenue() {
	    	return (ConnectionVenue)destinationBox.getSelectedItem();
	    }

	    
	}
}
